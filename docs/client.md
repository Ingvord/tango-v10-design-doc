# Client side specific

```java
interface TangoHost {
   String getHostName();
   String getIP();
   int getPort();
}

interface TangoDeviceName {
  /**
   * @return fullName + modifiers e.g. tango:<udp>://<tangohost>:<tangoport>/<domain>/<family>/<member>?no_db=true
   */
  String getRawName();
  /**
   * @return full name e.g. tango:<udp>://<tangohost>:<tangoport>/<domain>/<family>/<member>
   */
  String getFullName();
  String getProtocol();
  TangoHost getTangoHost();
  String getName();
  MultiMap<String,String> getModifiers();
}
```
