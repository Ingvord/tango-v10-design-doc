# Server side specific

## Internal event bus

Server side library is split into loosely coupled components. Each component interacts with others via internal event bus:

```java

interface EventBus {
    void subscribe(String event, Class<T> dataType);
    
    <T> void publish(String event, Message<T> message);//TODO runtime message type check
} 

interface Message<T> {
    String getEventName();
    T getEventData();    
}

interface EventBusListener {
    void onBeforeEvent();
        
    void onEvent(Object message);
    
    void onError(Exception clause);
    
    void onAfterEvent();
}

```

TangoEventBus extends EventBus by adding Tango specific server side events:

```java

interface AttributeRead<T extends TangoDeviceAttribute<V>, V extends TangoDataType> {
    T getAttribute();
    V getData();    
}

interface AttributeWrite<T extends TangoDeviceAttribute, V extends TangoDataType> {
    T getAttribute();
    V getData();    
}

interface CommandExecute<T extends TangoDeviceCommand<IN, OUT>, IN extends TangoDataType, OUT extends TangoDataType> {
    T getCommand();
    IN getInput();    
    OUT getOutput();
}

interface TangoEventBus extends EventBus {
    void attributeRead(Message<AttributeRead> data);
    
    void attributeWrite(Message<AttributeWrite> data);
    
    void commandExecute(Message<CommandExecute> data);
    
    void eventReceived(Message<TangoEvent> data);
}

interface TangoReadAttributeListener {
    void onBeforeAttributeRead(Message<AttributeRead> data);
    
    void onAttributeRead(Message<AttributeRead> data);
    
    void onAfterAttributeRead(Message<AttributeRead> data);
}

interface TangoWriteAttributeListener {
    void onBeforeAttributeWrite(Message<AttributeWrite> data);
    
    void onAttributeWrite(Message<AttributeWrite> data);
    
    void onAfterAttributeWrite(Message<AttributeWrite> data);
}

interface TangoExecuteCommandListener {
    void onBeforeExecuteCommand(Message<CommandExecute> data);
    
    void onExecuteCommand(Message<CommandExecute> data);
    
    void onAfterExecuteCommand(Message<CommandExecute> data);
}

interface TangoEventReceivedListener {
    void onBeforeEventReceived(Message<EventReceived> data);
    
    void onEventReceived(Message<EventReceived> data);
    
    void onAfterEventReceived(Message<EventReceived> data);
}

//etc
```

Each component receives TangoContext and DeviceContext during initialization:

```java

interface TangoKernelComponent {
    void initialize(TangoContext tangoContext, DeviceContext context);
}

interface TangoWorkerComponent extends TangoKernelComponent {
    void start();
}

``` 

Typical TangoComponentImpl:

```java

class Foo implements TangoKernelComponent, TangoWriteAttributeListener {//TODO generics
    //in Java this can be replaced with annotation type, i.e. @TangoComponent or @TangoWorkerComponent 
    static {
        TangoContext.registerComponent(Foo.class);
    }

    /**
    * NOTE: in Java this can be replaced with DependencyInjection aka Guice 
    */
    @Override
    void initialize(TangoContext tangoContext, DeviceContext context){
        tangoContext.getEventBus().subscribe("org.tango.read_attribute");
    }
    
    void onBeforeAttributeWrite(AttributeWrite<> message){//TODO generics
        System.out.printf("Write %d into attribute %s ",message.getData(), message.getAttribute().getName());        
    }
}

```

### TangoEngineComponent

Central component of the server side API is TangoEngineComponent. This component intiliazes all other components and performs 
an orchestration of all components i.e. wraps TangoEventBus:

```java

interface TangoEngineComponent extends TangoComponent{
    void start();
}

``` 

Typical implementation:

```java

class TangoEngineComponentImpl implements TangoEngineComponent {
    void initialize(TangoContext context, DeviceContext deviceContext){
        context.getRegisteredComponents()
            .forEach( (cmpt) -> System.out.println(cmpt.getClass().getSimpleName()));
    }
    
    void start(){
        context.getRegisteredWorkerComponents()
            .forEach( (cmpt) -> cmpt.start());        
    }
}

```

### AttributeReadComponent

### AttributeWriteComponent

### EventSystemComponent

### PollingComponent

### LoggingComponent

