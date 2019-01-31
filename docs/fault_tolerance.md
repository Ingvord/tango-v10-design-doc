### Fault tolerance in Tango

Tango inherits some properties from CORBA due to implementation as well as introduces new ones. Specifically:

1) transparent reconnection

2) device's state machine

3) propagating errors to the client

4) heartbeats in event system

These features can be extended with:

1) self monitoring - integrate heartbeat into protocol

2) start-up health check

3) implement errors self recovery (some of them)

**Conclusions**

Fault tolerance is already implemented quite well, but can be improved.

_ Lorenzo: this section should be part of the previous, e.g. reliability _