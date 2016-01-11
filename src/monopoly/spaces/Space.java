package monopoly.spaces;

import java.awt.Color;

import monopoly.Player;
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
  
  public abstract void action(GameContainer gc, Player p);

  public void render(GameContainer gc, Renderer r) {
    Color black = new Color(20, 20, 20);
    
    int side = (int) Math.floor(index/10);
    int offset = Math.floorMod(index, 10);
    
    int cX = 0, cY = 0, cW = 0, cH = 0;
    
    switch (side) {
      case 0:
        cX = startX + bWidth - height - width*offset;
        cY = startY + bHeight - height;
        cW = width; cH = height;
        break;
      case 1:
        cX = startX;
        cY = startY + bHeight - height - width*offset;
        cW = height; cH = width;
        break;
      case 2:
        cX = startX + height + width*(offset-1);
        cY = startY;
        cW = width; cH = height;
        break;
      case 3:
        cX = startX + bWidth - height;
        cY = startY + height + width * (offset-1);
        cW = height; cH = width;
        break;
    }
    r.drawRect(cX, cY, cW, cH, black);
    
    for (int i = 0; i < gc.getGame().getBoard().numberPlayers(); i++) {
      if (gc.getGame().getBoard().getPlayer(i).getPosition() == index) {
        r.fillRect(cX, cY, cW, cH, new Color(200, 100, 200));
      }
    }
  }

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
