package me.rhys.optizoom.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import me.rhys.optizoom.util.KeyboardHelper;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Camera.class)
public class CameraMixin {
    @ModifyReturnValue(method = {"calculateFov", "calculateHudFov"}, at = @At(value = "RETURN"))
    public float hookFov(float original) {
        if (KeyboardHelper.hasKeyPressed()) {
            original /= 3;
        }

        return original;
    }
}
