package monopoly.popups;

import java.awt.Color;
import java.awt.Font;

import monopoly.Board;
import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public abstract class Popup  {
  
  // Keep a reference to the board
  protected Board board;
  
  // Font and colours to use
  protected Font font = new Font("Arial", Font.PLAIN, 20);
  protected Color black = new Color(20, 20, 20);
  
  protected int x, y;
  protected int w, h;
  
  public Popup(GameContainer gc, int x, int y, int w, int h) {
    board = gc.getGame().getBoard();
    
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }
  
  public abstract void update(GameContainer gc);
  public abstract void render(GameContainer gc, Renderer r);
  
  public void close(GameContainer gc) {
    gc.getGame().pop();
  }
}
