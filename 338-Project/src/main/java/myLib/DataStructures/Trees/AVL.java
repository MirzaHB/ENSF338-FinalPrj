package main.java.myLib.DataStructures.Trees;
import main.java.myLib.DataStructures.Nodes.TNode;

public class AVL extends BST{

    private TNode root;

    public AVL() {
        super();
    }

    public AVL(int val) {
        super(val);
    }

    public AVL(TNode obj) {
        super(obj);
        if (obj.getLeft() != null || obj.getRight() != null) {
            balance(root);
        }
    }

    public TNode getRoot() {
        return root;
    }

    public void setRoot(TNode root) {
        this.root = root;
        if (root.getLeft() != null || root.getRight() != null) {
            balance(root);
        }
    }


    public void insert(TNode node){
        super.insert(node);
        balance(root);
    }

    public void insert(int val) {
        super.insert(val);
        balance(root);
    }

    public void delete(int val) {
        TNode node = search(val);
        if (node == null) {
            System.out.println("Value not found in tree");
            return;
        }
        root = deleteNode(root, val);
        balance(root);
    }
    
    private TNode deleteNode(TNode node, int val) {
        if (node == null) {
            return node;
        }
        if (val < node.getData()) {
            node.setLeft(deleteNode(node.getLeft(), val));
        } else if (val > node.getData()) {
            node.setRight(deleteNode(node.getRight(), val));
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                TNode temp;
                if (node.getLeft() == null) {
                    temp = node.getRight();
                } else {
                    temp = node.getLeft();
                }
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                TNode temp = minimumValueNode(node.getRight());
                node.setData(temp.getData());
                node.setRight(deleteNode(node.getRight(), temp.getData()));
            }
        }
        if (node == null) {
            return node;
        }
        updateHeight(node);
        return balance(node);
    }
    
    private TNode minimumValueNode(TNode node) {
        TNode current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }    

    public TNode search(int val) {
        return super.search(val);
    }

    public void printInOrder() {
        super.printInOrder();
    }

    public void printBF() {
        super.printBF();
    }

    private void updateHeight(TNode node) {
        int leftNodeBalance = node.getLeft().getBalance();
        int rightNodeBalance = node.getRight().getBalance();
        node.setBalance(Math.max(leftNodeBalance, rightNodeBalance) + 1);
    }

    private int balanceFactor(TNode node) {
        if (node == null) {
            return -1;
        }
        return node.getLeft().getBalance() - node.getRight().getBalance();
    }

    private TNode rotateLeft(TNode node) {
        TNode leftNode = node.getLeft();
        node.setLeft(leftNode.getRight());
        leftNode.setRight(node);
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;

    }

    private TNode rotateRight(TNode node) {
        TNode rightNode = node.getRight();
        node.setRight(rightNode.getLeft());
        rightNode.setLeft(node);
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    private TNode balance(TNode node) {
        int balanceFactor = balanceFactor(node);

        if (balanceFactor < -1) {
            if (balanceFactor(node.getLeft()) <= 0) {
                node = rotateRight(node);
            }
            else {
                node.setLeft(rotateLeft(node.getLeft()));
                node = rotateRight(node);
            }
        }

        if (balanceFactor > 1) {
            if (balanceFactor(node.getRight()) >= 0) {
                node = rotateLeft(node);
            }
            else {
                node.setRight(rotateRight(node.getRight()));
                node = rotateLeft(node);
            }
        }

        return node;
    }
    public static void main(String[] args) {
        AVL avl = new AVL(10);
        avl.insert(5);
        avl.insert(3);
        avl.insert(12);
        avl.insert(15);
        avl.insert(8);
        avl.insert(9);
        
        System.out.println("Printing in-order traversal of AVL tree:");
        avl.printInOrder();
        
        System.out.println("\nPrinting breadth-first traversal of AVL tree:");
        avl.printBF();
        
        System.out.println("\nDeleting node with value 8...");
        avl.delete(8);
        System.out.println("Printing in-order traversal of AVL tree after deletion:");
        avl.printInOrder();
        
        System.out.println("\nDeleting node with value 10...");
        avl.delete(10);
        System.out.println("Printing in-order traversal of AVL tree after deletion:");
        avl.printInOrder();
        
        System.out.println("\nInserting node with value 11...");
        avl.insert(11);
        System.out.println("Printing in-order traversal of AVL tree after insertion:");
        avl.printInOrder();
    }
    
    
}