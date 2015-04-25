package ohtu;

public final class TennisPlayer {

    private final String name;
    private final TennisScore score = new TennisScore();

    public TennisPlayer(String name) {
        this.name = name;
    }

    public TennisScore score() {
        return score;
    }

    public String name() {
        return name;
    }

    public String status(int scoresComparision) {
        String status = scoresComparision == 1 ? "Advantage " : "Win for ";
        return status + name();
    }
}
