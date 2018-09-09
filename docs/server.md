# Server side specific

[TOC]

# Existing codebase

## Polling 

### Polling framework classes

![](images/Polling_classes.png)

---
### PollingThread main loop

pollthread.cpp

![](images/Polling_loop_1.png)

---

![](images/Polling_loop_2.png)

---

![](images/Polling_loop_3.png)

---

#### PollingThread::main_loop::execute_cmd

![](images/Polling_execute_cmd.png)

---

##### PollingThread::main_loop::execute_cmd::poll_add_obj

![](images/Polling_poll_add_obj.png)

---

##### PollingThread::main_loop::execute_cmd::update_period

![](images/Polling_update_period.png)

---

#### PollingThread::main_loop::one_more_poll

![](images/Polling_one_more_poll.png)

---

#####  PollingThread::main_loop::one_more_poll::poll_cmd

![](images/Polling_poll_cmd.png)

---

#####  PollingThread::main_loop::one_more_poll::poll_attr

![](images/Polling_poll_attr.png)

---

#####  PollingThread::main_loop::one_more_poll::eve_heartbeat

![](images/Polling_heartbeat.png)

---

#####  PollingThread::main_loop::one_more_poll::store_subdev

![](images/Polling_store_subdev.png)

---

#### PollingThread::main_loop::one_more_trig

![](images/Polling_one_more_trig.png) 

---

## Polling client: DServer

### DServer::add_poll_obj

![](images/Polling_DServer_add_poll_obj_1.png)

---

![](images/Polling_DServer_add_poll_obj_2.png)

---

## Conclusions

Current code base tries to implement [Command pattern](https://en.wikipedia.org/wiki/Command_pattern) but fails to do so due to the lack of OOP principles implemented (no polymorphism; complicated responsibilities structure, etc).

The following key features may be extracted from the code base:

1) Heartbeat;

2) Thread control via shared command;

3) Attributes/Commands may be polled at different rate;

4) Thread pool;

5) Values are stored in RingBuffer;

6) external trigger (??? need more info/use cases).

## Refactoring proposal

Separate "Control thread" and "Worker threads". Implement standalone queue for execution tasks. "Control thread" will wake up at next execution time and submit the task to "Worker thread" via thread pool interface.

![](images/Polling_refactoring.png)

New classes diagram:

![](images/Package_polling.png)

See [Pull Request #472](https://github.com/tango-controls/cppTango/pull/472) for more details.
