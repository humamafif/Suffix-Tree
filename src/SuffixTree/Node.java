package SuffixTree;

public class Node {
    Node[] children = new Node[256];
    boolean isEnd;

    Node(){
        this.isEnd = false;
    }
}