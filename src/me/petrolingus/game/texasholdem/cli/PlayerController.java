package me.petrolingus.game.texasholdem.cli;

import me.petrolingus.game.texasholdem.core.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller representing a player at the table.
 */
public class PlayerController {

    /**
     * Updates the panel.
     *
     * @param player The player.
     */
    public void update(Player player) {

        List<String> info = new ArrayList<>();
        info.add(player.getName());
        info.add("Cash: $" + player.getCash());
        info.add("Bet: $" + player.getBet());
        info.add("Action: " + ((player.getAction() == null) ? "No action" : player.getAction()));

        if (player.hasCards() && player.getCards().length == 2) {
            info.add("Cards: " + Arrays.toString(player.getCards()));
        }

        System.out.println(String.join(" ", info));
    }
}
