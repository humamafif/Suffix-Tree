package coba;

import java.util.ArrayList;
import java.util.Collections;

class TrieNode {

    char c;
    TrieNode[] children = new TrieNode[27];
    String endOfWord = null;

    public TrieNode(char c) {
        this.c = c;
    }
}

    class SuffixTree {
        private TrieNode root = new TrieNode(' ');

        public void insert(String string) {
            insertRec(root, string, 0);
        }

        private void insertRec(TrieNode root, String string, int index) {
            if (index == string.length()) {
                root.endOfWord = string;
                return;
            }

            char c = string.charAt(index);
            if (root.children[c - 'a'] == null) {
                root.children[c - 'a'] = new TrieNode(c);
            }

            insertRec(root.children[c - 'a'], string, index + 1);
        }

        public void makeSuffixTree(String string) {
            string = string + "{";
            for (int i = 0; i < string.length(); i++) {
                insert(string.substring(i));
            }
        }

        public void display() {
            ArrayList<String> output = new ArrayList<>();
            displayRec(root, new StringBuilder(), output);
            Collections.sort(output);
            for (String str : output) {
                System.out.println(str);
            }
        }

        private void displayRec(TrieNode root, StringBuilder sb, ArrayList<String> output) {
            if (root == null) {
                return;
            }

            sb.append(root.c);

            if (root.endOfWord != null) {
                output.add(sb.toString());
            }

            for (char c = 0; c < 26; c++) {
                if (root.children[c] != null) {
                    displayRec(root.children[c], sb, output);
                }
            }

            sb.deleteCharAt(sb.length() - 1);
        }
    }


    class Main {
        public static void main(String[] args) {
            SuffixTree st = new SuffixTree();
            st.makeSuffixTree("nanana");
            st.display();
        }
    }
