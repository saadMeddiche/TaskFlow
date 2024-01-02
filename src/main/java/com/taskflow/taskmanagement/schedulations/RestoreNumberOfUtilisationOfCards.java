package com.taskflow.taskmanagement.schedulations;

import com.taskflow.taskmanagement.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestoreNumberOfUtilisationOfCards {

    private final CardService cardService;

    // The Last Day Of Every Month
    @Scheduled(cron = "0 0 L * ?")
    public void  restoreNumberOfUtilisationOfDeletionCards() {

    }

    // Each Day In The Mid Night
    @Scheduled(cron = "0 0 * * ?")
    public void  restoreNumberOfUtilisationOfModificationCards(){

    }
}
