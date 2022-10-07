package me.petrolingus.game.texasholdem.core.bots;

import me.petrolingus.game.texasholdem.core.Client;

/**
 * Base class for all Texas Hold'em poker bot implementations.
 */
public abstract class Bot implements Client {
    
    /** Number of hole cards. */
    protected static final int NO_OF_HOLE_CARDS = 2;
    
}
