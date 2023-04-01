package cz.meduna.quotationdemo.repository;

import cz.meduna.quotationdemo.model.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
}
