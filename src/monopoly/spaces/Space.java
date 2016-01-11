package monopoly.spaces;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
    Color white = new Color(255, 255, 255);
    Color black = new Color(20, 20, 20);
    
    BufferedImage img = new BufferedImage(width+1, height+1, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = (Graphics2D) img.getGraphics();
    g.setColor(white);
    g.fillRect(0, 0, width, height);
    g.setColor(black);
    g.drawRect(0, 0, width, height);
    
    for (int i = 0; i < gc.getGame().getBoard().numberPlayers(); i++) {
      if (gc.getGame().getBoard().getPlayer(i).getPosition() == index) {
        g.setColor(new Color(200, 100, 200));
        g.fillRect(1, 1, width-1, height-1);
      }
    }
    
    int side = (int) Math.floor(index/10);
    int offset = Math.floorMod(index, 10);
    
    switch (side) {
      case 0:
        r.drawImage(gc, startX + bWidth - height - width*offset, 
            startY + bHeight - height, 0, 0, 0, img);
        break;
      case 1:
        r.drawImage(gc, startX + height+1, startY + bHeight - height - width*offset, Math.PI/2, 0, 0, img);
        break;
      case 2:
        r.drawImage(gc, startX + height + width*(offset-1), startY, 0, 0, 0, img);
        break;
      case 3:
        r.drawImage(gc, startX + bWidth - height, startY + height + width * (offset) + 1, -Math.PI/2, 0, 0, img);
        break;
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
