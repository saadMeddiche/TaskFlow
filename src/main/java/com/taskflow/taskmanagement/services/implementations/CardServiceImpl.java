package com.taskflow.taskmanagement.services.implementations;

import com.taskflow.taskmanagement.entities.Card;
import com.taskflow.taskmanagement.entities.User;
import com.taskflow.taskmanagement.enums.CardType;
import com.taskflow.taskmanagement.enums.RangeType;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.taskflow.taskmanagement.handlingExceptions.costumExceptions.ValidationException;
import com.taskflow.taskmanagement.repositories.CardRepository;
import com.taskflow.taskmanagement.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public void createDefaultCards(User user){
        createDefaultModificationCardForUser(user);
        createDefaultDeletionCardForUser(user);
    }


    @Override
    public void createDefaultModificationCardForUser(User user) {

        user.getCards().stream()
                .filter(card -> card.getType().equals(CardType.Modification))
                .findFirst()
                .ifPresentOrElse(
                        card -> {
                            throw new AlreadyExistsException("Modification card already exists for user " + user.getUsername());
                        },
                        () -> {
                            Card card  = Card.builder()
                                    .id(null)
                                    .user(user)
                                    .numberOfUtilisation(2)
                                    .type(CardType.Modification)
                                    .rangeType(RangeType.PerDay)
                                    .build();

                            cardRepository.save(card);
                        }
                );

    }

    @Override
    public void createDefaultDeletionCardForUser(User user) {

        user.getCards().stream()
                .filter(card -> card.getType().equals(CardType.Deletion))
                .findFirst()
                .ifPresentOrElse(
                        card -> {
                            throw new AlreadyExistsException("Deletion card already exists for user " + user.getUsername());
                        },
                        () -> {
                            Card card  = Card.builder()
                                    .id(null)
                                    .user(user)
                                    .numberOfUtilisation(1)
                                    .type(CardType.Deletion)
                                    .rangeType(RangeType.PerMonth)
                                    .build();

                            cardRepository.save(card);
                        }
                );

    }

    @Override
    public void useModifyCard(User user) {

        user.getCards().stream().filter(card -> card.getType().equals(CardType.Modification))
                .findFirst()
                .ifPresentOrElse(
                        card -> {
                            card.setNumberOfUtilisation(card.getNumberOfUtilisation() - 1);
                            cardRepository.save(card);
                        },
                        () -> {
                            createDefaultModificationCardForUser(user);
                            throw new ValidationException("No modification card found for user " + user.getUsername() + ". But a new one has been created. You can now use it");
                        }
                );

    }

    @Override
    public void useDeleteCard(User user) {

        user.getCards().stream().filter(card -> card.getType().equals(CardType.Deletion))
                .findFirst()
                .ifPresentOrElse(
                        card -> {
                            card.setNumberOfUtilisation(card.getNumberOfUtilisation() - 1);
                            cardRepository.save(card);
                        },
                        () -> {
                            createDefaultDeletionCardForUser(user);
                            throw new ValidationException("No deletion card found for user " + user.getUsername() + ". But a new one has been created. You can now use it");
                        }
                );

    }

    @Override
    public boolean checkIfUserCanUseModifyCard(User user) {
        return user.getCards()
                .stream()
                .filter(card -> card.getType().equals(CardType.Modification))
                .findFirst()
                .map(card -> card.getNumberOfUtilisation() > 0)
                .orElse(false);
    }

    @Override
    public boolean checkIfUserCanUseDeleteCard(User user) {
        return user.getCards()
                .stream()
                .filter(card -> card.getType().equals(CardType.Deletion))
                .findFirst()
                .map(card -> card.getNumberOfUtilisation() > 0)
                .orElse(false);
    }


}
