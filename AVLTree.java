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
            root = new WeightedNode<E>(null, null, element, 0);
            return true;
        }

        // top down pass to find where to insert
        Stack<WeightedNode<E>> stack = new Stack<WeightedNode<E>>();
        for (WeightedNode<E> current = root; current != null;) {
            stack.push(current);
            if (current.val.compareTo(element) <= 0) {
                current = current.left;
            } else {
                current = current.left;
            }
        }

        // insertion
        WeightedNode<E> insertAt = stack.pop();
        stack.push(insertAt);
        if (insertAt.val.compareTo(element) <= 0) {
            insertAt.right = new WeightedNode<E>(null, null, element, 0);
        } else {
            insertAt.left = new WeightedNode<E>(null, null, element, 0);
        }
        WeightedNode<E> parent = null;

        // botom up pass to update weights
        WeightedNode<E> current;
        do {
            current = stack.pop();
            current.weight = height(current.left) - height(current.right);
            System.out.println(current.weight);
            if (parent == null && (current.weight > 1 || current.weight < 1)) {
                parent = current;
            }
        }while(!stack.empty());

        // no parent means no rebalancing to do
        if(parent == null)
            return true;

        // rebalancing the tree
        // TODO
    }

    private WeightedNode<E> rotateLeft(WeightedNode<E> node) {
        System.out.println("Rotate Left");
        WeightedNode<E> newRoot = node.right;
        WeightedNode<E> newRootLeft = node;
        if (newRoot.left != null) {
            newRootLeft.right = newRoot.left;
        }
        newRoot.left = newRootLeft;
        return newRoot;
    }

    private WeightedNode<E> rotateRight(WeightedNode<E> node) {
        System.out.println("Rotate Right");
        WeightedNode<E> newRoot = node.left;
        WeightedNode<E> newRootRight = node;
        if (newRoot.right != null) {
            newRootRight.left = newRoot.right;
        }
        newRoot.right = newRootRight;
        return newRoot;
    }

    private int height(WeightedNode<E> node){
        if (node == null) return 0;
        Stack<WeightedNode<E>> stack = new Stack<WeightedNode<E>>();
        stack.push(node);
        int maxDepth = 0;
        WeightedNode<E> prev = null;
        while (!stack.empty()) {
            WeightedNode<E> curr = stack.peek();
            if (prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null)
                    stack.push(curr.left);
                else if (curr.right != null)
                    stack.push(curr.right);
            } else if (curr.left == prev) {
                if (curr.right != null)
                    stack.push(curr.right);
            } else {
                stack.pop();
            }
            prev = curr;
            if (stack.size() > maxDepth)
                maxDepth = stack.size();
        }
        return maxDepth;
    }


    public boolean remove(Object element) {
        // TODO
        return false;
    }
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<Integer>();
        tree.add(3);
        System.out.println(tree.root);
        System.out.println(tree.root.left + " " + tree.root.right);
        System.out.println();
        tree.add(1);
        System.out.println(tree.root);
        System.out.println(tree.root.left + " " + tree.root.right);
        System.out.println();
        tree.add(2);
        System.out.println(tree.root);
        System.out.println(tree.root.left + " " + tree.root.right);
        System.out.println(tree.root.left.left + " " + tree.root.left.right);
        System.out.println();
        tree.add(0);
        System.out.println(tree.root);
        System.out.println(tree.root.left + " " + tree.root.right);
        System.out.println(tree.root.left.left + " " + tree.root.left.right);
        System.out.println(tree.root.left.left.left + " " + tree.root.left.left.right);
        System.out.println();
    }

}
