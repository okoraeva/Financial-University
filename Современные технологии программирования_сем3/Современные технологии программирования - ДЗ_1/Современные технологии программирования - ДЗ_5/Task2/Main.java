import java.util.*;
import java.util.Arrays;
import java.util.Random;

public class Main{

    public static void main(String[] args){
        int[] intList = new int[100000000];
        Random randomElement = new Random();
        for (int i = 0; i < intList.length; i++){
            intList[i] = randomElement.nextInt();
        }
        Arrays.sort(intList);

        long start = System.currentTimeMillis();
        System.out.println(perebor(intList, 2000));
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Перебор занял: " + elapsed);

        start = System.currentTimeMillis();
        System.out.println(binaryRecursive(intList, 2000, 0, intList.length));
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        System.out.println("Двоичный поиск занял: " + elapsed);
    }

    public static boolean perebor(int[] list, int element){
        for (int j : list){
            if (element == j){
                return true;
            }
        }
        return false;
    }

    public static int binaryRecursive(int[] list, int element, int low, int high){
        int middle = low  + ((high - low) / 2);

        if (high < low){
            return -1;
        }

        if (element == list[middle]){
            return middle;
        }else if (element < list[middle]){
            return binaryRecursive(list, element, low, middle - 1);
        }else{
            return binaryRecursive(list, element, middle + 1, high);
        }
    }
}
