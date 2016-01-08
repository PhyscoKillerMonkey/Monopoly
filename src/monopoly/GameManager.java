package monopoly;

import java.awt.event.KeyEvent;

import monopoly.core.GameContainer;
import monopoly.core.Input;
import monopoly.core.Renderer;

public class GameManager {
  
  private Board board;
  private Input i;

  public static void main(String[] args) {
    GameContainer gc = new GameContainer(new GameManager());
    gc.start();
  }
  
  public void init(GameContainer gc) {
    i = gc.getInput();
    
    board = new Board();
    board.addPlayer("Bob");
  }
  
  public void update(GameContainer gc) {
    if (i.isKeyPressed(KeyEvent.VK_R)) {
      board.update(gc);
    }
  }
  
  public void render(GameContainer gc, Renderer r) {
    board.render(gc, r);
  }

  public Board getBoard() {
    return board;
  }
}
