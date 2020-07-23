//file scritto da Cappellotto Lorenzo, matricola 1188257
public interface HIterator {
    /** Returns true if the iteration has more elements.
     *
      * @return boolean
     */
    boolean hasNext();

    /** Returns the next element in the iteration.
     *
     * @return Object
     */
    Object next();

    /** Removes from the underlying collection the last element returned by the iterator (optional operation).
     *
     */
    void remove();
}
