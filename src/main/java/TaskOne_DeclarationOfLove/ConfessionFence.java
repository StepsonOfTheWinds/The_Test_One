package TaskOne_DeclarationOfLove;

import java.util.Scanner;

public class ConfessionFence {

    /*На заборе мальчик пишет для девочки признание в любви из 15 букв и 3 пробелов.
    На каждые 3 буквы тратится по 62см длины забора; на каждый пробел - 12см.
    Нужна программа, которая по длине забора (введённой юзером) посчитает, поместится ли
    на забор такой длины надпись мальчика.
     */

    int numOfLetters = 15;
    int numOfSpaces = 3;
    int fenceLength;

    int calculationResult;

    public void setFenceLength() {
        Scanner inFenceLength = new Scanner(System.in);
        int fl;
        System.out.println("Введите длину забора.");
        if (inFenceLength.hasNextInt()) {
            fl = inFenceLength.nextInt();
            fenceLength = fl;
        } else {
            System.err.println("Введите число c помощью цифр на клавиатуре!");
        }
    }

    public void mainCalculation (int fenceLength) {
        int allLetterLength = (numOfLetters/3) * 62;
        int allSpaseLength = numOfSpaces * 12;
        calculationResult = allLetterLength + allSpaseLength;
        if ((calculationResult) <= fenceLength) {
            System.out.println("Для надписи нужно " + calculationResult + " см.");
            System.out.println("Вашей длины в " + fenceLength + " см достаточно для признания!");
        } else {
            int diff = calculationResult - fenceLength;
            System.out.println("Заявленной длины забора недостаточно. Нужно ещё " + diff + " см.");
            System.out.println("Придётся поискать забор помасштабнее...");
        }
    }

    public static void main(String[] args) {
        ConfessionFence FirstLove = new ConfessionFence();
        FirstLove.setFenceLength();
            FirstLove.mainCalculation(FirstLove.fenceLength);
    }
}
