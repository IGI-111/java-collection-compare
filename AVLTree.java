import java.util.Collection;

public class AVLTree<E extends Comparable<E>> extends Tree<E> implements Collection<E> {
    public class Node<E extends Comparable<E>> implements Tree.Node<E> {
        private Tree.Node<E> left;
        private Tree.Node<E> right;
        private E val;
        private int imbalance;
        public Node(Tree.Node<E> left, Tree.Node<E> right, E val, int imbalance) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        public Tree.Node<E> getLeft() {
            return left;
        }
        public Tree.Node<E> getRight() {
            return right;
        }
        public E getVal() {
            return val;
        }
        public int getImbalance() {
            return imbalance;
        }
        public void setLeft(Tree.Node<E> left) {
            this.left = left;
        }
        public void setRight(Tree.Node<E> right) {
            this.right = right;
        }
        public void setVal(E val) {
            this.val = val;
        }
        public void setImbalance(int imbalance) {
            this.imbalance = imbalance;
        }

    }

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

    private boolean addAVL(AVLNode<E> r, AVLNode<E> p, boolean g, Comparable e) {
        if (r == null) {
            r = new AVLNode(null, null, e, 0);
            if (p == null) root  = r;
            else if(g) p.left = r;
            else  p.right = r;
            return true;
        } else {
            int a = e.compareTo(r.val);
            if (a == 0) return false; // a already in the tree
            if (a < 0)
                if(addAVL(r.left, r, true, e)) return rotateLeft(r, p, g);
                else return false;
            else if(addAVL(r.right, r, false, e)) return rotateRight(r, p, g);
            else return false;
        }
    }

    private boolean rotateRight (AVLNode<E> r, AVLNode<E> p, boolean g) {
        // r is the left son of p if g is true
        // r is the right son of p if g is false
        // return true if tree grows up after the rotation
        AVLNode<E> r1, r2;
        switch(r.imbalance) {
        case -1 :
            r.setImbalance(0);
            return false;
        case  0 :
            r.setImbalance(1);
            return true;
        case  1 :
        default :
            r1 = r.getRight();
            if (r1.getImbalance() == 1) {
                r.setRight(r1.left);
                r1.setLeft(r);
                r.imbalance = 0;
                r = r1 ;
            } else {
                r2 = r1.left;
                r1.left = r2.right;
                r2.right = r1;
                r.right = r2.left;
                r2.right = r;
                if(r2.imbalance == 1)  r.imbalance = -1;
                else                   r.imbalance = 0;
                if(r2.imbalance == -1) r1.imbalance = 1;
                else                   r1.imbalance = 0;
                r = r2;
            }
            // link to the father
            if(p == null) root = r;
            else if( g ) p.left = r ;
            else p.right = r ;
            r.imbalance = 0;
            return false;
        }
    }

    private boolean rotateLeft (AVLNode<E> r, AVLNode<E> p, boolean g) {
        // r is the left son of p if g is true
        // r is the right son of p if g is false
        // return true if tree grows up after the rotation
        AVLNode<E> r1, r2;
        switch (r.imbalance) {
        case 1 :
            r.imbalance = 0;
            return false;
        case 0 :
            r.imbalance = -1;
            return true;
        case -1 :
        default :
            r1 = r.left;
            if (r1.imbalance == -1) {
                r.left = r1.right;
                r1.right = r;
                r.imbalance = 0;
                r = r1;
            } else {
                r2 = r1.right;
                r1.right = r2.left;
                r2.left=r1;
                r.left=r2.right;
                r2.right = r;
                if(r2.imbalance == -1) r.imbalance = 1;
                else                   r.imbalance = 0;
                if(r2.imbalance == 1)  r1.imbalance = -1;
                else                   r1.imbalance = 0;
                r = r2;
            }
            // link to the father
            if (p == null) root = r;
            else if( g )   p.left = r ;
            else           p.right = r ;
            r.imbalance = 0;
            return false;
        }
    }

    public boolean remove(Object element) {
        //TODO
        return false;
    }
}
