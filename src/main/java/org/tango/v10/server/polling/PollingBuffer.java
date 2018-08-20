package org.tango.v10.server.polling;

/**
 * @author ingvord
 * @since 8/19/18
 */
public interface PollingBuffer<T> {
    /**
     *
     *
     * @return actual size i.e. number of elements in this buffer
     */
    int size();

    /**
     * How many elements this buffer may have
     *
     * @return capacity
     */
    int capacity();

    /**
     * Adds value to this buffer, possibly throwing away oldest value size is exceeding capacity
     *
     * @param value a new value
     */
    void add(PolledValue<T> value);

    /**
     *
     * @return all values stored in this buffer
     */
    Iterable<T> get();

    /**
     *
     *
     * @return latest value added to this buffer
     */
    T getFirst();
    void clear();
}
