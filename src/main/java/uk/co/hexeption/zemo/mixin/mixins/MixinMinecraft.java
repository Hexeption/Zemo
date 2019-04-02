/*******************************************************************************
 * Zemo Client
 *
 * Copyright (c) 2019, Keir Davis
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 *  Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/

package uk.co.hexeption.zemo.mixin.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.zemo.Zemo;
import uk.co.hexeption.zemo.event.events.imput.EventKey;
import uk.co.hexeption.zemo.event.events.imput.EventMouse;
import uk.co.hexeption.zemo.event.events.imput.EventMouse.MouseButtons;
import uk.co.hexeption.zemo.event.events.update.EventTick;
import uk.co.hexeption.zemo.mixin.imp.IMixinMinecraft;

@Mixin(Minecraft.class)
public class MixinMinecraft implements IMixinMinecraft {

    @Mutable
    @Shadow
    @Final
    private Session session;

    @Shadow
    @Final
    private Timer timer;

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", ordinal = 2, shift = At.Shift.AFTER))
    private void init(CallbackInfo callbackInfo) {
        Zemo.INSTANCE.startClient();
    }

    @Inject(method = "runTick", at = @At("HEAD"))
    private void runTick(CallbackInfo callbackInfo) {
        Zemo.INSTANCE.eventBus.post(new EventTick());
    }

    @Inject(method = "runTickKeyboard", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", shift = Shift.AFTER))
    public void runTickKeyboard(CallbackInfo callbackInfo) {
        if (Keyboard.getEventKeyState()) {
            Zemo.INSTANCE.eventBus.post(new EventKey(Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey()));
        }
    }

    @Inject(method = "clickMouse", at = @At("HEAD"))
    public void leftClickMouse(CallbackInfo callbackInfo) {
        Zemo.INSTANCE.eventBus.post(new EventMouse(MouseButtons.LEFT));
    }

    @Inject(method = "middleClickMouse", at = @At("HEAD"))
    public void middleClickMouse(CallbackInfo callbackInfo) {
        Zemo.INSTANCE.eventBus.post(new EventMouse(MouseButtons.MIDDLE));
    }

    @Inject(method = "rightClickMouse", at = @At("HEAD"))
    public void rightClickMouse(CallbackInfo callbackInfo) {
        Zemo.INSTANCE.eventBus.post(new EventMouse(MouseButtons.RIGHT));
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Timer getTimer() {
        return timer;
    }
}
