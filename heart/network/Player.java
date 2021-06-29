package heart.network;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private static final long serialVersionUID = 2L;
    private String name;
    private ArrayList<Card> list = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    
    public void addCard(Card add) {
        list.add(add);
    }

    public void showCard() {
        for ( int i = 0; i < list.size(); i++ ) {
            System.out.println(list.get(i).getName());
        }
    }

}