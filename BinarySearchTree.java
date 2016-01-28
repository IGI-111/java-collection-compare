import java.util.Collection;

public class BinarySearchTree<E extends Comparable<E>> extends Tree<E, Node<E>> implements Collection<E> {

    public BinarySearchTree(){
        this.root = null;
        this.size = 0;
    }

    public BinarySearchTree(Collection<E> collection){
        addAll(collection);
    }

    public boolean add(E element){
        Node<E> previous = null;
        Node<E> current = root;
        while(current != null){
            Node<E> next = current.val.compareTo(element) < 0 ?
                current.right :
                current.left;
            previous = current;
            current = next;
        }

        if(previous == null){
            root = new Node<E>(null, null, element);
            ++size;
            return true;
        }

        if(previous.val.compareTo(element) < 0)
            previous.right = new Node<E>(null, null, element);
        else
            previous.left = new Node<E>(null, null, element);
        ++size;
        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean remove(Object element){
        Comparable<E> elt;
        try{
            elt = (Comparable<E>) element;
        } catch(ClassCastException e){
            return false;
        }
        if (root == null) return false;
        Node<E> parent = null;
        Node<E> node = root;
        while (node != null && !node.val.equals(element)) {
            parent = node;
            if (elt.compareTo(node.val) < 0) node = node.left;
            else node = node.right;
        }
        if (node != null){
            if(parent == null)
                root = removeRoot(node);
            else if(node == parent.left)
                parent.left = removeRoot(node);
            else
                parent.right = removeRoot(node);
            return true;
        }
        return false;
    }

    private Node<E> removeRoot(Node<E> tree) {
        if (tree.left == null) return tree.right;
        if (tree.right == null) return tree.left;
        Node<E> b = tree.left;
        if (b.right == null) {
            tree.val = b.val;
            tree.left = b.left;
        } else {
            Node<E> p = penultimateSon(b);
            Node<E> f = p.right;
            tree.val = f.val;
            p.right = f.left;
        }
        return tree;
    }

    private Node<E> penultimateSon(Node<E> tree)
    {
        while (tree.right.right != null) tree = tree.right;
        return tree;
    }

}
