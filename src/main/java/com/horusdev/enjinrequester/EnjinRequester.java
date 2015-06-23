package com.horusdev.enjinrequester;

import com.horusdev.enjinrequester.util.Debugger;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * The primary requester class, requires initialization
 *
 * @author Ben S (HorusDev)
 * @see <a href="http://www.github.com/HorusDev/EnjinRequester">http://www.github.com/HorusDev/EnjinRequester</a>
 */
@SuppressWarnings("unused")
public class EnjinRequester {

    private static final String URL_EXTENSION = "/api/v1/api.php";

    private static URL url;

    private static boolean initialized = false;

    /**
     * Enables EnjinRequester
     *
     * The baseSite should be structured something like http://www.yourenjinsite.net,
     * as EnjinRequester will automatically provide the rest
     *
     * @param baseSite The primary base of your Enjin website
     */
    public static void enable(String baseSite) {
        if (baseSite.endsWith("/"))
            baseSite = baseSite.substring(0, baseSite.length() - 1);

        boolean addExtension = !baseSite.contains(URL_EXTENSION);

        if (addExtension) {
            try {
                url = new URL(baseSite + URL_EXTENSION);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Debugger.error("Error generating the URL");
            }
        }

        initialized = true;
    }

    /**
     * Enables EnjinRequester
     *
     * The baseSite should be structured something like http://www.yourenjinsite.com
     * as EnjinRequester will automatically provide the rest
     *
     * @param baseSite The primary base of your Enjin website
     * @param debug Whether or not you want debug messages
     */
    public static void enable(String baseSite, boolean debug) {
        enable(baseSite);
        Debugger.setEnabled(debug);
    }

    public static boolean checkInitialized() {
        if (initialized)
            return true;

        Debugger.warning("Attempted to use method before initialized");
        return false;
    }

    /**
     * Get the complete formatted URL
     *
     * @return the URL
     */
    public static URL getUrl() {
        return url;
    }
}
