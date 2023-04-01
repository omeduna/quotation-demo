package cz.meduna.quotationdemo.controller.form;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class QuotationForm {

    private LocalDate start;
    private BigDecimal amount;
    private LocalDate signature;
    private long customerId;

}
