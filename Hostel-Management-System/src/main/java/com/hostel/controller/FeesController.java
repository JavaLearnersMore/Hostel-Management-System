package com.hostel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hostel.entity.Fees;
import com.hostel.service.FeesService;

@Controller
@RequestMapping("/admin")
public class FeesController {

    private final FeesService service;

    @Autowired
    private FeesService feesService;

    public FeesController(FeesService service) {
        this.service = service;
    }

    // Fetch all fee records
    @GetMapping("/fees")
    public String getAllFees(Model model) {
    	List<Fees> list = feesService.getAllFees();  // ✅ CORRECT
        System.out.println(list);
        model.addAttribute("fees", list);
        //model.addAttribute("page", "fees");

        return "admin/fees"; // your index page (fees.html)
    }

	public FeesService getService() {
		return service;
	}
	
	@GetMapping("/fees/delete/{id}")
	public String deleteFee(@PathVariable Long id) {

	    feesService.deleteById(id);

	    return "redirect:/admin/fees?deleted=true";
	}
	
	@GetMapping("/fees/edit/{id}")
	public String editFee(@PathVariable Long id, Model model) {

	    Fees fee = feesService.getById(id);

	    model.addAttribute("fee", fee);

	    return "admin/edit-fee";
	}
	
	@PostMapping("/fees/update")
	public String updateFee(@ModelAttribute Fees fee) {

	    feesService.save(fee);

	    return "redirect:/admin/fees?updated=true";
	}
}