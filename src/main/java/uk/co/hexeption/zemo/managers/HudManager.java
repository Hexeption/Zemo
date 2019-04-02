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

package uk.co.hexeption.zemo.managers;

import com.google.common.collect.Lists;
import java.util.List;
import org.reflections.Reflections;
import scala.actors.threadpool.Arrays;
import uk.co.hexeption.zemo.ui.hud.Hud;
import uk.co.hexeption.zemo.ui.hud.Hud.HudInfo;
import uk.co.hexeption.zemo.utils.LogHelper;

/**
 * HudManager
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 02/04/2019 - 01:07 AM
 */
public class HudManager implements IManagers {

  private final List<Hud> huds = Lists.newCopyOnWriteArrayList();

  private int hudIndex = 0;

  @Override
  public void Initilization() {
    initHuds();
    LogHelper.info(String.format("%s huds loaded!", huds.size()));
    huds.forEach(hud -> LogHelper.info(String.format("%s [%s] loaded", hud.getName(), hud.getVersion())));
  }

  private void initHuds() {
    Reflections reflections = new Reflections(Hud.class.getPackage().getName());

    reflections.getTypesAnnotatedWith(HudInfo.class).forEach(aClass -> {
      try {
        Hud hud = (Hud) aClass.newInstance();
        huds.add(hud);
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    });
  }

  public void addHud(Hud... huds) {
    this.huds.addAll(Arrays.asList(huds));
  }

  public List<Hud> getHuds() {
    return huds;
  }

  public Hud getCurrentHud() {
    return this.huds.get(this.hudIndex);
  }

  public void changeHud() {
    int index = this.hudIndex;
    int maxSize = this.huds.size();

    if (index != -1) {
      index++;

      if (index >= maxSize) {
        index = 0;
      }

      this.hudIndex = index;
    }
  }
}
