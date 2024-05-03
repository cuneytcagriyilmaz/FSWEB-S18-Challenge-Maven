package com.workintech.fswebs18challengemaven.controller;


import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/cards")
@Slf4j
public class CardController {

    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardRepository.findAll();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/byColor/{color}")
    public ResponseEntity<List<Card>> getCardsByColor(@PathVariable String color) {
        List<Card> cards = cardRepository.findByColor(color);
        return ResponseEntity.ok(cards);
    }

    @PostMapping
    public ResponseEntity<String> addCard(@RequestBody Card card) {
        try {
            CardValidation.validateCard(card);
            cardRepository.save(card);
            return ResponseEntity.status(HttpStatus.CREATED).body("Card successfully added");
        } catch (IllegalArgumentException e) {
            log.error("Invalid card data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid card data: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCard(@PathVariable Long id, @RequestBody Card newCard) {
        Card cardToUpdate = cardRepository.findById(id);
        if (cardToUpdate == null) {
            throw new CardException("Card not found with id: " + id);
        }
        newCard.setId(id);
        cardRepository.update(newCard);
        return ResponseEntity.ok("Card successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCard(@PathVariable Long id) {
        cardRepository.remove(id);
        return ResponseEntity.ok("Card successfully removed");
    }

    @GetMapping("/byValue/{value}")
    public ResponseEntity<List<Card>> getCardsByValue(@PathVariable Integer value) {
        List<Card> cards = cardRepository.findByValue(value);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/byType/{type}")
    public ResponseEntity<List<Card>> getCardsByType(@PathVariable String type) {
        List<Card> cards = cardRepository.findByType(type);
        return ResponseEntity.ok(cards);
    }

    @ExceptionHandler(CardException.class)
    public ResponseEntity<String> handleCardNotFoundException(CardException ex) {
        log.error("Card not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        log.error("An unexpected error occurred: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }
}
