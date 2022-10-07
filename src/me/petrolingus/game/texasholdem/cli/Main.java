package me.petrolingus.game.texasholdem.cli;

import me.petrolingus.game.texasholdem.core.*;
import me.petrolingus.game.texasholdem.core.actions.Action;
import me.petrolingus.game.texasholdem.core.bots.BasicBot;

import java.util.*;

/**
 * The game's main frame.
 * <p>
 * This is the core class of the Swing UI client application.
 */
public class Main implements Client {

    /**
     * Table type (betting structure).
     */
    private static final TableType TABLE_TYPE = TableType.NO_LIMIT;

    /**
     * The size of the big blind.
     */
    private static final int BIG_BLIND = 10;

    /**
     * The starting cash per player.
     */
    private static final int STARTING_CASH = 500;

    /**
     * The table.
     */
    private final Table table;

    /**
     * The players at the table.
     */
    private final Map<String, Player> players;

    /**
     * The board panel.
     */
    private final BoardController boardController;

    /**
     * The control panel.
     */
    private final Controller controller;

    /**
     * The player panels.
     */
    private final Map<String, PlayerController> playerPanels;

    /**
     * The human player.
     */
    private final Player humanPlayer;

    /**
     * Constructor.
     */
    public Main() {

        System.out.println("Texas Hold'em poker");

        controller = new Controller(TABLE_TYPE);
        boardController = new BoardController(controller);

        players = new LinkedHashMap<>();
        humanPlayer = new Player("Player", STARTING_CASH, this);
        players.put("Player", humanPlayer);
        players.put("Joe", new Player("Joe", STARTING_CASH, new BasicBot(0, 75)));
        players.put("Mike", new Player("Mike", STARTING_CASH, new BasicBot(25, 50)));
        players.put("Eddie", new Player("Eddie", STARTING_CASH, new BasicBot(50, 25)));

        table = new Table(TABLE_TYPE, BIG_BLIND);
        for (Player player : players.values()) {
            table.addPlayer(player);
        }

        playerPanels = new HashMap<>();
        for (Player player : players.values()) {
            PlayerController panel = new PlayerController();
            playerPanels.put(player.getName(), panel);
        }

        // Start the game.
        table.run();
    }

    /**
     * The application's entry point.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void joinedTable(TableType type, int bigBlind, List<Player> players) {
        for (Player player : players) {
            PlayerController playerController = playerPanels.get(player.getName());
            if (playerController != null) {
                playerController.update(player);
            }
        }
    }

    @Override
    public void messageReceived(String message) {
        boardController.setMessage(message);
        boardController.waitForUserInput();
    }

    @Override
    public void handStarted(Player dealer) {
        System.out.println("\nDealer is " + dealer.getName());
    }

    @Override
    public void actorRotated(Player actor) {
        System.out.println("Actor is " + actor.getName());
    }

    @Override
    public void boardUpdated(List<Card> cards, int bet, int pot) {
        boardController.update(cards, bet, pot);
        System.out.println();
    }

    @Override
    public void playerUpdated(Player player) {
        PlayerController playerController = playerPanels.get(player.getName());
        if (playerController != null) {
            playerController.update(player);
        }
    }

    @Override
    public void playerActed(Player player) {
        String name = player.getName();
        PlayerController playerController = playerPanels.get(name);
        if (playerController != null) {
            playerController.update(player);
            Action action = player.getAction();
            if (action != null) {
                boardController.setMessage(String.format("%s %s.", name, action.getVerb()));
                if (player.getClient() != this) {
                    boardController.waitForUserInput();
                }
            }
        } else {
            throw new IllegalStateException(
                    String.format("No PlayerPanel found for player '%s'", name));
        }
    }

    @Override
    public Action act(int minBet, int currentBet, Set<Action> allowedActions) {
        boardController.setMessage("Please select an action:");
        return controller.getUserInput(minBet, humanPlayer.getCash(), allowedActions);
    }

}
