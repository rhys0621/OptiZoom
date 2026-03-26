package me.rhys.optizoom.util;

import me.rhys.optizoom.Mod;

public class KeyboardHelper implements MinecraftProvider {
    public static boolean hasKeyPressed() {
        return !MC.isPaused() && Mod.zoomKey.isDown();
    }
}
