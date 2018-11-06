### Maintainability in Tango Controls

Tango Controls is a long term project therefore code readability and its modifiability must prevail over non-trivial code optimizations. Due to the fast progress in the IT nowadays, optimizations may decrease performance tomorrow i.e. consider new CPU commands and built-in compiler optimizations.

We prioritize the following attributes in Maintainability part:

- Modifiability (the most important);
- Modularity (guarantees Modifiability);
- Testability (as the result of Modularity).


Most important quality attribute within Maintainability group is _Modifiability_. 

Currently, it is relatively easy to add new functionality (i.e. extendability). For instance, add a new method to server API:

1) update IDL

2) implement some glue code on client/server side:

- on the client side: this usually means adding a new method to DeviceProxy;

- on the server: implement method.

But it is very hard to change existing functionality e.g. patches, changes to protocol (send new data as command response). Or to expand core functionality e.g. integration with dbus.

It is impossible to change underlying protocol - currently is bound to CORBA.

**Conclusions**

Either historically or intentionally architecture was adapted for adding new functionality (e.g. adding new attributes, commands). Nowadays when functionality is mature enough (it is hard to foresee any major additions like event system or new polling mechanism), focus must be switched to changes, to the existing code base or to allowing quick fixes, performance improvements and most importantly to pluggability, so that users can implement their own changes in a centralized manner without changing core library.
