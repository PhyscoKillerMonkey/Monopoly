package monopoly.spaces;

import java.awt.Color;

import monopoly.Player;
import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public class Jail extends Space {
  
  public Jail() {
    name = "jail";
    index = 10;
  }

  @Override
  public void action(GameContainer gc, Player p) {
    if (p.inJail()) {
      System.out.println(p.getName() + " is in jail");
    } else {
      System.out.println(p.getName() + " is just passing");
      gc.getGame().getBoard().setStatus(p.getName() + " is just passing jail. Press E to end turn");
    }
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    Color black = new Color(20, 20, 20);
    r.drawRect(startX, startY+bHeight-height, height, height, black);
    
    for (int i = 0; i < gc.getGame().getBoard().numberPlayers(); i++) {
      if (gc.getGame().getBoard().getPlayer(i).getPosition() == index) {
        r.fillRect(startX, startY+bHeight-height, height, height, 
            new Color(200, 100, 200));
      }
    }
  }

}
