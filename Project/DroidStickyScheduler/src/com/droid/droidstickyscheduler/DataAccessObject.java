package com.droid.droidstickyscheduler;

/**
 * Write a description of class DataAccessObject here.
 * 
 * @author Loie Buera
 * @version Nov. 19, 2014, 8:22 AM
 */

import java.util.ArrayList;
import java.io.File;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.Date;

public class DataAccessObject
{

    public static void test(){
        Transfer tr = new Transfer();
        tr.setEventName("Event1");
        tr.setDescription("testing123");
        tr.setStartDate(new Date());
        tr.setIsAllDay(false);
        tr.setEndDate(new Date());
        tr.setRepeatEvery(new Date());
        tr.setRepeatUntil(new Date());
        
        Transfer tr2 = new Transfer();
        tr2.setEventName("Event2");
        tr2.setDescription("Test");
        tr2.setStartDate(new Date());
        tr2.setIsAllDay(true);
        tr2.setEndDate(new Date());
        tr2.setRepeatEvery(new Date());
        tr2.setRepeatUntil(new Date());
        
        iWrap iWrap = new iWrap();
        iWrap.getList().add(tr);
        iWrap.getList().add(tr2);
        
        File file = new File("test.xml");
        save(iWrap.getList(),file);
    }
    
    public static void save(ArrayList<Transfer> list, File f){
        iWrap iWrap = new iWrap();
        iWrap.setList(list);
        try{
            JAXBContext jaxcont = JAXBContext.newInstance(iWrap.class);
            Marshaller marsh = jaxcont.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marsh.marshal(iWrap,f);
        }catch(JAXBException e){
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Transfer> load(InputStream f){
        try{
            JAXBContext jaxcont = JAXBContext.newInstance(iWrap.class);
            Unmarshaller unmarsh = jaxcont.createUnmarshaller();
            iWrap iWrap = (iWrap) unmarsh.unmarshal(f);
            return iWrap.getList();
        }catch(JAXBException e){
            e.printStackTrace();
        }
        return null;
    }
    

}
