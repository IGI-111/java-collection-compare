import java.util.Stack;
import java.util.Collection;

public class AVLTree<E extends Comparable<E>>
    extends Tree<E, WeightedNode<E>> implements Collection<E> {
    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public AVLTree(Collection<E> collection) {
        addAll(collection);
    }

    public boolean add(E element) {
        if (root == null) {
            // there is no tree
            root = new WeightedNode<E>(null, null, element, 1);
            return true;
        }

        // top down pass to find where to insert
        Stack<WeightedNode<E>> stack = new Stack<WeightedNode<E>>();
        for (WeightedNode<E> current = root; current != null;) {
            stack.push(current);
            if (current.val.compareTo(element) <= 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        // insertion
        WeightedNode<E> insertAt = stack.pop();
        stack.push(insertAt);
        if (insertAt.val.compareTo(element) <= 0) {
            insertAt.right = new WeightedNode<E>(null, null, element, 1);
        } else {
            insertAt.left = new WeightedNode<E>(null, null, element, 1);
        }

        // botom up pass to update heights
        while(!stack.empty()){
            WeightedNode<E> current = stack.pop();
            current.weight = 1 + Math.max(
                    current.left == null ? 0 : current.left.weight,
                    current.right == null ? 0 : current.right.weight);

            WeightedNode<E> parent = stack.empty() ? null : stack.pop();
            if(imbalance(current) == 2){
                if(imbalance(current.left) == 1) {
                    rotateRight(current, parent);
                } else if(imbalance(current.left) == -1) {
                    rotateLeft(current.left, current);
                    rotateRight(current, parent);
                }
            } else if(imbalance(current) == -2) {
                if(imbalance(current.right) == 1) {
                    rotateRight(current.right, current);
                    rotateLeft(current, parent);
                } else if(imbalance(current.right) == -1) {
                    rotateLeft(current, parent);
                }
            }
            if(parent != null) stack.push(parent);
        }
        return true;
    }

    private void rotateRight(WeightedNode<E> a, WeightedNode<E> parent) {
        WeightedNode<E> b = a.left;
        a.left = b.right;
        b.right = a;

        updateWeight(a);
        updateWeight(b);

        if(parent == null){
            root = b;
        } else {
            if(a == parent.right)
                parent.right = b;
            else
                parent.left = b;
            updateWeight(parent);
        }
    }

    private void rotateLeft(WeightedNode<E> a, WeightedNode<E> parent) {
        WeightedNode<E> b = a.right;
        a.right = b.left;
        b.left = a;

        updateWeight(a);
        updateWeight(b);

        if(parent == null){
            root = b;
        } else {
            if(a == parent.right)
                parent.right = b;
            else
                parent.left = b;
            updateWeight(parent);
        }
    }

    private void updateWeight(WeightedNode<E> node){
        node.weight = 1 + Math.max(
                node.left == null ? 0 : node.left.weight,
                node.right == null ? 0 : node.right.weight);
    }

    private int imbalance(WeightedNode<E> node){
        return (node.left == null ? 0 : node.left.weight) - (node.right == null ? 0 :node.right.weight);
    }


    public boolean remove(Object element) {
        // TODO
        return false;
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<Integer>();
        tree.add(1);
        System.out.println(tree.root.val);
        System.out.println();
        tree.add(3);
        System.out.println(tree.root.val);
        System.out.println(tree.root.left + " " + tree.root.right.val);
        System.out.println();
        tree.add(5);
        System.out.println(tree.root.val);
        System.out.println(tree.root.left.val + " " + tree.root.right.val);
        System.out.println();
        tree.add(2);
        System.out.println(tree.root.val);
        System.out.println(tree.root.left.val + " " + tree.root.right.val);
        System.out.println(
                tree.root.left.left + " " +
                tree.root.left.right.val + " " +
                tree.root.right.left + " " +
                tree.root.right.right);
        System.out.println();
        tree.add(6);
        System.out.println(tree.root.val);
        System.out.println(tree.root.left.val + " " + tree.root.right.val);
        System.out.println(
                tree.root.left.left + " " +
                tree.root.left.right.val + " " +
                tree.root.right.left + " " +
                tree.root.right.right.val);
        System.out.println();
        tree.add(4);
        System.out.println(tree.root.val);
        System.out.println(tree.root.left.val + " " + tree.root.right.val);
        System.out.println(
                tree.root.left.left + " " +
                tree.root.left.right.val + " " +
                tree.root.right.left.val + " " +
                tree.root.right.right.val);
        System.out.println();
        tree.add(7);
        System.out.println(tree.root.val);
        System.out.println(tree.root.left.val + " " + tree.root.right.val);
        System.out.println(
                tree.root.left.left + " " +
                tree.root.left.right.val + " " +
                tree.root.right.left.val + " " +
                tree.root.right.right.val);
        System.out.println();
        tree.add(8);
        System.out.println(tree.root.val);
        System.out.println(tree.root.left.val + " " + tree.root.right.val);
        System.out.println(
                tree.root.left.left + " " +
                tree.root.left.right.val + " " +
                tree.root.right.left.val + " " +
                tree.root.right.right.val);
        System.out.println();
    }
}
