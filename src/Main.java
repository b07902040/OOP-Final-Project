import src.model.*;
import src.event.*;
import java.util.List;
import java.util.ArrayList;
import src.utils.*;

public class Main {
    public static void main(String[] args) {
      	EventManager eventManager = new EventManager();
      	Game game = new Game(eventManager);
      	Test test =new Test(eventManager,game);
		test.run();
		System.out.println("GGGGGGGGGG");
		//2 connection
		//int id = Math.random(((int)(Math.random()+1)));
		//send event id 0 1 (random)
    }
}
