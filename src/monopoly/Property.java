package monopoly;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public class Property extends Space {
  
  private int price;
  private int houses;
  private String owner;
  
  public Property(String name, int price, int index) {
    this.name = name;
    this.price = price;
    this.index = index;
    this.owner = "bank";
  }

  @Override
  public void action(GameContainer gc) {
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
  }

  public int getHouses() {
    return houses;
  }

  public void setHouses(int houses) {
    this.houses = houses;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner.toLowerCase();
  }

  public int getPrice() {
    return price;
  }

}
