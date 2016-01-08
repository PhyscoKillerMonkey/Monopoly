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
  }

}
