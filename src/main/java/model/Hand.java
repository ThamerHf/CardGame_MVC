package model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;
    private final int FIRSTCARD = 0;

    public Hand(){
        this.cards = new ArrayList<Card>();
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public Card getCard(int index){
        return cards.get(index);
    }

    public Card removeCard(){
        return cards.remove(FIRSTCARD);
    }
}
