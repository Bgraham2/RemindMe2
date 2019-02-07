package com.thunderstorm.remindme2;



import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class AddActivity extends AppCompatActivity {
    DBHelper myDB;

    String reminder = "";
    int[] selectedDays = new int[7];
    Integer hour = 0;
    Integer minute = 0;
    String text = "";
    boolean isSun = false;
    boolean isMon = false;
    boolean isTue = false;
    boolean isWed = false;
    boolean isThu = false;
    boolean isFri = false;
    boolean isSat = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        myDB = new DBHelper(this);
        final TextView tvTitle = findViewById(R.id.tvTitle);
        final TextView tvTime = findViewById(R.id.tvTime);
        final Button bSunday = findViewById(R.id.btnSunday);
        final Button bMonday = findViewById(R.id.btnMonday);
        final Button bTuesday = findViewById(R.id.btnTuesday);
        final Button bWednesday = findViewById(R.id.btnWednesday);
        final Button bThursday = findViewById(R.id.btnThursday);
        final Button bFriday = findViewById(R.id.btnFriday);
        final Button bSaturday = findViewById(R.id.btnSaturday);
        final Button bTime = findViewById(R.id.btnTime);
        final Button title = findViewById(R.id.btnReminder);
        final Button bCancel = findViewById(R.id.btnCancel);
        final Button bSave = findViewById(R.id.btnSave);

        //Button click opens alert dialog to get user input for reminder title
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(AddActivity.this);
                final View tView = getLayoutInflater().inflate(R.layout.dialog_title, null);
                final EditText etTitle = tView.findViewById(R.id.etReminder);
                final Button cButton = tView.findViewById(R.id.btnCancelTitle);
                final Button sButton = tView.findViewById(R.id.btnSaveTitle);

                dialog.setView(tView)
                        .setTitle("Remind Me To:");

                final AlertDialog aTitle = dialog.create();

                //Hides the button and makes the TextView visible showing the title set by the user.
                sButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (etTitle.getText().toString().isEmpty()) {
                            Toast.makeText(AddActivity.this, "Enter a Reminder Title.", Toast.LENGTH_SHORT).show();
                        } else {
                            reminder = etTitle.getText().toString();
                            Toast.makeText(AddActivity.this, "Reminder Title Added.", Toast.LENGTH_SHORT).show();
                            tvTitle.setText(reminder);
                            tvTitle.setVisibility(View.VISIBLE);
                            title.setVisibility(View.GONE);
                            aTitle.dismiss();
                        }
                    }
                });

                cButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        aTitle.dismiss();
                    }
                });

                aTitle.show();
            }
        });

        //The following day buttons just toggle a boolean value, change button background color, and show Toast (bSunday -> bFriday)
        bSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSun = !isSun;
                int id = isSun ? R.drawable.day_button_selected : R.drawable.day_button_default;

                if (isSun) {
                    Toast.makeText(AddActivity.this, "Sunday added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Sunday removed", Toast.LENGTH_SHORT).show();
                }

                bSunday.setBackgroundResource(id);
            }
        });

        bSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSat = !isSat;
                int id = isSat ? R.drawable.day_button_selected : R.drawable.day_button_default;

                if (isSat) {
                    Toast.makeText(AddActivity.this, "Saturday added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Saturday removed", Toast.LENGTH_SHORT).show();
                }

                bSaturday.setBackgroundResource(id);
            }
        });

        bMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMon = !isMon;
                int id = isMon ? R.drawable.day_button_selected : R.drawable.day_button_default;

                if (isMon) {
                    Toast.makeText(AddActivity.this, "Monday added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Monday removed", Toast.LENGTH_SHORT).show();
                }

                bMonday.setBackgroundResource(id);
            }
        });

        bTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTue = !isTue;
                int id = isTue ? R.drawable.day_button_selected : R.drawable.day_button_default;

                if (isTue) {
                    Toast.makeText(AddActivity.this, "Tuesday added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Tuesday removed", Toast.LENGTH_SHORT).show();
                }

                bTuesday.setBackgroundResource(id);
            }
        });

        bWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWed = !isWed;
                int id = isWed ? R.drawable.day_button_selected : R.drawable.day_button_default;

                if (isWed) {
                    Toast.makeText(AddActivity.this, "Wednesday added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Wednesday removed", Toast.LENGTH_SHORT).show();
                }

                bWednesday.setBackgroundResource(id);
            }
        });

        bThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThu = !isThu;
                int id = isThu ? R.drawable.day_button_selected : R.drawable.day_button_default;

                if (isThu) {
                    Toast.makeText(AddActivity.this, "Thursday added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Thursday removed", Toast.LENGTH_SHORT).show();
                }

                bThursday.setBackgroundResource(id);
            }
        });

        bFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFri = !isFri;
                int id = isFri ? R.drawable.day_button_selected : R.drawable.day_button_default;

                if (isFri) {
                    Toast.makeText(AddActivity.this, "Friday added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "Friday removed", Toast.LENGTH_SHORT).show();
                }

                bFriday.setBackgroundResource(id);
            }
        });

        //Button opens an alert dialog that allows the user to select the time for the reminder.
        bTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(AddActivity.this);
                View timeView = getLayoutInflater().inflate(R.layout.dialog_time, null);
                final TimePicker tpTime = timeView.findViewById(R.id.timePicker);
                Button cancel = timeView.findViewById(R.id.btnCancelTime);
                Button save = timeView.findViewById(R.id.btnSaveTime);

                dialog.setView(timeView)
                    .setTitle("Choose a Time");

                final AlertDialog aTime = dialog.create();

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        aTime.dismiss();
                    }
                });

                //Hides the button and displays the TextView with the value showing the selected time.
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hour = tpTime.getCurrentHour();
                        minute = tpTime.getCurrentMinute();

                        Toast.makeText(AddActivity.this, "Time added", Toast.LENGTH_SHORT).show();

                        //For values less than 10 adds a 0 to the front.
                        if(minute < 10) {
                            String minutes = "0" + minute;

                            if (hour > 12) {
                                text = hour - 12 + ":" + minutes + "PM";
                            } else {
                                text = hour + ":" + minutes + "AM";
                            }
                        } else {
                            if (hour > 12) {
                                text = hour - 12 + ":" + minute + "PM";
                            } else {
                                text = hour + ":" + minute + "AM";
                            }

                        }

                        tvTime.setText(text);
                        tvTime.setVisibility(View.VISIBLE);
                        bTime.setVisibility(View.GONE);
                        aTime.dismiss();

                    }
                });

                aTime.show();
            }
        });

        //Cancels AddActivity and returns user to main screen
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(AddActivity.this, MainActivity.class));
                finish();
            }
        });

        //Save the values set by the user to the database.
        bSave.setOnClickListener(new View.OnClickListener() {
            @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                StringBuilder days = new StringBuilder();

                int index = 0;

                //The next 7 if statments creates a string of the days selected by the boolean value.
                if(isSun) {
                    selectedDays[index] = 1;
                    index++;
                    if(days.toString().equals("")) {
                        days.append("Sun");
                    } else {
                        days.append(" Sun");
                    }

                }
                if(isMon) {
                    selectedDays[index] = 2;
                    index++;
                    if(days.toString().equals("")) {
                        days.append("Mon");
                    } else {
                        days.append(" Mon");
                    }

                }
                if(isTue) {
                    selectedDays[index] = 3;
                    index++;
                    if(days.toString().equals("")) {
                        days.append("Tue");
                    } else {
                        days.append(" Tue");
                    }

                }
                if(isWed) {
                    selectedDays[index] = 4;
                    index++;
                    if(days.toString().equals("")) {
                        days.append("Wed");
                    } else {
                        days.append(" Wed");
                    }

                }
                if(isThu) {
                    selectedDays[index] = 5;
                    index++;
                    if(days.toString().equals("")) {
                        days.append("Thu");
                    } else {
                        days.append(" Thu");
                    }

                }
                if(isFri) {
                    selectedDays[index] = 6;
                    index++;
                    if(days.toString().equals("")) {
                        days.append("Fri");
                    } else {
                        days.append(" Fri");
                    }

                }
                if(isSat) {
                    selectedDays[index] = 7;
                    index++;
                    if(days.toString().equals("")) {
                        days.append("Sat");
                    } else {
                        days.append(" Sat");
                    }

                }

                //Verifies all values are set and then saves to the database.
                if(selectedDays.length != 0) {
                    if (hour != 0) {
                        if (myDB.updateData(reminder, days.toString(), text)) {

                            for (int i = 0; i < index; i++) {
                                if (myDB.addNotifyID(myDB.getReminderID(reminder), selectedDays[i] + hour + minute)) {
                                    scheduleNotification(AddActivity.this, selectedDays[i]);
                                }
                            }
                            Toast.makeText(AddActivity.this, "Reminder Added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddActivity.this, "Adding a Reminder Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddActivity.this, "Please select a time", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddActivity.this, "Please select the days for the reminder.", Toast.LENGTH_SHORT).show();
                }

                //startActivity(new Intent(AddActivity.this, MainActivity.class));
                finish();

            }
        });

    }

    //Sets notifications
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void scheduleNotification(Context c, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, days);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
        intent.putExtra("Title", reminder);
        intent.putExtra("ID", days + hour + minute);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), days + hour + minute, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);
        }

    }
}
