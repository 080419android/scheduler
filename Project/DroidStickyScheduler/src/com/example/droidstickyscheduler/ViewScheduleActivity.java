package com.example.droidstickyscheduler;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;

import com.tyczj.extendedcalendarview.CalendarProvider;
import com.tyczj.extendedcalendarview.Event;

public class ViewScheduleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_schedule);
		ContentValues values = new ContentValues();
		Calendar cal = Calendar.getInstance();
		
		// Modified: - - - - -
		File eventslist = new File("/data/data/com/example/droidstickyscheduler/files/eventslist.xml");
		
		//	Loads list of events from DAO
		ArrayList<Transfer> events = DataAccessObject.load(eventslist);
	    
		//	Adds list of events to values
		for(Transfer e: events){
			values.put(CalendarProvider.COLOR, Event.COLOR_RED);
			values.put(CalendarProvider.EVENT, e.getEventName());
		    values.put(CalendarProvider.DESCRIPTION, e.getDescription());
		    
		    cal.setTime(e.getStartDate());
		    values.put(CalendarProvider.START, cal.getTimeInMillis());
		    cal.setTime(e.getEndDate());
		    values.put(CalendarProvider.END, cal.getTimeInMillis());
		}
		
		// - - - - -
		
	    values.put(CalendarProvider.COLOR, Event.COLOR_RED);
	    values.put(CalendarProvider.DESCRIPTION, "Kainan");
	    values.put(CalendarProvider.LOCATION, "Katipunan");
	    values.put(CalendarProvider.EVENT, "Boys night out");

	    
	    int startDayYear = 2014;
	    int startDayMonth = 11;
	    int startDayDay = 15;
	    int startTimeHour = 18;
	    int startTimeMin = 0;
	    int julianDay = 7;
	    int endDayYear = 2014;
	    int endDayMonth = 11;
	    int endDayDay = 16;
	    int endTimeHour = 0;
	    int endTimeMin = 0;

	    cal.set(startDayYear, startDayMonth, startDayDay, startTimeHour, startTimeMin);
	    values.put(CalendarProvider.START, cal.getTimeInMillis());
	    values.put(CalendarProvider.START_DAY, julianDay);
	    TimeZone tz = TimeZone.getDefault();

	    cal.set(endDayYear, endDayMonth, endDayDay, endTimeHour, endTimeMin);
	    int endDayJulian = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));

	    values.put(CalendarProvider.END, cal.getTimeInMillis());
	    values.put(CalendarProvider.END_DAY, endDayJulian);

	    Uri uri = getContentResolver().insert(CalendarProvider.CONTENT_URI, values);
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
