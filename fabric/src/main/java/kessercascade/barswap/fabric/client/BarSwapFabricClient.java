package kessercascade.barswap.fabric.client;

import kessercascade.barswap.BarSwapClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public final class BarSwapFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(BarSwapClient.SWAP_HOTBAR_KEY);
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            while (BarSwapClient.SWAP_HOTBAR_KEY.consumeClick()) {
                BarSwapClient.swapHotbarWithBottomRow(client.player);
            }
        });
    }
}
