package cz.meduna.quotationdemo.repository;

import cz.meduna.quotationdemo.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
