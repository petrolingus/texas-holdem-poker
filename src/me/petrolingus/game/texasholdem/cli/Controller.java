package me.petrolingus.game.texasholdem.cli;

import me.petrolingus.game.texasholdem.core.TableType;
import me.petrolingus.game.texasholdem.core.actions.Action;
import me.petrolingus.game.texasholdem.core.actions.BetAction;
import me.petrolingus.game.texasholdem.core.actions.RaiseAction;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Controller with buttons to let a human player select an action.
 */
public class Controller {

    private static final boolean AUTO_CONTINUOUS = true;

    /**
     * The table type (betting structure).
     */
    private final TableType tableType;

    /**
     * Constructor.
     */
    public Controller(TableType tableType) {
        this.tableType = tableType;
    }

    /**
     * Waits for the user to click the Continue button.
     */
    public void waitForUserInput() {
        Set<Action> allowedActions = new HashSet<>();
        allowedActions.add(Action.CONTINUE);
        getUserInput(0, 0, allowedActions);
    }

    /**
     * Waits for the user to click an action button and returns the selected
     * action.
     *
     * @param minBet         The minimum bet.
     * @param cash           The player's remaining cash.
     * @param allowedActions The allowed actions.
     * @return The selected action.
     */
    public Action getUserInput(int minBet, int cash, final Set<Action> allowedActions) {

        Action selectedAction = null;

        Scanner scanner = new Scanner(System.in);

        if (allowedActions.contains(Action.CONTINUE)) {
            if (!AUTO_CONTINUOUS) {
                System.out.println("Type any key for continue...");
                scanner.next();
            }
            return Action.CONTINUE;
        } else {
            if (allowedActions.contains(Action.CHECK)) {
                System.out.println("1. CHECK");
            }
            if (allowedActions.contains(Action.CALL)) {
                System.out.println("2. CALL");
            }
            if (allowedActions.contains(Action.BET)) {
                System.out.println("3. BET");
            }
            if (allowedActions.contains(Action.RAISE)) {
                System.out.println("4. RAISE");
            }
            if (allowedActions.contains(Action.FOLD)) {
                System.out.println("5. FOLD");
            }
        }

        System.out.print("Select an action: ");
        int actionId = scanner.nextInt();
        switch (actionId) {
            case 0 -> selectedAction = Action.CONTINUE;
            case 1 -> selectedAction = Action.CHECK;
            case 2 -> selectedAction = Action.CALL;
            case 3 -> selectedAction = Action.BET;
            case 4 -> selectedAction = Action.RAISE;
            case 5 -> selectedAction = Action.FOLD;
        }

        // In case of a bet or raise, show panel to select amount.
        if (tableType == TableType.NO_LIMIT && (selectedAction == Action.BET || selectedAction == Action.RAISE)) {

            System.out.print("Enter the amount of cash (min=" + minBet + ")");
            int amount = scanner.nextInt();

            if (selectedAction == Action.BET) {
                selectedAction = new BetAction(amount);
            } else {
                selectedAction = new RaiseAction(amount);
            }
        }

        return selectedAction;
    }

}
