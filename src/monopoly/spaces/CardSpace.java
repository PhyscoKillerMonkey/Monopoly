package monopoly.spaces;

import monopoly.Player;
import monopoly.core.GameContainer;

public class CardSpace extends Space {
  
  public CardSpace(String name, int index) {
    super();
    this.name = name;
    this.index = index;
  }

  @Override
  public void action(GameContainer gc, Player p) {
    System.out.println(p.getName() + " landed on " + name + " at " + index);
  }
}
