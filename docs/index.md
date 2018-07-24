https://www.tutorialspoint.com/software_architecture_design/index.htm


## Preface

The document refers to the Tango Kernel. And all the following will be mostly about it.



# Mission of this document

Describe architecture of the Tango Controls core from the kernel developer point of view.

The ultimate goal of this document is to define a common language between developers with different backgrounds (Physics VS Software development). 
Therefore, improve future communication between them. Once such communication is established, the proposed design may be applied to the existing code base. This will dramatically improve code quality, resolve many current issues and also significantly simplify further bug fixing.

While this document describes the targeted architecture design for Tango core, we keep in mind current community needs and possible difficulties that may occur if migration to this new architecture will be approved. 

This document is supllied with "skeletal" implementation sketch based on Java interfaces. The mentioned implementation 
is inteded to demonstrate how the new design may be implemented in Tango kernel library as well it will help to define a roadmap for Tango kernel refactoring. And in the future will provide the basis for evolutionary refactoring of Tango Controls.

The goal of the new achitecture is to decrease time to market and lower integration and maintain costs of the Tango Controls users. 

Finally, it will greatly improve flexibility by reducing design and system complexity.



## Business goal

Make Tango Controls suitable for applying in Industry and in Commerce and make it de facto a standard framework for Industry 4.0. Guarantee sustainable existence for the next 15-20 years.

##  Stakeholders

The main idea of this document addresses the concerns of kernel developers. Though, we hope that it facilitates communication among other stakeholders (end users, project manages, maintainers).

developers require well defined architectural structures and their relation ships


## Terminology and definitions

To make our further discussions productive, we would like to define the key words from our point of view and show how we understand them. 

If we look on the concepts of Tango we would draw in the head the following scheme (taken from Project Tango-Python at LIONS, iramis - CEA):

![](images/Tango_schema_by_others.png)

And this is a high overview sketch of how we think the Tango elements connect with each other in real live.

![](images/Tango_schema_1.png)

The same information presented in another way:

![](images/Tango_schema_2.png)

Each block is opened up as following:

![](images/Tango_schema_3.png)

So talking about improvements and refactoring we would consider _only_ what is marked as "Core library".




## Preface

In this part you will find what to expect from this document and in which way information and ideas will be presented.


* At the moment users have to write .... We want to reduce user's operations and propose to do something like that:

![](images/UsersCode_toCoreLib.png)

These ideas will be presented in more details in the part N **TODO**


Protocols




## Quality attributes

Many factors determine the qualities in a system's archtecture.
The next figure shows the [ISO/IEC FCD 25010](http://iso25000.com/index.php/en/iso-25000-standards/iso-25010) product quality standard. This quality model determines which quality characteristics should be taken into account when evaluating the properties of a software product.

![](images/IEC_FCD_25010_product_quality_standard.png)

We must say that no list will ever be complete... (что-то да забудем, что-то невозможно будет реализовать из-за столкновения интересов)

Currently Tango core faces the following problems that prevent it from reaching its business goals:

1) Human resources which are allocated to development and improvement of the Tango core code are very limited.
2) Current code base structure does not meet quality level of commercial/open source projects of a similar size
3) dependency on a legacy CORBA framework and most importantly Tango is a thin wrapper onto of CORBA
4) parts of the Tango code violate principle of information hiding and separation of concerns
5) dependency on specific versions of 3rd party libraries (CORBA, ZMQ) in a way that changing the version or a library almost impossible
6) unclear ways of the interaction between components of the system

To solve the above problems we suggest to focus on the following quality attributes and redesign Tango kernel library accordingly(- ??????).

Some of the quality attributes from above picture are naturally inherited from CORBA design i.e. Interoperability.

Below is a short analysis of the presence of particular attributes in Tango:

TODO define and describe presence of all other quality attributes in Tango (inherited from CORBA)

The most important and those which Tango Controls mostly miss are:

The following quality attributes must be focused:

Maintainability

