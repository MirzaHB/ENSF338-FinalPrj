package main.java.myLib.DataStructures.Linear;

import main.java.myLib.DataStructures.Nodes.*;

public class SLL {
    protected DNode head;
    protected DNode tail;
    protected int size;
    protected boolean sorted;


    public SLL() {
        head = null;
        tail = null;
        size = 0;
    }

    public SLL(DNode head) {
        this.head = head;
        this.tail = head;
        size = 1;
        sorted = true;
    }
    
    public DNode getHead() {
    	return head;
    }
    
    public void insertHead(DNode node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
        sorted = false;
    }

    public void insertTail(DNode node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
        sorted = false;
    }

    public void insert(DNode node, int position) {
        if (position < 1 || position > size + 1) {
            System.out.println("Invalid position");
            return;
        }
        if (position == 1) {
            insertHead(node);
            return;
        }
        if (position == size + 1) {
            insertTail(node);
            return;
        }
        DNode current = head;
        for (int i = 1; i < position - 1; i++) {
            current = current.next;
        }
        node.next = current.next;
        current.next = node;
        size++;
        sorted = false;
    }

    public void sortedInsert(DNode node) {
        if (!sorted) {
            sort();
        }
        if (head == null || node.data < head.data) {
            insertHead(node);
            return;
        }
        if (node.data >= tail.data) {
            insertTail(node);
            return;
        }
        DNode current = head;
        while (current.next != null && current.next.data < node.data) {
            current = current.next;
        }
        node.next = current.next;
        current.next = node;
        size++;
    }

    public DNode search(int data) {
        DNode current = head;
        while (current != null) {
            if (current.data == data) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void deleteHead() {
        if (head == null) {
            return;
        }
        head = head.next;
        size--;
    }

    public void deleteTail() {
        if (head == null) {
            return;
        } else if (head.next == null) {
            head = null;
        } else {
            DNode current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        size--;
    }

    public void delete(DNode node) {
        if (head == null) {
            return;
        }

        if (head == node) {
            deleteHead();
            return;
        }

        DNode current = head;
        while (current.next != null) {
            if (current.next == node) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }
    public void sort() {
        if (head == null) {
            return;
        }
        DNode newHead = null;
        DNode current = head;
        while (current != null) {
            DNode next = current.getNext();
            if (newHead == null || current.getData() < newHead.getData()) {
                current.setNext(newHead);
                newHead = current;
            } else {
                DNode temp = newHead;
                while (temp.getNext() != null && current.getData() > temp.getNext().getData()) {
                    temp = temp.getNext();
                }
                current.setNext(temp.getNext());
                temp.setNext(current);
            }
            current = next;
        }
        head = newHead;
        DNode tail = newHead;
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }
        this.tail = tail;
        size = 0;
        current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
    }
    
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public void print() {
    	System.out.println();
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        else {
        	System.out.print("List Contents: ");
        	DNode current = head;
        	while (current != null) {
        		System.out.print(current.data + " ");
        		current = current.next;
        	}
        	System.out.println();
        }
        System.out.println("List Length: " + size);
        if(isSorted()) {System.out.print("Sorted Status: List IS Sorted");}
        else {System.out.println("Sorted Status: List is NOT sorted");}
        System.out.println();
    }
    
    public boolean isSorted() {
        DNode current = this.head;
        if (current == null || size == 1) {
            return true;
        }

        while (current.next !=null) {
            if (current.data > current.next.data) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }
    
    public static void main(String[] args) {
        SLL myList = new SLL();

        // Add nodes to the list
        myList.insertTail(new DNode(1));
        myList.insertTail(new DNode(4));
        myList.insertHead(new DNode(5));
        myList.insert(new DNode(7), 1);
        myList.insert(new DNode(3), 3);

        // Print the list
        System.out.println("Initial List State:");
        myList.print();

        // Search for a node
        DNode searchResult = myList.search(4);
        System.out.println("Search result:"+searchResult.data);
   

        // Delete a node
        
        DNode tmpp = new DNode(2);
        myList.insertTail(tmpp);
        System.out.println("List contents:");
        myList.print();
        myList.delete(tmpp);
        System.out.println("List after deleting node containing 2:");
        myList.print();

        // Sort the list
        myList.sort();
        System.out.println("List after sorting:");
        myList.print();
        System.out.println();
        // Delete the head and tail of the list
        myList.deleteHead();
        myList.deleteTail();
        System.out.println("List after deleting head and tail:");
        myList.print();

        // Add a node at position 1
        myList.insert(new DNode(6), 1);
        System.out.println();
        System.out.println("List after adding node 6 at position 1:");
        myList.print();
        
        myList.clear();
        System.out.println("State of list after clearing the list:");
        myList.print();
    }

}
