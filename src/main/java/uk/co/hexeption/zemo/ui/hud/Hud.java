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

package uk.co.hexeption.zemo.ui.hud;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import net.minecraft.client.Minecraft;

/**
 * Hud
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 02/04/2019 - 01:13 AM
 */
public abstract class Hud {

  @Retention(RetentionPolicy.RUNTIME)
  public @interface HudInfo {

    String name();

    String description();

    String[] authors();

    String version() default "0.0.1";
  }

  private String name = getClass().getAnnotation(HudInfo.class).name();
  private String description = getClass().getAnnotation(HudInfo.class).description();
  private String[] authors = getClass().getAnnotation(HudInfo.class).authors();
  private String version = getClass().getAnnotation(HudInfo.class).version();

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String[] getAuthors() {
    return authors;
  }

  public String getVersion() {
    return version;
  }

  public abstract void render(Minecraft minecraft, int displayWidth, int displayHeight);

  public abstract void keyboardEvent(int key);
}
