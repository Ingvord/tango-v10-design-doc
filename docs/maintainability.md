### Maintainability in Tango

Tango is a long term project therefore code readability and its modifiability must prevail over non-trivial code optimizations. Due to the fast progress in the IT nowadays optimizations may decrease performance tomorrow i.e. consider new CPU commands and built-in compiler optimizations.

- Modifiability (the most important)
- Modularity (guarantees Modifiability)
- Testability (as the result of Modularity)


Most important quality attribute within Maintainability group is _Modifiability_. 

Currently it is relatively easy to add new functionality i.e. extendability. For instance, to add a new command to a database server:

1) update IDL

2) implement some glue code on client/server side:

- on the client side: this usually means adding a new method to DeviceProxy

- on the server: implement command and glue it to TangoDatabse device server API

But it is very hard to change existing functionality i.e. patches, changes to protocol (send new data as command response). Or to expand core functionality e.g. integration with dbus.

Impossible to change underlying protocol - currently is bound to CORBA

**Conclusions**

Either historically or intentionally architecture was adapted for adding new functionality. Nowadays when functionality is mature enough (it is hard to foreseen any major additions like event system or new polling mechanism) focus must be switched to changes to the existing code base or allow quick fixes, performance improvements and most importantly pluggability, so users can implement their own changes in a centralized manner without changing core library.
