import java.util.Stack;
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
		Stack<WeightedNode<E>> stack = new Stack<WeightedNode<E>>();
		for ( WeightedNode<E> current = root ; current != null ; stack.push(current)) {
			if (current.val.compareTo(element) <= 0) {
				current = current.left;
			} else {
				current = current.left;
			}
		} 
		WeightedNode<E> insertAt = stack.pop();
		stack.push(insertAt);
		if (insertAt.val.compareTo(element) <= 0) {
			insertAt.right = new WeightedNode<E>(null, null, element, 0);
		} else {
			insertAt.left = new WeightedNode<E>(null, null, element, 0);
		}
		WeightedNode<E> parent = null;
		for (WeightedNode<E> current = root ; !stack.empty() ; current = stack.pop()) {
			
			current.weight = (current.left == null ? 0 : current.left.weight) 
			                 - (current.right == null ? 0 : current.right.weight);
			if (parent == null && (current.weight > 1 || current.weight < 1)) {
				parent = current;
			}
		}
		WeightedNode<E> child;
		WeightedNode<E> parentBis;
		
		if (parent.left == null) { 
			child = parent.right;
		} else if (parent.right == null) {
			child = parent.left;
		} else {
			child = parent.left.weight > parent.right.weight ? parent.left : parent.right;
		}
		do {
			parentBis = parent;
			if (child == parent.left) {
				if (parent.weight == 2) {
					if (child.weight == -1) {
						parentBis = child;
						child = rotateLeft(child);
					}
					parent = rotateRight(parent);
					break;
				}
				if (parent.weight == -1) {
					parent.weight = 0;
					break;
				}
				parent.weight = 1;
			} else {
				if (parent.weight == -2) {
					if(child.weight == 1) {
						parentBis = child;
						child = rotateRight(child);
					}
					parent = rotateRight(parent);
					break;
				}
				if (parent.weight == 1) {
					parent.weight = 0;
					break;
				}
				parent.weight = -1;
			}
			child = parent;
			parent = parentBis;
		} while(parent != null);
		return true;
	}
	
	public WeightedNode<E> rotateLeft(WeightedNode<E> node) {
		WeightedNode<E> newRoot = node.right;
		WeightedNode<E> newRootLeft = node;
		if (newRoot.left != null) {
			newRootLeft.right = newRoot.left;
		}
		newRoot.left = newRootLeft;
		return newRoot;
	}
	
	public WeightedNode<E> rotateRight(WeightedNode<E> node) {
		WeightedNode<E> newRoot = node.left;
		WeightedNode<E> newRootRight = node;
		if (newRoot.right != null) {
			newRootRight.left = newRoot.right;
		}
		newRoot.right = newRootRight;
		return newRoot;
	}
	/*
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
    */

    public boolean remove(Object element) {
        // TODO
        return false;
    }
   
    /*
        WeightedNode<E> a, aa, p, pp;
		WeightedNode<E> y = new WeightedNode<E>(null, null, element, 0);
		WeightedNode<E> r = root; 
		
		if (root == null) {
			root = y;
		}
		else {
			a = root;
			aa = null;
			p = root; 
			pp = null;
			
			while (p != null) {
				if (p.weight != 0) {
					a = p;
					r = a;
					aa = pp;
				}
				pp = p;
				p = y.val.compareTo(y.val) <= 0 ? p.left : p.right;
			}

			if (y.val.compareTo(pp.val) <= 0) {
				pp.left = y;
			} else {
				pp.right = y;
			}
			p = a;

			while ( p != y ) {
				if (y.val.compareTo(p.val) <= 0) {
					++p.weight;
					p = p.left;
				} else {
					--p.weight;
					p = p.right;
				}
			}
			
			if(a.weight == -2) {
				if (a.right.weight == -1) {
					r = rotateLeft(a);
					r.weight = 0;
					r.left.weight = 0;
				}
				else if(a.right.weight==1) {
					r = rotateRightLeft(a);
					if (r.weight == 1)	{
						r.left.weight = 0;
						r.right.weight = -1;
					} else if (r.weight == -1) {
						r.left.weight = 1;
						r.right.weight = 0;
							
					} else if (r.weight == 0) {
						r.left.weight = 0;
						r.right.weight = 0;
					}
					r.weight = 0;
				}
			}
						
			if(a.weight == 2)	{
				if(a.left.weight == 1) {	
					r = rotateRight(a);
					r.weight = 0;
					r.right.weight = 0;
				}
				else if(a.left.weight == -1) {	
					r = rotateLeftRight(a);	
					if (r.weight==1)	{
						r.left.weight = 1;
						r.right.weight = 0;
					} else if (r.weight == -1) {
						r.left.weight = 0;
						r.right.weight = -1;
					} else if (r.weight == 0) {
						r.left.weight = 0;
						r.right.weight = 0;
					}
					r.weight = 0;
				}
			}
			
			if(aa == null) root = r;
			else if(aa.val.compareTo(r.val) >= 0) aa.left = r;
			else aa.right = r;
			++size;
			return true;	
		}
		return false;
	}
	*/
}
