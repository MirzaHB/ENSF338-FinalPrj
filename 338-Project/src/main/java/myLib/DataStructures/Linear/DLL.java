package main.java.myLib.DataStructures.Linear;
import main.java.myLib.DataStructures.Nodes.DNode;

public class DLL extends SLL{
    
    private DNode head;
    private int size;
    // Constructor with no arguments
    public DLL() {
        head = null;
        size=0;
    }
    
    // Constructor with a Node argument
    public DLL(DNode head) {
        this.head = head;
        size=1;
    }
    

    
    // Method to insert a node at the end of the list
    @Override
    public void insertTail(DNode newNode) {
        if (head == null) {
            head = newNode;
        } else {
            DNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }
        size++;
    }
    @Override
    public void insertHead(DNode newNode) {
        // If the list is empty, set the head to the new node
        if (head == null) {
            head = newNode;
        } else {
            // Otherwise, insert the new node at the head of the list
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        // Increment the size of the list
        size++;
    }
    @Override
    public void insert(DNode newNode, int position) {
        if (position < 1 || position > size) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }

        if (position == 1) {
            insertHead(newNode);
        } else if (position == size+1) {
            insertTail(newNode);
        } else {
            DNode current = head;
            int index = 1;
            while (index < position - 1) {
                current = current.next;
                index++;
            }
            newNode.prev = current;
            newNode.next = current.next;
            current.next.prev = newNode;
            current.next = newNode;
            size++;
        }
    }
    @Override
    public void deleteHead() {
        // If the list is empty, there is nothing to delete
        if (head == null) {
            return;
        }

        // If the list has only one element, set the head to null
        if (head.next == null) {
            head = null;
            size--;
            return;
        }

        // Otherwise, set the head to the next node and remove the reference to the previous node
        head = head.next;
        head.prev = null;
        size--;
    }
    @Override
    public void deleteTail() {
        if (head == null) {
            return;
        } else if (head.next == null) {
            head = null;
            size--;
            return;
        }

        DNode current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.prev.next = null;
        size--;
    }
    @Override
    public void delete(DNode node) {
        if (head == null) {
            return;
        }
        if (head == node) {
            deleteHead();
            return;
        }
        DNode current = head;
        while (current != null && current != node) {
            current = current.next;
        }
        if (current != null) {
            current.prev.next = current.next;
            if (current.next != null) {
                current.next.prev = current.prev;
            }
            size--;
        }
    }
    @Override
    public void clear() {
        head = null;
        tail=null;
        size = 0;
    }

    
    public DNode search(DNode node) {
        DNode current = head;
        while (current != null) {
            if (current == node) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    
    public void sortedInsert(DNode newNode) {
    	insertHead(newNode);
    	sort();
    }
    @Override
    public void sort() {  
        DNode current = null, index = null;  
        int temp;  
        //Check whether list is empty  
        if(head == null) {  
            return;  
        }  
        else {  
            //Current will point to head  
            for(current = head; current.next != null; current = current.next) {  
                //Index will point to node next to current  
                for(index = current.next; index != null; index = index.next) {  
                    //If current's data is greater than index's data, swap the data of current and index  
                    if(current.data > index.data) {  
                        temp = current.data;  
                        current.data = index.data;  
                        index.data = temp;  
                    }  
                }  
            }  
        }  
    } 
    @Override
    public boolean isSorted() {
        if (size <= 1) {
            // If the list has 0 or 1 element, it's always sorted
            return true;
        }

        DNode current = head;
        while (current.next != null) {
            // Compare current node with the next one
            if (current.data > current.next.data) {
                return false;
            }
            current = current.next;
        }

        return true;
    }

    // Method to print the contents of the list
    @Override
    public void print() {
    	System.out.println();
        if (head == null) {
            System.out.println("List is empty.");
        } else {
        	System.out.print("List Contents: ");
            DNode current = head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
            System.out.println("List Length: " + size);
            if(isSorted()) {System.out.print("Sorted Status: List IS Sorted");}
            else {System.out.println("Sorted Status: List is NOT sorted");}
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        DLL list = new DLL();
        
        // Insert some nodes at the head of the list
        list.insertHead(new DNode(3));
        list.insertHead(new DNode(7));
        list.insertHead(new DNode(1));
        
        // Insert some nodes at the tail of the list
        list.insertTail(new DNode(8));
        list.insertTail(new DNode(5));
        list.insertTail(new DNode(2));
        
        // Insert a node at position 2
        list.insert(new DNode(9), 2);
        
        // Print the contents of the list
        System.out.print("Original list: ");
        list.print();
        
        // Delete the head node
        list.deleteHead();
        
        // Delete the tail node
        list.deleteTail();
        
        // Delete a specific node
        DNode tmp = new DNode(111);
        list.insertHead(tmp);
        System.out.print("Changed list: ");
        list.print();
        DNode nodeToDelete = list.search(tmp);
        list.delete(nodeToDelete);
        
        // Print the contents of the modified list
        System.out.print("Modified list: ");
        list.print();
        
        // Sort the list
        list.sort();
        
        // Print the sorted list
        System.out.print("Sorted list: ");
        list.print();
        
        list.sortedInsert(new DNode(1));
        System.out.print("After Sorted Insert: ");
        list.print();
        // Clear the list
        list.clear();
        
        // Print the contents of the empty list
        System.out.print("Cleared list: ");
        list.print();
    }

}


