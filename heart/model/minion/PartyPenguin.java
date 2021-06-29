package heart.model.minion;

//Complete
public class PartyPenguin extends AbstractMinion {

    private static String name = "PartyPenguin";
    private static String description = "";
    private static int baseCost = 0;
    private static int baseATK = 1;
    private static int baseHP = 1;

    public PartyPenguin() {
        super(PartyPenguin.name, PartyPenguin.description, PartyPenguin.baseCost, PartyPenguin.baseHP,
                PartyPenguin.baseATK);
    }

}