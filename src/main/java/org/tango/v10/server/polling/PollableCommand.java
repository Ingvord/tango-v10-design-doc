package org.tango.v10.server.polling;

import org.tango.v10.service.TangoCommand;

import java.util.concurrent.ExecutionException;

/**
 * @author ingvord
 * @since 8/20/18
 */
public class PollableCommand<T> extends AbstractPollable<T> {
    private final TangoCommand<Void, T> command;

    protected PollableCommand(PollingBuffer buffer, TangoCommand<Void, T> command) {
        super(buffer);
        this.command = command;
    }

    @Override
    public PolledValue<T> innerPoll() {
        try {
            return new PolledValue<>(command.execute(null));
        } catch (ExecutionException e) {
            return new PolledValue<>(e);
        }
    }
}
