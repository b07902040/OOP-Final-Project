package hearthclone.model;

public interface Spell extends Card{
    void takeEffect(Player user, Minion target);
}