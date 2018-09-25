# Summary
                                                                                                   
TODO написать, как новая архитектура повлияла на атрибуты, которые уже были (есть) в Танго 
(например, все используют корбу, и не думают, что есть какие-то атрубуты в ней по умолчанию)
и какие новые добавились и как это вообще всё взаимодействует.


The main idea of this design document was to reveal drawbacks and benefits of the existing Tango kernel and to give 
a first perspective of how to improve the pitfalls while maintaining the advantages.

We have looked at Tango core code from the different points of view:

- from a set of different quality attributes,

- different architectural views,

- stakeholder for a System and their interests.

And propose some ideas and thoughts of how this or that viewpoint can be applied in Tango. These views were described in chapters and have examples in block schemes and in "interface" presentation to have thigh level overview.

We hope that this design document will help to look at the Tango kernel from another point of view and to change the attitude to development and adding new features.

Business goals and scenarios were not covered in details in this document.

ОБОЗНАЧИТЬ ПУНКТЫ ДЛЯ ОБСУЖДЕНИЯ НА МИТИНГЕ.
Высокоуровневая часть ок. Но если смотреть на детали, то не очень. Смотришь на блок схему, тоже не очень. Но сама реализация, например, со статическими методами зачем-то. 
Часть можно вынести на уровень протокола, но реализованы в ядре(напрмиер, подписывание событий)

# Suggested roadmap

As the result of the code analysis and suggested changes the following roadmap can be foreseen:

1) add internal event bus into core library: implement event bus + integrate with existing code i.e. read/write attributes etc  

2) refactor logging (as least critical feature) to be plugin that listens internal event bus and logs all events

3) refactor polling feature to be plugin that listens  internal event bus

4) implement Tango transport on top of CORBA 

5) implement new event system on top of new transport, see 4)  