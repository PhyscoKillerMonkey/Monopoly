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

  protected final Color white = new Color(255, 255, 255);
  protected final Color black = new Color(20, 20, 20);
  protected final Color brown = new Color(121, 85, 72);
  protected final Color lBlue = new Color(41, 182, 246);
  protected final Color pink = new Color(236, 64, 122);
  protected final Color orange = new Color(255, 87, 34);
  protected final Color red = new Color(211, 47, 47);  
  protected final Color yellow = new Color(255, 235, 59);
  protected final Color green = new Color(76, 175, 80);
  protected final Color dBlue = new Color(63, 81, 181);
  
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
    BufferedImage img = new BufferedImage(width+1, height+1, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = (Graphics2D) img.getGraphics();
    g.setColor(white);
    g.fillRect(0, 0, width, height);
    g.setColor(black);
    g.drawRect(0, 0, width, height);

    int side = (int) Math.floor(index/10);
    int offset = Math.floorMod(index, 10);
    
    if (this instanceof Property) {
      if (index == 1 || index == 3) {
        g.setColor(brown);
      } else if (index == 6 || index == 8 || index == 9) {
        g.setColor(lBlue);
      } else if (index == 11 || index == 13 || index == 14) {
        g.setColor(pink);
      } else if (index == 16 || index == 18 || index == 19) {
        g.setColor(orange);
      } else if (index == 21 || index == 23 || index == 24) {
        g.setColor(red);
      } else if (index == 26 || index == 27 || index == 29) {
        g.setColor(yellow);
      } else if (index == 31 || index == 32 || index == 34) {
        g.setColor(green);
      } else if (index == 37 || index == 39) {
        g.setColor(dBlue);
      }
      g.fillRect(1, 1, width-1, 20);
      g.setColor(black);
      g.drawLine(0, 20, width, 20);
    }
    
    for (int i = 0; i < gc.getGame().getBoard().numberPlayers(); i++) {
      if (gc.getGame().getBoard().getPlayer(i).getPosition() == index) {
        g.setColor(black);
        g.fillOval(2, 22, width-4, width-4);
      }
    }
    
    switch (side) {
      case 0:
        r.drawImage(gc, startX + bWidth - height - width*offset, 
            startY + bHeight - height, 0, 0, 0, img);
        break;
      case 1:
        r.drawImage(gc, startX + height+1, startY + bHeight - height - width*offset, Math.PI/2, 0, 0, img);
        break;
      case 2:
        r.drawImage(gc, startX + height + width*(offset) + 1, startY + height + 1, Math.PI, 0, 0, img);
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
