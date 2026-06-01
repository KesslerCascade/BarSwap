package kessercascade.barswap.neoforge;

import dev.architectury.event.events.client.ClientTickEvent;
import kessercascade.barswap.BarSwap;
import kessercascade.barswap.BarSwapClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = BarSwap.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class BarSwapNeoForgeClient {
    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(BarSwapClient.SWAP_HOTBAR_KEY);
        ClientTickEvent.CLIENT_PRE.register(minecraft -> {
            if (minecraft.player == null) return;
            while (BarSwapClient.SWAP_HOTBAR_KEY.consumeClick()) {
                BarSwapClient.swapHotbarWithBottomRow(minecraft.player);
            }
        });
    }
}
