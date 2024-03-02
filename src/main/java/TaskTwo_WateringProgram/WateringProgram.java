package TaskTwo_WateringProgram;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class WateringProgram {

    Date lastWateringDate;
    int currentMonth;
    boolean humidityFlag = false;
    Date currentDate = new Date();
    Date nextWateringDate = new Date();

    /* У меня на подоконнике растёт кактус. Чтоб не забыть про его полив, я купил датчик влажности и решил составить программу
    для отслеживания времени полива.
    На вход программа ждет дату последнего полива кактуса. Если сейчас зима(декабрь-февраль),то кактус поливается раз вмесяц.
    Если осень или весна-раз в неделю. Летом поливается в зависимости от влажности воздуха,но не чаще одного раза в два дня.
    Значение влажности воздуха в программу попадает автоматически от датчика. Если влажность меньше 30%, кактус нужно полить.
    Ожидаю от программы,что после проверки всех данных она выведет сообщение с датой, когда надо поливать кактус.
    */

    public void toGetLastDate() throws ParseException {
        System.out.println("Введите дату последнего полива в формате 'dd.MM.yyyy':");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Scanner inputLastDate = new Scanner(System.in);
        lastWateringDate = dateFormat.parse(inputLastDate.nextLine());
    }

    public void toGetCurrentMonth() {
        Date currentDate = new Date();
        currentMonth = currentDate.getMonth();
        //Здесь намеренно использована устаревшая реализация класса Date вместо класса Calendar, потому что класс
        //Calendar не поддерживает Юлианский календарь, принятый в РФ.
    }

    public static void main(String[] args) throws ParseException {

        WateringProgram wP = new WateringProgram();
        Sensor sensor = new Sensor();

        wP.toGetLastDate();

        Calendar calendar = Calendar.getInstance();

        try {
            wP.toGetLastDate();
        } catch (Exception parseE) {
            System.out.println(parseE.getMessage());
        } finally {
            System.err.println("Неверный формат введённой даты!");
            System.exit(0);
        }

        wP.toGetCurrentMonth();

        if (wP.currentMonth<2||wP.currentMonth==11){
            calendar.setTime(wP.lastWateringDate);
            calendar.add(Calendar.MONTH,1);
            wP.nextWateringDate = calendar.getTime();
        } else if ((wP.currentMonth>1&&wP.currentMonth<5)||(wP.currentMonth>7&&wP.currentMonth<11)) {
            calendar.setTime(wP.lastWateringDate);
            calendar.add(Calendar.HOUR,168);
            wP.nextWateringDate = calendar.getTime();
        }else{
            if (sensor.humidityMeasurement()<0.3){
                wP.nextWateringDate=wP.currentDate;
                wP.humidityFlag=true;
            }
        }

        if(wP.currentDate.getTime()>wP.nextWateringDate.getTime()) wP.nextWateringDate=wP.currentDate;

        if(wP.humidityFlag) {
            System.out.println("Поливать пока не нужно.");
        } else {
            System.out.println("Дата следующего полива: " + new SimpleDateFormat("dd.MM.yyyy").format(wP.nextWateringDate) + ".");
        }
    }
}
