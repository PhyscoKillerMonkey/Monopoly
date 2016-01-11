package monopoly.spaces;

import monopoly.Player;
import monopoly.core.GameContainer;
import monopoly.core.Renderer;
import monopoly.popups.BuyPopup;

public class Station extends Space {
  
  private int price;
  private int rent;
  private Player owner;
  
  public Station(String name) {
    this.name = name;
    this.price = 200;
    this.rent = 25;
    this.owner = null;
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
    return rent;
  }
}
