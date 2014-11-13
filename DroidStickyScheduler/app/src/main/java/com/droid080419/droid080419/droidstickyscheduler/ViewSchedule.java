package com.droid080419.droid080419.droidstickyscheduler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class ViewSchedule extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        CalendarView calView = (CalendarView) findViewById(R.id.calendarView);
        Timestamp stamp = new Timestamp(calView.getDate());
        Date date = new Date(stamp.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String curMonth = cal.getDisplayName(Calendar.DATE,Calendar.SHORT, Locale.ENGLISH);
        setTitle(curMonth);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
