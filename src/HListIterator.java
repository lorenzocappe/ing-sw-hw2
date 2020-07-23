//file scritto da Cappellotto Lorenzo, matricola 1188257
public interface HListIterator extends HIterator{
    /** Inserts the specified element into the list (optional operation).
     *
     * @param o
     */
    void add(Object o);

    /** Returns true if this list iterator has more elements when traversing the list in the forward direction.
     *
     * @return boolean
     */
    boolean hasNext();

    /** Returns true if this list iterator has more elements when traversing the list in the reverse direction.
     *
     * @return boolean
     */
    boolean hasPrevious();

    /** Returns the next element in the list.
     *
     * @return Object
     */
    Object next();

    /** Returns the index of the element that would be returned by a subsequent call to next.
     *
     * @return int
     */
    int nextIndex();

    /** Returns the previous element in the list.
     *
     * @return Object
     */
    Object previous();

    /** Returns the index of the element that would be returned by a subsequent call to previous.
     *
     * @return int
     */
    int previousIndex();

    /** Removes from the list the last element that was returned by next or previous (optional operation).
     *
     */
    void remove();

    /** Replaces the last element returned by next or previous with the specified element (optional operation).
     *
     * @param o
     */
    void set(Object o);
}