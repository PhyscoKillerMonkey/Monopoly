package monopoly;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public abstract class Space {

  protected String name;
  protected int index;
  
  public Space() {
    name = "NoName";
    index = -1;
  }
  
  public abstract void action(GameContainer gc);
  public abstract void render(GameContainer gc, Renderer r);

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    if (index >= 0 && index <= 39){
      this.index = index;
    } else {
      try {
        throw(new Throwable("Space index out of bounds"));
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
  }

}
