package Task4;
import java.util.*;

public class BinarySearchTree{
    Node root;
    public void Add(int num, Object info){
        Node node = new Node(num, info);

        if (root == null){
            root = node;
        }else{

            Node parent;
            Node point = root;

            while (true){
                parent = point;
                if (num < point.num){
                    point = point.left;

                    if (point == null){
                        parent.left = node;
                        return;
                    }

                }else if (num > point.num){

                    point = point.right;
                    if (point == null){
                        parent.right = node;
                        return;
                    }

                }else{
                    point.info = info;
                    return;
                }
            }
        }
    }

    public Node findNode(int num){
        Node current = root;

        while (current.num != num){

            if (num < current.num){
                current = current.left;
            }else{
                current = current.right;
            }

            if (current == null)
                return null;
        }
        return current;
    }

    public void WalkInOrder(Node node){

        if (node != null){
            WalkInOrder(node.left);
            System.out.print(node.info);
            WalkInOrder(node.right);
        }
    }
}