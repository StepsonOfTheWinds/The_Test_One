package TaskTwo_WateringProgram;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
class SensorS{
    double measurement(){
        double a = Math.random();
        return a;
    }
}
public class Test {
    public static void main(String[] args) throws ParseException {
        System.out.println("Enter last date, format dd MM yyyy");
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
        Scanner s = new Scanner(System.in);
        Calendar cl = Calendar. getInstance();
        Date last_date = format.parse(s.nextLine());
        Date current_date = new Date();
        int month = current_date.getMonth();
        Date dest=new Date();
        SensorS sens = new SensorS();
        boolean flag = false;
        if (month<2||month==11){
            cl.setTime(last_date);
            cl.add(Calendar.MONTH,1);
            dest = cl.getTime();
        } else if ((month>1&&month<5)||(month>7&&month<11)) {
            cl.setTime(last_date);
            cl.add(Calendar.HOUR,168);
            dest = cl.getTime();
        }else{
            if (sens.measurement()<0.3){
                dest=current_date;
                flag=true;
            }
        }
        if (current_date.getTime()> dest.getTime()) dest=current_date;
        if (flag){
            System.out.println("No need to water");
        }else{
            System.out.println("Water date: "+new SimpleDateFormat("dd MM YYYY").format(dest));
        }
    }
}