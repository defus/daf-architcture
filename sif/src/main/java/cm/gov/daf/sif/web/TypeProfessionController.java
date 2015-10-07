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
import cm.gov.daf.sif.model.TypeProfession;
import cm.gov.daf.sif.model.TypeProfessions;
import cm.gov.daf.sif.service.TypeProfessionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Albert
 */
@Controller
@SessionAttributes(types = TypeProfession.class)
public class TypeProfessionController {

    private final TypeProfessionService typeProfessionService;


    @Autowired
    public TypeProfessionController(TypeProfessionService typeProfessionService) {
        this.typeProfessionService = typeProfessionService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/typeProfessions/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        TypeProfession typeProfession = new TypeProfession();
        model.put("typeProfession", typeProfession);
        return "type_professions/createOrUpdateTypeProfessionForm";
    }

    @RequestMapping(value = "/typeProfessions/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid TypeProfession typeProfession, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "type_professions/createOrUpdateTypeProfessionForm";
        } else {
            this.typeProfessionService.saveTypeProfession(typeProfession);
            status.setComplete();
            return "redirect:/typeProfessions/" + typeProfession.getId();
        }
    }

    @RequestMapping(value = "/typeProfessions/find", method = RequestMethod.GET)
    public String initFindForm(Map<String, Object> model) {
        model.put("typeProfession", new TypeProfession());
        return "type_professions/findTypeProfessions";
    }

    @RequestMapping(value = "/typeProfessions", method = RequestMethod.GET)
    public String processFindForm(TypeProfession typeProfession, BindingResult result, Map<String, Object> model) {
        // allow parameterless GET request for /owners to return all records
        if (typeProfession.getLibelle() == null) {
        	typeProfession.setLibelle(""); // empty string signifies broadest possible search
        }

        // find professions by libelle
        Collection<TypeProfession> results = this.typeProfessionService.findTypeProfessionByLibelle(typeProfession.getLibelle());
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("libelle", "notFound", "not found");
            return "type_professions/findTypeProfessions";
        }
        else if (results.size() == 1) {
    	// 1 owner found
        	typeProfession = results.iterator().next();
    	return "redirect:/typeProfessions/" + typeProfession.getId();
        }
        else {
            // multiple owners found
            model.put("selections", results);
            return "type_professions/typeProfessionsList";
        }
    }
        
    @RequestMapping(value = "/typeProfessions/{typeProfessionId}/edit", method = RequestMethod.GET)
    public String initUpdateOwnerForm(@PathVariable("typeProfessionId") int typeProfessionId, Model model) {
    	TypeProfession typeProfession = this.typeProfessionService.findTypeProfessionById(typeProfessionId);
        model.addAttribute(typeProfession);
        return "type_professions/createOrUpdateTypeProfessionForm";
    }

    @RequestMapping(value = "/typeProfessions/{typeProfessionId}/edit", method = RequestMethod.PUT)
    public String processUpdateProfessionForm(@Valid TypeProfession typeProfession, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "type_professions/createOrUpdateTypeProfessionForm";
        } else {
            this.typeProfessionService.saveTypeProfession(typeProfession);
            status.setComplete();
            return "redirect:/typeProfessions/{typeProfessionId}";
        }
    }

    /**
     * Custom handler for displaying an owner.
     *
     * @param professionId the ID of the profession to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/typeProfessions/{typeProfessionId}")
    public ModelAndView showProfession(@PathVariable("typeProfessionId") int typeProfessionId) {
        ModelAndView mav = new ModelAndView("type_professions/typeProfessionDetails");
        mav.addObject(this.typeProfessionService.findTypeProfessionById(typeProfessionId));
        return mav;
    }
    
    @RequestMapping(value={"/typeProfessions.xml","/typeProfessions.html"})
    public String showTypeProfessionList(Map<String, Object> model) {
        TypeProfessions typeProfessions = new TypeProfessions();
        typeProfessions.getTypeProfessionList().addAll(this.typeProfessionService.findAll());
        model.put("typeProfessions", typeProfessions);
        return "type_professions/typeProfessionsList";
    }
    
    @RequestMapping("/typeProfessions.json")
    public @ResponseBody TypeProfessions showResourcesProfessionList() {
    	TypeProfessions typeProfessions = new TypeProfessions();
    	typeProfessions.getTypeProfessionList().addAll(this.typeProfessionService.findAll());
        return typeProfessions;
    }

}
