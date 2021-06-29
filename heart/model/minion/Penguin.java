package heart.model.minion;

//Complete
public class Penguin extends AbstractMinion {

    private static String name = "Penguin";
    private static String description = "";
    private static int baseCost = 0;
    private static int baseATK = 1;
    private static int baseHP = 1;

    public Penguin() {
        super(Penguin.name, Penguin.description, Penguin.baseCost, Penguin.baseHP, Penguin.baseATK);
    }

}