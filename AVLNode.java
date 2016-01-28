public class AVLNode<E> extends Node<E> {
    private int imbalance;
    public AVLNode(Node<E> left, Node<E> right, E val, int imbalance){
        super(left, right, val);
        this.imbalance = imbalance;
    }
}
