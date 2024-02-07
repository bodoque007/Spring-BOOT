package com.bodoque007.springboot.mvcDemo.controller;


import com.bodoque007.springboot.mvcDemo.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "Lmao!");
        return "helloworld";
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }
    @PostMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customer-form";
        } else {
            return "customer-confirmation";
        }
    }
    // Makes sure to properly trim strings received in processForm post requests.
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, trimmer);
    }
}
