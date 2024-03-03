package TaskTwo_WateringProgram;

class Sensor {

    double humidity;

    double measurement() {
        humidity = Math.random();
        return humidity;
    }
}

/*Ниже - вариант на тот случай, если датчик возвращает проценты как целые числа, а гипотетическая влажность
может падать до 0% (пустыня) или достигать 100% (затопленная комната).
 */
//    int measurement() {
//        double dHumidity = Math.random() * 101;
//        if (dHumidity >= 1) {
//            humidity = (int) (Math.floor(dHumidity));
//        }
//        if (dHumidity <= 1 & dHumidity >= 0) {
//            humidity = 0;
//        }
//        return humidity;
//    }
//}