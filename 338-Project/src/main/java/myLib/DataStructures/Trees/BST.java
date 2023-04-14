package main.java.myLib.DataStructures.Trees;

import java.util.LinkedList;
import java.util.Queue;

import main.java.myLib.DataStructures.Nodes.*;

public class BST {
    protected TNode root;
    
    // Default constructor
    public BST() {
        root = null;
    }
    
    // Overloaded constructor with root value
    public BST(int val) {
        root = new TNode(val, 0, null, null, null);
    }
    
    // Overloaded constructor with TNode object as root
    public BST(TNode obj) {
        root = obj;
    }
    
    // Getter for root
    public TNode getRoot() {
        return root;
    }
    
    // Setter for root
    public void setRoot(TNode node) {
        root = node;
    }
    
    // Inserts new node with value val into the BST
    public void insert(int val) {
        TNode newNode = new TNode(val, 0, null, null, null);
        if (root == null) {
            root = newNode;
        } else {
            TNode currNode = root;
            while (true) {
                if (val < currNode.getData()) {
                    if (currNode.getLeft() == null) {
                        currNode.setLeft(newNode);
                        newNode.setParent(currNode);
                        updateBalance(newNode);
                        break;
                    }
                    currNode = currNode.getLeft();
                } else {
                    if (currNode.getRight() == null) {
                        currNode.setRight(newNode);
                        newNode.setParent(currNode);
                        updateBalance(newNode);
                        break;
                    }
                    currNode = currNode.getRight();
                }
            }
        }
    }
    private void updateBalance(TNode node) {
        if (node == null) {
            return;
        }
    
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        node.balance = rightHeight - leftHeight;
    
        if (node.parent != null) {
            if (node == node.parent.left) {
                node.parent.balance--;
            } else {
                node.parent.balance++;
            }
            if (node.parent.balance != 0) {
                updateBalance(node.parent);
            }
        }
    }
    
    private int height(TNode node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    // Inserts existing node into the BST
    public void insert(TNode node) {
        if (root == null) {
            root = node;
        } else {
            TNode currNode = root;
            while (true) {
                if (node.getData() < currNode.getData()) {
                    if (currNode.getLeft() == null) {
                        currNode.setLeft(node);
                        node.setParent(currNode);
                        updateBalance(node);
                        break;
                    }
                    currNode = currNode.getLeft();
                } else {
                    if (currNode.getRight() == null) {
                        currNode.setRight(node);
                        node.setParent(currNode);
                        updateBalance(node);
                        break;
                    }
                    currNode = currNode.getRight();
                }
            }
        }
    }
    
 
    public void delete(int val) {
    root = Delete(root, val);
}


    private TNode Delete(TNode node, int val) {
    if (node == null) {
        System.out.println("Value " + val + " is not in the tree.");
        return null;
    }

    if (val < node.getData()) {
        node.setLeft(Delete(node.getLeft(), val));
    } else if (val > node.getData()) {
        node.setRight(Delete(node.getRight(), val));
    } else {
        if (node.getLeft() == null) {
            return node.getRight();
        } else if (node.getRight() == null) {
            return node.getLeft();
        }

        TNode successor = getSuccessor(node.getRight());
        node.setData(successor.getData());
        node.setRight(Delete(node.getRight(), successor.getData()));
    }

    return node;
}

/**
 * Gets the node that comes after the given node in the tree.
 *
 * @param node The node to find the successor of.
 * @return The successor node.
 */
private TNode getSuccessor(TNode node) {
    TNode current = node;
    while (current.getLeft() != null) {
        current = current.getLeft();
    }
    return current;
}

/**
 * Searches for a node with the given value in the tree.
 *
 * @param val The value to search for.
 * @return The node with the given value, or null if it is not found.
 */
public TNode search(int val) {
    TNode current = root;
    while (current != null) {
        if (val == current.getData()) {
            return current;
        } else if (val < current.getData()) {
            current = current.getLeft();
        } else {
            current = current.getRight();
        }
    }
    return null;
}

/**
 * Prints the contents of the tree in ascending order.
 */


public void printInOrder() {
    printInOrder(root);
    System.out.println();
}

private void printInOrder(TNode node) {
    if (node == null) {
        return;
    }
    printInOrder(node.left);
    System.out.print(node.data + " ");
    printInOrder(node.right);
}
/**
 * Prints the contents of the tree in Breadth-First order, each level of the
 * tree will be printed on a separate line.
 */
public void printBF() {
    if (root == null) {
        return;
    }

    Queue<TNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            TNode node = queue.remove();
            System.out.print(node.getData() + " ");
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
        System.out.println();
    }
}



    public static void main(String[] args) {
        // create a new binary search tree
        BST tree = new BST();

        // insert some values
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        // print the tree contents in ascending order
        System.out.print("In-order traversal: ");
        tree.printInOrder();
        System.out.println();

        // print the tree contents in breadth-first order
        System.out.println("Breadth-first traversal:");
        tree.printBF();

        // search for a value
        TNode found = tree.search(40);
        System.out.println("Node found: " + found);

        // delete a value
        tree.delete(70);

        // print the tree contents in breadth-first order again
        System.out.println("Breadth-first traversal after deletion:");
        tree.printBF();
    }
}
