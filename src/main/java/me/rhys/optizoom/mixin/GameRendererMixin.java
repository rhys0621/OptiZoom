package me.rhys.optizoom.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import me.rhys.optizoom.util.KeyboardHelper;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @ModifyReturnValue(method = "getFov", at = @At(value = "RETURN"))
    public float modifyFov(float original) {
        if (KeyboardHelper.hasKeyPressed()) {
            original /= 3;
        }

        return original;
    }
}
