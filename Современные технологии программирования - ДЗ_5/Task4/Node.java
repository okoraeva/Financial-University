package Task4;
import java.util.*;

public class Node{
    int num;
    Object info;
    Node left;
    Node right;

    Node(int num, Object info){
        this.num = num;
        this.info = info;
    }

    public void describeNode(){
        System.out.println("Это узел " + this.num + " : " + this.info);
        if (this.left != null){
            System.out.println("Левый дочерний узел: " + this.left.num + " : " + this.left.info);
        }
        if (this.right != null){
            System.out.println("Правый дочерний узел: " + this.right.num + " : " + this.right.info);
        }
        if (this.right == null & this.left == null){
            System.out.println("Дочерних узлов нет");
        }
        System.out.println("\n");
    }
}