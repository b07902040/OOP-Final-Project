package heart.model;

public interface Spell extends Card{
    void takeEffect(Player user, Minion target);
}