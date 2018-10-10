# Quality attributes

Many factors determine the qualities in a system's architecture.
The next figure shows the [ISO/IEC FCD 25010](http://iso25000.com/index.php/en/iso-25000-standards/iso-25010) product quality standard. This quality model determines which quality characteristics should be taken into account when evaluating the properties of a software product.

![](images/IEC_FCD_25010_product_quality_standard.png)

Currently Tango core faces the following problems that prevent it from reaching its business goals:

1) Human resources which are allocated to development and improvement of the Tango core code are very limited

2) Current code base structure does not meet quality level of commercial/open source projects of a similar size

3) Dependency on a legacy CORBA framework. And most importantly Tango is a thin wrapper on top of CORBA

4) Parts of the Tango code violate principle of information hiding and separation of concerns

5) Dependency on specific versions of 3rd party libraries (CORBA, ZMQ) in a way that changing the version or a library is almost impossible

6) Unclear ways of the interaction between components of the system

To solve the above problems we suggest to focus on the following quality attributes and redesign Tango core library.

Some of the quality attributes from the above picture are naturally inherited from CORBA design i.e. Interoperability.

Below is a short list of the presence of particular attributes in Tango:

**TODO** define and describe presence of quality attributes in Tango (incl. inherited from CORBA).

**TODO** 

- Write about CORBA. 
- Perhaps, take parts of CORBA which are mostly used and put it into kernel (make some parts of CORBA be Tango Controls).
- Which benifits are we going to have? Which consequence this decision will make to the whole Tango community?
- If it is decided to get rid of CORBA, what should be done? What influence will it have to Tango Controls?



The most important and those which Tango Controls mostly miss are the following:

- [Maintainability](maintainability.md)    
- [Reliability](reliability.md)   
- [Fault tolerance](fault_tolerance.md)   
- [Performance](performance.md) 
- [Extendability](extendability.md) 
- [Portability](portability.md)
- [Adaptability](adaptability.md)
