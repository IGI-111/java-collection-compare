public class WeightedNode<E extends Comparable<E>>
    extends Node<E, WeightedNode<E>> {
    public int weight;

    public WeightedNode(
        WeightedNode<E> left, WeightedNode<E> right, E val, int weight) {
        super(left, right, val);
        this.weight = weight;
    }
}
