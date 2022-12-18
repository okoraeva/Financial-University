package Task4;
import java.util.*;

public class Main {

    public static void main(String[] args){

        BinarySearchTree tree1 = new BinarySearchTree();

        tree1.Add(1, "ex");
        tree1.Add(49, "Hello");
        tree1.Add(100, "meow");
        tree1.Add(75, "boom");

        tree1.WalkInOrder(tree1.root);
        System.out.println("\n");

        try{
            tree1.findNode(150).describeNode();
        }catch (NullPointerException e){
            System.out.println("Узла не существует");
        }

    }
}