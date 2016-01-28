import java.util.Collection;

public class BinarySearchTree<E extends Comparable<E>> extends Tree<E> implements Collection<E> {

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

     public boolean remove(E element){
        if (root == null) return false;
        Node<E> parent = null;
        Node<E> node = root;
        while (node != null && !node.val.equals(element)) {
            parent = node;
            if (element.compareTo(node.val) < 0) node = node.left;
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
        System.out.println(tree.val);
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

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(2);
        tree.add(0);
        tree.add(3);
        tree.add(1);
        System.out.println(tree.remove(-4) ? "modified" : "not modified");
        System.out.println(tree.remove(2) ? "modified" : "not modified");
        System.out.println(tree.remove(0) ? "modified" : "not modified");
        System.out.println(tree.remove(3) ? "modified" : "not modified");
        System.out.println(tree.remove(1) ? "modified" : "not modified");
        System.out.println(tree.root);
        System.out.println(tree.root.left + " " + tree.root.right);
        //System.out.println(tree.root.left.left + " " + tree.root.left.right +
        //         " " + tree.root.right.left + " " + tree.root.right.right);
        System.out.println("added values");
        for (Integer i : tree) {
            System.out.println(i);
        }

    }

}
