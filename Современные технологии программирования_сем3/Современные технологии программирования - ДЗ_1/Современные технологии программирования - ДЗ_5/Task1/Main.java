import java.util.*; 

public class Main{

    public static void main(String[] args){
        System.out.println(num(7));
    }

    public static StringBuilder num(int number){
        StringBuilder str = new StringBuilder();
        if (number > 1){
            str.append(num(number-1));
            str.append(" ");
            str.append(number);
        }else{
            str.append(1);
        }
        return str;

    }
}

