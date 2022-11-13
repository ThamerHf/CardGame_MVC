package controller;
import view.GameViewer;
import model.Player;
import model.Deck;
import model.Card;


import java.util.ArrayList;
import java.util.List;

public class GameController{
    enum GamerState{
        AddingPlayers, CardDealt, WinnerRevealed;
    }

    private final int FIRSTCARD = 0;
    private Deck deck;
    private GameViewer view;
    private List<Player> players;
    private Player winner;
    private GamerState gamerState;

    public GameController(Deck deck, GameViewer view){
        this.deck = deck;
        this.view = view;
        this.players = new ArrayList<Player>();
        this.view.setController(this);
        this.gamerState = GamerState.AddingPlayers;
    }

    public void run(){
        while(this.gamerState == GamerState.AddingPlayers){
            this.view.something();
        }

        switch (gamerState) {
            case CardDealt:
                this.view.something();
                break;
            case WinnerRevealed:
                this.view.something();
                break;
        }
    }

    public void addPlayer(String playerName){
        if(gamerState == GamerState.AddingPlayers){
            this.players.add(new Player(playerName));
            this.view.something();
        }
    }

    public void startGame(){
        if(gamerState != GamerState.CardDealt){
            this.deck.shuffle();
            for(Player player : this.players){
                player.addCardToHand(deck.removeTopCard());
                this.view.something();
            }
            this.gamerState = GamerState.CardDealt;
        }

        this.run();
    }

    public void flipCards(){
        for(Player player : this.players){
            Card card = player.getCard(FIRSTCARD);
            card.flip();
            view.something();
        }

        this.evaluateWinner();
        this.displayWinner();
        this.rebuildDeck();
        this.run();
    }

    private boolean newBestPlayer(Player player, int bestRank, int bestSuit){
        Card playerCard = player.getCard(FIRSTCARD);
        if(playerCard.getRank().value() > bestRank){
            return true;
        }
        else {
            if (playerCard.getSuit().value() > bestSuit) {
                return true;
            }
        }
        return false;
    }

    public void displayWinner(){
        this.view.something();
    }

    public void rebuildDeck(){
        Card playerCard = null;
        for(Player player : this.players){
            playerCard = player.removeCard();
            playerCard.flip();
            this.deck.returnCardToDeck(playerCard);
        }
    }

    public void evaluateWinner(){
        Player bestPlayer = null;
        int bestRank = -1;
        int bestSuit = -1;
        Card bestCard = null;

        for(Player player : this.players){
            Boolean newBestPlayer = false;
            if(bestPlayer == null){
                newBestPlayer = true;
            }else{
                newBestPlayer = this.newBestPlayer(player, bestRank, bestSuit);
            }

            if(newBestPlayer){
                bestPlayer = player;
                bestCard = player.getCard(FIRSTCARD);
                bestRank = bestCard.getRank().value();
                bestSuit = bestCard.getRank().value();
            }
        }
    }


}
