package com.horusdev.enjinrequester.auth;

/**
 * EnjinAuth using an Enjin API key
 *
 * @author Ben S (HorusDev)
 */
public class KeyAuth extends EnjinAuth {

    public KeyAuth(String key) {
        super(key, "api_key");
    }
}
