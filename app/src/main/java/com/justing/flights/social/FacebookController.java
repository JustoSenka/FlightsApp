package com.justing.flights.social;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Justas on 05/21/2016.
 */
public class FacebookController {
    private static FacebookController ourInstance = new FacebookController();
    public static FacebookController getInstance() {
        return ourInstance;
    }
    private FacebookController() {
    }

    public void promptLogin(Context context) {
        Toast.makeText(context, "Sorry, facebook login not yet implemented", Toast.LENGTH_SHORT).show();
    }
}
