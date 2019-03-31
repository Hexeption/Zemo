/*******************************************************************************
 * Zemo Client
 *
 * Copyright (c) 2019, Keir Davis
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * • Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * • Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * • Neither the name of the copyright holder nor the names of its
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

package uk.co.hexeption.zemo.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.*;

public class LogHelper {

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    private static void log(Level level, Object message) {
        Logger logger = LogManager.getLogger("Zemo");

        logger.log(level, "[" + PURPLE + "Zemo" + RESET + "] " + String.valueOf(message));
    }

    /**
     * No events will be logged.
     */
    public static void off(Object message) {

        log(Level.OFF, message);
    }

    /**
     * A severe error that will prevent the application from continuing.
     */
    public static void fatal(Object message) {

        log(Level.FATAL, message);
    }

    /**
     * An error in the application, possibly recoverable.
     */
    public static void error(Object message) {

        log(Level.ERROR, message);
    }

    /**
     * An event that might possible lead to an error.
     */
    public static void warn(Object message) {

        log(Level.WARN, message);
    }

    /**
     * An event for informational purposes.
     */
    public static void info(Object message) {

        log(Level.INFO, message);
    }

    /**
     * A general debugging event.
     */
    public static void debug(Object message) {

        log(Level.DEBUG, message);
    }

    /**
     * A fine-grained debug message, typically capturing the flow through the application.
     */
    public static void tace(Object message) {

        log(Level.TRACE, message);
    }

    /**
     * All events should be logged.
     */
    public static void all(Object message) {

        log(Level.ALL, message);
    }

    public static void section(String message) {

        info(StringUtils.center(" " + message + " ", 70, '='));
    }

    public static void endSection() {
        info("======================================================================");
    }
}
