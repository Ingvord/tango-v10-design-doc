package org.tango.v10.server.polling;

import org.tango.v10.service.TangoAttribute;

import java.util.concurrent.ExecutionException;

/**
 * @author ingvord
 * @since 8/19/18
 */
public class PollableAttribute<T> extends AbstractPollable<T> {
    private final TangoAttribute<T> attribute;

    protected PollableAttribute(PollingBuffer buffer, TangoAttribute<T> attribute) {
        super(buffer);
        this.attribute = attribute;
    }

    @Override
    public PolledValue<T> innerPoll() {
        try {
            return new PolledValue<T>(attribute.read().get());
        } catch (TangoAttribute.TangoAttributeException | InterruptedException | ExecutionException e) {
            return new PolledValue<>(e);
        }
    }
}
