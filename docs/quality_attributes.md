# Quality attributes

Many factors determine the qualities in a system's architecture.
The next figure shows the [ISO/IEC FCD 25010](http://iso25000.com/index.php/en/iso-25000-standards/iso-25010) product quality standard. This quality model determines which quality characteristics should be taken into account when evaluating the properties of a software product.

![](images/IEC_FCD_25010_product_quality_standard.png)

Currently Tango core faces the following problems that prevent it from reaching its business goals:

1) Human resources which are allocated to development and improvement of the Tango core code are very limited

2) Current code base structure does not meet quality level of commercial/open source projects of a similar size

3) Dependency on a legacy CORBA framework. And most importantly Tango is a thin wrapper on top of CORBA

_ Lorenzo: whilst I can see the drawbacks with CORBA dependency, I'm not sure I understand why the wrapper
layer should be an issue. _

4) Parts of the Tango code violate principle of information hiding and separation of concerns

5) Dependency on specific versions of 3rd party libraries (CORBA, ZMQ) in a way that changing the version or a library is almost impossible

6) Unclear ways of the interaction between components of the system

To solve the above problems we suggest to focus on the following quality attributes and redesign Tango core library.

_ Lorenzo: the following is a bit confused. Which are the quality attributes? Is it the list bottom page? _

Some of the quality attributes from the above picture are naturally inherited from CORBA design i.e. Interoperability.

Below is a short list of the presence of particular attributes in Tango:

**TODO** define and describe presence of quality attributes in Tango (incl. inherited from CORBA).

**TODO** 

- Write about CORBA. 
- Perhaps, take parts of CORBA which are mostly used and put it into kernel (make some parts of CORBA be Tango Controls).
- Which benefits are we going to have? Which consequence this decision will make to the whole Tango community?
- If it is decided to get rid of CORBA, what should be done? What influence will it have to Tango Controls?

_ Lorenzo: this TODO is a bit unclear to me. It was my understanding that the goal for TANGO (not sure it fits into the undergoing effort) was to make the transport layer pluggable. This clearly does not mean incorporating CORBA into TANGO, rather redisigning the product to have a clear separation and a defined interface between TANGO and the transport. Incidentally, this also solves the problem of obsolescence, making the transport replaceable if required._


The most important and those which Tango Controls mostly miss are the following:

- [Maintainability](maintainability.md)    
- [Reliability](reliability.md)   
- [Fault tolerance](fault_tolerance.md)   
- [Performance](performance.md) 
- [Extendability](extendability.md) 
- [Portability](portability.md)
- [Adaptability](adaptability.md)

_ Lorenzo: reading through each quality attribute section there are gorups and repetitions. This does not facilitate reading. I'd suggest to avoid quality attribute grouping, and keep a plain list. Easier to mantain and understand. Also, easier to prioritize. _