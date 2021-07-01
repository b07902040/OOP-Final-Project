package heart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomDeckLoader extends DeckLoader {
    private static final int[] cardNumber = {1,2,3,2,2,5,5,1,2,2,3,2};
    
    @Override
    public  ArrayList<ArrayList<Card>> loadDecks() {
        ArrayList<ArrayList<Card>> decks = new  ArrayList<ArrayList<Card>>();
        decks.add(new ArrayList<Card>());
        decks.add(new ArrayList<Card>());
        ArrayList<Card> cards;
        for(int i = 0; i < DeckLoader.library.size(); i++){
            cards = this.pickCards(DeckLoader.library.get(i), 2 * cardNumber[i]);
            decks.get(0).addAll(cards.subList(0, cardNumber[i]));
            decks.get(1).addAll(cards.subList(cardNumber[i], 2 * cardNumber[i]));
        }
        Collections.shuffle(decks.get(0), new Random());
        Collections.shuffle(decks.get(1), new Random());
        this.balanceCost(decks.get(0));
        this.balanceCost(decks.get(1));
        return decks;
    }
    private void balanceCost(ArrayList<Card> input){
        for(int j = 0;j < shuffleTime; j++){
            for(int i = 0; i < shuffleCard; i++){
                if(input.get(i).getCost() < shuffleThresHold) continue;
                input.add(input.get(i));
                input.remove(i);
            }
        }
        Collections.shuffle(input.subList(shuffleCard, input.size()), new Random());
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


