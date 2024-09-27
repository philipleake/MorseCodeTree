package cmsc256;

import bridges.base.BSTElement;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException, RateLimitException {
        MorseCodeTree tree = new MorseCodeTree();
        populate(tree, new File("codefile.dat"));
        Bridges bridges = new Bridges(9, "samuelsarzaba", "354265675431");
        bridges.setDataStructure(tree.getRoot());
        bridges.visualize();
        System.out.println("getRoot:" + printNode(tree.getRoot()));
        System.out.println("getHeight: " + tree.getHeight());
        System.out.println("getNumberOfNodes: " + tree.getNumberOfNodes());
        System.out.println("isEmpty: " + tree.isEmpty());
        System.out.println("*CLEARING*");
        tree.clear();
        System.out.println("getHeight: " + tree.getHeight());
        System.out.println("getNumberOfNodes: " + tree.getNumberOfNodes());
        System.out.println("isEmpty: " + tree.isEmpty());
        System.out.println("*POPULATING*");
        populate(tree, new File("codefile.dat"));
        System.out.println("inOrderTraversal: " + tree.inOrderTraversal(tree.getRoot()));
        System.out.println("levelOrderTraversal: " + tree.levelOrderTraversal(tree.getRoot()));
        System.out.println("postOrderTraversal: " + tree.postOrderTraversal(tree.getRoot()));
        System.out.println("preOrderTraversal: " + tree.preOrderTraversal(tree.getRoot()));
        System.out.println(tree.inOrderTraversal(tree.getRoot()).equals("5H4SV3IFU?_2ELR!APWJ'16-BDXNC:KYT7Z.GQM;8O9,0"));
    }

    private static String printNode(BSTElement<String, MorseCharacter> n) {
        return String.format("%s%nK: %s%nE: %s", n.getLabel(), n.getKey(), n.getValue().toString());
    }

    private static void populate(MorseCodeTree t, File f) {
        try {
            Scanner reader = new Scanner(f);
            while (reader.hasNext()) {
                String k = reader.next();
                String v = reader.next();
                t.add(new BSTElement<>(k, v, new MorseCharacter(k.charAt(0), v)));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
}
