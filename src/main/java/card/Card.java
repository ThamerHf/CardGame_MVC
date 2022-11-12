package card;

public class Card {
    private Rank rank;
    private Suit suit;
    private boolean faceUp = false;

    public Card(Rank rank,Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank(){
        return this.rank;
    }

    public Suit getSuit(){
        return this.suit;
    }

    public boolean isFaceUp(){
        return this.faceUp;
    }

    public boolean flip(){
        this.faceUp = !this.faceUp;
        return this.faceUp;
    }
}
