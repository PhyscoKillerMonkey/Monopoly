package monopoly;

import java.util.ArrayList;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public class Board {

  private ArrayList<Player> players;
  private int currentPlayer;
  
  private int die1;
  private int die2;
  
  public Board() {
    players = new ArrayList<>();
    currentPlayer = 0;
    die1 = 1;
    die2 = 1;
    
    makeBoard();
  }
  
  public void makeBoard() {
    // Correctly fill the spaces
  }
  
  public int rollDie() {
    die1 = (int) Math.ceil(Math.random() * 6);
    die2 = (int) Math.ceil(Math.random() * 6);
    return die1 + die2;
  }
  
  public void update(GameContainer gc) {
    
  }
  
  public void render(GameContainer gc, Renderer r) {
    
  }
}
