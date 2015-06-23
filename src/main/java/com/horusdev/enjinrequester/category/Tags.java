package com.horusdev.enjinrequester.category;

import com.horusdev.enjinrequester.EnjinRequester;
import com.horusdev.enjinrequester.auth.KeyAuth;
import com.horusdev.enjinrequester.enums.Param;
import com.horusdev.enjinrequester.enums.RequestResult;
import com.horusdev.enjinrequester.object.Tag;
import com.horusdev.enjinrequester.object.User;
import com.horusdev.enjinrequester.request.PreparedConnection;
import com.horusdev.enjinrequester.request.RequestResponse;
import com.horusdev.enjinrequester.util.Debugger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * All of the API methods categorized under Tags
 *
 * @author Ben S (HorusDev)
 */
@SuppressWarnings({"unchecked", "unused"})
public class Tags {

    public static RequestResponse<Tag[]> getTagTypes(KeyAuth auth) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        try {
            PreparedConnection connection = new PreparedConnection("Tags.getTagTypes", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            JSONObject result = (JSONObject) connection.getResponse();

            Tag[] tags = UserHelper.deserializeTags(result);

            return new RequestResponse<>(tags, RequestResult.SUCCESS);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to get tags");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }

    /**
     * Get every tag with all the Users they're populated with
     *
     * @param auth An Enjin API key
     * @return the tags
     */
    public static RequestResponse<Tag[]> get(KeyAuth auth) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        try {
            PreparedConnection connection = new PreparedConnection("Tags.get", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            JSONObject result = (JSONObject) connection.getResponse();

            User[] users = UserHelper.deserializeUsers((JSONObject) result.get("users"));
            Tag[] tags = UserHelper.deserializeTags((JSONObject) result.get("tags"), users);

            return new RequestResponse<>(tags, RequestResult.SUCCESS);


        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to get tags");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }

    /**
     * Get every tag of the queried user
     *
     * @param auth An Enjin API key
     * @param target The user being used
     * @return the tags
     */
    public static RequestResponse<Tag[]> get(KeyAuth auth, User target) {
        return get(auth, target.getId());
    }

    /**
     * Get every tag of the queried user
     *
     * @param auth An Enjin API key
     * @param target The ID of the user being used
     * @return the tags
     */
    public static RequestResponse<Tag[]> get(KeyAuth auth, int target) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        params.put(Param.USER_ID.toString(), target);

        try {
            PreparedConnection connection = new PreparedConnection("Tags.get", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            JSONObject result = (JSONObject) connection.getResponse();

            User[] users = UserHelper.deserializeUsers((JSONObject) result.get("users"));
            Tag[] tags = UserHelper.deserializeTags((JSONObject) result.get("tags"), users);

            return new RequestResponse<>(tags, RequestResult.SUCCESS);


        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to get tags");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }

    /**
     * Get every tag of the queried user
     *
     * @param auth An Enjin API key
     * @param target The name of the character being used
     * @return the tags
     */
    public static RequestResponse<Tag[]> get(KeyAuth auth, String target) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        params.put(Param.CHARACTER.toString(), target);
        params.put(Param.CHARACTERS.toString(), true);

        try {
            PreparedConnection connection = new PreparedConnection("Tags.get", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            JSONObject result = (JSONObject) connection.getResponse();

            User[] users = UserHelper.deserializeUsers((JSONObject) result.get("users"));
            Tag[] tags = UserHelper.deserializeTags((JSONObject) result.get("tags"), users);

            return new RequestResponse<>(tags, RequestResult.SUCCESS);


        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to get tags");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }
}
