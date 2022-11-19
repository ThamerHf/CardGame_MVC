import controller.GameController;
import model.Deck;
import view.GameViewer;

public class Main {
    public static void main(String[] args) {
        GameController controller = new GameController(new Deck(), new GameViewer());
        controller.run();
    }
}