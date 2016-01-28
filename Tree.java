import java.util.Stack;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Collection;
import java.util.AbstractCollection;

public abstract class Tree<E extends Comparable<E>> extends AbstractCollection<E> {
    protected Node<E> root;
    protected int size;

    protected class Node<E> {
        public Node<E> left;
        public Node<E> right;
        public E val;
        public Node(Node<E> left, Node<E> right, E val){
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }

    protected class NodeIterator implements Iterator<E> {
        private Stack<Node<E>> stack;
        private E previous;
        private Tree<E> tree;
        public NodeIterator(Tree<E> tree){
            stack = new Stack<Node<E>>();
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

    public Iterator<E> iterator(){
        return new NodeIterator(this);
    }
}
