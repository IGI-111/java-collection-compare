import java.util.Collection;

public class AVLTree<E extends Comparable<E>> extends Tree<E> implements Collection<E> {
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
    public boolean remove(E element){
        //TODO
        return false;
    }
}
