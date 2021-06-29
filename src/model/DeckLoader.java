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
        deck.add(new AlexStrasza());            
        deck.add(new Onyxia());
        deck.add(new LordBarov());    
        deck.add(new LordBarov());    
        deck.add(new DrBoom());            
        deck.add(new PenguinParty());
        deck.add(new AlAkir());
        deck.add(new Antonidas());
        deck.add(new BlackKnight());
        deck.add(new DeathWing());
        deck.add(new DoomGuard());            
        deck.add(new Fordring());
        deck.add(new FriedChicken());
        deck.add(new Hogger());
        deck.add(new KingKrush());
        deck.add(new KingMukla());
        deck.add(new LichKing());
        deck.add(new LightRagnaros());
        deck.add(new Ragnaros());
        deck.add(new Patches());
        deck.add(new RenoJackson());
        deck.add(new Tazdingo());
        deck.add(new Thaurissan());
        deck.add(new VanCleef());
        deck.add(new VarianWrynn());
        deck.add(new Penguin());
        deck.add(new Penguinn());
        deck.add(new PartyPenguin());
        return deck;
    }
}

