public class Node<E> {
    public Node<E> left;
    public Node<E> right;
    public E val;
    public Node(Node<E> left, Node<E> right, E val){
        this.left = left;
        this.right = right;
        this.val = val;
    }
}
