package monopoly.popups;

import java.awt.Color;
import java.awt.event.KeyEvent;

import monopoly.Player;
import monopoly.core.GameContainer;
import monopoly.core.Renderer;
import monopoly.spaces.Property;
import monopoly.spaces.Space;
import monopoly.spaces.Station;
import monopoly.spaces.Utility;

public class BuyPopup extends Popup {
  
  private Player p;
  private Space prop;
  
  // Information on the property
  private int price;
  private int[] rents;
  private int houseCost;
  
  public BuyPopup(GameContainer gc, Space prop, Player p) {
    super(gc, 100, 100, 400, 600);
    this.prop = prop;
    this.p = p;
    
    if (prop instanceof Property) {
      price = ((Property) prop).getPrice();
      rents = ((Property) prop).getAllRents();
      houseCost = ((Property) prop).getHouseCost();
    } else if (prop instanceof Station) {
      price = ((Station) prop).getPrice();
    } else if (prop instanceof Utility) {
      price = ((Utility) prop).getPrice();
    }
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
    r.fillRect(140, 140, 410, 410, new Color(200, 200, 200));
    r.drawStringCentered(prop.getName(), gc.getWidth()/2, 140+50, font, black);
    r.drawStringCentered("Price: " + price, gc.getWidth()/2, 140+80, font, black);
    if (prop instanceof Property) {
      r.drawStringCentered("Rent: £" + rents[0], gc.getWidth()/2, 140+120, font, black);
      r.drawStringCentered("With 1 house: £" + rents[1], gc.getWidth()/2, 140+145, font, black);
      r.drawStringCentered("With 2 houses: £" + rents[2], gc.getWidth()/2, 140+170, font, black);
      r.drawStringCentered("With 3 houses: £" + rents[3], gc.getWidth()/2, 140+195, font, black);
      r.drawStringCentered("With 4 houses: £" + rents[4], gc.getWidth()/2, 140+220, font, black);
      r.drawStringCentered("With hotel: £" + rents[5], gc.getWidth()/2, 140+245, font, black);
      r.drawStringCentered("Houses cost: £" + houseCost, gc.getWidth()/2, 140+270, font, black);
    } else if (prop instanceof Station) {
      r.drawStringCentered("Rent: £25", gc.getWidth()/2, 140+140, font, black);
      r.drawStringCentered("Rent if 2 owned: £50", gc.getWidth()/2, 140+170, font, black);
      r.drawStringCentered("Rent if 3 owned: £100", gc.getWidth()/2, 140+200, font, black);
      r.drawStringCentered("Rent if 4 owned: £200", gc.getWidth()/2, 140+230, font, black);
    } else if (prop instanceof Utility) {
      r.drawStringCentered("If ONE Utility is owned rent is 4x the dice" , gc.getWidth()/2, 140+170, font, black);
      r.drawStringCentered("If BOTH Utilities are owned rent is 10x the dice" , gc.getWidth()/2, 140+200, font, black);
    }
    r.drawStringCentered("Buy? Press Y or N", gc.getWidth()/2, 140+330, font, black);
  }
}
