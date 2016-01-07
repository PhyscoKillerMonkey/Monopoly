package monopoly;

import java.util.ArrayList;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public class Player {

  private String name;
  private int position;
  private int money;
  private ArrayList<Integer> owned;
  
  public Player(String name) {
    this.name = name;
    this.position = 0;
    this.money = 1500;
    owned = new ArrayList<>();
  }
  
  public void move(int steps) {
    position += steps;
  }
  
  public void render(GameContainer gc, Renderer r) {
    
  }
  
  public void changeMoney(int change) {
    money += change;
  }
  
  public int getMoney() {
    return money;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    if (position >= 0 && position <= 39) {
      this.position = position; 
    } else {
      try {
        throw(new Throwable("Space index out of bounds"));
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
  }

  public ArrayList<Integer> getOwned() {
    return owned;
  }
}
