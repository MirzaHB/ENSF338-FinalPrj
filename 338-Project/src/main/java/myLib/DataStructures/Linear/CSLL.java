package main.java.myLib.DataStructures.Linear;


import main.java.myLib.DataStructures.Nodes.*;

public class CSLL extends SLL {
    // Default constructor that creates an empty circular linked list
    public CSLL() {
        super();
    }

    // Constructor that takes a node as an argument and sets it as the head
    public CSLL(DNode node) {
        super(node);
        node.setNext(node);
    }

    // Method to insert a node at the beginning of the circular linked list
    @Override
    public void insertHead(DNode node) {
        if (head == null) {
            head = node;
            node.setNext(node);
        } else {
            node.setNext(head);
            DNode last = getLastNode();
            last.setNext(node);
            head = node;
        }
        size++;
    }

    // Method to insert a node at the end of the circular linked list
    @Override
    public void insertTail(DNode node) {
        if (head == null) {
            head = node;
            node.setNext(node);
        } else {
            DNode last = getLastNode();
            last.setNext(node);
            node.setNext(head);
        }
        size++;
    }

    // Method to delete the head of the circular linked list
    @Override
    public void deleteHead() {
        if (head == null) {
            return;
        }
        DNode last = getLastNode();
        last.setNext(head.getNext());
        head = head.getNext();
        size--;
    }

    // Method to delete the tail of the circular linked list
    @Override
    public void deleteTail() {
        if (head == null) {
            return;
        }
        if (head.getNext() == head) {
            head = null;
        } else {
            DNode secondLast = getSecondLastNode();
            secondLast.setNext(head);
        }
        size--;
    }

    // Method to get the last node of the circular linked list
    private DNode getLastNode() {
        DNode current = head;
        while (current.getNext() != head) {
            current = current.getNext();
        }
        return current;
    }

    // Method to get the second last node of the circular linked list
    private DNode getSecondLastNode() {
        DNode current = head;
        while (current.getNext().getNext() != head) {
            current = current.getNext();
        }
        return current;
    }
   
    @Override
    public void sort(){
        if(head == null){
            return;
        }else{
            DNode current = head;
            DNode next = null;
            int temp;
            while(current.getNext() != head){
                next = current.getNext();
                while(next != head){
                    if(current.getData() > next.getData()){
                        temp = current.getData();
                        current.setData(next.getData());
                        next.setData(temp);
                    }
                    next = next.getNext();
                }
                current = current.getNext();
            }
        }
    }

    
    public void SortedInsert(DNode node){
        if(head == null){
            head = node;
            tail = node;
            head.setNext(head);
        }else{
            if(!isSorted()){
                sort();
            }
            DNode current = head;
            while(current.getNext() != head && current.getNext().getData() < node.getData()){
                current = current.getNext();
            }
            node.setNext(current.getNext());
            current.setNext(node);
            if(current == tail){
                tail = node;
            }
        }
        size++;
    }

    
    // Method to print the circular linked list
    @Override
    public void print() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.print("List Contents: ");
        DNode current = head;
        do {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        } while (current != head);
        System.out.println();
        if(isSorted()) {System.out.println("Sorted Status: List IS Sorted");}
        else {System.out.println("Sorted Status: List is NOT sorted");}
        System.out.println("List Length: " + size);
        System.out.println();
    }
    @Override
    public boolean isSorted() {
        DNode current = this.head;
        if (current == null || size == 1) {
            return true;
        }

        while (current.next !=head) {
            if (current.data > current.next.data) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }
    
    public static void main(String[] args) {
    	CSLL myList = new CSLL();

        // Insert some nodes at the beginning of the list
        myList.insertHead(new DNode(5));
        myList.insertHead(new DNode(3));
        myList.insertHead(new DNode(15));

        // Insert some nodes at the end of the list
        myList.insertTail(new DNode(20));
        myList.insertTail(new DNode(4));
        myList.insertTail(new DNode(30));
        
        myList.print();
        // Insert some nodes at specific positions
        myList.insert(new DNode(35), 2);
        myList.insert(new DNode(40), 5);
        myList.insert(new DNode(45), 8);
        myList.print();
        // Delete some nodes
        myList.deleteHead();
        myList.deleteTail();
        myList.delete(myList.search(35));
        
        
        myList.print();
        myList.sort();

        // Print the sorted list
        System.out.print("Sorted List: ");
        myList.print();

        // Print the list
        System.out.print("List: ");
        myList.print();

        // Sort the list
        myList.sort();

        // Print the sorted list
        System.out.print("Sorted List: ");
        myList.print();
        myList.insert(new DNode(6),4);
        myList.print();
        // Test the circular property by traversing the list multiple times
        System.out.println("Traversing the list multiple times:");
        DNode current = myList.getHead();
        for (int i = 0; i < 20; i++) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
            if (current == myList.head) {
                System.out.println("(end of one loop)");
            }
        }
    }

}