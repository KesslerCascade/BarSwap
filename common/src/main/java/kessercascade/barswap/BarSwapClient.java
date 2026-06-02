package kessercascade.barswap;

import com.mojang.blaze3d.platform.InputConstants;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.HashedStack;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.ContainerInput;
import org.lwjgl.glfw.GLFW;

public final class BarSwapClient {
    public static final KeyMapping.Category BARSWAP_CATEGORY =
        KeyMapping.Category.register(Identifier.fromNamespaceAndPath("barswap", "category"));

    public static final KeyMapping SWAP_HOTBAR_KEY = new KeyMapping(
        "key.barswap.swap_hotbar",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_GRAVE_ACCENT,
        BARSWAP_CATEGORY
    );

    public static void swapHotbarWithBottomRow(LocalPlayer player) {
        for (int i = 0; i < 9; i++) {
            player.connection.send(new ServerboundContainerClickPacket(
                player.inventoryMenu.containerId,
                player.inventoryMenu.getStateId(),
                (short)(27 + i),
                (byte)i,
                ContainerInput.SWAP,
                new Int2ObjectOpenHashMap<HashedStack>(),
                HashedStack.EMPTY
            ));
        }
    }
}
