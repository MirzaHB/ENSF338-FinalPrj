package main.java.myLib.DataStructures.Nodes;


public class TNode {
    public int data;
    public TNode left;
    public TNode right;
    public TNode parent;
    public int balance;

    public TNode() {
        data = 0;
        left = null;
        right = null;
        parent = null;
        balance = 0;
    }

    public TNode(int data, int balance, TNode parent, TNode left, TNode right) {
        this.data = data;
        this.balance = balance;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TNode getLeft() {
        return left;
    }

    public void setLeft(TNode left) {
        this.left = left;
    }

    public TNode getRight() {
        return right;
    }

    public void setRight(TNode right) {
        this.right = right;
    }

    public TNode getParent() {
        return parent;
    }

    public void setParent(TNode parent) {
        this.parent = parent;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void print() {
        System.out.println("Node data: " + data + ", balance: " + balance);
    }

    @Override
    public String toString() {
        return Integer.toString(data);
    }
}

