package heart.model;

import java.util.ArrayList;

import heart.model.minion.DrBoom;
import heart.model.minion.Fordring;
import heart.model.minion.Goblin;
import heart.model.minion.LanternFish;
import heart.model.minion.LichKing;
import heart.model.minion.LootThief;
import heart.model.minion.ManaTideTotem;
import heart.model.minion.Ragnaros;
import heart.model.minion.VoidLord;
import heart.model.spell.DivineFavor;
import heart.model.spell.DivineHeal;
import heart.model.spell.FireBall;
import heart.model.spell.Infestation;
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
        return cards;
    }
}


