package heart.model.minion;

public class Guldan extends Hero {

    private static String name = "Guldan";
    private static String description = "Embrace the shadow.";

    public Guldan() {
        super(Guldan.name, Guldan.description);
    }
    public void hhp(int hhp){
        this.HP = hhp;
    }
}