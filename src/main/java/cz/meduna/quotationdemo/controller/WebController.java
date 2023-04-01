package cz.meduna.quotationdemo.controller;

import cz.meduna.quotationdemo.repository.CustomerRepository;
import cz.meduna.quotationdemo.repository.QuotationRepository;
import cz.meduna.quotationdemo.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final QuotationRepository quotationRepository;
    private final CustomerRepository customerRepository;
    private final SubscriptionRepository subscriptionRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("quotations", quotationRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("subscriptions", subscriptionRepository.findAll());
        return "index";
    }

}
