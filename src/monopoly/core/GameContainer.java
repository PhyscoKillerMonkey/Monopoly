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
  
  public GameContainer(GameManager game) {
    this.game = game;
    debug = true;
    
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
      window = new Window();
      renderer = new Renderer();
      input = new Input();
      
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
    
    // game.init(this);
    
    while(isRunning) {
      boolean shouldRender = false;
      
      firstTime = System.nanoTime() / 1000000000.0;
      passedTime = firstTime - lastTime;
      lastTime = firstTime;
      
      timeLeft += passedTime;
      
      while (timeLeft > frameLength) {
        // game.update();
        // input.update();
        
        timeLeft -= frameLength;
        shouldRender = true;
      }
      
      if (shouldRender) {
        // game.render();
        // window.update();
      } else {
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

}
