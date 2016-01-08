package monopoly;

import java.awt.Color;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public class Property extends Space {
  
  private int price;
  private int houses;
  private Player owner;
  private int rent;
  
  public Property(String name, int price, int rent, int index) {
    this.name = name;
    this.price = price;
    this.index = index;
    this.owner = null;
    this.rent = rent;
  }

  @Override
  public boolean action(GameContainer gc, Player p) {
    if (owner == null) {
      System.out.println("Unowned, buy " + name + " for " + price);
      return false;
    } else {
      System.out.println(p.getName() + " owes " + owner.getName() + " " + rent + " in rent");
      p.changeMoney(-rent);
      owner.changeMoney(rent);
      return true;
    }
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    Color black = new Color(20, 20, 20);
    
    int side = (int) Math.floor(index/10);
    int offset = Math.floorMod(index, 10);
    
    int cX = 0, cY = 0, cW = 0, cH = 0;
    
    switch (side) {
      case 0:
        cX = startX + bWidth - height - width*offset;
        cY = startY + bHeight - height;
        cW = width; cH = height;
        break;
      case 1:
        cX = startX;
        cY = startY + bHeight - height - width*offset;
        cW = height; cH = width;
        break;
      case 2:
        cX = startX + height + width*(offset-1);
        cY = startY;
        cW = width; cH = height;
        break;
      case 3:
        cX = startX + bWidth - height;
        cY = startY + height + width * (offset-1);
        cW = height; cH = width;
        break;
    }
    r.drawRect(cX, cY, cW, cH, black);
    
    for (int i = 0; i < gc.getGame().getBoard().numberPlayers(); i++) {
      if (gc.getGame().getBoard().getPlayer(i).getPosition() == index) {
        r.fillRect(cX, cY, cW, cH, new Color(200, 100, 200));
      }
    }
  }

  public int getHouses() {
    return houses;
  }

  public void setHouses(int houses) {
    this.houses = houses;
  }

  public Player getOwner() {
    return owner;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }

  public int getPrice() {
    return price;
  }

}
