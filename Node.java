public abstract class Node<E extends Comparable<E>, N extends Node<E, N>> {
    public N left;
    public N right;
    public E val;
    public Node(N left, N right, E val) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
