package card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

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
        return cards.remove(0);
    }
}
