package me.petrolingus.game.texasholdem.core;

import me.petrolingus.game.texasholdem.core.actions.Action;

import java.util.List;
import java.util.Set;

/**
 * A player client showing the table information and acting on behalf of the
 * player.
 * <p>
 * Must be implemented by any client representing a player, either human or bot.
 */
public interface Client {

    /**
     * Handles a game message.
     *
     * @param message The message.
     */
    void messageReceived(String message);

    /**
     * Handles the player joining a table.
     *
     * @param type     The table type (betting structure).
     * @param bigBlind The table's big blind.
     * @param players  The players at the table (including this player).
     */
    void joinedTable(TableType type, int bigBlind, List<Player> players);

    /**
     * Handles the start of a new hand.
     *
     * @param dealer The dealer.
     */
    void handStarted(Player dealer);

    /**
     * Handles the rotation of the actor (the player who's turn it is).
     *
     * @param actor The new actor.
     */
    void actorRotated(Player actor);

    /**
     * Handles an update of this player.
     *
     * @param player The player.
     */
    void playerUpdated(Player player);

    /**
     * Handles an update of the board.
     *
     * @param cards The community cards.
     * @param bet   The current bet.
     * @param pot   The current pot.
     */
    void boardUpdated(List<Card> cards, int bet, int pot);

    /**
     * Handles the event of a player acting.
     *
     * @param player The player that has acted.
     */
    void playerActed(Player player);

    /**
     * Requests this player to act, selecting one of the allowed actions.
     *
     * @param minBet         The minimum bet.
     * @param currentBet     The current bet.
     * @param allowedActions The allowed actions.
     * @return The selected action.
     */
    Action act(int minBet, int currentBet, Set<Action> allowedActions);

}
