package uk.co.stableweb.firebasehero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class SubscribeActivity extends AppCompatActivity {

    private static final String TAG = "SubscribeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        Button subscribeBtn = (Button) findViewById(R.id.subscribeButton);
        subscribeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // [START subscribe_topics]
                FirebaseMessaging.getInstance().subscribeToTopic("news");
                // [END subscribe_topics]

                // Log and toast
                String msg = "Subscribed to topic";
                Log.d(TAG, msg);
                Toast.makeText(SubscribeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        Button logTokenBtn = (Button) findViewById(R.id.logTokenButton);
        logTokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get token
                String token = FirebaseInstanceId.getInstance().getToken();

                // Log and toast
                String msg = "Logging token " + token;
                Log.d(TAG, msg);
                Toast.makeText(SubscribeActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
