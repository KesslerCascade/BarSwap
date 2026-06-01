package kessercascade.barswap.fabric.client;

import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import kessercascade.barswap.BarSwapClient;
import net.fabricmc.api.ClientModInitializer;

public final class BarSwapFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyMappingRegistry.register(BarSwapClient.SWAP_HOTBAR_KEY);
        ClientTickEvent.CLIENT_PRE.register(minecraft -> {
            if (minecraft.player == null) return;
            while (BarSwapClient.SWAP_HOTBAR_KEY.consumeClick()) {
                BarSwapClient.swapHotbarWithBottomRow(minecraft.player);
            }
        });
    }
}
