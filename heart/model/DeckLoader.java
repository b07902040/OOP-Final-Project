package heart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import heart.model.minion.*;
import heart.model.spell.*;
public class DeckLoader implements Serializable {
    private static final long serialVersionUID = 1L;

    public ArrayList<Card> loadDecks() {
        ArrayList<ArrayList<Card>> decks = new  ArrayList<ArrayList<Card>>();
        //
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Intellect());
        cards.add(new PyroBlast());
        ArrayList<Card> x = this.pickCards(cards, 3);
        return x;
    }

    private ArrayList<Card> pickCards(ArrayList<Card> input, int num){
        ArrayList<Card> cards = new ArrayList<Card>();
        for(Card card : input)
            cards.add(card);
        Collections.shuffle(cards,new Random());
        for(int i = cards.size() - 1 ; i >= num;i--)
            cards.remove(i);
        return cards;
    }
}


