package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;

public class CardValidation {

    public static void validateCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card object cannot be null");
        }

        if (card.getValue() != null && card.getType() != null) {
            throw new IllegalArgumentException("A card cannot have both value and type set");
        }

        if (card.getType() == Type.JOKER && (card.getValue() != null || card.getColor() != null)) {
            throw new IllegalArgumentException("For a Joker card, both value and color must be null");
        }
    }
}
