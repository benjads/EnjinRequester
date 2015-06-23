package com.horusdev.enjinrequester.category;

import com.horusdev.enjinrequester.EnjinRequester;
import com.horusdev.enjinrequester.auth.KeyAuth;
import com.horusdev.enjinrequester.enums.Param;
import com.horusdev.enjinrequester.enums.RequestResult;
import com.horusdev.enjinrequester.object.User;
import com.horusdev.enjinrequester.request.PreparedConnection;
import com.horusdev.enjinrequester.request.RequestResponse;
import com.horusdev.enjinrequester.util.Debugger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * All of the API methods categorized under UserAdmin
 *
 * @author Ben S (HorusDev)
 */
@SuppressWarnings({"unchecked", "unused"})
public class UserAdmin {

    /**
     * Get every user that is registered to your site (master list)
     *
     * <p>
     *     Warning: this process can be taxing on your resources
     * </p>
     *
     * @param auth An Enjin API key
     * @return all users
     */
    public static RequestResponse<User[]> get(KeyAuth auth) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        params.put(Param.CHARACTERS.toString(), true);
        params.put(Param.MC_PLAYERS.toString(), true);

        try {
            PreparedConnection connection = new PreparedConnection("UserAdmin.get", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            JSONObject result = (JSONObject) connection.getResponse();

            User[] users = UserHelper.deserializeUsers((JSONObject) result.get("users"));

            return new RequestResponse<>(users, RequestResult.SUCCESS);


        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to get tags");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }
}
