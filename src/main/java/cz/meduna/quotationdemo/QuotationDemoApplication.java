package cz.meduna.quotationdemo;

import cz.meduna.quotationdemo.model.Customer;
import cz.meduna.quotationdemo.model.Quotation;
import cz.meduna.quotationdemo.repository.CustomerRepository;
import cz.meduna.quotationdemo.repository.QuotationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class QuotationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuotationDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository customerRepository, QuotationRepository quotationRepository) {
        return args -> {
            Customer customer1 = Customer.builder().firstName("Tom").lastName("Green").birthDate(LocalDate.of(1990, 5, 1)).build();
            Customer customer2 = Customer.builder().firstName("John").lastName("Black").birthDate(LocalDate.of(1989, 5, 11)).build();
            Customer customer3 = Customer.builder().firstName("Lucy").lastName("Pug").birthDate(LocalDate.of(1995, 7, 3)).build();

            customerRepository.save(customer1);
            customerRepository.save(customer2);
            customerRepository.save(customer3);

            quotationRepository.save(Quotation.builder()
                    .beginningOfInsurance(LocalDate.of(2023, 1, 1))
                    .insuredAmount(new BigDecimal("15612"))
                    .dateOfSigningMortgage(LocalDate.of(2022, 12, 23))
                    .customer(customer1)
                    .build());
            quotationRepository.save(Quotation.builder()
                    .beginningOfInsurance(LocalDate.of(2023, 1, 1))
                    .insuredAmount(new BigDecimal("15612.50"))
                    .dateOfSigningMortgage(LocalDate.of(2022, 12, 23))
                    .customer(customer2)
                    .build());
            quotationRepository.save(Quotation.builder()
                    .beginningOfInsurance(LocalDate.of(2023, 2, 2))
                    .insuredAmount(new BigDecimal("51561.80"))
                    .dateOfSigningMortgage(LocalDate.of(2022, 12, 23))
                    .customer(customer3)
                    .build());
            quotationRepository.save(Quotation.builder()
                    .beginningOfInsurance(LocalDate.of(2022, 2, 15))
                    .insuredAmount(new BigDecimal("815689.25"))
                    .dateOfSigningMortgage(LocalDate.of(2021, 10, 20))
                    .customer(customer3)
                    .build());
        };
    }

}
