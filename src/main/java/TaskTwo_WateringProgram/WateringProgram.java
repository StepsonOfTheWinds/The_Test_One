package TaskTwo_WateringProgram;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class WateringProgram {

    Date lastWateringDate;
    int currentMonth;
    boolean summerWateringFlag = false;
    Date currentDate = new Date();
    Date nextWateringDate = new Date();

    /* У меня на подоконнике растёт кактус. Чтоб не забыть про его полив, я купил датчик влажности и решил составить программу
    для отслеживания времени полива.
    На вход программа ждет дату последнего полива кактуса. Если сейчас зима(декабрь-февраль),то кактус поливается раз вмесяц.
    Если осень или весна-раз в неделю. Летом поливается в зависимости от влажности воздуха,но не чаще одного раза в два дня.
    Значение влажности воздуха в программу попадает автоматически от датчика. Если влажность меньше 30%, кактус нужно полить.
    Ожидаю от программы,что после проверки всех данных она выведет сообщение с датой, когда надо поливать кактус.
    */

    void toGetLastDate() {
        try {
            System.out.println("Введите дату последнего полива в формате 'дд.мм.гггг':");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Scanner inputLastDate = new Scanner(System.in);
            lastWateringDate = dateFormat.parse(inputLastDate.nextLine());
        } catch (ParseException parseEx) {
            System.err.println("Неверный формат введённой даты!");
            System.exit(0);
        }
    }

    void toGetCurrentMonth() {
        Date current_date = new Date();
        currentMonth = current_date.getMonth();
    }

    public static void main(String[] args) {

        WateringProgram wP = new WateringProgram();
        Sensor sensor = new Sensor();

        Calendar calendar = Calendar.getInstance();

        wP.toGetLastDate();

        wP.toGetCurrentMonth();

        if (wP.currentMonth<2||wP.currentMonth==11){
            calendar.setTime(wP.lastWateringDate);
            calendar.add(Calendar.MONTH,1);
            wP.nextWateringDate = calendar.getTime();
        } else if ((wP.currentMonth>1 && wP.currentMonth<5)||(wP.currentMonth>7 && wP.currentMonth<11)) {
            calendar.setTime(wP.lastWateringDate);
            calendar.add(Calendar.HOUR,168);
            wP.nextWateringDate = calendar.getTime();
        } else {
            if (sensor.measurement()<0.3) {
                wP.nextWateringDate = wP.currentDate;
                wP.summerWateringFlag = true;
            }
        }

        if(wP.currentDate.getTime()>wP.nextWateringDate.getTime()) wP.nextWateringDate = wP.currentDate;

        if(wP.summerWateringFlag) {
            if((wP.lastWateringDate).before(wP.currentDate)) {
                System.out.println("Сегодня поливка не требуется.");
            } else {
                System.out.println("Дата следующего полива: " + new SimpleDateFormat("dd.MM.yyyy").format(wP.nextWateringDate) + ".");
            }
        } else {
            System.out.println("Дата следующего полива: " + new SimpleDateFormat("dd.MM.yyyy").format(wP.nextWateringDate) + ".");
        }
    }
}
