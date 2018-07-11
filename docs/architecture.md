# Layered architecture

![](images/layers.png)

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

## Separation data production from data consuming

Event bus separates modules that produce data from modules that consumes data. This is required by modifiability qa.

## Concurrency

The system designed to run in multithreaded environment
