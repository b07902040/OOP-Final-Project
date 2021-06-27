import java.io.Serializable;

public class Card implements Serializable {
    private static final long serialVersionUID = 3L;
    private String cardName;

    public Card(String name) {
        this.cardName = name;
    }

    public String getName() {
        return cardName;
    }

    public void setName(String name) {
        cardName = name;
    }
}