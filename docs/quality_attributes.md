# Quality attributes

Many factors determine the qualities in a system's architecture.
The next figure shows the [ISO/IEC FCD 25010](http://iso25000.com/index.php/en/iso-25000-standards/iso-25010) product quality standard. This quality model determines which quality characteristics should be taken into account when evaluating the properties of a software product.

![](images/IEC_FCD_25010_product_quality_standard.png)

Currently Tango core faces the following problems that prevent it from reaching its business goals:

1) Human resources which are allocated to development and improvement of the Tango core code are very limited

2) Current code base structure does not meet quality level of commercial/open source projects of a similar size

3) Dependency on a legacy CORBA framework and most importantly Tango is a thin wrapper onto of CORBA

4) Parts of the Tango code violate principle of information hiding and separation of concerns

5) Dependency on specific versions of 3rd party libraries (CORBA, ZMQ) in a way that changing the version or a library almost impossible

6) Unclear ways of the interaction between components of the system

To solve the above problems we suggest to focus on the following quality attributes and redesign Tango core library.

Some of the quality attributes from the above picture are naturally inherited from CORBA design i.e. Interoperability.

Below is a short analysis of the presence of particular attributes in Tango:

**TODO** define and describe presence of all other quality attributes in Tango (inherited from CORBA)

The most important and those which Tango Controls mostly miss are the following.

НАПИСАТЬ, ПРО КОРБУ. НАДО ПОНЯТЬ ИЗБАВЛЯТЬСЯ ОТ НЕЁ ИЛИ НЕТ. ЕСЛИ ДА, ТО КАКИЕ ПОСЛЕДСТВИЯ, ЕСЛИ НЕТ, ТО КАК МОЖНО ИЗМЕНИТЬ КОД ДЛЯ ЭТОГО.


_Maintainability_

Tango is a long term project therefore code readability and its modifiability must prevail over non-trivial code optimizations. Due to the fast progress in the IT nowadays optimizations may decrease performance tomorrow i.e. consider new CPU commands and built-in compiler optimizations.

- Modifiability (the most important)
- Modularity (guarantees Modifiability)
- Testability (as the result of Modularity)

_Reliability_

Tango is a framework used in critical environments (non stop operations, minimal downtime etc) therefore Tango kernel must foreseen errors, faults etc

- Fault tolerance (the system should recover itself)
- Recoverability (...)

_Performance_

We must also test what developers of device servers write.

- Resource utilization (the library itself should not take much)

_Extensibility_ (like possibility to use maven. Use plugin-like model of development) 

- Tango Controls must provide an easy way to extend itself to users aka plugins


The following must be considerate during design but not the goal per se.

_Portability_

- Installability (there must be packaging)
- Adaptability (like device catalogue)


_Functional suitability_
