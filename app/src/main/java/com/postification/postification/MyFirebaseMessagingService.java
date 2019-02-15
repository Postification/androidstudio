package com.postification.postification;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    public void onNewToken(String token){

        Log.d("fcmToken", "Refreshed token: " + token);

        //Firebase Database
        DatabaseReference myRef = database.getReference("/user/user1/token");
        myRef.setValue(token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

    }
}
