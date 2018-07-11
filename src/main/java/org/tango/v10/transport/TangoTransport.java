package org.tango.v10.transport;

import java.io.IOException;

/**
 * Responsible for sending and receiving TangoMessages. Implementation handles (un)marshaling via delegation to {@link TangoMarshaller}.
 * The later is being injected during initialization in {@link TangoTransportFactory}
 *
 * This design intends blocking IO. 
 *
 * TODO we may think about non blocking IO, but this will dramatically increase complexity, i.e. storing errors mechanism, partial send/receive  etc
 *
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoTransport {
    /**
     * Blocks until message is fully transmitted
     *
     * @param message
     * @throws IOException
     * @throws InterruptedException
     */
    void send(TangoMessage message) throws IOException, InterruptedException;

    /**
     * Blocks until new message is arrived. Underlying implementation may use non blocking IO
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    TangoMessage receive() throws IOException, InterruptedException;
}
