package org.tango.v10.server.polling;

import org.tango.v10.service.TangoAttribute;

/**
 * Wraps values from command or attribute polling to store them in the {@link PollingBuffer}
 *
 * @author ingvord
 * @since 8/19/18
 */
public class PolledValue<T> {
    public PolledValue(T value) {
    }

    public PolledValue(Throwable throwable) {
    }
}
