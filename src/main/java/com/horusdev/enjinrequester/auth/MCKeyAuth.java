package com.horusdev.enjinrequester.auth;

/**
 * EnjinAuth using an Enjin Minecraft key
 *
 * @author Ben S (HorusDev)
 */
@SuppressWarnings("unused")
public class MCKeyAuth extends EnjinAuth {

    public MCKeyAuth(String key) {
        super(key, "authkey");
    }
}
