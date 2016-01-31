public class SimpleNode<E extends Comparable<E>>
    extends Node<E, SimpleNode<E>> {
    public SimpleNode(SimpleNode<E> left, SimpleNode<E> right, E val) {
        super(left, right, val);
    }
}
