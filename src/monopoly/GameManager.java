package monopoly;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public class GameManager {
  
  private Board board;

  public static void main(String[] args) {
    GameContainer gc = new GameContainer(new GameManager());
    gc.start();
  }
  
  public void init(GameContainer gc) {
    gc.setWidth(688);
    gc.setHeight(688);
    
    board = new Board(gc);
    board.addPlayer("Bob");
  }
  
  public void update(GameContainer gc) {
    board.update(gc);
  }
  
  public void render(GameContainer gc, Renderer r) {
    board.render(gc, r);
  }

  public Board getBoard() {
    return board;
  }
}
