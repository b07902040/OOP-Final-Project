// import src.model.*;
import src.event.*;
import src.client.view.View;
import src.client.controller.Controller;
import src.client.model.GameInfo;
import java.util.List;
import java.util.ArrayList;
import java.lang.Thread;
import utils.*;

import src.model.minion.*;
import src.model.Card;

public class ViewTest {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        GameInfo game = new GameInfo(eventManager);
        eventManager.post(new EventClientInitalize(0));
        for (int i = 0; i < 0; i++)
            eventManager.post(new EventTurnStart());
        Controller controller = new Controller(eventManager, game);
      	View view = new View(eventManager, game, controller);
        view.initialize();
        while(true){
            eventManager.post(new EventEveryTick());
            try {
                Thread.sleep(50);
            } catch (Exception e) {};
        }
    }
}
