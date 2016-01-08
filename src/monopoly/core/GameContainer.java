package monopoly.core;

import java.io.PrintStream;

import monopoly.GameManager;

public class GameContainer implements Runnable {
  
  private Thread thread;
  private Window window;
  private Renderer renderer;
  private Input input;
  private GameManager game;
  
  private boolean isRunning;
  private boolean debug;
  
  // Window variables
  private int width, height;
  private String title;
  
  public GameContainer(GameManager game) {
    this.game = game;
    debug = true;
    
    // Initialise the window variables
    title = "Game";
    width = 640;
    height = 480;
    
    // Custom System.out.println
    PrintStream stream = new PrintStream(System.out) {
      public void println(String s) {
        if (debug) {
          String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
          String className = fullClassName.substring(fullClassName.lastIndexOf(".")+1);
          String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
          int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
          super.println("(" + className + "-" + methodName + " @ " + lineNumber + "): " + s); 
        }
      }
    };
    System.setOut(stream);
  }
  
  public void start() {
    if (!isRunning) {
      window = new Window(this);
      renderer = new Renderer(this);
      input = new Input(this);
      
      thread = new Thread(this);
      thread.run();
    }
  }
  
  @Override
  public void run() {
    isRunning = true;
    
    // Times are in seconds
    double frameLength = 1.0 / 60.0;
    
    double firstTime = 0;
    double lastTime = System.nanoTime() / 1000000000.0;
    double passedTime = 0;
    double timeLeft = 0;
    
    game.init(this);
    
    while(isRunning) {
      boolean shouldRender = false;
      
      firstTime = System.nanoTime() / 1000000000.0;
      passedTime = firstTime - lastTime;
      lastTime = firstTime;
      
      timeLeft += passedTime;
      
      while (timeLeft > frameLength) {
        game.update(this);
        input.update();
        
        timeLeft -= frameLength;
        shouldRender = true;
      }
      
      if (shouldRender) {
        game.render(this, renderer);
        window.update();
      } else {
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
    renderer.updateSize(this);
    window.updateSize(this);
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
    window.updateSize(this);
    renderer.updateSize(this);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isDebug() {
    return debug;
  }

  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  public Window getWindow() {
    return window;
  }

  public Renderer getRenderer() {
    return renderer;
  }

  public Input getInput() {
    return input;
  }

  public GameManager getGame() {
    return game;
  }

}
