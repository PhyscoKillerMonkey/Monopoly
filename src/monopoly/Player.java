package monopoly;

import java.util.ArrayList;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;
import monopoly.spaces.Property;

public class Player {

  private String name;
  private int position;
  private int money;
  private ArrayList<Property> owned;
  private boolean inJail;
  
  public Player(String name) {
    this.name = name;
    this.position = 0;
    this.money = 1500;
    owned = new ArrayList<>();
    inJail = false;
  }
  
  public void move(int steps) {
    position += steps;
    if (position > 39) {
      position = position - 40;
      changeMoney(200);
    }
    System.out.println("Player " + name + " at " + position + " Money: " + money);
  }
  
  public void render(GameContainer gc, Renderer r) {
    
  }
  
  public void changeMoney(int change) {
    System.out.println("Player " + name + " got " + change + " money");
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

  public ArrayList<Property> getOwned() {
    return owned;
  }
  
  public void addOwned(Property p) {
    owned.add(p);
    System.out.println(name + " bought " + p.getName());
  }

  public boolean inJail() {
    return inJail;
  }

  public void setInJail(boolean inJail) {
    this.inJail = inJail;
  }
}
