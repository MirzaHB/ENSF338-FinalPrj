package main.java.myLib.DataStructures.Linear;

import main.java.myLib.DataStructures.Nodes.*;

public class QueueLL extends SLL {

    public QueueLL(){
        super();
    }

    public QueueLL(DNode head){
        super(head);
    }

    @Override
    public void insertHead(DNode node){
        super.insertHead(node);
    }

    

    @Override
    public void insert(DNode node, int position){
        if (position > size) {
            insertTail(node);
            return;
        }
        super.insert(node, position);
    }

    @Override
    public void deleteTail(){
        super.deleteTail();
    }

    @Override
    public void deleteHead(){
        super.deleteHead();
    }

    @Override
    public void delete(DNode node){
        if (node == null) {
            return;
        }
        super.delete(node);
    }

    @Override
    public void sortedInsert(DNode node){
        if (node == null) {
            return;
        }
        super.sortedInsert(node);
    }

    @Override
    public void sort(){
        super.sort();
    }

    public void enqueue(DNode node){
        super.insertTail(node);
    }

    public DNode dequeue(){
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
        // Create a new empty queue
        QueueLL queue = new QueueLL();
    
        // Enqueue some nodes
        queue.enqueue(new DNode(1));
        queue.enqueue(new DNode(5));
        queue.enqueue(new DNode(4));
        queue.enqueue(new DNode(3));
        queue.enqueue(new DNode(2));
        System.out.print("Current List: ");
        queue.print();
        

        queue.sort();
        System.out.print("Current List after sorting: ");
        queue.print();
        

        queue.insertHead(new DNode(7));
        System.out.print("Current List after Inserting 7 in head: ");
        queue.print();
        

        queue.insert(new DNode(8), 3);
        System.out.print("Current List after Inserting 8 in position 3: ");
        queue.print();
        

        queue.delete(new DNode(8));
        System.out.print("Current List after deleting 8: ");
        queue.print();

        queue.deleteHead();
        queue.deleteTail();
        System.out.print("List after deleting head and tail: ");
        queue.print();

        queue.sort();
        System.out.print("Current List after sorting: ");
        queue.print();
        
        System.out.println("Current List size: " + queue.size);
    
        // Peek the head of the queue without dequeuing
        System.out.println("Peek: " + queue.peek().getData());
    
        // Dequeue some nodes
        DNode node1 = queue.dequeue();
        DNode node2 = queue.dequeue();
        DNode node3 = queue.dequeue();
        DNode node4 = queue.dequeue();
    
        // Print the dequeued nodes
        System.out.println("Dequeued nodes: " + node1.getData() + ", " + node2.getData() + ", " + node3.getData() + ", " + node4.getData());
    
        // Enqueue some more nodes
        queue.enqueue(new DNode(6));
        queue.enqueue(new DNode(7));
        System.out.print("Enqueued nodes: ");
        DNode current = queue.getHead();
        current = current.getNext();
        while (current != null) {
            
            System.out.print(current.getData() + " ");
            current = current.getNext();
            
        }
        System.out.println();
        System.out.print("Current List:  ");
        queue.print();
        System.out.println();

        

        // Search for a node
        DNode searchNode = new DNode(5);
        int position = queue.search(searchNode);
        if (position != -1) {
            System.out.println("Found node " + searchNode.getData() + " at position " + position);
        } else {
            System.out.println("Node " + searchNode.getData() + " not found");
        }
    }



}
