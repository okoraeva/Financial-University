package org.example;

import org.example.database.DatabaseInterface;
import org.example.database.H2Database;
import org.example.tree.Node;
import org.example.tree.Tree;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        H2Database database = new H2Database();
//        createAndPopulateTreesTable(database);
        List<Tree> trees = readTreesFromDatabase(database);
        int totalLeaves = getTotalLeaves(trees);

        writeOutputFile("output.csv", totalLeaves);
    }

//    private static void createAndPopulateTreesTable(DatabaseInterface databaseInterface) {
//        try (Connection connection = databaseInterface.getConnection();
//             Statement statement = connection.createStatement()) {
//
//            String createTableQuery = "CREATE TABLE TREES (parent_id INT, id INT)";
//            statement.executeUpdate(createTableQuery);
//
//            String insertDataQuery = "INSERT INTO TREES (parent_id, id) VALUES "
//                    + "(1, 1), "
//                    + "(2, 1), "
//                    + "(3, 1), "
//                    + "(4, 2), "
//                    + "(5, 2), "
//                    + "(6, 3), "
//                    + "(7, 3), "
//                    + "(8, 3), "
//                    + "(9, 4), "
//                    + "(10, 4), "
//                    + "(11, 11), "
//                    + "(12, 11), "
//                    + "(13, 12), "
//                    + "(14, 12)";
//            statement.executeUpdate(insertDataQuery);
//
//            System.out.println("Таблица TREES создана и заполнена данными.");
//        } catch (SQLException e) {
//            System.err.println("Произошла ошибка при создании и заполнении таблицы TREES: " + e.getMessage());
//        }
//    }


    private static List<Tree> readTreesFromDatabase(DatabaseInterface databaseInterface) {
        List<Tree> trees = new ArrayList<>();

        try (Connection connection = databaseInterface.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, parent_id FROM TREES");

            Map<Integer, Node> nodeMap = new HashMap<>();

            while (resultSet.next()) {
                int nodeId = resultSet.getInt("id");
                int parentId = resultSet.getInt("parent_id");

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trees;
    }

    private static int getTotalLeaves(List<Tree> trees) {
        int totalLeaves = 0;

        for (Tree tree : trees) {
            List<Node> leaves = tree.getAllLeaves();
            totalLeaves += leaves.size();
        }

        return totalLeaves;
    }

    private static void writeOutputFile(String filename, int totalLeaves) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(String.valueOf(totalLeaves));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