Tango is a long term project therefore code readability and its modifiability must prevail over non-trivial code optimizations. Due to the fast progress in the IT nowdays optimizations may decrease performance tomorrow i.e. consider new CPU commands and built-in compiler optimizations.

- Modifiability (the most important)
- Modularity (guarantees Modifiability)
- Testability (as the result of Modularity)

Reliability

Tango is a framework used in critical environments (non stop operations, minimal downtime etc) therefore Tango kernel must foreseen errors, faults etc

- Fault tolerance (the system should recover itself)
- Recoverability (...)

Performance (we must also test what developers of device servers write)
- Resource utilization (the library itself should not take much)

Extensibility (like possibility to use maven. Use plugin-like model of development) -- Tango Controls must provide an easy way to extend itself to users aka plugins

The following must be considerate during design but not the goal per se.

Portability
- Installability (there must be packaging)
- Adaptability (like device catalogue)


Functional suitability

TODO describe









# Maintainability in Tango

Most important quality attribute within **Maintainability** group is **Modifiability**. Below is a short analysis of the current situation:

Currently it is relatively easy to add new functionality i.e. extendability. For instance, to add a new command to a database server:
1) update IDL
2) implement some glue code on client/server side:
  - on the client side this usually means adding a new method to DeviceProxy
  - on the server - implement command and glue it to TangoDatabse device server API
  
very hard to change existing functionality i.e. patches, changes to protocol (send new data as command response). Or to extand core functionality e.g. integration with dbus.

Impossible to change underlying protocol - currently is bound to CORBA

## Conclusions

Either historically or intentionaly architecture was adapted for adding new functionality. Nowdays when functionality is mature enough (it is hard to foreseen any major additons like event system or new polling mechanism) focus must be switched to changes to the existing code base ot allow quick fixes, performance improvements and most importantly pluggability, so users can implement their own changes in a centralized manner without changing kernel library

# Reliability  in Tango

The most important feature of availability must be fault tolerance as Tango performs in critical environments. Fault tolerance provides several imortant feaures that must be foreseen in Tango:

1) self recoverage

2) self monitoring

3) minimal downtime


Below is the short analysis of the current situation:

## Fault tolerance in Tango

Tango inherits some of the properties from CORBA due to implementation as well as introduces new ones. Specifically:

1) transparent reconnection

2) device's state machine

3) propagating errors to the client

4) heartbeats in event system

These features can be extended with:

1) self monitoring - integrate heartbeat into protocol

2) start-up health check

3) implement errors self recovery (some of them)

## Conclusions

Fault tolerance is already implemented quite well, but can be improved.

# Adaptability   in Tango

As Tango aims to be installed in a variety of environments it is essential to highlight **adaptability** quality attribute.  Most important is to provide extendability implemented in the kernel library. Below is the short description of the current situation.

## Extendability in Tango

Impossible to extend current kernel library with a custom functionality. Tango may be extended via introduction of new Tango device servers, that may enrich Tango functionality. Which is sometimes not so effecient nor enough.

## Conclusions

Extendability of the kernel library must be designed from scratch.

# Summary
                                                                                                   
TODO написать, как новая архитектура повлияла на атрибуты, которые уже были (есть) в Танго 
(например, все используют корбу, и не думают, что есть какие-то атрубуты в ней по умолчанию)
и какие новые добавились и как это вообще всё взаимодействует.


The main idea of this design document was to reveal drawbacks and benefits of the existing Tango kernel and to give 
a first perspective of how to improve the pitfalls while maintaining the advantages.

We have looked at Tango core code from the different points of view:
- from a set of different quality attributes,
- different architectrual views,
- stakeholder for a System and their interests.
And propose some ideas and thoughts of how this or that viewpoint can be applaid in Tango.

These views were discribed in diffenets chaptars and have examples both in "interface" presentation  and writen ones to have thigh level overview.

We hope that this design document will help to look at the Tango kernel from another point of view and changes the attitude to development and adding new features.

Business goals and scenarios were not covered in details in this design documents.




