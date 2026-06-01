package kessercascade.barswap.neoforge;

import kessercascade.barswap.BarSwap;
import kessercascade.barswap.BarSwapClient;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;

@EventBusSubscriber(modid = BarSwap.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class BarSwapNeoForgeClient {
    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(BarSwapClient.SWAP_HOTBAR_KEY);
        NeoForge.EVENT_BUS.addListener(BarSwapNeoForgeClient::onClientTick);
    }

    private static void onClientTick(ClientTickEvent.Pre event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) return;
        while (BarSwapClient.SWAP_HOTBAR_KEY.consumeClick()) {
            BarSwapClient.swapHotbarWithBottomRow(minecraft.player);
        }
    }
}
