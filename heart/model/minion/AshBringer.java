
package heart.model.minion;

//Complete
public class AshBringer extends AbstractMinion {

    private static String name = "AshBringer";
    private static String description = "Fording's weapon";
    private static int baseCost = 5;
    private static int baseATK = 5;
    private static int baseHP = 5;

    public AshBringer() {
        super(AshBringer.name, AshBringer.description, AshBringer.baseCost, AshBringer.baseHP, AshBringer.baseATK);
    }

}