package heart.model.minion;

import heart.model.Taunt;

//Complete
public class MirrorImage extends AbstractMinion implements Taunt {

    private static String name = "MirrorImage";
    private static String description = "Taunt";
    private static int baseCost = 0;
    private static int baseATK = 0;
    private static int baseHP = 2;

    public MirrorImage() {
        super(MirrorImage.name, MirrorImage.description, MirrorImage.baseCost, MirrorImage.baseHP, MirrorImage.baseATK);

    }
}