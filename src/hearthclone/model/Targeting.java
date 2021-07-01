package hearthclone.model;

import java.util.List;

public interface Targeting {

    List<Minion> getCandidates(List<Minion> ally, List<Minion> enemy);

}