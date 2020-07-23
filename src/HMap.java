//file scritto da Cappellotto Lorenzo, matricola 1188257
public interface HMap {
    /** Removes all mappings from this map (optional operation).
     *
      */
    void clear();

    /** Returns true if this map contains a mapping for the specified key.
     *
     * @param key
     * @return boolean
     * @throws NullPointerException
     */
    boolean containsKey(Object key) throws NullPointerException;

    /** Returns true if this map maps one or more keys to the specified value.
     *
     * @param value
     * @return boolean
     * @throws NullPointerException
     */
    boolean containsValue(Object value) throws NullPointerException;

    /** Returns a set view of the mappings contained in this map.
     *
     * @return HSet
     */
    HSet entrySet();

    /** Compares the specified object with this map for equality.
     *
     * @param o
     * @return boolean
     */
    boolean equals(Object o);

    /** Returns the value to which this map maps the specified key.
     *
     * @param key
     * @return Object
     * @throws NullPointerException
     */
    Object get(Object key) throws NullPointerException;

    /** Returns the hash code value for this map.
     *
     * @return int
     */
    int	hashCode();

    /** Returns true if this map contains no key-value mappings.
     *
     * @return boolean
     */
    boolean isEmpty();

    /** Returns a set view of the keys contained in this map.
     *
     * @return HSet
     */
    HSet keySet();

    /** Associates the specified value with the specified key in this map (optional operation).
     *
     * @param key
     * @param value
     * @return Object
     * @throws NullPointerException
     */
    Object put(Object key, Object value) throws NullPointerException;

    /** Copies all of the mappings from the specified map to this map (optional operation).
     *
     * @param m
     * @throws NullPointerException
     */
    void putAll(HMap m) throws NullPointerException;

    /** Removes the mapping for this key from this map if it is present (optional operation).
     *
     * @param key
     * @return Object
     * @throws NullPointerException
     */
    Object remove(Object key) throws NullPointerException;

    /** Returns the number of key-value mappings in this map.
     *
     * @return int
     */
    int size();

    /** Returns a collection view of the values contained in this map.
     *
     * @return HCollection
     */
    HCollection values();

    interface Entry{
        /** Compares the specified object with this entry for equality.
         *
         * @param o
         * @return boolean
         */
        boolean equals(Object o);

        /** Returns the key corresponding to this entry.
         *
         * @return Object
         */
        Object getKey();

        /** Returns the value corresponding to this entry.
         *
         * @return Object
         */
        Object getValue();

        /** Returns the hash code value for this map entry.
         *
         * @return int
         */
        int hashCode();

        /** Replaces the value corresponding to this entry with the specified value (optional operation).
         *
         * @param value
         * @return Object
         * @throws NullPointerException
         */
        Object setValue(Object value) throws NullPointerException;
    }
}