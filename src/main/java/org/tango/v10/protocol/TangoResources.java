package org.tango.v10.protocol;

import java.net.URI;

/**
 * @author ingvord
 * @since 7/11/18
 */
public abstract class TangoResources {
    abstract TangoResource fromURI(URI uri);
}
