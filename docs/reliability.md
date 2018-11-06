### Reliability in Tango Controls

Tango Controls is a framework used in critical environments (non stop operations, minimal downtime, etc) therefore Tango kernel must foresee errors, faults etc

We prioritize the following attributes in Reliability part:

- Fault tolerance (the system should recover itself);
- Recoverability.


The most important feature of reliability must be fault tolerance as Tango Controls performs in critical environments. Fault tolerance provides several important features that must be foreseen in Tango:

1) self recoverage;

2) self monitoring;

3) minimal downtime.


**Conclusions**

The above features currently missing from the Tango core libraries and typically are implemented on top of Tango by the users. We suggest integrating these features into Tango core. This is especially handy because this can be done in the existing code base. For this a Tango protocol additions must be defined. See [Tango V10 section](tango_v10.md)
