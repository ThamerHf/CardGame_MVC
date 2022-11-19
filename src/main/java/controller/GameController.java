package controller;
import view.GameViewer;
import model.Player;
import model.Deck;
import model.Card;

import java.util.ArrayList;
import java.util.List;

public class GameController{
    enum GameState{
        AddingPlayers, CardDealt, WinnerRevealed;
    }

    private final int FIRSTCARD = 0;
    private Deck deck;
    private GameViewer view;
    private List<Player> players;
    private Player winner;
    private GameState gameState;

    public GameController(Deck deck, GameViewer view){
        this.deck = deck;
        this.view = view;
        this.players = new ArrayList<Player>();
        this.view.setController(this);
        this.gameState = GameState.AddingPlayers;
    }

    public void run(){
        while(this.gameState == GameState.AddingPlayers){
            this.view.askForPlayerName();
        }

        switch (gameState) {
            case CardDealt:
                this.view.askForFlip();
                break;
            case WinnerRevealed:
                this.gameState = GameState.AddingPlayers;
                this.view.askForNewGame();
                break;
        }
    }

    public void addPlayer(String playerName){
        if(gameState == GameState.AddingPlayers){
            this.players.add(new Player(playerName));
            this.view.showPlayerName(players.size(), playerName);
        }
    }

    public void startGame(){
        if(gameState != GameState.CardDealt){
            this.deck.shuffle();
            int playerIndex = 1;
            for(Player player : this.players){
                player.addCardToHand(deck.removeTopCard());
                this.view.showFaceDownCardForPlayer(playerIndex++, player.getName());
            }
            this.gameState = GameState.CardDealt;
        }

        this.run();
    }

    public void flipCards(){
        int playerIndex = 1;
        for(Player player : this.players){
            Card card = player.getCard(FIRSTCARD);
            card.flip();
            view.showFaceUpCardForPlayer(playerIndex++, player.getName(), card);
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
        this.view.showWinner(winner.getName());
        this.gameState = GameState.WinnerRevealed;
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

        this.winner = bestPlayer;
    }
}
