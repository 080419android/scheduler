package com.droid.droidstickyscheduler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;


public class CaledarViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caledar_view);
     
        // Modified: - - - - -
 		AssetManager am = getAssets();
 	
 		//	Loads list of events from DAO
 		ArrayList<Transfer> events;
		try {
			events = DataAccessObject.load(am.open("events.xml"));
			 final Map<Date,List<Transfer>> eventMap = new HashMap<Date,List<Transfer>>();
		 	    //	Adds list of events to values
		 		for(Transfer e: events){
		 			Date date = e.getStartDate();
		 			if(!eventMap.containsKey(date)){
		 				List<Transfer> eventList = new ArrayList<Transfer>();
		 				eventMap.put(date,eventList);
		 			}
		 			
		 			eventMap.get(date).add(e);
		 		}        
		        
		        final CalendarView cal = (CalendarView)findViewById(R.id.calendarView1);
				 cal.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
				    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
				    	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				    	String dateStr = year + "-" + month + "-" + dayOfMonth;
				    	Date cur_date;
						try {
							cur_date = ft.parse(dateStr);
					    	final List<Transfer> list = eventMap.get(cur_date);
					    	viewEvent(cal,list);
						} catch (ParseException e) {
							System.out.println("Unable to parse String");
						}
				    }
				 });
		        
		        
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			 Log.e("YOUR_APP_LOG_TAG", "I got an error", e1);
		}
 	   
       
		 
    }
		 
	 public void viewEvent(View view, List<Transfer> events){
		SimpleDateFormat ft = new SimpleDateFormat("M d, y", Locale.ENGLISH);
		Intent intent = new Intent(this,ViewScheduleActivity.class);
		String names[] = new String[events.size()];
		String startDates[] = new String[events.size()];
		String endDates[] = new String[events.size()];
		String descriptions[] = new String[events.size()];
		
		int i = 0;
		for(Transfer e: events){
			names[i] = e.getEventName();
			startDates[i] = ft.format(e.getStartDate());
			endDates[i] = ft.format(e.getEndDate());
			descriptions[i] = e.getDescription();
		}
		
		intent.putExtra("names", names);
		intent.putExtra("startDates", startDates);
		intent.putExtra("endDates", endDates);
		intent.putExtra("descriptions", descriptions);
		startActivity(intent);
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.caledar_view, menu);
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
