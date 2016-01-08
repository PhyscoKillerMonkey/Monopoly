package monopoly;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import monopoly.core.GameContainer;
import monopoly.core.Input;
import monopoly.core.Renderer;

public class Board {

  private ArrayList<Player> players;
  private ArrayList<Space> spaces;
  private int currentPlayer;
  
  private int die1;
  private int die2;
  
  private Input in;
  
  // Game Flow Variables
  private boolean canEnd;
  private boolean canRoll;
  private boolean canBuy;
  private Property currentProperty;
  
  public Board(GameContainer gc) {
    players = new ArrayList<>();
    currentPlayer = 0;
    die1 = 1;
    die2 = 1;
    
    in = gc.getInput();
    
    canEnd = canBuy = false;
    canRoll = true;
    currentProperty = null;
    
    makeBoard();
  }
  
  public void makeBoard() {
    spaces = new ArrayList<>();
    
    // Correctly fill the spaces
    for (int i = 0; i <= 39; i++) {
      switch (i) {
        case 0: spaces.add(new Go()); break;
        case 10: spaces.add(new Jail()); break;
        case 20: spaces.add(new Parking()); break;
        case 30: spaces.add(new GotoJail()); break;
        default: spaces.add(new Property("Property", 10, 10, i));
      }
    }
  }
  
  public void addPlayer(String name) {
    players.add(new Player(name));
  }
  
  public Player getPlayer(int index) {
    return players.get(index);
  }
  
  public int numberPlayers() {
    return players.size();
  }
  
  public int rollDie() {
    die1 = (int) Math.ceil(Math.random() * 6);
    die2 = (int) Math.ceil(Math.random() * 6);
    System.out.println(die1 + die2 + " rolled");
    return die1 + die2;
  }
  
  public void update(GameContainer gc) {
    Player p = players.get(currentPlayer);
    
    // End turn
    if (in.isKeyPressed(KeyEvent.VK_E) && canEnd) {
      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      p = players.get(currentPlayer);
      canRoll = true;
      canEnd = false;
    }
    
    // Roll dice
    if (in.isKeyPressed(KeyEvent.VK_R) && canRoll) {
      p.move(rollDie());
      canRoll = false;
      if (spaces.get(p.getPosition()).action(gc, p)) {
        canEnd = true;
      } else {
        currentProperty = (Property) spaces.get(p.getPosition());
        canBuy = true;
      }
    }
    
    // Buy or don't buy
    if (in.isKeyPressed(KeyEvent.VK_Y) && canBuy) {
      currentProperty.setOwner(p);
      p.changeMoney(-currentProperty.getPrice());
      p.addOwned(currentProperty);
      canBuy = false;
      canEnd = true;
    }
    if (in.isKeyPressed(KeyEvent.VK_N) && canBuy) {
      // This should be auction!!!!
      canBuy = false;
      canEnd = true;
    }
  }
  
  public void render(GameContainer gc, Renderer r) {
    r.fillRect(0, 0, gc.getWidth(), gc.getHeight(), new Color(255,255,255));
    r.drawRect(50, 50, 588, 588, new Color(20,20,20));
    spaces.forEach(s -> s.render(gc, r));
  }
}
