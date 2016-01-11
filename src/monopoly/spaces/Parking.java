package monopoly.spaces;

import java.awt.Color;

import monopoly.Player;
import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public class Parking extends Space {
  
  public Parking() {
    super();
    name = "parking";
    index = 20;
  }

  @Override
  public void action(GameContainer gc, Player p) {
    gc.getGame().getBoard().setStatus("Free parking! Press E to end turn");
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    Color black = new Color(20, 20, 20);
    r.drawRect(startX, startY, height, height, black);
    
    for (int i = 0; i < gc.getGame().getBoard().numberPlayers(); i++) {
      if (gc.getGame().getBoard().getPlayer(i).getPosition() == index) {
        r.fillRect(startX, startY, height, height, 
            new Color(200, 100, 200));
      }
    }
  }

}
