
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author plantaNieves
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR, 2013);
        c.set(Calendar.MONTH,11);
        c.set(Calendar.DAY_OF_MONTH, 30);
        
        Date d=c.getTime();
        
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        
        System.out.println(sdf.format(d));
        System.out.println(c.get(Calendar.WEEK_OF_YEAR));
    }
    
}
