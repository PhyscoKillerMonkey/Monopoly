package monopoly.popups;

import java.awt.Color;
import java.awt.event.KeyEvent;

import monopoly.Player;
import monopoly.core.GameContainer;
import monopoly.core.Renderer;
import monopoly.spaces.Property;

public class BuyPopup extends Popup {
  
  private Player p;
  private Property prop;
  
  public BuyPopup(GameContainer gc, Property prop, Player p) {
    super(gc, 100, 100, 400, 600);
    this.prop = prop;
    this.p = p;
  }

  public void update(GameContainer gc) {
    if (gc.getInput().isKeyPressed(KeyEvent.VK_Y)) {
      p.changeMoney(-prop.getPrice());
      p.addOwned(prop);
      prop.setOwner(p);
      close(gc);
    } 
    if (gc.getInput().isKeyPressed(KeyEvent.VK_N)) {
      System.out.println("Auction");
      close(gc);
    }
  }
  
  public void render(GameContainer gc, Renderer r) {
    //r.fillRect(100, 100, 400, 600, new Color(255, 255, 255));
  }
}
