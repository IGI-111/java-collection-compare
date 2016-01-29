import java.util.Collection;

public class BinarySearchTree<E extends Comparable<E>> extends Tree<E, SimpleNode<E>> implements Collection<E>
{
    public BinarySearchTree()
    {
        this.root = null;
        this.size = 0;
    }

    public BinarySearchTree(Collection<E> collection)
    {
        addAll(collection);
    }

    public boolean add(E element)
    {
        SimpleNode<E> previous = null;
        SimpleNode<E> current = root;
        while (current != null)
        {
            SimpleNode<E> next = current.val.compareTo(element) < 0 ?
                                 current.right :
                                 current.left;
            previous = current;
            current = next;
        }

        if (previous == null)
        {
            root = new SimpleNode<E>(null, null, element);
            ++size;
            return true;
        }

        if (previous.val.compareTo(element) < 0)
            previous.right = new SimpleNode<E>(null, null, element);
        else
            previous.left = new SimpleNode<E>(null, null, element);
        ++size;
        return true;
    }

    public boolean remove(Object element)
    {
        @SuppressWarnings("unchecked")
        Comparable<E> elt = (Comparable<E>) element;
        if (root == null) return false;
        SimpleNode<E> parent = null;
        SimpleNode<E> node = root;
        while (node != null && !node.val.equals(element))
        {
            parent = node;
            if (elt.compareTo(node.val) < 0) node = node.left;
            else node = node.right;
        }
        if (node != null)
        {
            if (parent == null)
                root = removeRoot(node);
            else if (node == parent.left)
                parent.left = removeRoot(node);
            else
                parent.right = removeRoot(node);
            return true;
        }
        return false;
    }

    private SimpleNode<E> removeRoot(SimpleNode<E> tree)
    {
        if (tree.left == null) return tree.right;
        if (tree.right == null) return tree.left;
        SimpleNode<E> b = tree.left;
        if (b.right == null)
        {
            tree.val = b.val;
            tree.left = b.left;
        }
        else
        {
            SimpleNode<E> p = penultimateSon(b);
            SimpleNode<E> f = p.right;
            tree.val = f.val;
            p.right = f.left;
        }
        return tree;
    }

    private SimpleNode<E> penultimateSon(SimpleNode<E> tree)
    {
        while (tree.right.right != null) tree = tree.right;
        return tree;
    }

}
