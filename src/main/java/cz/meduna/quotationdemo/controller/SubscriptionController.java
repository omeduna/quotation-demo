package cz.meduna.quotationdemo.controller;

import cz.meduna.quotationdemo.controller.form.SubscriptionForm;
import cz.meduna.quotationdemo.model.Quotation;
import cz.meduna.quotationdemo.model.Subscription;
import cz.meduna.quotationdemo.repository.QuotationRepository;
import cz.meduna.quotationdemo.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/subscription")
public class SubscriptionController {

    private final QuotationRepository quotationRepository;
    private final SubscriptionRepository subscriptionRepository;

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("quotations", quotationRepository.findAll());
        model.addAttribute("subscriptionForm", new SubscriptionForm());
        return "subscription/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createSubscription(@ModelAttribute SubscriptionForm form) {
        Optional<Quotation> quotationOpt = quotationRepository.findById(form.getQuotationId());
        if (quotationOpt.isPresent()) {
            subscriptionRepository.save(Subscription.builder()
                    .startDate(form.getStartDate())
                    .validUntil(form.getValidUntil())
                    .quotation(quotationOpt.get())
                    .build());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quotation not found");
        }
        return "redirect:/";
    }

}
