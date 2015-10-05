/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cm.gov.daf.sif.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import cm.gov.daf.sif.model.Profession;
import cm.gov.daf.sif.service.ClinicService;
import cm.gov.daf.sif.service.ProfessionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Albert
 */
@Controller
@SessionAttributes(types = Profession.class)
public class ProfessionController {

    private final ProfessionService professionService;


    @Autowired
    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/professions/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Profession profession = new Profession();
        model.put("profession", profession);
        return "professions/createOrUpdateProfessionForm";
    }

    @RequestMapping(value = "/professions/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Profession profession, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "professions/createOrUpdateProfessionForm";
        } else {
            this.professionService.saveProfession(profession);
            status.setComplete();
            return "redirect:/professions/" + profession.getId();
        }
    }

    @RequestMapping(value = "/professions/find", method = RequestMethod.GET)
    public String initFindForm(Map<String, Object> model) {
        model.put("profession", new Profession());
        return "professions/findProfessions";
    }

    @RequestMapping(value = "/professions", method = RequestMethod.GET)
    public String processFindForm(Profession profession, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /owners to return all records
        if (profession.getLibelle() == null) {
        	profession.setLibelle(""); // empty string signifies broadest possible search
        }

        // find professions by libelle
        Collection<Profession> results = this.professionService.findProfessionByLibelle(profession.getLibelle());
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("libelle", "notFound", "not found");
            return "professions/findProfessions";
        }
        else if (results.size() == 1) {
    	// 1 owner found
        profession = results.iterator().next();
    	return "redirect:/professions/" + profession.getId();
        }
        else {
            // multiple owners found
            model.put("selections", results);
            return "professions/professionsList";
        }
    }
        
    @RequestMapping(value = "/professions/{professionId}/edit", method = RequestMethod.GET)
    public String initUpdateOwnerForm(@PathVariable("professionId") int professionId, Model model) {
    	Profession profession = this.professionService.findProfessionById(professionId);
        model.addAttribute(profession);
        return "professions/createOrUpdateProfessionForm";
    }

    @RequestMapping(value = "/professions/{professionId}/edit", method = RequestMethod.PUT)
    public String processUpdateProfessionForm(@Valid Profession profession, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "professions/createOrUpdateProfessionForm";
        } else {
            this.professionService.saveProfession(profession);
            status.setComplete();
            return "redirect:/professions/{professionId}";
        }
    }

    /**
     * Custom handler for displaying an owner.
     *
     * @param ownerId the ID of the owner to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/professions/{professionId}")
    public ModelAndView showProfession(@PathVariable("professionId") int professionId) {
        ModelAndView mav = new ModelAndView("professions/professionDetails");
        mav.addObject(this.professionService.findProfessionById(professionId));
        return mav;
    }

}
