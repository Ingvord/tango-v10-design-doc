# Business goal

...

# Mission of this document

To achieve the following quality attributes:

1) Maintainability -- long term project, code readability and modifiability over optimization

2) Availability (Fault tolerance) -- Tango is a framework used in critical environments - must foreseen errors, faults etc

3) Extanability -- system must provide an easy way to extend itself to users aka plugins

4) Performance -- same as 2)

# Maintainability

Most important quality attribute within **Maintainability** group is **Modifiability**. Below is a short analysis of the current situation:

## Modifiability in Tango: 

relatively easy to add new functionality i.e. extendability. For instance, to add a new command to a database server:
1) update IDL
2) implement some glue code on client/server side:
  - on the client side this usually means adding a new method to DeviceProxy
  - on the server - implement command and glue it to TangoDatabse device server API
  
very hard to change existing functionality i.e. patches, changes to protocol (send new data as command response). Or to extand core functionality e.g. integration with dbus.

Impossible to change underlying protocol - currently is bound to CORBA

## Conclusions

Either historically or intentionaly architecture was adapted for adding new functionality. Nowdays when functionality is mature enough (it is hard to foreseen any major additons like event system or new polling mechanism) focus must be switched to changes to the existing code base ot allow quick fixes, performance improvements and most importantly pluggability, so users can implement their own changes in a centralized manner without changing kernel library

# Availability

The most important feature of availability must be fault tolerance as Tango performs in critical environments. Fault tolerance provides several imortant feaures that must be foreseen in Tango:

1) self recoverage
2) self monitoring
3) minimal downtime

Below is the short analysis of the current situation:

## Fault tolerance in Tango

Tango inherits some of the properties from CORBA due to implementation as well as introduces new ones. Specifically:

1) transparent reconnection
2) device's state machine
3) propagating errors to the client
4) heartbeats in event system

These features can be extended with:

1) self monitoring - integrate heartbeat into protocol
2) start-up health check
3) implement errors self recovery (some of them)

## Conclusions

Fault tolerance is already implemented quite well, but can be improved.

# Adaptability

As Tango aims to be installed in a variety of environments it is essential to highlight **adaptability** quality attribute.  Most important is to provide extendability implemented in the kernel library. Below is the short description of the current situation.

## Extendability in Tango

Impossible to extend current kernel library with a custom functionality. Tango may be extended via introduction of new Tango device servers, that may enrich Tango functionality. Which is sometimes not so effecient nor enough.

## Conclusions

Extendability of the kernel library must be designed from scratch.

# Layered architecture
1. Transport layer:

```java

/**
 * Transport aka (tango://)
 */
interface TangoTransport {
  TangoMessage send(TangoMessage) throws IOException;

}

/**
 * Builder pattern for TangoMessage
 */
interface TangoMessageBuilder {
  TangoMessageBuilder setProtocolVersion(ProtocolVersion);
  TangoMessageBuilder setKernelVersion(KernelVersion);
  TangoMessageBuilder setType(TangoMessageType);
  TangoMessageBuilder setDataType(TangoDataType);
  TangoMessageBuilder setBody(TangoMessageBody);
}

enum TangoMessageType {
  COMMAND,
  ATTRIBUTE,
  PIPE,
  EVENT_SUBSCRIBE,
  PING
  //etc
}

enum TangoDataType {
  VOID,
  INT,
  UINT
  //etc
}

interface TangoMessageBodyBuilder {
  TangoMessageBodyBuilder setData(int);
  TangoMessageBodyBuilder setData(long);
  TangoMessageBodyBuilder setData(etc);
}


interface TangoMessageBody {
  byte[] getData();
}
```

Layer remarks:
- low level basic layer

Implementation remarks:
- transparent reconnection

2. Protocol layer:

```java

interface TangoProtocol {
  TangoProtocolVersion getVersion();
  TangoResponse readAttributes(TangoRequest) throws IOException, TangoProtocolException;
  TangoResponse writeAttributes(TangoRequest) throws IOException, TangoProtocolException;
  TangoResponse executeCommands(TangoRequest) throws IOException, TangoProtocolException;
  TangoResponse readPipes(TangoRequest) throws IOException, TangoProtocolException;
  boolean validate(TangoRequest);
  boolean validate(TangoResponse);
  //etc
}

interface TangoRequestBuilder {
  TangoRequestBuilder setVersion(TangoProtocolVersion);
  TangoRequestBuilder setTarget(TangoTarget);
  TangoRequestBuilder setMessage(TangoMessage);
}

interface TangoResponseBuilder {
}

interface TangoTarget {
  TangoProtocol requiresProtocol();
  URL toUrl();
  //etc
}

/**
 * Provides information about exception
 */
class TangoProtocolException extends Exception{
  TangoProtocolVersion getProtocolVersion();
  TangoRequest getRequest();  
}

```

Layer remarks:
- adds Tango semantics to TangoTransport by implementing logic of transforming TangoRequest/TangoResponse to/from TangoMessage

Implementation remarks:
- validates Request/Response and throws TangoProtocolException if validations fails

3. TangoInterfaceLayer

```java

/**
 * Service discovery
 */
interface TangoServiceProvider {  
  TangoHost lookupHost(URL) throws TangoServiceProviderException, IOException;
  TangoDevice lookupDevice(URL) throws TangoServiceProviderException, IOException;
  TangoAttribute lookupAttribute(URL) throws TangoServiceProviderException, IOException;
  TangoCommand lookupCommand(URL) throws TangoServiceProviderException, IOException;
  //etc
}

interface TangoHost extends TangoTarget {
  //TODO
}

interface TangoDevice extends TangoTarget {
  Future<?> readAttribute(TangoAttribute<?>) throws IOException;
  Stream<?> readAttributeAsStream(TangoAttribute<?>) throws IOException, TangoDeviceException; 
  Stream<Stream<?>> readAtrtibutesAsStream(Collection<TangoAttribute>) throws IOException, TangoDeviceException;  
  Future<TangoCommand> executeCommand(TangoCommand) throws IOException;
  Future<Collection<TangoCommand>> executeCommands(Collection<TangoCommand>) throws IOException;
}

interface TangoAttribute<?> extends Configurable, TangoTarget  {
  Stream<?> readAsStream();
  Future<?> read();
  ChangeEvent asChangeEvent();
  PeriodicEvent asPeriodicEvent();
  ArchiveEvent asArchiveEvent();
}

//etc

//utility interfaces/mixins
interface Subscriable {
  void subscribe(Callback);
}

interface Configuration {
  //anotation based implementations
}

interface Configurable {
  void setConfiguration(Configuration);
}

interface ChangeEvent extends Configurable, Subscriable{
  
}

interface PeriodicEvent extends Configurable, Subscriable{
  
}

interface ArchiveEvent extends Configurable, Subscriable{
  
}



```

Layer remarks:
- adds Tango interface semantics to TangoProtocol layer by introducing high level abstractions (Host, Device etc)
- TangoTarget is an interface from lower layer


4. TangoLogicLayer (Client)

General purpose client library. Introduces even more high level API: AdminDevice; DataBase etc

5. TangoCompatibility layer

Bridge to previous Tango version
