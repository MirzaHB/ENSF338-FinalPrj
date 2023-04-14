package main.java.myLib.DataStructures.Linear;

import main.java.myLib.DataStructures.Nodes.DNode;

public class CDLL extends DLL{
    private DNode head;
    private DNode tail;
    private int size;

    public CDLL(){
        head = null;
        tail = null;
        size = 0;
    }

    public CDLL(DNode head){
        this.head = head;
        tail = null;
        size = 1;
        DNode current = head;
        if(current.getNext() != null) {
            while (current.getNext().getData() != head.getData()) {
                current = current.getNext();
                size++;
            }
        }
        current.setNext(this.head);
        tail = current;
    }

    @Override
    public void insertHead(DNode node){
        if(head == null){
            head = node;
            tail = node;
            head.setNext(head);
            head.setPrev(head);
        }else{
            node.setNext(head);
            tail.setNext(node);
            node.setPrev(tail);
            head = node;
        }
        size++;
    }

    @Override
    public void insertTail(DNode node){
        if(head == null){
            head = node;
            tail = node;
            head.setNext(head);
        }else{
            tail.setNext(node);
            node.setNext(head);
            head.setPrev(node);
            tail = node;
        }
        size++;
    }

    @Override
    public void insert(DNode node, int position) {
        if (position < 1 || position > size + 1) {
            throw new IndexOutOfBoundsException();
        }
        if (position == 1) {
            insertHead(node);
        } else if (position == size + 1) {
            insertTail(node);
        } else {
            DNode current = head;
            for (int i = 1; i < position - 1; i++) {
                current = current.getNext();
            }
            node.setNext(current.getNext());
            current.getNext().setPrev(node);
            node.setPrev(current);
            current.setNext(node);
            size++;
        }
    }

        @Override
        public void sortedInsert(DNode node){
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
                node.setPrev(current);
                current.getNext().setPrev(node);
                current.setNext(node);
                if(current == tail){
                    tail = node;
                }
            }
            size++;
        }

    @Override
    public DNode search(DNode node){
        if(head == null){
            return null;
        }else{
            DNode current = head;
            while(current.getNext() != head && current.getData() != node.getData()){
                current = current.getNext();
            }
            if(current.getData() == node.getData()){
                return current;
            }else{
                return null;
            }
        }
    }

    @Override
    public void deleteHead(){
        if(head == null){
            return;
        }else{
            head.getNext().setPrev(tail);
            head = head.getNext();
            tail.setNext(head);
            size--;
        }
    }

    @Override
    public void deleteTail(){
        if(head == null){
            return;
        }else{
            DNode current = head;
            while(current.getNext() != tail){
                current = current.getNext();
            }
            current.setNext(head);
            tail = current;
            head.setPrev(tail);
            size--;
        }
    }

    @Override
    public void delete(DNode node){
        if(head == null){
            return;
        }else{
            DNode current = head;
            while(current.getNext() != head && current.getNext().getData() != node.getData()){
                current = current.getNext();
            }
            if(current.getNext().getData() == node.getData()){
                current.setNext(current.getNext().getNext());
                current.getNext().setPrev(current);
                size--;
            }
        }
    }

    public boolean isSorted(){
        if(head == null){
            return true;
        }else{
            DNode current = head;
            while(current.getNext() != head && current.getData() <= current.getNext().getData()){
                current = current.getNext();
            }
            if(current.getNext() == head){
                return true;
            }else{
                return false;
            }
        }
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

    @Override
    public void print() {
    	System.out.println();
        System.out.println("List length: " + size);

        if (isSorted()) {
            System.out.println("Sorted Status: List IS sorted");
        } else {
            System.out.println("Sorted Status: List is NOT sorted");
        }

        System.out.print("List content: ");

        if (head == null) {
            System.out.println("empty");
        } else {
            DNode current = head;
            System.out.print(current.getData());
            current = current.getNext();
            while (current.getData() != head.getData()) {
                System.out.print(" " + current.getData());
                current = current.getNext();
            }
            System.out.println();
        }
    }

    @Override
    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    public static void main(String[] args) {
    	CDLL myList = new CDLL();

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
        //myList.delete(myList.Search(35));
        // DNode searchResult = myList.Search(4);
        // System.out.println("Search result:"+searchResult.getData());

        myList.delete(new DNode(3));
        myList.delete(new DNode(5));
        
        
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

        System.out.println("\nSize of the list: " + myList.size);
        myList.sortedInsert(new DNode(27));
        // Test the circular property by traversing the list multiple times
        System.out.println("Traversing the list multiple times:");
        DNode current = myList.head;
        for (int i = 0; i < 20; i++) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
            if (current == myList.head) {
                System.out.println("(end of one loop)");
            }
        }

        System.out.println("\nSize of the list: " + myList.size);
    }
}