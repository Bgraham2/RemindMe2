package com.thunderstorm.remindme2;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    DBHelper myDB;
    ArrayList<Reminder> reminderList;
    Reminder reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This section populates the list view from a SQLite database.
        ListView lv = findViewById(R.id.lvItems);
        FloatingActionButton fab = findViewById(R.id.btnFab);

        myDB = new DBHelper(this);

        reminderList = new ArrayList<>();
        Cursor data = myDB.getListContents();

        if(data.getCount() != 0) {
            while(data.moveToNext()) {
                 reminder = new Reminder(data.getString(1), data.getString(2), data.getString(3));
                 reminderList.add(reminder);
            }
        }

        //Custom list adapter to show 3 columns in list view
        final myListAdapter listAdapter = new myListAdapter(this, R.layout.list_view_row, reminderList);
        lv.setAdapter(listAdapter);

        //Click listener to delete clicked item from the database and remove the notification.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                TextView tv = view.findViewById(R.id.tvTitle);
                final String title = tv.getText().toString();

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setTitle("Delete Reminder " + title + "?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            //Clicking the Yes button deletes the reminder and the notification
                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Cursor ids = myDB.getNotifyID(myDB.getReminderID(title));

                                if(ids.getCount() != 0) {
                                    while(ids.moveToNext()) {
                                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                        notificationManager.cancel(ids.getInt(1));
                                        myDB.deleteID(ids.getInt(1));
                                    }
                                }

                                if(myDB.deleteItem(title)) {
                                    reminderList.remove(position);
                                    listAdapter.notifyDataSetChanged();
                                    Toast.makeText(MainActivity.this, "Reminder Deleted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Item removal failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })

                        //Closes the alert dialog
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                dialog.show();
            }
        });

        //Floating action button opens the add activity.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
    }

}
