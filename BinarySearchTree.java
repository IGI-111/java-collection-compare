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
        //FIXME: TODO
        return false;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.add(2);
        tree.add(0);
        tree.add(3);
        tree.add(1);
        System.out.println(tree.root);
        System.out.println(tree.root.left.val + " " + tree.root.right);
        System.out.println(tree.root.left.left + " " + tree.root.left.right +
                " " + tree.root.right.left + " " + tree.root.right.right);
        System.out.println("added values");
        for (Integer i : tree) {
            System.out.println(i);
        }

    }

}
