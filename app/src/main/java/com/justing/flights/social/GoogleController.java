package com.justing.flights.social;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Justas on 05/21/2016.
 */
public class GoogleController {
    private static GoogleController ourInstance = new GoogleController();
    public static GoogleController getInstance() {
        return ourInstance;
    }
    private GoogleController() {
    }

    public void promptLogin(Context context) {
        Toast.makeText(context, "Sorry, google+ login not yet implemented", Toast.LENGTH_SHORT).show();
    }
}
