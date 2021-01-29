public class Node {

    String name;
    Node left; //Children
    Node right; //Sibling
    Node parent;

    int numberOfChildren;
    int value;

    public Node(String name, int numberOfChildren, Node parent, int value) {
        this.name = name;
        this.numberOfChildren = numberOfChildren;
        this.parent = parent;
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Node add(String name, int numberOfChildren, Node location, Node parent, int value){
        if(location == null)
            return new Node(name, numberOfChildren, parent, value);

        if(value < location.value){
            location.left = add(name, numberOfChildren, location.left, parent, value);
        }
        else
            location.right = add(name, numberOfChildren, location.right, parent, value);

        return location;
    }

    @Override
    public String toString() {
        return "Name: "+ name +" Brother: " +right + "\n" + name+" has "+numberOfChildren+ " son(s): " +left;
    }
}
