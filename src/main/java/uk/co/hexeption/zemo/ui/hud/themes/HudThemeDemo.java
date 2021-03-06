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

package uk.co.hexeption.zemo.ui.hud.themes;

import net.minecraft.client.Minecraft;
import uk.co.hexeption.zemo.ui.hud.Hud;
import uk.co.hexeption.zemo.ui.hud.Hud.HudInfo;

/**
 * HudThemeZemo
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 02/04/2019 - 01:06 AM
 */
@HudInfo(name = "Demo", description = "A demo theme to test swapping huds", authors = {"Hexeption", ""}, version = "0.0.1")
public class HudThemeDemo extends Hud {

  @Override
  public void render(Minecraft minecraft, int displayWidth, int displayHeight) {
    Minecraft.getMinecraft().fontRenderer.drawString("Demo Test", 1, 1, -1);
  }

  @Override
  public void keyboardEvent(int key) {

  }
}
