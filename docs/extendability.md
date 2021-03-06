### Extendability in Tango Controls

The following must be considerate during design Tango V10 but not the goal per se.

Tango Controls must provide an easy way to extend itself to users aka plugins (use plugin-like model of development).

**Conclusions**

Extendability of the core library must be designed from scratch. 

It is impossible to extend current core library with a custom functionality (e.g. there is no way to integrate dbus directly into the core library). Tango may be extended via introduction of new Tango device servers, that may enrich Tango functionality (which is sometimes not so efficient nor enough).
