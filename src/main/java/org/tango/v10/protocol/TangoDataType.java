package org.tango.v10.protocol;

import org.tango.v10.transport.TangoMessageHeader;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoDataType  extends TangoMessageHeader<Integer> {
    enum TangoDataTypeEnum implements TangoDataType{
        VOID,
        INT,
        UINT;
        //etc

        @Override
        public Integer getHeaderValue() {
            return ordinal();
        }
    }
}
