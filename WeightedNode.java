public class WeightedNode<E extends Comparable<E>> extends Node<E, WeightedNode<E>>{
    public WeightedNode(WeightedNode<E> left, WeightedNode<E> right, E val, int weight) {
        super(left, right, val);
        this.weight = weight;
    }
    public int weight;
}
