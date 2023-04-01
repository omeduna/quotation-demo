package cz.meduna.quotationdemo.controller;

import cz.meduna.quotationdemo.model.Subscription;
import cz.meduna.quotationdemo.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscription")
public class SubscriptionDetailController {

    private final SubscriptionRepository subscriptionRepository;

    @GetMapping("/{subscriptionId}")
    public Subscription detail(@PathVariable Long subscriptionId) {
        Optional<Subscription> subscriptionOpt = subscriptionRepository.findById(subscriptionId);
        if (subscriptionOpt.isPresent()) {
            return subscriptionOpt.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription not found");
        }
    }

}
