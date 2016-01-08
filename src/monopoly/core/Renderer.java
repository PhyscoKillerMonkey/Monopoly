package monopoly.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Renderer {
  
  private int width, height;
  private BufferedImage backBuffer;
  private Graphics2D g;

  public Renderer(GameContainer gc) {
    width = gc.getWidth();
    height = gc.getHeight();
    backBuffer = gc.getWindow().getBuffer();
    g = (Graphics2D) backBuffer.getGraphics();

    RenderingHints rh = new RenderingHints(null);
    rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHints(rh);
  }
  
  public void drawRect(double x, double y, double w, double h, Color c) {
    g.setColor(c);
    g.draw(new Rectangle2D.Double(x, y, w, h));
  }
  
  public void fillRect(double x, double y, double w, double h, Color c) {
    g.setColor(c);
    g.fill(new Rectangle2D.Double(x, y, w, h));
  }
  
  public void drawEllipse(GameContainer gc, double x, double y, double w, double h, Color c) {
    g.setColor(c);
    g.draw(new Ellipse2D.Double(x, y, width, height));
  }
  
  public void drawCross(GameContainer gc, double x, double y, double w, double h, Color c) {
    g.setColor(c);
    g.draw(new Line2D.Double(x, y, x+w, y+h));
    g.draw(new Line2D.Double(x, y+h, x+w, y));
  }
  
  public void drawString(String string, int x, int y, Font f, Color c) {
    int ascent = g.getFontMetrics(f).getAscent();
    g.setFont(f);
    g.setColor(c);
    g.drawString(string, x, ascent+y);
  }
  
  public void drawStringCentered(String string, int x, int y, Font f, Color c) {
    int w = g.getFontMetrics(f).stringWidth(string);
    int hAscent = g.getFontMetrics(f).getAscent() / 2;
    drawString(string, x - w/2, y - hAscent, f, c);
  }
  
  public void clear() {
    g.clearRect(0, 0, width, height);
  }
  
 public void updateSize(GameContainer gc) {
    width = gc.getWidth();
    height = gc.getHeight();
    backBuffer = gc.getWindow().getBuffer();
    g = (Graphics2D) backBuffer.getGraphics();
    RenderingHints rh = new RenderingHints(null);
    rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHints(rh);
  }
}
