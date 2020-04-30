package ukcrobots.util;

/**
 * A dynamic array-backed list.
 * Based on java.util.ArrayList.
 *
 * @author d.j.barnes@ukc.ac.uk
 * @version 2001.12.20
 */
public class ArrayList {
    // The stored items.
    private Object[] items;
    // How many items are stored.
    private int numItems;

    /**
     * Create an array list of default capacity.
     */
    public ArrayList() {
        this(7);
    }

    /**
     * Create an array list of required capacity.
     *
     * @param capacity The desired capacity.
     */
    public ArrayList(int capacity) {
        items = new Object[capacity];
        numItems = 0;
    }

    /**
     * Return the object at the given index.
     *
     * @param index Which object to return. Must be in the
     *              range [0 ... size()-1]
     * @throws IndexOutOfBoundsException if index is out of bounds.
     */
    public Object get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= numItems) {
            throw new IndexOutOfBoundsException();
        }
        return items[index];
    }

    /**
     * Store a new object at index size().
     *
     * @param obj The object to be stored.
     */
    public boolean add(Object obj) {
        int index = numItems;
        ensureCapacity(index + 1);
        items[index] = obj;
        numItems++;
        return true;
    }

    /**
     * Remove the object at the given index.
     * All items to its right are moved one place left.
     *
     * @param index The index of the item to be removed.
     *              It must lie in the [0 ... size()-1].
     * @throws IndexOutOfBoundsException if index is out of bounds.
     */
    public Object remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= numItems) {
            throw new IndexOutOfBoundsException();
        }
        Object obj = items[index];
        arraycopy(items, index + 1, items, index, numItems - index - 1);
        numItems--;
        // Clear where the last item was moved from. This is
        // really only needed if there is garbage collection.
        items[numItems] = null;
        return obj;
    }

    /**
     * Remove the first occurence of an object from the list.
     * @param obj Which object to remove.
     * @return true if the item was present, false otherwise.
     */
    /*
    public boolean remove(Object obj)
    {
        int numItems = size();
        for(int index = 0; index < numItems; index++){
          if(items[index] == obj){
              arraycopy(items,index+1,items,index,numItems-index-1);
              return true;
          }
        }
        return false;
    }
    */

    /**
     * Ensure that the list is of the given capacity.
     *
     * @param minCapacity The minimum capacity.
     */
    public void ensureCapacity(int minCapacity) {
        if (items.length < minCapacity) {
            // Allocate in reasonable size quanta.
            int newCapacity = items.length * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            Object[] newItems = new Object[newCapacity];
            arraycopy(items, 0, newItems, 0, numItems);
            items = newItems;
        }
    }

    /**
     * @return The number of items in the list.
     */
    public int size() {
        return numItems;
    }

    /**
     * A private version of the arraycopy method.
     * In the leJOS System class, the following method available only for char[]:
     * static void arraycopy (char[] src, int srcOffset, char[] dest, int destOffset, int length)
     * with the same implementation
     */
    private void arraycopy(Object[] src, int srcIndex,
                           Object[] dest, int destIndex, int length) {
        for (int i = 0; i < length; i++) {
            dest[destIndex + i] = src[srcIndex + i];
        }
    }

}
