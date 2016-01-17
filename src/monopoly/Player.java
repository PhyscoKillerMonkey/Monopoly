package monopoly;

import java.util.ArrayList;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;
import monopoly.spaces.Property;
import monopoly.spaces.Station;
import monopoly.spaces.Utility;

public class Player {

  private String name;
  private int position;
  private int money;
  private ArrayList<Property> properties;
  private ArrayList<Station> stations;
  private ArrayList<Utility> utilities;
  private boolean inJail;
  
  public Player(String name) {
    this.name = name;
    this.position = 0;
    this.money = 1500;
    properties = new ArrayList<>();
    stations = new ArrayList<>();
    utilities = new ArrayList<>();
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

  public ArrayList<Property> getProperty() {
    return properties;
  }
  
  public void addProperty(Property p) {
    properties.add(p);
    System.out.println(name + " bought " + p.getName());
  }

  public ArrayList<Station> getStations() {
    return stations;
  }
  
  public int getNumStations() {
    return stations.size();
  }

  public void addStation(Station s) {
    stations.add(s);
    System.out.println(name + " bought " + s.getName());
  }
  
  public ArrayList<Utility> getUtilities() {
    return utilities;
  }

  public int getNumUtilities() {
    return utilities.size();
  }
  
  public void addUtility(Utility u) {
    utilities.add(u);
    System.out.println(name + " bought " + u.getName());
  }
  
  public boolean inJail() {
    return inJail;
  }

  public void setInJail(boolean inJail) {
    this.inJail = inJail;
  }
}
