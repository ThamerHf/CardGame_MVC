package card;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class Deck {
    private List<Card> cards;

    public Deck(){
        this.cards = new ArrayList<Card>();
        for(Rank rank : Rank.values()){
            for(Suit suit: Suit.values()){
                cards.add(new Card(rank, suit));
            }
        }
    }

    private void shuffle() {
        Random random = new Random();
        for(int i = 0; i < cards.size(); i++){
            Collections.swap(cards, i, random.nextInt(cards.size()));
        }
    }

    public Card removeTopCard(){
        int first = 0;
        return this.cards.remove(first);
    }

    public void returnCardToDeck(Card card){
        this.cards.add(card);
    }

    public List<Card> getCards(){
        return this.cards;
    }
}
