package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestModel {
    private final int FIRSTCARD = 0;

    //************** Rank test ***************//
    @Test
    @DisplayName("Rank value() test")
    public void rankTest1(){
        Rank rank = Rank.JACK;
        assertEquals(rank.value(), 10);
    }

    //************** Suit test ***************//
    @Test
    @DisplayName("Suit value() test")
    public void suitTest1(){
        Suit suit = Suit.SPADES;
        assertEquals(suit.value(), 3);
    }

    //************** Card test ***************//
    @Test
    @DisplayName("Card's flip and isFaceUp tests")
    public void cardTest1(){
        Card card = new Card(Rank.ACE, Suit.CUBS);
        card.flip();
        assertEquals(card.isFaceUp(), true);
    }

    @Test
    @DisplayName("card's getSuit, getRank tests")
    public void cardTest2(){
        Card card = new Card(Rank.ACE, Suit.CUBS);
        assertEquals(card.getRank(), Rank.ACE);
        assertEquals(card.getSuit(),Suit.CUBS);
    }

    @Test
    @DisplayName("deck function removeTopCard test")
    public void deckTest1(){
        Deck deck = new Deck();
        Card topCard = deck.removeTopCard();
        assertEquals(deck.getCards().contains(topCard), false);
    }

    @Test
    @DisplayName("deck function returnCardToDeck test")
    public void deckTest2(){
        Deck deck = new Deck();
        Card topCard = deck.removeTopCard();
        deck.returnCardToDeck(topCard);
        assertEquals(deck.getCards().contains(topCard), true);
    }

    //************** Hand test ***************//
    @Test
    @DisplayName("Hand functions addCard and removeCard test")
    public void handTest1(){
        Hand hand = new Hand();
        Card card = new Card(Rank.ACE, Suit.CUBS);
        hand.addCard(card);
        assertEquals(hand.removeCard(), card);
    }

    @Test
    @DisplayName("Hand functions getCard and addCard test")
    public void handTest2(){
        Hand hand = new Hand();
        Card card = new Card(Rank.ACE, Suit.CUBS);
        hand.addCard(card);
        assertEquals(hand.getCard(FIRSTCARD), card);
    }

    //************** Player test ***************//
    @Test
    @DisplayName("Player functions addCardToHand and removeCard test")
    public void playerTest1(){
        Player player = new Player("Thamer");
        Card card = new Card(Rank.ACE, Suit.CUBS);
        player.addCardToHand(card);
        assertEquals(player.removeCard(), card);
    }

    @Test
    @DisplayName("Player functions getCard and addCardToHand test")
    public void playerTest2(){
        Player player = new Player("Thamer");
        Card card = new Card(Rank.ACE, Suit.CUBS);
        player.addCardToHand(card);
        assertEquals(player.getCard(FIRSTCARD), card);
    }

}
