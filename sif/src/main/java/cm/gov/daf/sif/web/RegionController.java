package cm.gov.daf.sif.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import cm.gov.daf.sif.titrefoncier.model.Region;
import cm.gov.daf.sif.titrefoncier.model.Regions;
import cm.gov.daf.sif.titrefoncier.service.RegionService;
import cm.gov.daf.sif.web.utils.WebConstants;

/**
 * @author Albert
 */
@Controller
@SessionAttributes(types = Region.class)
public class RegionController {

	private final RegionService regionService;

	@Autowired
	public RegionController(RegionService regionService) {
		this.regionService = regionService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(value = "/regions", method = RequestMethod.GET)
	public String search() {
		return "regions/list";
	}

	@RequestMapping(value = "/regions/data", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> searchJson(@RequestParam(value = "start", defaultValue = "0") Integer page,
			@RequestParam(value = "draw", defaultValue = "0") Integer draw,
			@RequestParam(value = "search[value]", defaultValue = "") String search) {

		Map<String, Object> data = new HashMap<String, Object>();

		int pageNumber = (page + 1) / WebConstants.PAGE_SIZE;

		Pageable pageable = new PageRequest(pageNumber, WebConstants.PAGE_SIZE);

		Page<Region> results = this.regionService.find(search, pageable);

		data.put("data", results.getContent());
		data.put("draw", draw);
		data.put("recordsTotal", results.getTotalElements());
		data.put("recordsFiltered", results.getTotalElements());

		return data;
	}

	@RequestMapping(value = "/regions/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		Region region = new Region();
		model.addAttribute(region);
		return "regions/createOrUpdate";
	}

	@RequestMapping(value = "/regions/new", method = RequestMethod.POST)
	public String processCreationForm(Model model, @Valid Region region, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "professions/createOrUpdate";
		} else {
			this.regionService.save(region);
			status.setComplete();
			return "redirect:/regions/" + region.getId();
		}
	}

	@RequestMapping(value = "/regions/{regionId}/edit", method = RequestMethod.GET)
	public String initUpdateOwnerForm(@PathVariable("regionId") int regionId, Model model) {
		Region region = this.regionService.findById(regionId);
		model.addAttribute(region);
		return "regions/createOrUpdate";
	}

	@RequestMapping(value = "/regions/{regionId}/edit", method = RequestMethod.PUT)
	public String processUpdateRegionForm(@Valid Region region, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "regions/createOrUpdate";
		} else {
			this.regionService.save(region);
			status.setComplete();
			return "redirect:/regions/{regionId}";
		}
	}

	@RequestMapping(value = "/regions/{regionId}", method = RequestMethod.DELETE)
	public String processDeleteRegion(@PathVariable("regionId") int regionId) {
		Region region = regionService.findById(regionId);
		if (region != null) {
			regionService.delete(region);
		}
		return "redirect:/regions";
	}

	@RequestMapping("/regions/{regionId}")
	public ModelAndView showRegion(@PathVariable("regionId") int regioinId) {
		ModelAndView mav = new ModelAndView("regions/view");
		mav.addObject(this.regionService.findById(regioinId));
		return mav;
	}

	@RequestMapping(value = { "/regions.xml", "/regions.html" } , method = RequestMethod.GET)
    public @ResponseBody Regions showResourcesRegionsList() {
		Regions regions = new Regions();
		regions.getRegionList().addAll(this.regionService.findAll());
        return regions;
    }

}
