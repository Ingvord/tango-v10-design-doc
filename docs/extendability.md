### Extendability in Tango


(like possibility to use maven. Use plugin-like model of development) 

- Tango Controls must provide an easy way to extend itself to users aka plugins


The following must be considerate during design but not the goal per se.


It is impossible to extend current core library with a custom functionality. Tango may be extended via introduction of new Tango device servers, that may enrich Tango functionality (which is sometimes not so efficient nor enough).

**Conclusions**

Extendability of the core library must be designed from scratch.
