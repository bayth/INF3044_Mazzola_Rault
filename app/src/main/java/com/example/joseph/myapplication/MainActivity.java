package com.example.joseph.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_hw = (Button)findViewById(R.id.button);
        btn_hw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, getString(R.string.toasttext), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
       getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.toast :
                Toast.makeText(MainActivity.this, getString(R.string.toasttext), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.dialog :
                new AlertDialog.Builder(MainActivity.this).setTitle("Dialog").setMessage("Ce ci est un dialog").show();
                return true;
            case R.id.notif :
                Notification.Builder mBuilder = new Notification.Builder(MainActivity.this).setSmallIcon(R.drawable.smallicon).setContentTitle("Notif")
                        .setContentText("Ce ci est une notification").setAutoCancel(true);
                Intent resultIntent = new Intent(this, MainActivity.class);
                PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(MainActivity.NOTIFICATION_SERVICE);
                mNotificationManager.notify(0, mBuilder.build());
                return true;
        }
        return false;
    }
}
