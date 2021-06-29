package heart.model.minion;

//Complete
public class Hyena extends AbstractMinion {

    private static String name = "Hyena";
    private static String description = "";
    private static int baseCost = 2;
    private static int baseATK = 2;
    private static int baseHP = 2;

    public Hyena() {
        super(Hyena.name, Hyena.description, Hyena.baseCost, Hyena.baseHP, Hyena.baseATK);
    }

}