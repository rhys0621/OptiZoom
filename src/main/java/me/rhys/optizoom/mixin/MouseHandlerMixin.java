package me.rhys.optizoom.mixin;

import me.rhys.optizoom.util.KeyboardHelper;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.Options;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @Redirect(method = "turnPlayer",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/Options;smoothCamera:Z", opcode = Opcodes.GETFIELD))
    public boolean turnPlayerHook(Options instance) {
        return instance.smoothCamera || KeyboardHelper.hasKeyPressed();
    }
}
