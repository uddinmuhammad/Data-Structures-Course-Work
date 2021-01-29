import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataCollector {

    private String familyTreeFile = "src/FamilyTree.txt";

    public void readInputData() {
        try {
            Scanner fTree = new Scanner(new File(familyTreeFile));
            ArrayList<Node> nodesList = new ArrayList<>();
            Node root;
            Node focusNode;
            String name;
            int numberOfChildren;
            int value = 10;

            name = fTree.next();
            numberOfChildren = fTree.nextInt();

            root = new Node(name, numberOfChildren, null, value);
            focusNode = root;
            System.out.println(root.name + " is the root Node");
            if (root.numberOfChildren > 0) {
                nodesList.add(root);
            }
            int index = 0;
            while (fTree.hasNext()) {

                while (nodesList.get(0).numberOfChildren != index) {

                    //addingChildren
                    if (nodesList.get(0).numberOfChildren > 0 && index == 0) {
                        name = fTree.next();
                        numberOfChildren = fTree.nextInt();
                        value = focusNode.value - 1;
                        focusNode.add(name, numberOfChildren, focusNode, nodesList.get(0), value);

                        System.out.println(focusNode.left.name + " is eldest son of " + focusNode.name);

                        if (focusNode.left.numberOfChildren > 0) {
                            nodesList.add(focusNode.left);
                        }
                        focusNode = focusNode.left;
                        index++;
                    }

                    //adding Siblings
                    if (nodesList.get(0).numberOfChildren > 1 && index > 0) {
                        while (index < nodesList.get(0).numberOfChildren) {
                            name = fTree.next();
                            numberOfChildren = fTree.nextInt();
                            value = focusNode.value + 1;
                            focusNode.add(name, numberOfChildren, focusNode, focusNode.parent, value);

                            System.out.println(focusNode.right.name + " is younger brother of " + focusNode.name);

                            if (focusNode.right.numberOfChildren > 0)
                                nodesList.add(focusNode.right);
                            focusNode = focusNode.right;
                            index++;
                        }
                    }

                }
                if (nodesList.get(0).numberOfChildren <= index) {
                    nodesList.remove(0);
                    if (nodesList.size() != 0)
                        focusNode = nodesList.get(0);
                    index = 0;
                }
            }

            System.out.println();
            System.out.println(root.toString());
            System.out.println();

            findSon(root.left);

            findFather(root.left);
            System.out.println();

            findBrothers(root.left);
            System.out.println();

            findOldestBrother(root.left);
            findYougestBrother(root.left);

            findOldestSon(root.left);
            findYoungestSon(root.left);
            findGrandFather(root.left);


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void start() {
        readInputData();
    }


    public void findFather(Node p) {
        if (p != null && p.parent != null)
            System.out.println(p.name + "'s father is " + p.parent.name);

        else
            System.out.println(p.name + "has is the root node");
    }

    public void findBrothers(Node p) {
        ArrayList<Node> brothers;
        if (p == null)
            System.out.println("given node is null");

        else if (p != null && p.parent == null)
            System.out.println(p.name + " is the root node");

        else {
            Node focusNode = p.parent;
            brothers = new ArrayList<>();
            brothers.add(focusNode.left);
            focusNode = focusNode.left;

            while (focusNode.right != null) {
                brothers.add(focusNode.right);
                focusNode = focusNode.right;
            }
            System.out.print(p.name + " are " + brothers.size() + " brothers: ");
            for (int i = 0; i < brothers.size(); i++) {
                System.out.print(brothers.get(i).name + ", ");
            }
        }
    }

    public void findOldestBrother(Node p) {
        if (p == null)
            System.out.println("given node is null");

        else if (p != null && p.parent == null)
            System.out.println(p.name + " is the root node");

        else {
            Node oldestBrother = p.parent.left;
            if (p.name.equals(oldestBrother.name))
                System.out.println(p.name + " is the oldest brother in all");
            else
                System.out.println(p.name + "'s oldest Brother is " + oldestBrother.name);
        }

    }

    public void findYougestBrother(Node p) {
        if (p == null)
            System.out.println("given node is null");

        else if (p != null && p.parent == null)
            System.out.println(p.name + " is the root node");

        else if (p.parent.left == p && p.right == null) {
            System.out.println(p.name + " is the only child of " + p.parent.name);
        } else if (p.parent.left != p && p.right == null) {
            System.out.println(p.name + " is the yougest brother of all");
        } else {
            Node focusNode = p.parent.left;
            while (focusNode.right != null) {
                focusNode = focusNode.right;
            }
            System.out.println(p.name + "'s yougest brother is " + focusNode.name);
        }

    }

    public void findSon(Node p) {
        if (p.numberOfChildren > 0) {
            ArrayList<Node> children = new ArrayList<>();
            children.add(p.left);
            int index = 0;
            while (children.get(index).right != null) {
                children.add(children.get(index).right);
                index++;
            }

            System.out.print(p.name + " has " + p.numberOfChildren + " Son(s): ");

            for (int i = 0; i < p.numberOfChildren; i++) {
                System.out.print(children.get(i).name + ", ");
            }
            System.out.println();
        } else
            System.out.println(p.name + " has no child");
    }

    public void findOldestSon(Node p) {
        if (p.left == null)
            System.out.println(p.name + " has no child");
        else
            System.out.println(p.name + "'s oldest son is " + p.left.name);
    }

    public void findYoungestSon(Node p) {
        if (p == null)
            System.out.println("given node is null");

        else if (p.left == null)
            System.out.println(p.name + "has no son");

        else {
            Node focusNode = p.left;

            while (focusNode.right != null) {
                focusNode = focusNode.right;
            }

            System.out.println(p.name + "'s youngest son is " + focusNode.name);
        }
    }

    public void findGrandFather(Node p){
        if (p == null)
            System.out.println("given node is null");

        else if(p.parent == null)
            System.out.println(p.name +"is the root node, it has no parents");

        else if(p.parent.parent == null)
            System.out.println(p.name+"'s father "+p.parent.name +" is the root Node therefore he has no Grandfather");

        else
            System.out.println(p.name+"'s grandfather is "+p.parent.parent);
    }
}
