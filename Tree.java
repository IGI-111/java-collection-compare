import java.util.Stack;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Collection;
import java.lang.reflect.Array;

public abstract class Tree<E extends Comparable<E>, N extends Node<E>> extends AbstractCollection<E> {
    protected Node<E> root;
    protected int size;


    protected class NodeIterator implements Iterator<E> {
        private Stack<Node<E>> stack;
        private E previous;
        private Tree<E, N> tree;
        public NodeIterator(Tree<E, N> tree){
            this.tree = tree;
            this.stack = new Stack<Node<E>>();
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

    public int size(){
        return size;
    }

    abstract public boolean remove(Object element);
    abstract public boolean add(E element);

    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        Iterator<? extends E> e = c.iterator();
        while (e.hasNext()) {
            if (add(e.next()))
                modified = true;
        }
        return modified;
    }

    public boolean equals(Tree<E, N> tree){
        if(this.size() != tree.size()) return false;
        Iterator<E> thisIterator = this.iterator();
        Iterator<E> treeIterator = tree.iterator();
        while(thisIterator.hasNext())
            if(thisIterator.next() != treeIterator.next())
                return false;
        return true;
    }

    public boolean contains(Object element){
        @SuppressWarnings("unchecked")
        Comparable<E> elt = (Comparable<E>) element;
        Node<E> current = root;
        while(current != null && current.val != elt){
            if (elt.compareTo(current.val) < 0) current = current.left;
            else current = current.right;
        }
        return current != null;
    }

    public Iterator<E> iterator(){
        return new NodeIterator(this);
    }

}
