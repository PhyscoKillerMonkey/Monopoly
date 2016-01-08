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
  public void action(GameContainer gc) {
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    Color black = new Color(20, 20, 20);
    r.drawRect(startX+bWidth-height, startY+bHeight-height, height, height, black);
  }

}
