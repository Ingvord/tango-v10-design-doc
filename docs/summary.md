# Summary
                                                                                                   
The main idea of this design document was to reveal drawbacks and benefits of the existing Tango core and to give 
a first perspective of how to improve the pitfalls while maintaining the advantages.

We have looked at Tango core code from the different points of view:

- a set of different quality attributes view;
- architectural view;
- design and implementaion view.

We propose some ideas and thoughts of how viewpoints can be applied in Tango. These views were described in previous chapters and have examples in block schemes and in "interface" presentation to have thigh level overview.

We hope that this design document will help to look at the Tango core from another point of view and will help to change the attitude to development and adding new features.

Business goals and scenarios were not covered in details in this document.

TODO написать, как новая архитектура повлияла на атрибуты, которые уже были (есть) в Танго 
(например, все используют корбу, и не думают, что есть какие-то атрубуты в ней по умолчанию)
и какие новые добавились и как это вообще всё взаимодействует.

In overall Tango Controls architecture is good. But design and implementation of existing Tango core library fails to meet some of the essential quality attributes. Therefore a number of refactorings have to be proposed. Few of them were presenten in the current version of this document and more are going to be in the future versions. For example, some part of the core library may become part of the protocol layer (e.g. heartbeat).

# Suggested roadmap

As the result of the code analysis and suggested changes the following roadmap can be foreseen:

1) add internal event bus into core library: implement event bus + integrate with existing code i.e. read/write attributes etc;  
2) refactor logging (as least critical feature) to be plugin that listens internal event bus and logs all events;

3) refactor polling feature to be plugin that listens  internal event bus;

4) implement Tango transport on top of CORBA;

5) implement new event system on top of new transport (see 4).
