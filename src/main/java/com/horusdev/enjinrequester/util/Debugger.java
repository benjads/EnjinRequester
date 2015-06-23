package com.horusdev.enjinrequester.util;

/**
 * A simple warning tool for debugging
 *
 * @author Ben S (HorusDev)
 */
@SuppressWarnings("unused")
public class Debugger {

    private static boolean enabled;

    public static void info(String message) {
        if (!enabled)
            return;

        System.out.println(String.format("[EnjinRequester] %s", message));
    }

    public static void warning(String message) {
        if (!enabled)
            return;

        System.out.println(String.format("[EnjinRequester] [Warn] %s", message));
    }

    public static void error(String message) {
        System.out.println(String.format("[EnjinRequester] [Error] %s", message));
    }

    public static void setEnabled(boolean enabled) {
        Debugger.enabled = enabled;
    }

    public static boolean isEnabled() {
        return enabled;
    }
}
