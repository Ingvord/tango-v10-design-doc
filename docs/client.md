[TOC]

# Event System

Here you can find an analysis of the existing code base related to the Tango Event System (9.3.x). Existing code base is presented in block scheme diagrams. These block scheme diagrams are close to actual code, i.e. only a very few very low level details are omitted.

---

## Event System classes

![](images/EventSystem_classes_EventConsumer.png)

![](images/EventSystem_classes_EventChannel.png)

![](images/EventSystem_classes_EventCallback.png)

![](images/EventSystem_classes_NotConnectedEvent.png)

---

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

---

## Event reconnect

Reconnection happens in KeepAliveThread after having missed a heartbeat event.

![](images/Event_reconnect_event.png)

![](images/Event_main_reconnect.png)

![](images/Event_zmq_reconnect_channel.png)

![](images/Event_zmq_reconnect_channel_1.png)

![](images/Event_zmq_reconnect_event.png)

## Event unsubscribe

Entry point: EventConsumer::unsubscribe_event

![](images/Event_unsubscribe_client.png)

ZMQ specific:

![](images/Event_unsubscribe_client_disconnect_event.png)

![](images/Event_unsubscribe_client_disconnect_channel.png)

---

## Conclusions

Currently event subscription algorithm on the client side is strictly procedural. Basically, all the code is located in EventConsumer::connect_event method.

There are several severe problems with the existing code base:
 
 1) It does not follow OOP principles. Moreover, existing class structure makes it up side down - Channel and Callback structures inherit from more specific Zmq and Base (Notifd???) structures.
 
 2) Logic is hard to read and maintain due to a lot of similar but quite differently written code; a lot of nested if/else statements; a lot of if-without-else statements, etc.
 
 3) Code duplicates - full event name is built several times.
 
 4) There is no clear API for event subscription algorithm. Frankly speaking, there is only one method EventConsumer::connect_event which is impossible to unit test.
 
 5) There is no clear boundaries between low level and high level logic, e.g. raw response from the admin server passed through all over the code base.

A PR has been created to address these issues and to propose refactoring: [link to GitHub](https://github.com/tango-controls/cppTango/pull/470).
