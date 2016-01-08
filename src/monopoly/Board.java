package monopoly;

import java.awt.Color;
import java.util.ArrayList;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public class Board {

  private ArrayList<Player> players;
  private ArrayList<Space> spaces;
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
    spaces = new ArrayList<>();
    
    // Correctly fill the spaces
    for (int i = 0; i <= 39; i++) {
      switch (i) {
        case 0: spaces.add(new Go()); break;
        case 10: spaces.add(new Jail()); break;
        case 20: spaces.add(new Parking()); break;
        case 30: spaces.add(new GotoJail()); break;
        default: spaces.add(new Property("Property", 10, i));
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
    p.move(rollDie());
    System.out.println("Player is at " + spaces.get(p.getPosition()).getName());
  }
  
  public void render(GameContainer gc, Renderer r) {
    r.fillRect(0, 0, gc.getWidth(), gc.getHeight(), new Color(255,255,255));
    r.drawRect(100, 40, 392, 392, new Color(20,20,20));
    spaces.forEach(s -> s.render(gc, r));
  }
}
