//file scritto da Cappellotto Lorenzo, matricola 1188257
public interface HSet {
    /** Adds the specified element to this set if it is not already present (optional operation).
     *
     * @param o
     * @return boolean
     */
    boolean add(Object o);

    /** Adds all of the elements in the specified collection to this set if they're not already present (optional operation).
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    boolean addAll(HCollection c) throws NullPointerException;

    /** Removes all of the elements from this set (optional operation).
     *
     */
    void clear();

    /** Returns true if this set contains the specified element.
     *
     * @param o
     * @return boolean
     */
    boolean contains(Object o);

    /** Returns true if this set contains all of the elements of the specified collection.
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    boolean containsAll(HCollection c) throws NullPointerException;

    /** Compares the specified object with this set for equality.
     *
     * @param o
     * @return boolean
     */
    boolean equals(Object o);

    /** Returns the hash code value for this set.
     *
     * @return int
     */
    int hashCode();

    /** Returns true if this set contains no elements.
     *
     * @return boolean
     */
    boolean isEmpty();

    /** Returns an iterator over the elements in this set.
     *
     * @return HIterator
     */
    HIterator iterator();

    /** Removes the specified element from this set if it is present (optional operation).
     *
     * @param o
     * @return boolean
     */
    boolean remove(Object o);

    /** Removes from this set all of its elements that are contained in the specified collection (optional operation).
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    boolean removeAll(HCollection c) throws NullPointerException;

    /** Retains only the elements in this set that are contained in the specified collection (optional operation).
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    boolean retainAll(HCollection c) throws NullPointerException;

    /** Returns the number of elements in this set (its cardinality).
     *
     * @return int
     */
    int size();

    /** Returns an array containing all of the elements in this set.
     *
     * @return Object[]
     */
    Object[] toArray();

    /** Returns an array containing all of the elements in this set; the runtime type of the returned array is that of the specified array.
     *
     * @param a
     * @return Object[]
     * @throws NullPointerException
     * @throws ArrayStoreException
     *
     */
    Object[] toArray(Object[] a) throws NullPointerException, ArrayStoreException;
}