package com.horusdev.enjinrequester.auth;

/**
 * EnjinAuth using a user's login session
 *
 * @author Ben S (HorusDev)
 */
@SuppressWarnings("unused")
public class UserAuth extends EnjinAuth {

    public UserAuth(String string) {
        super(string, "session_key");
    }
}
