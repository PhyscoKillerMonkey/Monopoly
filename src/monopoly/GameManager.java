package monopoly;

import java.util.Stack;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;
import monopoly.popups.Popup;

public class GameManager {
  
  private Board board;
  
  private Stack<Popup> popups;

  public static void main(String[] args) {
    GameContainer gc = new GameContainer(new GameManager());
    gc.start();
  }
  
  public void init(GameContainer gc) {
    gc.setWidth(688);
    gc.setHeight(688);
    
    popups = new Stack<>();
    
    board = new Board(gc);
    board.addPlayer("Bob");
  }
  
  public void update(GameContainer gc) {
    if (popups.isEmpty()) {
      board.update(gc);
    } else {
      popups.peek().update(gc);
    }
  }
  
  public void render(GameContainer gc, Renderer r) {
    if (popups.isEmpty()) {
      board.render(gc, r);
    } else {
      popups.peek().render(gc, r);
    }
  }

  public Board getBoard() {
    return board;
  }
  
  public Popup peek() {
    return popups.peek();
  }
  
  public void push(Popup p) {
    popups.push(p);
  }
  
  public void pop() {
    popups.pop();
  }
}
