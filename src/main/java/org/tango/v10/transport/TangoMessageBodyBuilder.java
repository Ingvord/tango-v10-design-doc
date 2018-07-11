package org.tango.v10.transport;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoMessageBodyBuilder {
    TangoMessageBodyBuilder setData(int data);
    TangoMessageBodyBuilder setData(long data);
    TangoMessageBodyBuilder setData(/*etc*/);
    TangoMessageBody build();
}
