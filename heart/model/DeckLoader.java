package heart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import heart.model.minion.*;
import heart.model.spell.*;
public class DeckLoader implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<ArrayList<Card>> library;
    private static final int[] cardNumber = {1,1,2,2,2,6,6,1,1,2,3,3};
    private static final int shuffleThresHold = 4;
    private static final int shuffleCard = 4;
    private static final int shuffleTime = 3;
    public DeckLoader(){
        library = new ArrayList<ArrayList<Card>>();
        this.library.add(minionType0());
        this.library.add(minionType1());
        this.library.add(minionType2());
        this.library.add(minionType3());
        this.library.add(minionType4());
        this.library.add(minionType5());
        this.library.add(minionType6());
        this.library.add(spellType0());
        this.library.add(spellType1());
        this.library.add(spellType2());
        this.library.add(spellType3());
        this.library.add(spellType4());
    }
    public  ArrayList<ArrayList<Card>> loadDecks() {
        ArrayList<ArrayList<Card>> decks = new  ArrayList<ArrayList<Card>>();
        /*decks.add(new ArrayList<Card>());
        decks.add(new ArrayList<Card>());
        ArrayList<Card> cards;
        for(int i = 0; i < this.library.size(); i++){
            cards = this.pickCards(library.get(i), 2 * cardNumber[i]);
            decks.get(0).addAll(cards.subList(0, cardNumber[i]));
            decks.get(1).addAll(cards.subList(cardNumber[i], 2 * cardNumber[i]));
        }
        Collections.shuffle(decks.get(0), new Random());
        Collections.shuffle(decks.get(1), new Random());
        this.balanceCost(decks.get(0));
        this.balanceCost(decks.get(1));*/
        decks.add(this.customDeck());
        decks.add(this.customDeck());
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
    private static ArrayList<Card> customDeck(){
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new AlAkir());
        cards.add(new FireBall());
        cards.add(new PatientAssassin());
        cards.add(new Tazdingo());
        cards.add(new Doom());
        cards.add(new UldmanKeeper());
        
        return cards;
    }
    private static ArrayList<Card> minionType0(){
        ArrayList<Card> cards = new ArrayList<Card>();
        //10
        cards.add(new VarianWrynn());
        cards.add(new ForgottenKing());
        cards.add(new DeathWing());
        return cards;
    }
    private static ArrayList<Card> minionType1(){
        ArrayList<Card> cards = new ArrayList<Card>();
        //9
        cards.add(new AlexStrasza());
        cards.add(new VoidLord());
        cards.add(new KingKrush());
        cards.add(new Onyxia());
        return cards;
    }
    private static ArrayList<Card> minionType2(){
        ArrayList<Card> cards = new ArrayList<Card>();
        //8
        cards.add(new Fordring());
        cards.add(new LichKing());
        cards.add(new AlAkir());
        cards.add(new LightRagnaros());
        cards.add(new Ragnaros());
        //7
        cards.add(new Antonidas());
        cards.add(new BaronGeddon());
        cards.add(new DrBoom());
        return cards;
    }
    private static ArrayList<Card> minionType3(){
        ArrayList<Card> cards = new ArrayList<Card>();
        //6
        cards.add(new Hogger());
        cards.add(new RenoJackson());
        cards.add(new SunKeeper());
        cards.add(new Maexxna());
        cards.add(new BlackKnight());
        cards.add(new Thaurissan());
        cards.add(new FriedChicken());
        return cards;
    }
    private static ArrayList<Card> minionType4(){
        ArrayList<Card> cards = new ArrayList<Card>();
        //5
        cards.add(new DoomGuard());
        cards.add(new Sludge());
        cards.add(new DarkHealer());
        cards.add(new LionKing());
        cards.add(new DreadLord());
        cards.add(new VileSpine());
        cards.add(new TempleEnforcer()); 
        return cards;
    }
    private static ArrayList<Card> minionType5(){
        ArrayList<Card> cards = new ArrayList<Card>();
        //4
        cards.add(new BigGameHunter());
        cards.add(new TombPillager());
        cards.add(new TurtleElder());
        cards.add(new EliteWarrior());
        cards.add(new LordBarov());
        cards.add(new Tazdingo());
        cards.add(new ZombieHorse());
        cards.add(new TwilightDrake());
        cards.add(new UldmanKeeper());
        //3
        cards.add(new DancingSwords());
        cards.add(new SunCleric());
        cards.add(new KingMukla());
        cards.add(new Panther());
        cards.add(new LanternFish());
        cards.add(new HotSpring());
        cards.add(new RageGhoul());
        cards.add(new ManaTideTotem());
        cards.add(new HarvestGolem());
        cards.add(new PeaceKeeper());
        cards.add(new VanCleef());
        return cards;
    }
    private ArrayList<Card> minionType6(){
        ArrayList<Card> cards = new ArrayList<Card>();
        //2
        cards.add(new PoisonSnail());
        cards.add(new WhirlingZap());
        cards.add(new AnnoyRobot());
        cards.add(new PatientAssassin());
        cards.add(new UnstableGhoul());
        cards.add(new WolfGrandma());
        cards.add(new LootThief());
        cards.add(new CruelMaster());
        //1
        cards.add(new Goblin());
        cards.add(new FlameImp());
        cards.add(new Patches());
        cards.add(new VoidWalker());
        cards.add(new DaoKe());
        cards.add(new Pharmacist());
        return cards;
    }
    private static ArrayList<Card> spellType0(){
        ArrayList<Card> cards = new ArrayList<Card>();
        //10
        cards.add(new Doom());
        cards.add(new Infestation());
        cards.add(new PyroBlast());
        return cards;
    }
    private static ArrayList<Card> spellType1(){
        ArrayList<Card> cards = new ArrayList<Card>();
        //8
        cards.add(new BlackHole());
        cards.add(new DivineHeal());
        cards.add(new BigDragon());
        //7
        cards.add(new FlameStrike());
        return cards;
    }
    private static ArrayList<Card> spellType2(){
        ArrayList<Card> cards = new ArrayList<Card>();
        //6
        cards.add(new Plague());
        cards.add(new LightBomb());
        //5
        cards.add(new HolyFire());
        cards.add(new PenguinParty());
        cards.add(new HolyNova());
        cards.add(new Brawl());
        return cards;
    }
    private static ArrayList<Card> spellType3(){
        ArrayList<Card> cards = new ArrayList<Card>();
        //4
        cards.add(new Blessing());
        cards.add(new FireBall());
        cards.add(new HellFire());
        cards.add(new DoubleATK());
        //3
        cards.add(new KnivesFan());
        cards.add(new ShadowWord());
        cards.add(new UnleashHounds());
        cards.add(new DivineFavor());
        cards.add(new Equality());
        cards.add(new Intellect());
        return cards;
    }
    private static ArrayList<Card> spellType4(){
        ArrayList<Card> cards = new ArrayList<Card>();
        //2
        cards.add(new HolyLight());
        cards.add(new DivineSpirit());
        cards.add(new WildGrowth());
        cards.add(new WildPower());
        cards.add(new MindBlast());
        //1
        cards.add(new Naturalize());
        cards.add(new Execute());
        cards.add(new PowerShield());
        cards.add(new InnerFire());
        cards.add(new Mirror());
        cards.add(new SoulFire());
        return cards;
    }
}


