package monopoly.spaces;

import java.util.Timer;
import java.util.TimerTask;

import monopoly.Player;
import monopoly.core.GameContainer;
import monopoly.popups.BuyPopup;

public class Utility extends Space {
  
  private Player owner;
  private int price;
  
  public Utility(String name) {
    super();
    this.name = name;
    this.price = 150;
    switch (name) {
      case "Electric": index = 12; break;
      case "Waterworks": index = 28; break;
    }
  }

  @Override
  public void action(GameContainer gc, Player p) {
    if (owner == null) {
      Space prop = this;
      // Wait before pushing the popop (so the player can move there) 
      Timer t = new Timer();
      t.schedule(new TimerTask() {
        @Override
        public void run() {
          gc.getGame().push(new BuyPopup(gc, prop, p));
          // Must cancel the thread else it will not exit
          t.cancel();
        }
      }, 10);
    } else {
      int rent = getRent(gc);
      p.changeMoney(-rent);
      owner.changeMoney(rent);
      gc.getGame().getBoard().setStatus(owner.getName() + " owns " + name + " pay " + rent + " rent");
    }  
  }
  
  public int getRent(GameContainer gc) {
    int rent = 0;
    switch(owner.getNumUtilities()) {
      case 1:
        rent = gc.getGame().getBoard().getDice() * 4;
        break;
      case 2:
        rent = gc.getGame().getBoard().getDice() * 10;
        break;
    }
    return rent;
  }

  public int getPrice() {
    return price;
  }
}
