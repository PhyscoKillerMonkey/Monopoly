package monopoly.spaces;

import monopoly.Player;
import monopoly.core.GameContainer;
import monopoly.popups.BuyPopup;

public class Station extends Space {
  
  private int price;
  private Player owner;
  
  public Station(String name) {
    super();
    this.name = name;
    switch(name) {
      case "King's Cross Station": index = 5; break;
      case "Marylebone Station": index = 15; break;
      case "Fenchurch St Station": index = 25; break;
      case "Liverpool Street Station": index = 35; break;
    }
    this.price = 200;
    this.owner = null;
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
    return owner.getNumStations() * 25;
  }
}
