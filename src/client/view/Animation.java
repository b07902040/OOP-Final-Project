package src.client.view;

public interface Animation extends Painter{
    boolean isExpired();
    void stop();
    void update();
}