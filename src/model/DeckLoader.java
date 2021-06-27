package src.model;
import java.util.List;
import java.util.ArrayList;
import src.model.minion.*;
import src.model.spell.*;
import src.constant.*;
public class DeckLoader{
    private ArrayList<ArrayList<Card>> decks;
    
    public ArrayList<Card> loadDeck0(){
        ArrayList<Card> deck = new ArrayList<Card>();
        for(int i = 0; i < 5; i++){
            deck.add(new DrBoom());
            deck.add(new TheCoin());
            deck.add(new SoulFire());
            deck.add(new HellFire());
            deck.add(new Intellect());
        }
        return deck;
    }
}

