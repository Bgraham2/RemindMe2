package com.thunderstorm.remindme2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class myListAdapter extends ArrayAdapter<Reminder> {

    private LayoutInflater myInflater;
    private ArrayList<Reminder> reminder;
    private int myViewResourceId;

    public  myListAdapter(Context context, int textViewResourceId, ArrayList<Reminder> reminder) {
        super(context, textViewResourceId, reminder);
        this.reminder = reminder;
        myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents) {
        convertView = myInflater.inflate(myViewResourceId, null);

        Reminder reminders = reminder.get(position);

        if(reminders != null) {
            TextView title = convertView.findViewById(R.id.tvTitle);
            TextView day = convertView.findViewById(R.id.tvDays);
            TextView time = convertView.findViewById(R.id.tvTime);

            if(title != null) {
                title.setText(reminders.getTitle());
            }

            if(day != null) {
                day.setText(reminders.getDays());
            }

            if(time != null) {
                time.setText(reminders.getTime());
            }
        }

        return convertView;
    }

}
