package com.horusdev.enjinrequester.enums;

/**
 * The response from calling an API method
 *
 * @author Ben S (HorusDev)
 */
public enum RequestResult {
    SUCCESS(false, -1), ERROR(true, -1), INVALID_PARAMS(true, -32602), WRONG_RESULT(true, -1), AUTH_FAIL(true, -32001);

    private boolean error;
    private int errorCode;

    RequestResult(boolean error, int errorCode) {
        this.error = error;
        this.errorCode = errorCode;
    }

    public boolean isError() {
        return error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public static RequestResult getError(int code) {
        for (RequestResult value : values())
            if (value.getErrorCode() == code)
                return value;

        return ERROR;
    }
}
