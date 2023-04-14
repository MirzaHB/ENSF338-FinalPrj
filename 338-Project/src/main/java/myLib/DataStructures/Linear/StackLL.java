package main.java.myLib.DataStructures.Linear;
import main.java.myLib.DataStructures.Nodes.DNode;

public class StackLL extends SLL{

    public StackLL(){
        super();
    }

    public StackLL(DNode head){
        super(head);
    }

    @Override
    public void insertTail(DNode node){
        super.insertTail(node);
    }

    @Override
    public void insert(DNode node, int position){
        if (position > size+1) {
            insertTail(node);
            return;
        }
        super.insert(node, position);
    }

    @Override
    public void sortedInsert(DNode node){
        if (node == null) {
            return;
        }
        super.sortedInsert(node);
    }

    @Override
    public void deleteTail(){
        super.deleteTail();
    }

    @Override
    public void delete(DNode node){
        if (node == null) {
            return;
        }
        super.delete(node);
    }

    @Override
    public void sort(){
        super.sort();
    }

    public void push(DNode node){
        super.insertHead(node);
    }

    public DNode pop(){
        DNode node = super.getHead();
        super.deleteHead();
        return node;
    }

    public DNode peek(){
        return super.getHead();
    }

    public int search(DNode node) {
        DNode current = super.getHead();
        int position = 0;
        while (current != null) {
            position++;
            if (current.getData() == node.getData()) {
                return position;
            }
            current = current.getNext();
        }
        return -1;
    }

    @Override
    public void print() {
        DNode current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        
        // create a new stack
        StackLL stack = new StackLL();

        // add elements to the stack
        stack.push(new DNode(1));
        stack.push(new DNode(2));
        stack.push(new DNode(3));
        stack.push(new DNode(4));
        stack.push(new DNode(5));

        // print the elements in the stack
        System.out.print("Stack: ");
        stack.print();

        
        
    
        // remove an element from the top of the stack
        DNode poppedNode = stack.pop();

        // print the popped node and the updated stack
        System.out.println("Popped Node: " + poppedNode.getData());
        
        System.out.print("Updated Stack: ");
        stack.print();

        // peek at the element on the top of the stack
        DNode peekedNode = stack.peek();

        // print the peeked node and the stack
        System.out.println("Peeked Node: " + peekedNode.getData());
        System.out.print("Stack: ");
        stack.print();

        // search for an element in the stack
        DNode nodeToSearch = new DNode(3);
        int position = stack.search(nodeToSearch);

        // print the search result and the stack
        if (position != -1) {
            System.out.println("Node 3 found at position: " + position);
        } else {
            System.out.println("Node not found in the stack");
        }
        
        
    }



}
