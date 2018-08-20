package org.tango.v10.server.polling;

/**
 * Handles pollable buffer and common methods like getLastValue()
 *
 * @author ingvord
 * @since 8/20/18
 */
public abstract class AbstractPollable<T> implements Pollable<T> {
    protected final PollingBuffer<T> buffer;

    protected AbstractPollable(PollingBuffer<T> buffer) {
        this.buffer = buffer;
    }

    @Override
    public PollingBuffer getBuffer() {
        return buffer;
    }

    @Override
    public Iterable<T> getValues() {
        return buffer.get();
    }

    @Override
    public T getLatestValue() {
        return buffer.getFirst();
    }

    @Override
    public long getTimeout() {
        return 0;
    }

    @Override
    public final void poll() {
        PolledValue<T> value = innerPoll();
        buffer.add(value);
    }

    protected abstract PolledValue<T> innerPoll();
}
