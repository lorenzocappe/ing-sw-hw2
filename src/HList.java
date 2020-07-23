//file scritto da Cappellotto Lorenzo, matricola 1188257
public interface HList extends HCollection{
    /** Inserts the specified element at the specified position in this list (optional operation).
     *
     * @param index
     * @param element
     * @throws IndexOutOfBoundsException
     */
    void add(int index, Object element) throws IndexOutOfBoundsException;

    /** Appends the specified element to the end of this list (optional operation).
     *
     * @param o
     * @return boolean
     */
    boolean add(Object o);

    /** Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator (optional operation).
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    boolean addAll(HCollection c) throws NullPointerException;

    /** Inserts all of the elements in the specified collection into this list at the specified position (optional operation).
     *
     * @param index
     * @param c
     * @return boolean
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     */
    boolean addAll(int index, HCollection c) throws  NullPointerException, IndexOutOfBoundsException;

    /** Removes all of the elements from this list (optional operation).
     *
     */
    void clear();

    /** Returns true if this list contains the specified element.
     *
     * @param o
     * @return boolean
     */
    boolean contains(Object o);

    /** Returns true if this list contains all of the elements of the specified collection.
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    boolean containsAll(HCollection c) throws NullPointerException;

    /** Compares the specified object with this list for equality.
     *
     * @param o
     * @return boolean
     */
    boolean equals(Object o);

    /** Returns the element at the specified position in this list.
     *
     * @param index
     * @return Object
     * @throws IndexOutOfBoundsException
     */
    Object get(int index) throws IndexOutOfBoundsException;

    /** Returns the hash code value for this list.
     *
     * @return int
     */
    int hashCode();

    /** Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     *
     * @param o
     * @return int
     */
    int indexOf(Object o);

    /** Returns true if this list contains no elements.
     *
     * @return boolean
     */
    boolean isEmpty();

    /** Returns an iterator over the elements in this list in proper sequence.
     *
     * @return HIterator
     */
    HIterator iterator();

    /** Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     *
     * @param o
     * @return int
     */
    int lastIndexOf(Object o);

    /** Returns a list iterator of the elements in this list (in proper sequence).
     *
     * @return HListIterator
     */
    HListIterator listIterator();

    /** Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     *
     * @param index
     * @return HListIterator
     * @throws IndexOutOfBoundsException
     */
    HListIterator listIterator(int index) throws IndexOutOfBoundsException;

    /** Removes the element at the specified position in this list (optional operation).
     *
     * @param index
     * @return Object
     * @throws IndexOutOfBoundsException
     */
    Object remove(int index) throws IndexOutOfBoundsException;

    /** Removes the first occurrence in this list of the specified element (optional operation).
     *
     * @param o
     * @return boolean
     */
    boolean remove(Object o);

    /** Removes from this list all the elements that are contained in the specified collection (optional operation).
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    boolean removeAll(HCollection c) throws NullPointerException;

    /** Retains only the elements in this list that are contained in the specified collection (optional operation).
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    boolean retainAll(HCollection c) throws NullPointerException;

    /** Replaces the element at the specified position in this list with the specified element (optional operation).
     *
     * @param index
     * @param element
     * @return Object
     * @throws IndexOutOfBoundsException
     */
    Object set(int index, Object element) throws IndexOutOfBoundsException;

    /** Returns the number of elements in this list.
     *
     * @return int
     */
    int size();

    /** Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     *
     * @param fromIndex
     * @param toIndex
     * @return HList
     * @throws IndexOutOfBoundsException
     */
    HList subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException;

    /** Returns an array containing all of the elements in this list in proper sequence.
     *
     * @return Object[]
     */
    Object[] toArray();

    /** Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array.
     *
     * @param a
     * @return Object[]
     * @throws NullPointerException
     * @throws ArrayStoreException
     *
     */
    Object[] toArray(Object[] a) throws NullPointerException, ArrayStoreException;
}
