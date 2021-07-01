import heart.controller.Controller;
import heart.event.EventClientInitalize;
import heart.event.LocalEventManager;
import heart.model.Game;
import heart.model.GameInfo;
import heart.view.View;

public class LocalMain {
  public static void main(String[] args) {
    LocalEventManager eventManager = new LocalEventManager();
    Game model = new Game(eventManager);
    GameInfo game0 = new GameInfo(eventManager);
    GameInfo game1 = new GameInfo(eventManager);
    Controller controller0 = new Controller(eventManager, game0);
    Controller controller1 = new Controller(eventManager, game1);
    View view0 = new View(eventManager, game0, controller0);
    View view1 = new View(eventManager, game1, controller1);
    view0.notify(new EventClientInitalize(0));
    view1.notify(new EventClientInitalize(1));
    controller0.notify(new EventClientInitalize(0));
    controller1.notify(new EventClientInitalize(1));
    game0.notify(new EventClientInitalize(0));
    game1.notify(new EventClientInitalize(1));
    model.run();
  }
}
