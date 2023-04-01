package cz.meduna.quotationdemo.controller;

import cz.meduna.quotationdemo.controller.form.QuotationForm;
import cz.meduna.quotationdemo.model.Customer;
import cz.meduna.quotationdemo.model.Quotation;
import cz.meduna.quotationdemo.repository.CustomerRepository;
import cz.meduna.quotationdemo.repository.QuotationRepository;
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
@RequestMapping("/quotation")
public class QuotationController {

    private final QuotationRepository quotationRepository;
    private final CustomerRepository customerRepository;

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("quotationForm", new QuotationForm());
        return "quotation/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createQuotation(@ModelAttribute QuotationForm form) {
        Optional<Customer> customer = customerRepository.findById(form.getCustomerId());
        if (customer.isPresent()) {
            quotationRepository.save(Quotation.builder()
                    .beginningOfInsurance(form.getStart())
                    .insuredAmount(form.getAmount())
                    .dateOfSigningMortgage(form.getSignature())
                    .customer(customer.get())
                    .build());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        return "redirect:/";
    }

}
