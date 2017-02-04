package ex7;

public class Node {
    private Node next;
    private String data;
    public static void main(String[] args) {
        Node alice   = new Node("Alice");
        Node bob     = new Node("Bob");
        Node charlie = new Node("Charlie");
        Node diana   = new Node("Diana");
        Node elmo    = new Node("Elmo");
        Node fred    = new Node("Fred");
        alice.setNext(bob).setNext(charlie).setNext(diana).setNext(elmo);
        alice.getNext().getNext().getNext().getNext().setNext(fred);

        Node p = alice;
        while (p != null) {
            System.out.print(p + " -> ");
            p = p.getNext();
      }
    }
    public Node(String data){
        next = null;
        this.data = data;
    }
    public Node setNext(Node node){
        next = node;
        return node;
    }
    public Node getNext(){
        return next;
    }
    public String getData(){
        return data;
    }
    public String toString(){
        return "[" + getData() + "]";
    }
}
