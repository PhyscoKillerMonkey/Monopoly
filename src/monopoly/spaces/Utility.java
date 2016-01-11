package monopoly.spaces;

import monopoly.Player;
import monopoly.core.GameContainer;

public class Utility extends Space {
  
  public Utility(String name) {
    super();
    this.name = name;
    switch (name) {
      case "Electric": index = 12; break;
      case "Waterworks": index = 28; break;
    }
  }

  @Override
  public void action(GameContainer gc, Player p) {
    System.out.println(p.getName() + " landed on " + name + " at " + index);
  }
}
