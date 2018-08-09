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

# Event System

In this section an analysis of the existing cide base related to Tango Event System (9.3.x). Exisiting code base is presented in blockscheme diagrams. These blockscheme diagrams are close to actual code i.e. only a very few very low level detailes are omitted.

## Event System classes

![](images/EventSystem_classes_EventConsumer.png)

![](images/EventSystem_classes_EventChannel.png)

![](images/EventSystem_classes_EventCallback.png)

![](images/EventSystem_classes_NotConnectedEvent.png)

## Event subscription

### AttrProxy::subscribe_event

![](images/AttrProxy_subscribe_event.png)

### EventConsumer::subscribe_event

![](images/EventConsumer_subscribe_event.png)

### EventConsumer::connect_event

![](images/EventConsumer_connect_event.png)

![](images/EventConsumer_connect_event_1.png)

![](images/EventConsumer_connect_event_3.png)

### ZmqEventConsumer::zmq_specific

![](images/EventConsumer_zmq_specific.png)

### ZmqEventConsumer::connect_event_channel

![](images/EventConsumer_connect_event_channel.png)

### ZmqEventConsumer::connect_event_system

![](images/EventConsumer_connect_event_system.png) 

## Event reconnect



## Conclusions

Currently event subscription algorithm on the client side is strictly procedural. Basically all the code is located in EventConsumer::connect_event method.

There are several severe problems with the existing code base:
 
 1) does not follow OOP principles. Event worse existing class structure makes it up side down - Channel and Callback structures inherit from more specific Zmq and Base (Notifd???) structures
 2) logic hard to read and maintain due to a lot of similar but quite different code; a lot of nested if/else statements; a lot of if-without-else statements etc
 3) code duplications - full event name is built several times
 4) there is no clear API for event subscription algorithm, basically there is only one method EventConsumer::connect_event which is impossible to unit test

A PR has been created to address these issues and to propose refactoring: [link](https://github.com/tango-controls/cppTango/pull/470) 