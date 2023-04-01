package cz.meduna.quotationdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "SUBSCRIPTION")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private LocalDate startDate;
    private LocalDate validUntil;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "quotation_id", referencedColumnName = "id")
    @JsonIgnore
    private Quotation quotation;

    public String getQuotationData() {
        return quotation.getBeginningOfInsurance()
                + " - " + quotation.getDateOfSigningMortgage()
                + " Insured amound: " + quotation.getInsuredAmount();
    }

}
