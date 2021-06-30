package heart.model;

import java.util.ArrayList;

import heart.model.minion.LanternFish;
import heart.model.minion.ManaTideTotem;
import heart.model.spell.Intellect;

public class CustomDeckLoader extends DeckLoader {
    private static final long serialVersionUID = 1L;
    
    @Override
    public  ArrayList<ArrayList<Card>> loadDecks() {
        ArrayList<ArrayList<Card>> decks = new  ArrayList<ArrayList<Card>>();
        decks.add(this.customDeck());
        decks.add(this.customDeck());
        return decks;
    }
    private ArrayList<Card> customDeck(){
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new ManaTideTotem());
        cards.add(new ManaTideTotem());
        cards.add(new Intellect());
        cards.add(new Intellect());
        cards.add(new ManaTideTotem());
        cards.add(new ManaTideTotem());
        cards.add(new LanternFish());;
        return cards;
    }
}


