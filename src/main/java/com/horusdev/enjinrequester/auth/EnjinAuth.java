package com.horusdev.enjinrequester.auth;

/**
 * The abstract form of any the methods of authentication
 *
 * @author Ben S (HorusDev)
 */
public abstract class EnjinAuth {

    private String string;
    private String jsonKey;

    EnjinAuth(String string, String jsonKey) {
        this.string = string;
        this.jsonKey = jsonKey;
    }

    /**
     * Get the key's data
     *
     * @return the data
     */
    public String getString() {
        return string;
    }

    /**
     * Get the name of the JSON object when using the implementation as an authentication method
     *
     * @return the key
     */
    public String getJsonKey() {
        return jsonKey;
    }
}
