/*
 * Copyright 2016-2017 Tom Misawa, riversun.org@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 *  INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.riversun.recvfcm;

import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Service called when firebase cloud message received
 *
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Logg.d();

        final Map<String, String> data = remoteMessage.getData();
        final String val1 = data.get("myKey1");
        final String val2 = data.get("myKey2");

        Logg.d("Message received val1=" + val1 + " val2=" + val2);


        Handler handler = new Handler(getMainLooper());
        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(MyFirebaseMessagingService.this.getApplicationContext(),
                        "Message received " + "val1=" + val1 + " val2=" + val2, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onNewToken(String registrationToken) {


        Logg.d("Firebase #onNewToken registrationToken=" + registrationToken);

        startService(new Intent(this, FcmTokenRegistrationService.class));
    }
}
