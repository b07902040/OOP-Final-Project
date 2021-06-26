// import src.model.*;
import src.event.*;
import src.client.view.View;
import src.client.controller.Controller;
import src.client.model.GameInfo;
import java.util.List;
import java.util.ArrayList;
import utils.*;

public class ViewTest {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        GameInfo game = new GameInfo(eventManager);
        Controller controller = new Controller(eventManager, game);
      	View view = new View(eventManager, game, controller);
        
		view.initialize();
    }

	
}
