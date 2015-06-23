package com.horusdev.enjinrequester.category;

import com.horusdev.enjinrequester.EnjinRequester;
import com.horusdev.enjinrequester.auth.EnjinAuth;
import com.horusdev.enjinrequester.auth.UserAuth;
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
 * All of the API methods categorized under Points
 *
 * @author Ben S (HorusDev)
 */
@SuppressWarnings({"unchecked", "unused"})
public class Points {

    /**
     * Get the point count of a player
     *
     * @param auth A form of EnjinAuth to authenticate with
     * @param target The user being checked for
     * @return an instance of RequestResponse with the amount and result
     */
    public static RequestResponse<Integer> get(EnjinAuth auth, User target) {
        return get(auth, target.getId());
    }

    /**
     * Get the point count of a player
     *
     * @param auth A form of EnjinAuth to authenticate with
     * @param target The ID of the user being checked for
     * @return an instance of RequestResponse with the amount and result
     */
    public static RequestResponse<Integer> get(EnjinAuth auth, int target) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        if (!(auth instanceof UserAuth))
            params.put(Param.USER_ID.toString(), target);

        try {
            PreparedConnection connection = new PreparedConnection("Points.get", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            Long result = (Long) connection.getResponse();

            return new RequestResponse<>(Integer.parseInt(String.valueOf(result)), RequestResult.SUCCESS);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to get points");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }

    /**
     * Get the point count of a player
     *
     * @param auth A form of EnjinAuth to authenticate with
     * @param target The player being checked for
     * @return an instance of RequestResponse with the amount and result
     */
    public static RequestResponse<Integer> get(EnjinAuth auth, String target) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        if (!(auth instanceof UserAuth))
            params.put(Param.PLAYER.toString(), target);

        try {
            PreparedConnection connection = new PreparedConnection("Points.get", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            Long result = (Long) connection.getResponse();

            return new RequestResponse<>(Integer.parseInt(String.valueOf(result)), RequestResult.SUCCESS);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to get points");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }

    /**
     * Set the point count of a player
     *
     * @param auth A form of EnjinAuth to authenticate with
     * @param target The user being used
     * @param amount The number to set the points to
     * @return an instance of RequestResponse with the amount and result
     */
    public static RequestResponse<Integer> set(EnjinAuth auth, User target, int amount) {
        return set(auth, target.getId(), amount);
    }

    /**
     * Set the point count of a player
     *
     * @param auth A form of EnjinAuth to authenticate with
     * @param target The ID of the user being used
     * @param amount The number to set the points to
     * @return an instance of RequestResponse with the amount and result
     */
    public static RequestResponse<Integer> set(EnjinAuth auth, int target, int amount) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        if (!(auth instanceof UserAuth))
            params.put(Param.USER_ID.toString(), target);

        params.put(Param.PLAYER.toString(), "null");
        params.put(Param.POINTS.toString(), amount);

        try {
            PreparedConnection connection = new PreparedConnection("Points.set", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            Long result = (Long) connection.getResponse();

            return new RequestResponse<>(Integer.parseInt(String.valueOf(result)), RequestResult.SUCCESS);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to run method");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }

    /**
     * Set the point count of a player
     *
     * @param auth A form of EnjinAuth to authenticate with
     * @param target The player being used
     * @param amount The number to set the points to
     * @return an instance of RequestResponse with the amount and result
     */
    public static RequestResponse<Integer> set(EnjinAuth auth, String target, int amount) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        if (!(auth instanceof UserAuth))
            params.put(Param.PLAYER.toString(), target);

        params.put(Param.POINTS.toString(), amount);

        try {
            PreparedConnection connection = new PreparedConnection("Points.set", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            Long result = (Long) connection.getResponse();

            return new RequestResponse<>(Integer.parseInt(String.valueOf(result)), RequestResult.SUCCESS);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to run method");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }

    /**
     * Add to the point count of a player
     *
     * @param auth A form of EnjinAuth to authenticate with
     * @param target The user being used
     * @param amount The number of points to add
     * @return an instance of RequestResponse with the amount and result
     */
    public static RequestResponse<Integer> add(EnjinAuth auth, User target, int amount) {
        return add(auth, target.getId(), amount);
    }

    /**
     * Add to the point count of a player
     *
     * @param auth A form of EnjinAuth to authenticate with
     * @param target The ID of the user being used
     * @param amount The number of points to add
     * @return an instance of RequestResponse with the amount and result
     */
    public static RequestResponse<Integer> add(EnjinAuth auth, int target, int amount) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        if (!(auth instanceof UserAuth))
            params.put(Param.USER_ID.toString(), target);

        params.put(Param.PLAYER.toString(), "null");
        params.put(Param.POINTS.toString(), amount);

        try {
            PreparedConnection connection = new PreparedConnection("Points.add", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            Long result = (Long) connection.getResponse();

            return new RequestResponse<>(Integer.parseInt(String.valueOf(result)), RequestResult.SUCCESS);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to run method");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }

    /**
     * Add to the point count of a player
     *
     * @param auth A form of EnjinAuth to authenticate with
     * @param target The user being used
     * @param amount The number of points to add
     * @return an instance of RequestResponse with the amount and result
     */
    public static RequestResponse<Integer> add(EnjinAuth auth, String target, int amount) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        if (!(auth instanceof UserAuth))
            params.put(Param.PLAYER.toString(), target);

        params.put(Param.POINTS.toString(), amount);

        try {
            PreparedConnection connection = new PreparedConnection("Points.add", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            Long result = (Long) connection.getResponse();

            return new RequestResponse<>(Integer.parseInt(String.valueOf(result)), RequestResult.SUCCESS);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to run method");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }

    /**
     * Remove from the point count of a player
     *
     * @param auth A form of EnjinAuth to authenticate with
     * @param target The user being used
     * @param amount The number of points to remove
     * @return an instance of RequestResponse with the amount and result
     */
    public static RequestResponse<Integer> remove(EnjinAuth auth, User target, int amount) {
        return remove(auth, target.getId(), amount);
    }

    /**
     * Remove from the point count of a player
     *
     * @param auth A form of EnjinAuth to authenticate with
     * @param target The ID of the user being used
     * @param amount The number of points to remove
     * @return an instance of RequestResponse with the amount and result
     */
    public static RequestResponse<Integer> remove(EnjinAuth auth, int target, int amount) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        if (!(auth instanceof UserAuth))
            params.put(Param.USER_ID.toString(), target);

        params.put(Param.PLAYER.toString(), "null");
        params.put(Param.POINTS.toString(), amount);

        try {
            PreparedConnection connection = new PreparedConnection("Points.remove", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            Long result = (Long) connection.getResponse();

            return new RequestResponse<>(Integer.parseInt(String.valueOf(result)), RequestResult.SUCCESS);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to run method");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }

    /**
     * Remove from the point count of a player
     *
     * @param auth A form of EnjinAuth to authenticate with
     * @param target The player being used
     * @param amount The number of points to remove
     * @return an instance of RequestResponse with the amount and result
     */
    public static RequestResponse<Integer> remove(EnjinAuth auth, String target, int amount) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        if (!(auth instanceof UserAuth))
            params.put(Param.PLAYER.toString(), target);

        params.put(Param.POINTS.toString(), amount);

        try {
            PreparedConnection connection = new PreparedConnection("Points.remove", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            Long result = (Long) connection.getResponse();

            return new RequestResponse<>(Integer.parseInt(String.valueOf(result)), RequestResult.SUCCESS);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to run method");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }
}
