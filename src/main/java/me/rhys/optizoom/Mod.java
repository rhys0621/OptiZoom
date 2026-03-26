package me.rhys.optizoom;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class Mod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("OptiZoom");
    public static final KeyMapping zoomKey = KeyMappingHelper.registerKeyMapping(
            new KeyMapping(
                    "OptiZoom Trigger Key",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_C,
                    KeyMapping.Category.register(Identifier.fromNamespaceAndPath("optizoom", "keybinds"))
            )
    );

    @Override
    public void onInitialize() {
        LOGGER.info("Mod Initialized!");
    }
}
