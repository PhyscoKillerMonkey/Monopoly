package monopoly;

import java.awt.Color;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public class GotoJail extends Space {
  
  public GotoJail() {
    name = "gotoJail";
    index = 30;
  }

  @Override
  public boolean action(GameContainer gc, Player p) {
    p.setPosition(10);
    p.setInJail(true);
    return true;
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    Color black = new Color(20, 20, 20);
    r.drawRect(startX+bWidth-height, startY, height, height, black);
    
    for (int i = 0; i < gc.getGame().getBoard().numberPlayers(); i++) {
      if (gc.getGame().getBoard().getPlayer(i).getPosition() == index) {
        r.fillRect(startX+bWidth-height, startY, height, height, 
            new Color(200, 100, 200));
      }
    }
  }

}
