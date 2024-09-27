/*
 * Philip Leake
 * cmsc-256-001
 * Spring Semester
 * MorseCodeTree.java
 * Implements own binary search tree using morse code
 */
package cmsc256;
import bridges.base.*;
import bridges.connect.*;
import bridges.validation.*;

import java.util.*;
import java.io.*;
public class MorseCodeTree {
    private BSTElement<String, MorseCharacter> root = new BSTElement<String, MorseCharacter>("Root", "", new MorseCharacter());
    private HashMap<Character,String> b = new HashMap<>();

    private int NumberOfNodes = -1;
    public MorseCodeTree(BSTElement<String,MorseCharacter> ent) {
        root = ent;
        NumberOfNodes++;
    }

    public MorseCodeTree() {
        root = null;
    }
    public int getHeight() {
        if(isEmpty()) {
            return 0;
        }
        return getRealHeight(root) -1;
        //return 0;
    }
    private int getRealHeight(BSTElement a) {
        if (a == null) {
            return 0;
        }
        int l = getRealHeight(a.getLeft());
        int r = getRealHeight(a.getRight());
        if (l > r) {
            return l +1;
        }
        else {
            return r +1;
        }
        //return 1 + Math.max(getRealHeight(a.getLeft()), getRealHeight(a.getRight()));
    }
    public int getNumberOfNodes() {
        if(root==null) {
            return 0;
        }
        return NumberOfNodes;
    }
    public BSTElement getRoot() {
        return root;
    }
    public boolean isEmpty() {
        return root==null;
    }
    public void clear() {
        root = null;
        NumberOfNodes = 0;
    }
    public void add(BSTElement<String,MorseCharacter> node) {
        NumberOfNodes++;
        if(root == null) {
            root = new BSTElement<>("","",new MorseCharacter());
        }
        BSTElement<String,MorseCharacter> current = root;
        String hold = node.getValue().getCode();
        for(int i = 0; i < hold.length(); i++) {
            char a = hold.charAt(i);
            if(a == '.') {
                if(current.getLeft() == null) {
                    current.setLeft(new BSTElement<>("",hold.substring(0,i+1),new MorseCharacter()));
                }
                current = current.getLeft();

            }
            else if (a == '-') {
                if (current.getRight() == null) {
                    current.setRight(new BSTElement<>("",hold.substring(0,i+1),new MorseCharacter()));
                }
                current = current.getRight();

            }
        }
        current.setLabel(node.getLabel());
        current.setValue(node.getValue());
        b.put(current.getValue().getLetter(), current.getKey());


        /*current = addHelp(root,a);


        */
    }

    public String encode(Character a) {
        if(a == null) {
            throw new IllegalArgumentException();
        }


        return b.get(a);
    }
    public Character decode(String a) {
        if(a == null) {
            throw new IllegalArgumentException();
        }
        BSTElement<String,MorseCharacter> current = root;
        char[] b = a.toCharArray();
        for(int i = 0; i < b.length; i++) {
            if(b[i] == '.') {
                current = current.getLeft();
            }
            else if (b[i] == '-'){
                current = current.getRight();
            }
        }
        return current.getValue().getLetter();
    }
    public BSTElement decodeHelper(BSTElement<String,MorseCharacter> a, String b) {
        return a;
    }
    public String inOrderTraversal(BSTElement<String,MorseCharacter> a) {
        if(a == null) {
            return "";
        }
        String b;
        if(a.getValue().getLetter() == '\0') {
            b = "";
        }
        else {
            b = a.getValue().toString();
        }
        return inOrderTraversal(a.getLeft()) + b + inOrderTraversal(a.getRight());
    }
    public String levelOrderTraversal(BSTElement<String,MorseCharacter> a) {
        if(a == null) {
            return "";
        }
        String res = "";
        Queue<BSTElement<String, MorseCharacter>> b = new LinkedList<>();
        b.add(a);
        while(!b.isEmpty()) {
            for(int i = 0; i < b.size(); i++) {
                BSTElement<String,MorseCharacter> current = b.remove();
                if(current.getValue().getLetter() == '\0') {
                    res += "";
                }
                else {
                    res += current.getValue().getLetter();
                }
                if(current.getLeft() != null) {
                    b.add(current.getLeft());
                }
                if(current.getRight() != null) {
                    b.add(current.getRight());
                }
            }
        }
        return res;
    }
    public String postOrderTraversal(BSTElement<String,MorseCharacter> a) {
        if(a == null) {
            return "";
        }
        String b;
        if(a.getValue().getLetter() == '\0') {
            b = "";
        }
        else {
            b = a.getValue().toString();
        }
        return postOrderTraversal(a.getLeft()) + postOrderTraversal(a.getRight()) +b;
    }
    public String preOrderTraversal(BSTElement<String,MorseCharacter> a) {
        if(a == null) {
            return "";
        }
        String b;
        if(a.getValue().getLetter() == '\0') {
            b = "";
        }
        else {
            b = a.getValue().toString();
        }
        return b+ preOrderTraversal(a.getLeft()) + preOrderTraversal(a.getRight());
    }
    public static void main(String args[]) throws RateLimitException, IOException {
        Bridges bridges = new Bridges(5, "leakepa", "1482812171976");
        bridges.setTitle("Project6");
        BSTElement<String, MorseCharacter> rootEl = new BSTElement<String, MorseCharacter>("Root", "", new MorseCharacter());
        MorseCodeTree tree1 = new MorseCodeTree(rootEl);
        try {
            File in = new File("codefile.dat");
            Scanner scan = new Scanner(in);
            while(scan.hasNextLine()) {
                String a = scan.nextLine();
                String[] b = a.split(" ");
                MorseCharacter one = new MorseCharacter(b[0].charAt(0), b[1]);
                System.out.println(one.toString() + " " + one.getCode());
                BSTElement<String,MorseCharacter> ent = new BSTElement<>(one.getCode(), one);
                tree1.add(ent);
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        /*MorseCharacter b = new MorseCharacter<>('A',".-");
        BSTElement<String,MorseCharacter> a = new BSTElement<>(b.getCode(),b);
        MorseCodeTree tree2 = new MorseCodeTree(a);
        tree1.add(a);
        */
        bridges.setDataStructure(rootEl);
        bridges.visualize();
        System.out.println(rootEl.getLeft()== null);
        System.out.println(tree1.NumberOfNodes);
        System.out.println(tree1.getHeight());
        System.out.println(tree1.isEmpty());
        System.out.print(tree1.preOrderTraversal(rootEl));



    }
}
