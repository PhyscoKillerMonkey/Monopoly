package monopoly.spaces;

import java.awt.Color;

import monopoly.Player;
import monopoly.core.GameContainer;
import monopoly.core.Renderer;
import monopoly.popups.BuyPopup;

public class Property extends Space {
  
  private int price;
  private int houses;
  private int houseCost;
  private Player owner;
  private int[] rent;
  
  public Property(String name, int price, int houseCost, int[] rent, int index) {
    this.name = name;
    this.price = price;
    this.houseCost = houseCost;
    this.houses = 0;
    this.index = index;
    this.owner = null;
    this.rent = rent;
  }

  @Override
  public void action(GameContainer gc, Player p) {
    if (owner == null) {
      gc.getGame().push(new BuyPopup(gc, this, p));
    } else {
      p.changeMoney(-getRent());
      owner.changeMoney(getRent());
      gc.getGame().getBoard().setStatus(owner.getName() + " owns " + name + " pay " + rent + " rent");
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
  
  public void addHouse(int num) {
    houses += num;
    owner.changeMoney(-houseCost * num);
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

  public int getRent() {
    return rent[houses];
  }
}
