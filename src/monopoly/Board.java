package monopoly;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;

import monopoly.core.GameContainer;
import monopoly.core.Input;
import monopoly.core.Renderer;
import monopoly.spaces.CardSpace;
import monopoly.spaces.Go;
import monopoly.spaces.GotoJail;
import monopoly.spaces.Jail;
import monopoly.spaces.Parking;
import monopoly.spaces.Property;
import monopoly.spaces.Space;
import monopoly.spaces.Station;
import monopoly.spaces.Utility;

public class Board {

  private ArrayList<Player> players;
  private Space[] spaces;
  private int currentPlayer;
  
  private int die1;
  private int die2;
  
  private Input in;
  
  // Game Flow Variables
  private boolean hasRolled;
  
  private String status;
  
  public Board(GameContainer gc) {
    players = new ArrayList<>();
    currentPlayer = 0;
    die1 = 1;
    die2 = 1;
    
    in = gc.getInput();
    
    hasRolled = false;
    
    status = "Welcome to Monopoly";
    
    makeBoard();
  }
  
  public void makeBoard() {
    spaces = new Space[40];
    
    spaces[0] = new Go();
    spaces[2] = new CardSpace("Community Chest", 2);
    spaces[4] = new Tax("Income Tax");
    spaces[5] = new Station("King's Cross Station");
    spaces[7] = new CardSpace("Chance", 7);
    spaces[10] = new Jail();
    spaces[12] = new Utility("Electric");
    spaces[15] = new Station("Marylebone Station");
    spaces[17] = new CardSpace("Community Chest", 17);
    spaces[20] = new Parking();
    spaces[22] = new CardSpace("Chance", 22);
    spaces[25] = new Station("Fenchurch St Station");
    spaces[28] = new Utility("Waterworks");
    spaces[30] = new GotoJail();
    spaces[33] = new CardSpace("Community Chest", 33);
    spaces[35] = new Station("Liverpool Street Station");
    spaces[36] = new CardSpace("Chance", 36);
    spaces[38] = new Tax("Super Tax");
    
    // Read Properties
    BufferedReader reader = new BufferedReader(new InputStreamReader
        (GameManager.class.getResourceAsStream("/monopoly/spaces/Properties.json")));
    String file = "";
    try {
      String line = reader.readLine();
      while (line != null) {
        file += line.trim();
        line = reader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    JsonArray items = Json.parse(file).asArray();
    for (JsonValue item : items) {
      int index = item.asObject().getInt("index", -1);
      String name = item.asObject().getString("name", "NoName");
      int price = item.asObject().getInt("price", 0);
      int houseCost = item.asObject().getInt("houseCost", 0);
      int rent0 = item.asObject().getInt("rent0", 0);
      int rent1 = item.asObject().getInt("rent1", 0);
      int rent2 = item.asObject().getInt("rent2", 0);
      int rent3 = item.asObject().getInt("rent3", 0);
      int rent4 = item.asObject().getInt("rent4", 0);
      int rent5 = item.asObject().getInt("rent5", 0);
      int[] rents = {rent0, rent1, rent2, rent3, rent4, rent5};
      spaces[index] = new Property(name, price, houseCost, rents, index);
    }
    for (int i = 0; i < spaces.length; i++) {
      Space s = spaces[i];
      if (s != null) {
        System.out.println(i + " " + s.getName() + " ");
      } else {
        System.out.println(i + " null");
      }
    }
  }
  
  public void addPlayer(String name) {
    players.add(new Player(name));
  }
  
  public Player getPlayer(int index) {
    return players.get(index);
  }
  
  public Player getCurrentPlayer() {
    return players.get(currentPlayer);
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
    
    // Roll dice
    if (in.isKeyPressed(KeyEvent.VK_R) && !hasRolled) {
      p.move(rollDie());
      hasRolled = true;
      System.out.println(p.getPosition());
      spaces[p.getPosition()].action(gc, p);
      status = "Press E to end turn";
    }
    
    // DEBUG move one space
    if (in.isKeyPressed(KeyEvent.VK_M)) {
      p.move(1);
      hasRolled = true;
      System.out.println(p.getPosition());
      spaces[p.getPosition()].action(gc, p);
      status = "Press E to end turn";
    }
    
    // End turn
    if (in.isKeyPressed(KeyEvent.VK_E) && hasRolled) {
      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      p = players.get(currentPlayer);
      hasRolled = false;
      status = p.getName() + "'s turn, press R to roll";
    }
  }
  
  public void render(GameContainer gc, Renderer r) {
    r.fillRect(0, 0, gc.getWidth(), gc.getHeight(), new Color(255,255,255));
    
    r.drawRect(50, 50, 588, 588, new Color(20,20,20));
    for (int i = 0; i < spaces.length; i++) {
      spaces[i].render(gc, r);
    }
    
    // Draw the status panel
    r.drawStringCentered(status, 344, 344, new Font("Arial", Font.PLAIN, 20), new Color(20, 20, 20));
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
