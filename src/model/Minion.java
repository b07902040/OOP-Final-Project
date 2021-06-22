package src.model;

public interface Minion(){
    Player getMaster();

    int getHP();

    int setHP();

    int getATK();

    int setATK();
    
    boolean isAlive();

    boolean canAttack();

    boolean canAttacked();

    void attackTarget(Minion target);
}