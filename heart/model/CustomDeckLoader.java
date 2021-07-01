package heart.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import heart.model.minion.*;
import heart.model.spell.*;

public class CustomDeckLoader extends DeckLoader {
    private static final long serialVersionUID = 1L;
    
    @Override
    public  ArrayList<ArrayList<Card>> loadDecks() {
        ArrayList<ArrayList<Card>> decks = new  ArrayList<ArrayList<Card>>();
        decks.add(this.customDeck());
        decks.add(this.customDeck());
        Collections.shuffle(decks.get(0), new Random());
        Collections.shuffle(decks.get(1), new Random());
        this.balanceCost(decks.get(0));
        this.balanceCost(decks.get(1));
        return decks;
    }
    private ArrayList<Card> customDeck(){
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Goblin());
        cards.add(new Mirror());
        cards.add(new Patches());
        cards.add(new WhirlingZap());
        cards.add(new DrBoom());
        cards.add(new Ragnaros());
        cards.add(new AlAkir());
        cards.add(new LichKing());
        cards.add(new UnstableGhoul());
        cards.add(new FriedChicken());
        cards.add(new TurtleElder());
        cards.add(new HotSpring());
        cards.add(new KingMukla());
        cards.add(new FlameImp());
        cards.add(new PeaceKeeper());
        cards.add(new WolfGrandma());
        cards.add(new HarvestGolem());
        cards.add(new VoidWalker());
        cards.add(new VoidLord());
        cards.add(new Tazdingo());
        cards.add(new PyroBlast());
        cards.add(new FireBall());
        cards.add(new Infestation());
        cards.add(new PenguinParty());
        cards.add(new Intellect());
        cards.add(new BlackHole());
        cards.add(new Tazdingo());
        cards.add(new WildPower());
        cards.add(new Pharmacist());
        cards.add(new LootThief());
        return cards;
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


