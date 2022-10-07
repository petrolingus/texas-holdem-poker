package me.petrolingus.game.texasholdem.core.actions;

/**
 * The action of placing a bet.
 */
public class BetAction extends Action {

    /**
     * Constructor.
     * 
     * @param amount
     *            The amount to bet.
     */
    public BetAction(int amount) {
        super("Bet", "bets", amount);
    }
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("Bet(%d)", getAmount());
    }
    
}
