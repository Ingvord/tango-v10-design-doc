### Performance


We must also test what developers of device servers write.

_ Lorenzo: I think I disagree with the above. Performance tests should be against the TANGO core and a number of reference use cases. There should be a way to distinguish between the core performance and the specific device server added overhead. _

- Resource utilization (the library itself should not take much)

**Conclusions**

Currently there are no benchmark tests, no publicly available results. Without it there is no reliable source based on which any assumptions about performance can be done. Some use cases must be covered by benchmark tests on a regular basis i.e. integrated in continuous integration. 
