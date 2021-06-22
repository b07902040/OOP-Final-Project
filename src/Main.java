import src.model.*;
import src.event.*;

public class Main {
    public static void main(String[] args) {
      EventManager eventManager=new EventManager();
      new Game(eventManager);
      System.out.println("running");
    }
}
