package org.tango.v10.protocol;

import org.tango.v10.transport.TangoMessage;

/**
 * @author ingvord
 * @since 7/12/18
 */
public interface TangoPackage {
    Iterable<TangoMessage> getMessages();
}
