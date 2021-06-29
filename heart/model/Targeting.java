package heart.model;

import java.util.List;

public interface Targeting {

    List<Minion> getCandidates(Player player);

}