package model;

public class Player {
    private String name;
    private Hand hand;

    public Player(String name){
        this.name = name;
        this.hand = new Hand();
    }

    public void addCardToHand(Card card){
        this.hand.addCard(card);
    }

    public Card getCard(int index){
        return this.hand.getCard(index);
    }

    public Card removeCard(){
        return this.hand.removeCard();
    }
}
