package src.model;
import java.util.List;
import java.util.ArrayList;
import src.model.minion.*;
import src.model.spell.*;
import src.constant.*;
import java.io.Serializable;
public class DeckLoader implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<ArrayList<Card>> decks;
    
    public ArrayList<Card> loadDeck0(){
        ArrayList<Card> deck = new ArrayList<Card>();
        for(int i = 0; i < 5; i++){
            deck.add(new DrBoom());
            deck.add(new HolyNova());
            deck.add(new PenguinParty());

        }
        return deck;
    }
}

