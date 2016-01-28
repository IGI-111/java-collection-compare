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

    private class NodeIterator<E> implements Iterator<E> {
        private Node<E> next;
        private Stack<Node<E>> stack;
        public NodeIterator(Node<E> root){
            stack = new Stack();
            while (next != null) {
                stack.push(next);
                next = next.left;
            }
        }
        public boolean hasNext(){
            return next != null;
        }
        public E next(){
            while (next != null) {
                stack.push(next);
                next = next.left;
            }

            next = stack.pop();
            Node<E> node = next;
            next = next.right;

            return node.val;
        }
        //public void remove(){
        //}
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

    public int size(){
        return size;
    }

    public Iterator<E> iterator(){
        return new NodeIterator<E>(root);
    }

}
