package com.droid.droidstickyscheduler;

/**
 * Transfer Object for the Data Access Object.
 * For use in system scheduler.
 * 
 * @author Louie
 * @version Nov. 19, 2014, 7:51 AM
 */
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlType( propOrder = {"eventName", "startDate","endDate","isAllDay","repeatEvery","repeatUntil","description"})
public class Transfer
{
    // instance variables - replace the example below with your own
    private String event_name;
	private Date start_date;
    private Date end_date;
    private Boolean is_all_day;
    private Date repeat_every;
    private Date repeat_until;
    private String description;
    //private android.location.Location
    
    public String toString(){
        return 
        event_name + "\n" +
        start_date.toString() + "\n" +
        end_date.toString() + "\n" +
        is_all_day.toString() + "\n" +
        repeat_every.toString() + "\n" +
        repeat_until.toString() + "\n" +
        description;
    }
    
    public String getEventName(){
    	return event_name;
    }
    
    public Date getStartDate(){
        return start_date;
    }
    public Date getEndDate(){
        return end_date;
    }
    public Boolean getIsAllDay(){
        return is_all_day;
    }
    public Date getRepeatEvery(){
        return repeat_every;
    }
    public Date getRepeatUntil(){
        return repeat_until;
    }
    public String getDescription(){
        return description;
    }
    
    @XmlElement
    public void setEventName(String event_name){
    	this.event_name = event_name;
    }
    @XmlElement
    public void setStartDate(Date start_date){
        this.start_date = start_date;
    }
    @XmlElement
    public void setEndDate(Date end_date){
        this.end_date = end_date;
    }
    @XmlElement
    public void setIsAllDay(Boolean is_all_day){
        this.is_all_day = is_all_day;
    }
    @XmlElement
    public void setRepeatEvery(Date repeat_every){
        this.repeat_every = repeat_every;
    }
    @XmlElement
    public void setRepeatUntil(Date repeat_until){
        this.repeat_until = repeat_until;
    }
    @XmlElement
    public void setDescription(String description){
        this.description = description;
    }
}
