package com.horusdev.requestertest;

import com.horusdev.enjinrequester.EnjinRequester;
import com.horusdev.enjinrequester.auth.KeyAuth;
import com.horusdev.enjinrequester.category.Points;
import com.horusdev.enjinrequester.category.UserHelper;
import com.horusdev.enjinrequester.object.User;
import com.horusdev.enjinrequester.object.character.GameCharacter;
import com.horusdev.enjinrequester.request.RequestResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * An example for educational purposes of how to use the Enjin Requester
 *
 * @author Ben (HorusDev)
 * @see <a href="http://www.github.com/HorusDev/EnjinRequester">http://www.github.com/HorusDev/EnjinRequester</a>
 */
@SuppressWarnings("unused")
public class Example {

    /*
    For testing purposes
     */
    private static final String API_KEY = "b418b5cdd83c8ea0437c6d58ac7b1dc2af9f13d3c4d1df08";
    protected static final String USERNAME = "Horus";
    private static final int USER_ID = 3265927;
    //Using an ID is far less resource taxing than a name

    private static KeyAuth auth;

    static {
        auth = new KeyAuth(API_KEY);
        /*
        Prepares a form of authentication we can use

        Generally, the Enjin API key is the best/most universal authentication form for performing administrative actions
         */

        //With debug, EnjinRequester.enable("http://devsite.enjin.com", true);
        EnjinRequester.enable("http://devsite.enjin.com");
        /*
        Enables EnjinRequester, which is required for it to function
         */
    }

    public static void main(String[] args) {
        printDescription(USER_ID);
    }

    /**
     * Prints out the amount of points a user has
     *
     * @param name The user
     */
    private static void printPoints(String name) {
        RequestResponse<User> response = UserHelper.getUser(auth, name, true);
        /*
        Gets the user using the the authentication prepared earlier, the username we want to use, and mention that name is of a character
         */

        switch (response.getResult()) {
            case SUCCESS:
                //It worked
                break;
            case ERROR:
                //Something went wrong, the full result of provided if debug was enabled when EnjinRequester was initialized
                return;
            case INVALID_PARAMS:
                //Something wrong was provided as the parameters of the method
                return;
            case WRONG_RESULT:
                //Nothing matched the query or multiple were found when there should have only been one
                return;
            case AUTH_FAIL:
                //An invalid key was used or that API is disabled
        }

        User user = response.getData();


        System.out.println(String.format("%s (ID: %d)'s point count is %d", user.getUsername(), user.getId(), Points.get(auth, user).getData()));
    }

    private static void printDescription(int id) {
        RequestResponse<User> response = UserHelper.getUser(auth, id);

        if (!response.isSuccessful())
            return;
        //A simplified version of what's above

        User user = response.getData();

        //Start story
        System.out.println(String.format("Once upon a time there was a fellow Enjin-er named %s", user.getUsername()));

        //Talk about dates
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        if (user.getLastSeen().before(calendar.getTime()))
            System.out.println(String.format("%s hasn't been here in awhile since he/she was last here %s but he/she joined %s", user.getUsername(), dateFormat.format(user.getLastSeen()), dateFormat.format(user.getDateJoined())));
        else
            System.out.println(String.format("%s was here recently since he/she was last here %s but he/she joined %s", user.getUsername(), dateFormat.format(user.getLastSeen()), dateFormat.format(user.getDateJoined())));

        //Talk about characters
        List<String> characterNames = new LinkedList<>();
        for (GameCharacter gameCharacter : user.getCharacters())
            characterNames.add(gameCharacter.getName());

        if (characterNames.size() > 0) {
            StringBuilder sb = new StringBuilder();
            Iterator<String> nameIterator = characterNames.listIterator();

            while (nameIterator.hasNext()) {
                String currentName = nameIterator.next();

                if (nameIterator.hasNext())
                    sb.append(currentName).append(", ");
                else
                    sb.append(currentName);
            }

            System.out.println(String.format("He/she has %d " + (characterNames.size() == 1 ? "friend (character)" : "friends (characters)") + " to show you!", characterNames.size()));
            System.out.println(String.format((characterNames.size() == 1 ? "Its name is %s" : "Their names are %s"), sb.toString()));
        }
    }
}
