package kessercascade.barswap;

import com.mojang.blaze3d.platform.InputConstants;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public final class BarSwapClient {
    public static final KeyMapping SWAP_HOTBAR_KEY = new KeyMapping(
        "key.barswap.swap_hotbar",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_GRAVE_ACCENT,
        "key.categories.barswap"
    );

    public static void swapHotbarWithBottomRow(LocalPlayer player) {
        for (int i = 0; i < 9; i++) {
            player.connection.send(new ServerboundContainerClickPacket(
                player.inventoryMenu.containerId,
                player.inventoryMenu.getStateId(),
                27 + i,
                i,
                ClickType.SWAP,
                ItemStack.EMPTY,
                new Int2ObjectOpenHashMap<>()
            ));
        }
    }
}
