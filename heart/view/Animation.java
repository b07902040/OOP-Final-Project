package heart.view;

public interface Animation extends Painter {
    boolean isExpired();

    void stop();

    void update();
}