package monopoly;

import monopoly.core.GameContainer;
import monopoly.spaces.Space;

public class Tax extends Space {
  
  public Tax(String name) {
    super();
    this.name = name;
    switch (name) {
      case "Income Tax": index = 4; break;
      case "Super Tax": index = 38; break;
    }
  }

  @Override
  public void action(GameContainer gc, Player p) {
  }
}
