package monopoly;

import monopoly.core.GameContainer;
import monopoly.core.Renderer;

public abstract class Space {

  protected String name;
  protected int index;
  
  //Drawing variables
  protected int width, height;
  protected int bWidth, bHeight;
  protected int startX, startY;
  
  public Space() {
    name = "NoName";
    index = -1;
    
    // Drawing variables
    width = 48;
    height = 78;
    bWidth = bHeight = width*9 + height*2;
    startX = 50;
    startY = 50;
  }
  
  public abstract boolean action(GameContainer gc, Player p);
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
