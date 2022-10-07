package me.petrolingus.game.texasholdem.core;

/**
 * Supported table types (betting structures).
 */
public enum TableType {

    /**
     * Fixed-Limit Texas Hold'em.
     */
    FIXED_LIMIT("Fixed-Limit"),

    /**
     * No-Limit Texas Hold'em.
     */
    NO_LIMIT("No-Limit");

    /**
     * Display name.
     */
    private final String name;

    /**
     * Constructor.
     *
     * @param name The display name.
     */
    TableType(String name) {
        this.name = name;
    }

    /**
     * Returns the display name.
     *
     * @return The display name.
     */
    public String getName() {
        return name;
    }

}
