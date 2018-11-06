## Summary
                                                                                                   
The main idea of this design document was to reveal drawbacks and benefits of the existing Tango core and to give 
a first perspective of how to improve the pitfalls while maintaining the advantages.

We have looked at Tango core code from the different points of view:

- a set of different quality attributes view;
- architectural view;
- design and implementation view.

We propose some ideas and thoughts of how viewpoints can be applied in Tango. These views were described in previous chapters and have examples in block schemes and in "interface" presentation to have a high level overview. We also looked through the [EPICs](https://epics.anl.gov/) architecture to find out and compare the ideas of its design to have a better feeling that we are on the right way.

The essential question of CORBA existence in the Tango Controls core was reviewed.

We hope that this design document will help to look at the Tango core from another point of view and will help to change the attitude to development and adding new features.

Business goals and scenarios were not covered in details in this document.

Overall, Tango Controls architecture is intended to satisfy most of important to the project quality attributes. But design and implementation of existing Tango core library fails to meet some of the essential quality attributes. Therefore, a number of refactorings should to be done in the future. Some of them were presented in the current version of this document and more are going to be in the next versions. For example, some part of the core library may become part of the protocol layer (e.g. heartbeat).



## Suggested roadmap

As the result of the code analysis and suggested changes the following roadmap can be foreseen:

1) add internal event bus into core library: implement event bus + integrate with existing code i.e. read/write attributes etc;  
2) refactor logging (as least critical feature) to be plugin that listens internal event bus and logs all events;

3) refactor polling feature to be plugin that listens  internal event bus;

4) implement Tango transport on top of CORBA;

5) implement new event system on top of new transport (see 4).
