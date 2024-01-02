package com.taskflow.taskmanagement.schedulations;

import com.taskflow.taskmanagement.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
// Cron Expression
// https://www.baeldung.com/cron-expressions
// https://javatechonline.com/spring-scheduling-cron-expression/
public class RestoreNumberOfUtilisationOfCards {


   private final CardRepository cardRepository;

    // The Last Day Of Every Month
    @Scheduled(cron = "0 0 0 L * ?")
    public void  restoreNumberOfUtilisationOfDeletionCards() {
       cardRepository.restoreNumberOfUtilisationOfDeletionCards();
    }

    // Each Day In The Mid Night
    @Scheduled(cron = "0 0 0 * * ?")
    public void  restoreNumberOfUtilisationOfModificationCards(){
        cardRepository.restoreNumberOfUtilisationOfModificationCards();
        cardRepository.updateModificationCardsIfDemandIsPending();
    }
}
