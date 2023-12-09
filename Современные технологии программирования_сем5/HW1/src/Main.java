import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    private int id;
    private Node parent;
    private List<Node> children;

    public Node(int id) {
        this.id = id;
        this.children = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public boolean isRoot() {
        return parent == null;
    }
}

class Tree {
    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public List<Node> getAllNodes() {
        List<Node> nodes = new ArrayList<>();
        traverse(root, nodes);
        return nodes;
    }

    private void traverse(Node node, List<Node> nodes) {
        nodes.add(node);
        for (Node child : node.getChildren()) {
            traverse(child, nodes);
        }
    }

    public List<Node> getAllLeaves() {
        List<Node> leaves = new ArrayList<>();
        traverseLeaves(root, leaves);
        return leaves;
    }

    private void traverseLeaves(Node node, List<Node> leaves) {
        if (node.isLeaf()) {
            leaves.add(node);
        }
        for (Node child : node.getChildren()) {
            traverseLeaves(child, leaves);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        List<Tree> trees = readInputFile("/home/olya/Рабочий стол/HW1/src/input.csv");
        Tree treeWithMaxLeaves = findTreeWithMaxLeaves(trees);

        if (treeWithMaxLeaves == null) {
            writeOutputFile("/home/olya/Рабочий стол/HW1/src/output.csv", 0, 0);
        } else {
            int maxLeaves = treeWithMaxLeaves.getAllLeaves().size();
            writeOutputFile("/home/olya/Рабочий стол/HW1/src/output.csv", treeWithMaxLeaves.getRoot().getId(), maxLeaves);
        }
    }

    private static List<Tree> readInputFile(String filename) {
        List<Tree> trees = new ArrayList<>();
        Map<Integer, Node> nodeMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int nodeId = Integer.parseInt(parts[0]);
                int parentId = Integer.parseInt(parts[1]);

                Node node = nodeMap.getOrDefault(nodeId, new Node(nodeId));
                Node parent = nodeMap.getOrDefault(parentId, new Node(parentId));

                node.setParent(parent);
                parent.addChild(node);

                nodeMap.put(nodeId, node);
                nodeMap.put(parentId, parent);
            }

            for (Node node : nodeMap.values()) {
                if (node.isRoot()) {
                    trees.add(new Tree(node));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trees;
    }

    private static Tree findTreeWithMaxLeaves(List<Tree> trees) {
        Tree treeWithMaxLeaves = null;
        int maxLeaves = 0;

        for (Tree tree : trees) {
            List<Node> leaves = tree.getAllLeaves();
            int numLeaves = leaves.size();
            if (numLeaves > maxLeaves) {
                treeWithMaxLeaves = tree;
                maxLeaves = numLeaves;
            } else if (numLeaves == maxLeaves) {
                treeWithMaxLeaves = null; // Multiple trees with the same number of leaves
                break;
            }
        }

        return treeWithMaxLeaves;
    }

    private static void writeOutputFile(String filename, int treeId, int numLeaves) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(treeId + "," + numLeaves);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}