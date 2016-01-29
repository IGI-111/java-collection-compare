import java.util.Collection;

public class BinarySearchTree<E extends Comparable<E>> extends Tree<E> implements Collection<E> {

    public class Node<E extends Comparable<E>> implements Tree.Node<E> {
        private Tree.Node<E> left;
        private Tree.Node<E> right;
        private E val;
        public Node(Tree.Node<E> left, Tree.Node<E> right, E val) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        public Tree.Node<E> getLeft() {
            return left;
        }
        public Tree.Node<E> getRight() {
            return right;
        }
        public E getVal() {
            return val;
        }
        public void setLeft(Tree.Node<E> left) {
            this.left = left;
        }
        public void setRight(Tree.Node<E> right) {
            this.right = right;
        }
        public void setVal(E val) {
            this.val = val;
        }

    }

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public BinarySearchTree(Collection<E> collection) {
        addAll(collection);
    }

    public boolean add(E element) {
        Tree.Node<E> previous = null;
        Tree.Node<E> current = root;
        while(current != null) {
            Tree.Node<E> next = current.getVal().compareTo(element) < 0 ?
                                current.getRight() :
                                current.getLeft();
            previous = current;
            current = next;
        }

        if(previous == null) {
            root = new Node<E>(null, null, element);
            ++size;
            return true;
        }

        if(previous.getVal().compareTo(element) < 0)
            previous.setRight(new Node<E>(null, null, element));
        else
            previous.setLeft(new Node<E>(null, null, element));
        ++size;
        return true;
    }

    public boolean remove(Object element) {
        @SuppressWarnings("unchecked")
        Comparable<E> elt = (Comparable<E>) element;
        if (root == null) return false;
        Tree.Node<E> parent = null;
        Tree.Node<E> node = root;
        while (node != null && !node.getVal().equals(element)) {
            parent = node;
            if (elt.compareTo(node.getVal()) < 0) node = node.getLeft();
            else node = node.getRight();
        }
        if (node != null) {
            if(parent == null)
                root = removeRoot(node);
            else if(node == parent.getLeft())
                parent.setLeft(removeRoot(node));
            else
                parent.setRight(removeRoot(node));
            return true;
        }
        return false;
    }

    private Tree.Node<E> removeRoot(Tree.Node<E> tree) {
        if (tree.getLeft() == null) return tree.getRight();
        if (tree.getRight() == null) return tree.getLeft();
        Tree.Node<E> b = tree.getLeft();
        if (b.getRight() == null) {
            tree.setVal(b.getVal());
            tree.setLeft(b.getLeft());
        } else {
            Tree.Node<E> p = penultimateSon(b);
            Tree.Node<E> f = p.getRight();
            tree.setVal(f.getVal());
            p.setRight(f.getLeft());
        }
        return tree;
    }

    private Tree.Node<E> penultimateSon(Tree.Node<E> tree)
    {
        while (tree.getRight().getRight() != null) tree = tree.getRight();
        return tree;
    }

}
