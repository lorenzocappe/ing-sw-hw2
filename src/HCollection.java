//file scritto da Cappellotto Lorenzo, matricola 1188257
public interface HCollection {
    /** Ensures that this collection contains the specified element (optional operation).
     * 
     * @param o
     * @return boolean
     */
    boolean add(Object o);

    /** Adds all of the elements in the specified collection to this collection (optional operation).
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    boolean addAll(HCollection c) throws NullPointerException;

    /** Removes all of the elements from this collection (optional operation).
     *
     */
    void clear();

    /** Returns true if this collection contains the specified element.
     *
     * @param o
     * @return boolean
     */
    boolean contains(Object o);

    /** Returns true if this collection contains all of the elements in the specified collection.
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    boolean containsAll(HCollection c) throws NullPointerException;

    /** Compares the specified object with this collection for equality.
     *
     * @param o
     * @return boolean
     */
    boolean equals(Object o);

    /** Returns the hash code value for this collection.
     *
     * @return int
     */
    int hashCode();

    /** Returns true if this collection contains no elements.
     *
     * @return boolean
     */
    boolean isEmpty();

    /** Returns an iterator over the elements in this collection.
     *
     * @return HIterator
     */
    HIterator iterator();

    /** Removes a single instance of the specified element from this collection, if it is present (optional operation).
     *
     * @param o
     * @return boolean
     */
    boolean remove(Object o);

    /** Removes all this collection's elements that are also contained in the specified collection (optional operation).
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    boolean removeAll(HCollection c) throws NullPointerException;

    /** Retains only the elements in this collection that are contained in the specified collection (optional operation).
     *
     * @param c
     * @return boolean
     * @throws NullPointerException
     */
    boolean retainAll(HCollection c) throws NullPointerException;

    /** Returns the number of elements in this collection.
     *
     * @return int
     */
    int size();

    /** Returns an array containing all of the elements in this collection.
     *
     * @return Object[]
     */
    Object[] toArray();

    /** Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
     *
     * @param a
     * @return Object[]
     * @throws NullPointerException
     * @throws ArrayStoreException
     *
     */
    Object[] toArray(Object[] a) throws NullPointerException, ArrayStoreException;
}