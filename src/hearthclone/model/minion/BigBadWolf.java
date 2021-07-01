package hearthclone.model.minion;

//Complete
public class BigBadWolf extends AbstractMinion {

    private static String name = "BigBadWolf";
    private static String description = "Scary Wolf";
    private static int baseCost = 2;
    private static int baseATK = 3;
    private static int baseHP = 2;

    public BigBadWolf() {
        super(BigBadWolf.name, BigBadWolf.description, BigBadWolf.baseCost, BigBadWolf.baseHP, BigBadWolf.baseATK);
    }

}