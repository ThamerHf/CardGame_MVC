package view;

import controller.GameController;
import model.Card;

import java.util.Scanner;

public class GameViewer {
    private GameController controller;
    Scanner keybord = new Scanner(System.in);

    public void setController(GameController controller){
        this.controller = controller;
    }

    public void askForPlayerName(){
        System.out.println("Enter Player name");
        String playerName = keybord.nextLine();
        if(playerName.isEmpty()){
            this.controller.startGame();
        }else{
            this.controller.addPlayer(playerName);
        }
    }

    public void askForFlip(){
        System.out.println("Press enter to reveal cards");
        keybord.nextLine();
        this.controller.flipCards();
    }

    public void askForNewGame(){
        System.out.println("Press enter to Start newGame");
        keybord.nextLine();
        this.controller.run();
    }

    public void showWinner(String winnerName){
        System.out.println("Winner is: " +winnerName + "!");
    }

    public void showPlayerName(int size, String playerName){
        System.out.println(new String(String.format("[%d]:[%s]",
                size, playerName)));
    }

    public void showFaceDownCardForPlayer(int playerIndex, String playerName){
        System.out.println(new String(String.format("[%d]:[%s]:[X][X]",
                playerIndex, playerName)));
    }

    public void showFaceUpCardForPlayer(int playerIndex, String playerName, Card card){
        String rank = card.getRank().toString();
        String suit = card.getSuit().toString();
        System.out.println(new String(String.format("[%d]:[%s]:[%s][%s]",
                playerIndex, playerName, rank, suit)));
    }
}
