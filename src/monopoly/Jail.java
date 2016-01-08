package monopoly;

import java.awt.Color;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public class Jail extends Space {
  
  public Jail() {
    name = "jail";
    index = 10;
  }

  @Override
  public void action(GameContainer gc) {
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
