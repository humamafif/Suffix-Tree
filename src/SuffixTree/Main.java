package SuffixTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Suffix app = new Suffix();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Input text : ");
            String text = input.readLine();
            app.buildSuffixTree(text);

            System.out.print("input nama file (.dot) : ");
            String namaFile = input.readLine();
            app.generateFile(namaFile);
        } catch (Exception e){
            System.err.println("Error : " + e);
        }
    }
}
//dot -Tpng -o namafile.png namafile.dot