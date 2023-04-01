package cz.meduna.quotationdemo.repository;

import cz.meduna.quotationdemo.model.Quotation;
import org.springframework.data.repository.CrudRepository;

public interface QuotationRepository extends CrudRepository<Quotation, Long> {
}
