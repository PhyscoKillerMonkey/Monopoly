package monopoly.popups;

import java.awt.event.KeyEvent;

import monopoly.Player;
import monopoly.core.GameContainer;
import monopoly.core.Renderer;
import monopoly.spaces.Property;
import monopoly.spaces.Station;
import monopoly.spaces.Space;

public class BuyPopup extends Popup {
  
  private Player p;
  private Space prop;
  
  public BuyPopup(GameContainer gc, Space prop, Player p) {
    super(gc, 100, 100, 400, 600);
    this.prop = prop;
    this.p = p;
  }

  public void update(GameContainer gc) {
    if (gc.getInput().isKeyPressed(KeyEvent.VK_Y)) {
      if (prop instanceof Property) {
        p.changeMoney(-((Property) prop).getPrice());
        p.addProperty((Property) prop);
        ((Property) prop).setOwner(p);
      } else if (prop instanceof Station) {
        p.changeMoney(-((Station) prop).getPrice());
        p.addStation((Station) prop);
        ((Station) prop).setOwner(p);
      }
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
