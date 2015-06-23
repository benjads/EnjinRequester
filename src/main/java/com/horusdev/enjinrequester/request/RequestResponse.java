package com.horusdev.enjinrequester.request;

import com.horusdev.enjinrequester.enums.RequestResult;
import com.horusdev.enjinrequester.util.Debugger;

/**
 * What's received from every API method that involves data
 *
 * @author Ben S (HorusDev)
 */
public class RequestResponse<T> {

    private T data;
    private RequestResult result;

    public RequestResponse(T data, RequestResult result) {
        this.data = data;
        this.result = result;

       Debugger.info(String.format("The result was %s" + (isSuccessful() ? " and the data was " + String.valueOf(data) : ""), result.name()));
    }

    public boolean isSuccessful() {
        return result == RequestResult.SUCCESS;
    }

    public T getData() {
        if (!isSuccessful()) {
            Debugger.info("The result was not success in RequestResponse");
            return null;
        }

        return data;
    }

    public RequestResult getResult() {
        return result;
    }
}
