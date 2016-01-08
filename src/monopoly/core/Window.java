package monopoly.core;

import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window {
  
  private BufferedImage backBuffer;
  private JFrame frame;
  private Insets insets;
  private int width, height;
  private Graphics2D sg;

  public Window(GameContainer gc) {
    backBuffer = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
    
    frame = new JFrame(gc.getTitle());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    
    // Add insets to get the correct frame size
    frame.pack();
    insets = frame.getInsets();
    width = gc.getWidth();
    height = gc.getHeight();
    frame.setSize(insets.left + width + insets.right, 
        insets.top + height + insets.bottom);
    
    frame.setVisible(true);
    
    sg = (Graphics2D) frame.getGraphics();
  }
  
  public void update() {
    sg.drawImage(backBuffer, insets.left, insets.top, width, height, null);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
  
  public void updateSize(GameContainer gc) {
    backBuffer = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
    
    insets = frame.getInsets();
    width = gc.getWidth();
    height = gc.getHeight();
    frame.setSize(insets.left + width + insets.right, 
        insets.top + height + insets.bottom);
  }

  public BufferedImage getBuffer() {
    return backBuffer;
  }

  public JFrame getFrame() {
    return frame;
  }
}
