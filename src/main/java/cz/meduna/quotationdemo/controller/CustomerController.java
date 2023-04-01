package cz.meduna.quotationdemo.controller;

import cz.meduna.quotationdemo.controller.form.CustomerForm;
import cz.meduna.quotationdemo.model.Customer;
import cz.meduna.quotationdemo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("customerForm", new CustomerForm());
        return "customer/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createCustomer(@ModelAttribute CustomerForm form) {
            customerRepository.save(Customer.builder()
                    .firstName(form.getFirstName())
                    .lastName(form.getLastName())
                    .middleName(form.getMiddleName())
                    .email(form.getMiddleName())
                    .phoneNumber(form.getPhoneNumber())
                    .birthDate(form.getBirthDate())
                    .build());
        return "redirect:/";
    }

    @RequestMapping(value = "/{customerId}/edit", method = RequestMethod.GET)
    public String getEditCustomer(@PathVariable long customerId, Model model) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            Customer cust = customer.get();
            CustomerForm form = new CustomerForm();
            form.setId(cust.getId());
            form.setFirstName(cust.getFirstName());
            form.setLastName(cust.getLastName());
            form.setMiddleName(cust.getMiddleName());
            form.setEmail(cust.getEmail());
            form.setPhoneNumber(cust.getPhoneNumber());
            form.setBirthDate(cust.getBirthDate());
            model.addAttribute("customerForm", form);
            return "customer/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editCustomer(@ModelAttribute CustomerForm form) {
        Optional<Customer> customer = customerRepository.findById(form.getId());
        if (customer.isPresent()) {
            Customer cust = customer.get();
            cust.setFirstName(form.getFirstName());
            cust.setLastName(form.getLastName());
            cust.setMiddleName(form.getMiddleName());
            cust.setEmail(form.getEmail());
            cust.setPhoneNumber(form.getPhoneNumber());
            cust.setBirthDate(form.getBirthDate());
            customerRepository.save(cust);
            return "redirect:/";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }

    @RequestMapping(value = "/{customerId}/delete", method = RequestMethod.GET)
    public String deleteCustomer(@PathVariable long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            customerRepository.deleteById(customer.get().getId());
            return "redirect:/";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }

}
