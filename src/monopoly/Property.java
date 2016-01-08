package monopoly;

import java.awt.Color;

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
    Color black = new Color(20, 20, 20);
    
    int side = (int) Math.floor(index/10);
    int offset = Math.floorMod(index, 10);
    
    switch (side) {
      case 0:
        r.drawRect((startX+bWidth-height)-width*offset, startY+bHeight-height, width, height, black);
        break;
      case 1:
        r.drawRect(startX, startY+bHeight-height-width*offset, height, width, black);
        break;
      case 2:
        r.drawRect(startX+(height-width)+width*offset, startY, width, height, black);
        break;
      case 3:
        r.drawRect(startX+bWidth-height, startY+(height-width)+width*offset, height, width, black);
        break;
    }
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
