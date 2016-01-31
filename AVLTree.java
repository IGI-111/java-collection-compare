
import java.util.Collection;

public class AVLTree<E extends Comparable<E>>
    extends Tree<E, WeightedNode<E>> implements Collection<E> {
    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public AVLTree(Collection<E> collection) {
        addAll(collection);
    }

    public boolean add(E element) {
        return addAVL(root, null, true, element);
    }

    private boolean addAVL(
        WeightedNode<E> r, WeightedNode<E> p, boolean g, E e) {
        if (r == null) {
            r = new WeightedNode<E>(null, null, e, 0);
            if (p == null)
                root = r;
            else if (g)
                p.left = r;
            else
                p.right = r;
            return true;
        } else {
            int a = e.compareTo(r.val);
            if (a == 0)
                return false; // a already in the tree
            if (a < 0)
                if (addAVL(r.left, r, true, e))
                    return rotateLeft(r, p, g);
                else
                    return false;
            else if (addAVL(r.right, r, false, e))
                return rotateRight(r, p, g);
            else
                return false;
        }
    }

    private boolean rotateRight(
        WeightedNode<E> r, WeightedNode<E> p, boolean g) {
        // r is the left son of p if g is true
        // r is the right son of p if g is false
        // return true if tree grows up after the rotation
        WeightedNode<E> r1, r2;
        switch (r.weight) {
            case -1:
                r.weight = 0;
                return false;
            case 0:
                r.weight = 1;
                return true;
            case 1:
            default:
                r1 = r.right;
                if (r1.weight == 1) {
                    r.right = r1.left;
                    r1.left = r;
                    r.weight = 0;
                    r = r1;
                } else {
                    r2 = r1.left;
                    r1.left = r2.right;
                    r2.right = r1;
                    r.right = r2.left;
                    r2.right = r;
                    if (r2.weight == 1)
                        r.weight = -1;
                    else
                        r.weight = 0;
                    if (r2.weight == -1)
                        r1.weight = 1;
                    else
                        r1.weight = 0;
                    r = r2;
                }
                // link to the father
                if (p == null)
                    root = r;
                else if (g)
                    p.left = r;
                else
                    p.right = r;
                r.weight = 0;
                return false;
        }
    }

    private boolean rotateLeft(
        WeightedNode<E> r, WeightedNode<E> p, boolean g) {
        // r is the left son of p if g is true
        // r is the right son of p if g is false
        // return true if tree grows up after the rotation
        WeightedNode<E> r1, r2;
        switch (r.weight) {
            case 1:
                r.weight = 0;
                return false;
            case 0:
                r.weight = -1;
                return true;
            case -1:
            default:
                r1 = r.left;
                if (r1.weight == -1) {
                    r.left = r1.right;
                    r1.right = r;
                    r.weight = 0;
                    r = r1;
                } else {
                    r2 = r1.right;
                    r1.right = r2.left;
                    r2.left = r1;
                    r.left = r2.right;
                    r2.right = r;
                    if (r2.weight == -1)
                        r.weight = 1;
                    else
                        r.weight = 0;
                    if (r2.weight == 1)
                        r1.weight = -1;
                    else
                        r1.weight = 0;
                    r = r2;
                }
                // link to the father
                if (p == null)
                    root = r;
                else if (g)
                    p.left = r;
                else
                    p.right = r;
                r.weight = 0;
                return false;
        }
    }

    public boolean remove(Object element) {
        // TODO
        return false;
    }
}
