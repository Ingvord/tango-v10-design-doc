# Layered architecture

Below are the layers that should be designed and implemented in **Tango core library**:

![](images/layers.png)

---


Design sketch of Tango component and connectors separated by layers:

![](images/Tango_as_CandC.png)

_ Lorenzo: as depicted this is more like a module view rather than C&C. Moreover, I'm not sure I understand the separation highlighted into the ellipses on the left side: transport, protocol and interface they apply to both server and client...
Also it would be useful to keep the color selection for each layer coherent with the first figure, e.fg. transport grey, protocol lilla, interface loght brown. Moreover, what the small transport box in the large transpost box means? I'd also move the third party components (e.g. CORBA, ZMQ, TCP/IP, UDP/IP) outside the TANGO transport box. 
The bottom part of the figure should go into a separate one. _

---


**1 Tango Transport layer**

Skeletal implementation resides in [org.tango.v10.transport](https://github.com/Ingvord/tango-v10-design-doc/tree/master/src/main/java/org/tango/v10/transport) package

Tango Transport layer remarks:

- low level basic transport layer

Tango Transport layer implementation remarks:

- transparent reconnection;
- session auto-close;
- message delivery guarantee.

**2 Tango Protocol layer**

Skeletal implementation resides in [org.tango.v10.protocol](https://github.com/Ingvord/tango-v10-design-doc/tree/master/src/main/java/org/tango/v10/protocol) package

We propose to design Tango protocol using block schemes as below and translate them in an API. If needed sequence diagrams can be used.

Block scheme where server receives configuration form Tango Host:

![](images/server_start_block_sch.png)

Sequence diagram where server recieves configuration form Tango Host:
![](images/sequence_diagr_server_start.png)


Tango Protocol layer implementation remarks:

- Tango Protocol layer validates Request/Response and throws TangoProtocolException if validations fails
- How it is done in EPICs https://epics.anl.gov/docs/APS2014/05-CA-Concepts.pdf



**3 Tango Interface layer**

Skeletal implementation resides in [org.tango.v10.service](https://github.com/Ingvord/tango-v10-design-doc/tree/master/src/main/java/org/tango/v10/service) package  ("service" word is used as "interface" can not be used as package name)

```java

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

Tango Interface layer remarks:

- adds Tango interface semantics to Tango Protocol layer by introducing high level abstractions (Host, Device etc);
- TangoTarget is an interface from lower layer


**4 Tango API layer (client/server)**

General purpose client library. Introduces even more high level API: AdminDevice; DataBase etc

Skeletal implementation resides in [org.tango.v10.client](https://github.com/Ingvord/tango-v10-design-doc/tree/master/src/main/java/org/tango/v10/client/event) package

Skeletal implementation resides in [org.tango.v10.server](https://github.com/Ingvord/tango-v10-design-doc/tree/master/src/main/java/org/tango/v10/server) package

Skeletal implementation resides in [org.tango.v10.api.client/server](https://github.com/Ingvord/tango-v10-design-doc/tree/master/src/main/java/org/tango/v10/api) package


**5 Tango Compatibility layer**

Bridge to previous Tango versions.


# General remarks of a new Tango core library

## Separation data production from data consuming
 
Event bus separates modules that produce data from modules that consume data. This is required by modifiability quality attribute.

## Concurrency

The system should be designed to run in multithreaded environment.
