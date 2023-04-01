package cz.meduna.quotationdemo.controller.form;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscriptionForm {

    private LocalDate startDate;
    private LocalDate validUntil;
    private long quotationId;

}
