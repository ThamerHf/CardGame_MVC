package model;

public enum Suit {
    DIAMONDS (1),
    HEART (2),
    SPADES (3),
    CUBS (4);

    int suit;
    private Suit(int value){
        this.suit = value;
    }

    public int value(){
        return this.suit;
    }
}
