package me.rhys.optizoom.util;

import lombok.experimental.UtilityClass;
import me.rhys.optizoom.Mod;

@UtilityClass
public class KeyboardHelper implements MinecraftProvider {
    public boolean hasKeyPressed() {
        return !MC.isPaused() && Mod.zoomKey.isDown();
    }
}
