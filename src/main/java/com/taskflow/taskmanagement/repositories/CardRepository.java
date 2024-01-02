package com.taskflow.taskmanagement.repositories;

import com.taskflow.taskmanagement.entities.Card;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends BaseRepository<Card> {

    @Modifying
    @Query("UPDATE Card SET numberOfUtilisation = 1 WHERE type = 'Deletion'")
    void restoreNumberOfUtilisationOfDeletionCards();

    @Modifying
    @Query("UPDATE Card SET numberOfUtilisation = 2 WHERE type = 'Modification'")
    void restoreNumberOfUtilisationOfModificationCards();


    @Modifying
    @Query( "UPDATE Card c SET c.numberOfUtilisation = 4 WHERE c.type = 'Modification' AND c.user.id in " +
            "(SELECT d.demandedBy.id FROM DemandReplacement d WHERE  d.status = 'PENDING' AND d.demandedBy.id = c.user.id )")
    void updateModificationCardsIfDemandIsPending();
}
