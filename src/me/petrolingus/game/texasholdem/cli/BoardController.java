package me.petrolingus.game.texasholdem.cli;

import me.petrolingus.game.texasholdem.core.Card;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Board controller with the community cards and general information.
 */
public class BoardController {

    /**
     * The maximum number of community cards.
     */
    private static final int NO_OF_CARDS = 5;

    /**
     * The control panel.
     */
    private final Controller controller;

    /**
     * Constructor.
     *
     * @param controller The control panel.
     */
    public BoardController(Controller controller) {
        this.controller = controller;
//        update(null, 0, 0);
    }

    /**
     * Updates the current hand status.
     *
     * @param bet The bet.
     * @param pot The pot.
     */
    public void update(List<Card> cards, int bet, int pot) {
//        System.out.println("BoardPanel.update");
        System.out.print("Bet: $" + bet + " Pot: $" + pot + " ");
        if (cards != null && cards.size() > 0) {
            System.out.println("Cards: " + cards.stream().map(Card::toString).collect(Collectors.joining(" ")));
        } else {
            System.out.println();
        }
    }

    /**
     * Sets a custom message.
     *
     * @param message The message.
     */
    public void setMessage(String message) {
        System.out.println(message);
    }

    /**
     * Waits for the user to continue.
     */
    public void waitForUserInput() {
        controller.waitForUserInput();
    }

}
