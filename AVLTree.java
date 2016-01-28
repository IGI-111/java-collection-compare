import java.util.Collection;

public class AVLTree<E extends Comparable<E>> extends Tree<E> implements Collection<E> {
    public class Node<E extends Comparable<E>> implements Tree.Node<E>{
        private Tree.Node<E> left;
        private Tree.Node<E> right;
        private E val;
        private int imbalance;
        public Node(Tree.Node<E> left, Tree.Node<E> right, E val, int imbalance){
            this.val = val;
            this.left = left;
            this.right = right;
        }
        public Tree.Node<E> getLeft(){
            return left;
        }
        public Tree.Node<E> getRight(){
            return right;
        }
        public E getVal(){
            return val;
        }
        public int getImbalance(){
            return imbalance;
        }
        public void setLeft(Tree.Node<E> left){
            this.left = left;
        }
        public void setRight(Tree.Node<E> right){
            this.right = right;
        }
        public void setVal(E val){
            this.val = val;
        }
        public void setImbalance(int imbalance){
            this.imbalance = imbalance;
        }

    }

    public AVLTree(){
        this.root = null;
        this.size = 0;
    }

    public AVLTree(Collection<E> collection){
        addAll(collection);
    }
    public boolean add(E element){
        //TODO
        return false;
    }
    public boolean remove(Object element){
        //TODO
        return false;
    }
}
