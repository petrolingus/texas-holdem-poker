package me.petrolingus.game.texasholdem.core.bots;

import me.petrolingus.game.texasholdem.core.Card;
import me.petrolingus.game.texasholdem.core.Player;
import me.petrolingus.game.texasholdem.core.TableType;
import me.petrolingus.game.texasholdem.core.actions.Action;

import java.util.List;
import java.util.Set;

/**
 * Dummy Texas Hold'em poker bot that always just checks or calls.
 * <p>
 * This bot allowed for perfectly predictable behavior.
 */
public class DummyBot extends Bot {

    /**
     * {@inheritDoc}
     */
    @Override
    public void messageReceived(String message) {
        // Not implemented.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void joinedTable(TableType type, int bigBlind, List<Player> players) {
        // Not implemented.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handStarted(Player dealer) {
        // Not implemented.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actorRotated(Player actor) {
        // Not implemented.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playerUpdated(Player player) {
        // Not implemented.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void boardUpdated(List<Card> cards, int bet, int pot) {
        // Not implemented.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playerActed(Player player) {
        // Not implemented.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action act(int minBet, int currentBet, Set<Action> allowedActions) {
        if (allowedActions.contains(Action.CHECK)) {
            return Action.CHECK;
        } else {
            return Action.CALL;
        }
    }

}
