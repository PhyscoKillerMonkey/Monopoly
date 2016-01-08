package monopoly;

import java.awt.Color;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public class Go extends Space {
  
  public Go() {
    name = "go";
    index = 0;
  }

  @Override
  public boolean action(GameContainer gc, Player p) {
    return true;
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    Color black = new Color(20, 20, 20);
    r.drawRect(startX+bWidth-height, startY+bHeight-height, height, height, black);
    
    for (int i = 0; i < gc.getGame().getBoard().numberPlayers(); i++) {
      if (gc.getGame().getBoard().getPlayer(i).getPosition() == index) {
        r.fillRect(startX+bWidth-height, startY+bHeight-height, height, height, 
            new Color(200, 100, 200));
      }
    }
  }

}
