package SuffixTree;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Suffix {
    Node root;
    int nodeCounter;
    
    Suffix(){
        this.root = new Node();
        this.nodeCounter = 0;
    }
    
    void buildSuffixTree(String text){
        for (int i = 0; i < text.length(); i++) {
            insert(text, i);
        }
    }
    
    void insert(String text, int startIndex){
        Node currentNode = root;
        for (int i = startIndex; i < text.length(); i++) {
            char currenChar = text.charAt(i);
            Node child = currentNode.children[currenChar];

            if (child == null){
                child = new Node();
                currentNode.children[currenChar] = child;
            }
            currentNode = child;
        }
        currentNode.isEnd = true;
    }

    void generateFile(String filename){
        try(PrintWriter writer = new PrintWriter(new FileWriter(filename))){
            writer.println("digraph SuffixTree {");
            writer.println("  node [shape=circle]");
            generateGraphviz(root, "root", writer);
            writer.println("}");
            System.out.println("generate file succes!!");
        } catch (Exception e){
            System.err.println("Error : " + e);
        }
    }

    void generateGraphviz(Node node, String parentLabel, PrintWriter writer){
        int currentNodeNumber = nodeCounter++;
        String currentNodeLabel = "node" + currentNodeNumber;
        writer.println("  " + currentNodeLabel + " [label=\"" + currentNodeNumber + "\", shape=circle]");

        if (node.isEnd){
            writer.println("  " + currentNodeLabel + " [shape=doublecircle]");
        }

        for (int i = 0; i < node.children.length; i++) {
            if (node.children[i] != null){
                char currentChar = (char) i;
                String edgeLabel = Character.toString(currentChar);
                generateGraphviz(node.children[i], currentNodeLabel, writer);
                writer.println("  " + parentLabel + " -> " + currentNodeLabel + " [label=\"" + edgeLabel + "\"]");
            }
        }
    }
}
