import java.util.Iterator;
import java.util.Stack;
import java.util.Collection;
import java.util.AbstractCollection;
import java.util.NoSuchElementException;

public class ABR<E extends Comparable<E>> extends AbstractCollection<E> implements Collection<E> {
    private Node<E> root;
    private int size;

    private class Node<E> {
        public Node<E> left;
        public Node<E> right;
        public E val;
        public Node(Node<E> left, Node<E> right, E val){
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }

    private class NodeIterator implements Iterator<E> {
        private Stack<Node<E>> stack;
        private E previous;
        private ABR<E> tree;
        public NodeIterator(ABR<E> tree){
            stack = new Stack();
            Node<E> next = tree.root;
            while (next != null) {
                stack.push(next);
                next = next.left;
            }
        }
        public boolean hasNext(){
            return !stack.empty();
        }
        public E next(){
            Node<E> next = stack.pop();
            Node<E> node = next;
            next = next.right;

            while (next != null) {
                stack.push(next);
                next = next.left;
            }

            previous = node.val;
            return node.val;
        }
        public void remove(){
            tree.remove(previous);
        }
    }

    public ABR(){
        this.root = null;
        this.size = 0;
    }

    public ABR(Collection<E> collection){
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

    public int size(){
        return size;
    }

    public Iterator<E> iterator(){
        return new NodeIterator(this);
    }

    public static void main(String[] args) {
        ABR<Integer> tree = new ABR<Integer>();
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
