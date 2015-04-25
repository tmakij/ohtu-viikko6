package ohtu;

import java.util.HashMap;
import java.util.Map;

public final class TennisGame {

    private final Map<String, TennisPlayer> playersByName = new HashMap<>();
    private final TennisPlayer playerOne, playerTwo;

    public TennisGame(String player1Name, String player2Name) {
        playerOne = addPlayer(player1Name);
        playerTwo = addPlayer(player2Name);
    }

    private TennisPlayer addPlayer(String playerName) {
        TennisPlayer player = new TennisPlayer(playerName);
        playersByName.put(player.name(), player);
        return player;
    }

    public void wonPoint(String playerName) {
        playersByName.get(playerName).score().increment();
    }

    public String getScore() {
        if (playerOne.score().equals(playerTwo.score())) {
            return playerOne.score().toStringEven();
        }
        if (playerOne.score().isGame() || playerTwo.score().isGame()) {
            int scoresAdvantage = playerOne.score().compare(playerTwo.score());
            TennisPlayer whoHasAdvantage = scoresAdvantage > 0 ? playerOne : playerTwo;
            return whoHasAdvantage.status(Math.abs(scoresAdvantage));
        }
        return playerOne.score().toStringUneven() + "-" + playerTwo.score().toStringUneven();
    }
}
