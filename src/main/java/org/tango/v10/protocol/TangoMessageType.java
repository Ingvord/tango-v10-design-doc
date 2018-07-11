package org.tango.v10.protocol;

import org.tango.v10.transport.TangoMessageHeader;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoMessageType  extends TangoMessageHeader<Integer> {
    enum TangoMessageTypeEnum implements TangoMessageType {
        COMMAND,
        ATTRIBUTE,
        PIPE,
        EVENT_SUBSCRIBE,
        PING;
        //etc

        @Override
        public Integer getHeaderValue() {
            return ordinal();
        }
    }
}
