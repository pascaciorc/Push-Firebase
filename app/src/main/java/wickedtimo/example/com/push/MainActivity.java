package wickedtimo.example.com.push;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button notificationButton;
    private TextView tokenTextView;
    public MainActivity mainActivity;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

        notificationButton = findViewById(R.id.button_notificacion);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyThis(mainActivity,"HOLA","AMIGO");
            }
        });

        tokenTextView = findViewById(R.id.tokenTextView);
        SharedPreferences sp = getSharedPreferences("PREF",MODE_PRIVATE);
        if(!sp.getString("token","error").equals("error")) {
            tokenTextView.setText(sp.getString("token","error"));
            Log.d("TOKEN",sp.getString("token",null));
        }

        mFirebaseInstance = FirebaseDatabase.getInstance();
    }

    public void notifyThis(Context context, String title, String message) {
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setTicker("{your tiny message}")
                .setContentTitle(title)
                .setContentText(message)
                .setContentInfo("INFO");

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, b.build());
    }
}
