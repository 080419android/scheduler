
/**
 * Write a description of class iWrap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
public class iWrap
{
    // instance variables - replace the example below with your own
    private ArrayList<Transfer> list = new ArrayList<Transfer>();
    
    public iWrap(){
        list = new ArrayList<Transfer>();
    }
    
    public ArrayList<Transfer> getList(){
        return list;
    }
    
    @XmlElementWrapper(name = "transfers")
    
    @XmlElement( name = "transfer")
    public void setList(ArrayList<Transfer> orderDetailList){
        this.list = orderDetailList;
    }
}
