package me.petrolingus.game.texasholdem.core.actions;

/**
 * The action of raising a previous bet.
 */
public class RaiseAction extends Action {

    /**
     * Constructor.
     * 
     * @param amount
     *            The amount to raise with.
     */
    public RaiseAction(int amount) {
        super("Raise", "raises", amount);
    }
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.format("Raise(%d)", getAmount());
    }
    
}
