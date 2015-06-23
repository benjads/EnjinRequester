package com.horusdev.enjinrequester.category;

import com.horusdev.enjinrequester.EnjinRequester;
import com.horusdev.enjinrequester.auth.KeyAuth;
import com.horusdev.enjinrequester.enums.Param;
import com.horusdev.enjinrequester.enums.RequestResult;
import com.horusdev.enjinrequester.enums.VisibilityType;
import com.horusdev.enjinrequester.object.Tag;
import com.horusdev.enjinrequester.object.User;
import com.horusdev.enjinrequester.object.character.GameCharacter;
import com.horusdev.enjinrequester.request.PreparedConnection;
import com.horusdev.enjinrequester.request.RequestResponse;
import com.horusdev.enjinrequester.util.Debugger;
import com.horusdev.enjinrequester.util.Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Provides extra features relevant to Users and Tags
 *
 * @author Ben S (HorusDev)
 */
@SuppressWarnings("unchecked")
public class UserHelper {

    /**
     * Get a User for processing or use with other methods based on an Enjin ID
     *
     * @param auth An Enjin API key
     * @param id The user's Enjin ID
     * @return the user with all of its data
     *
     */
    public static RequestResponse<User> getUser(KeyAuth auth, int id) {
        if (!EnjinRequester.checkInitialized())
            return new RequestResponse<>(null, RequestResult.ERROR);

        JSONObject params = new JSONObject();

        params.put(Param.CHARACTERS.toString(), true);
        params.put(Param.MC_PLAYERS.toString(), true);
        params.put(Param.USER_ID, id);

        try {
            PreparedConnection connection = new PreparedConnection("UserAdmin.get", auth, params);

            if (!connection.isSuccessful())
                return new RequestResponse<>(null, connection.getResult());

            JSONObject result = (JSONObject) connection.getResponse();

            User[] users = deserializeUsers(result);

            return (users.length == 1 ? new RequestResponse<>(users[0], RequestResult.SUCCESS) : new RequestResponse<User>(null, RequestResult.WRONG_RESULT));


        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to get tags");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }

    /**
     * Get a User for processing or use with other methods based on a name
     *
     * @param auth An Enjin API key
     * @param name The name to get for
     * @param isCharacter Whether name refers to a profile name or the name of a character
     * @return the user with all of its data
     */
    public static RequestResponse<User> getUser(KeyAuth auth, String name, boolean isCharacter) {
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

            User[] users = deserializeUsers(result);

            for (User user : users) {
                if (isCharacter) {
                    for (GameCharacter character : user.getCharacters()) {
                        if (character.getName().equalsIgnoreCase(name)) {
                            return new RequestResponse<>(user, RequestResult.SUCCESS);
                        }
                    }
                } else {
                    if (user.getUsername().equals(name)) {
                        return new RequestResponse<>(user, RequestResult.SUCCESS);
                    }
                }
            }

            return new RequestResponse<>(null, RequestResult.WRONG_RESULT);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Debugger.error("Unable to get tags");
            return new RequestResponse<>(null, RequestResult.ERROR);
        }
    }

    /**
     * Deserialize the raw JSONObject of users to an organized array of Users
     *
     * @param rawUsers The input to process
     * @return all of the users
     */
    protected static User[] deserializeUsers(JSONObject rawUsers) {
        List<User> users = new LinkedList<>();

        for (Object rawUser : rawUsers.entrySet()) {
            Map.Entry<String, Object> userEntry = (Map.Entry<String, Object>) rawUser;

            int id = Integer.parseInt(userEntry.getKey());

            JSONObject data = (JSONObject) userEntry.getValue();

            Debugger.info(String.format("Data is %s", data.toString()));

            String username = (String) data.get(Param.USERNAME.toString());

            int forumPosts = -1, forumVotes = -1, forumUpVotes = -1, forumDownVotes = -1;

            if (data.containsKey(Param.FORUM_POST_COUNT.toString())) {
                forumPosts = Integer.parseInt(String.valueOf(data.get(Param.FORUM_POST_COUNT.toString())));
                forumVotes = Integer.parseInt(String.valueOf(data.get(Param.FORUM_VOTES.toString())));
                forumUpVotes = Integer.parseInt(String.valueOf(data.get(Param.FORUM_UP_VOTES.toString())));
                forumDownVotes = Integer.parseInt(String.valueOf(data.get(Param.FORUM_DOWN_VOTES.toString())));
            }

            int lastSeenRaw = Integer.parseInt(String.valueOf(data.get(Param.LAST_SEEN.toString())));
            Date lastSeen = new Date(lastSeenRaw);

            int dateJoinedRaw = Integer.parseInt(String.valueOf(data.get(Param.DATE_JOINED.toString())));
            Date dateJoined = new Date(dateJoinedRaw);

            List<GameCharacter> characters = new LinkedList<>();
            JSONObject rawGames = (JSONObject) data.get(Param.CHARACTERS.toString());

            if (rawGames != null) {
                for (Object rawGame : rawGames.entrySet()) {
                    Map.Entry<String, Object> characterEntry = (Map.Entry<String, Object>) rawGame;

                    int gameId =  Integer.parseInt(characterEntry.getKey());

                    JSONArray gameArray = (JSONArray) characterEntry.getValue();

                    for (Object rawCharacter : gameArray) {
                        JSONObject character = (JSONObject) rawCharacter;

                        String characterName = String.valueOf(character.get(Param.NAME.toString()));
                        String characterType = String.valueOf(character.get(Param.TYPE.toString()));

                        characters.add(new GameCharacter(gameId, characterName, characterType));
                    }
                }
            }

            if (forumPosts != -1)
                users.add(new User(id, username, forumPosts, forumVotes, forumUpVotes,
                        forumDownVotes, lastSeen, dateJoined, Util.toArray(characters, GameCharacter.class)));
            else
                users.add(new User(id, username, lastSeen, dateJoined, Util.toArray(characters, GameCharacter.class)));
        }

        return Util.toArray(users, User.class);
    }

    /**
     * Deserialize the raw JSONObject of tags to an organized array of Tags
     *
     * @param rawTags The input to process
     * @param users All users (with at least one tag)
     * @return all of the tags
     */
    protected static Tag[] deserializeTags(JSONObject rawTags, User[] users) {
        List<Tag> tags = new LinkedList<>();

        for (Object rawTag : rawTags.entrySet()) {
            Map.Entry<String, Object> tagEntry = (Map.Entry<String, Object>) rawTag;

            int id = Integer.parseInt(tagEntry.getKey());

            JSONObject data = (JSONObject) tagEntry.getValue();

            String name = String.valueOf(data.get(Param.NAME.toString()));

            int rawVisibility = Integer.parseInt(String.valueOf(data.get(Param.VISIBILITY.toString())));
            VisibilityType visibility = VisibilityType.fromCode(rawVisibility);

            List<User> associatedUsers = new LinkedList<>();
            JSONArray rawUsers = (JSONArray) data.get(Param.USERS.toString());

            for (Object rawUser : rawUsers) {
                int userId = Integer.parseInt(String.valueOf(rawUser));

                for (User user : users)
                    if (user.getId() == userId)
                        associatedUsers.add(user);
            }

            tags.add(new Tag(id, name, associatedUsers.toArray(new User[associatedUsers.size() - 1]), visibility));
        }

        return tags.toArray(new Tag[tags.size() - 1]);
    }

    /**
     * Deserialize the raw JSONObject of tags to an organized array of Tags
     *
     * @param rawTags The input to process
     * @return all of the tags
     */
    protected static Tag[] deserializeTags(JSONObject rawTags) {
        List<Tag> tags = new LinkedList<>();

        for (Object rawTag : rawTags.entrySet()) {
            Map.Entry<String, Object> tagEntry = (Map.Entry<String, Object>) rawTag;

            int id = Integer.parseInt(tagEntry.getKey());

            JSONObject data = (JSONObject) tagEntry.getValue();

            String name = String.valueOf(data.get(Param.TAGNAME.toString()));

            int rawVisibility = Integer.parseInt(String.valueOf(data.get(Param.VISIBILITY.toString())));
            VisibilityType visibility = VisibilityType.fromCode(rawVisibility);

            int userCount = Integer.parseInt(String.valueOf(data.get(Param.NUMBER_USERS.toString())));

            tags.add(new Tag(id, name, userCount, visibility));
        }

        return tags.toArray(new Tag[tags.size() - 1]);
    }
}
