package com.workintech.fswebs18challengemaven.repository;


import com.workintech.fswebs18challengemaven.entity.Card;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class CardRepositoryImpl implements CardRepository {

    private EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findByColor(String color) {
        return entityManager.createQuery("SELECT c FROM Card c WHERE c.color = :color", Card.class)
                .setParameter("color", color)
                .getResultList();
    }

    @Override
    public List<Card> findAll() {
        return entityManager.createQuery("SELECT c FROM Card c", Card.class)
                .getResultList();
    }

    @Override
    public List<Card> findByValue(Integer value) {
        return entityManager.createQuery("SELECT c FROM Card c WHERE c.value = :value", Card.class)
                .setParameter("value", value)
                .getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        return entityManager.createQuery("SELECT c FROM Card c WHERE c.type = :type", Card.class)
                .setParameter("type", type)
                .getResultList();
    }

    @Override
    @Transactional
    public Card update(Card card) {
        entityManager.merge(card);
        return card;
    }

    @Override
    @Transactional
    public Card remove(Long id) {
        Card card = entityManager.find(Card.class, id);
        if (card != null) {
            entityManager.remove(card);
        }
        return card;
    }

    @Override
    public Card findById(Long id) {
        return entityManager.find(Card.class, id);
    }

}
