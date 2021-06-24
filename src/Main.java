import src.model.*;
import src.event.*;
import java.util.List;
import java.util.ArrayList;
import utils.*;

public class Main {
    public static void main(String[] args) {
      	EventManager eventManager=new EventManager();
      	Game game = new Game(eventManager);
      	Test test =new Test(eventManager,game);
		test.run();
		System.out.println("GGGGGGGGGG");
    }

	
}
