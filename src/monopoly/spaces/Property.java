package monopoly.spaces;

import monopoly.Player;
import monopoly.core.GameContainer;
import monopoly.popups.BuyPopup;

public class Property extends Space {
  
  private int price;
  private int houses;
  private int houseCost;
  private Player owner;
  private int[] rent;
  
  public Property(String name, int price, int houseCost, int[] rent, int index) {
    super();
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
      gc.getGame().getBoard().setStatus(owner.getName() + " owns " + name + " pay " + getRent() + " rent");
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
