# What is Inversion of Control

the Inversion of Control (IoC) principle is implemented using the Dependency Injection (DI) design pattern. <br>


# IoC Container

The IoC container is responsible to instantiate, configure and assemble the objects. <br>
The IoC container gets informations from the XML file or config files with annotations. <br>
The main tasks performed by IoC container are:

1. to instantiate the application class
2. to configure the object
3. to assemble the dependencies between the objects

There are two types of IoC containers. They are:

1. BeanFactory
2. ApplicationContext

# Life cycle of beans

These activities are known as bean Lifecycle.

*Standard bean lifecycle interfaces & there standard order of execution are given below.*

1. IoC container will look for the configuration metadata of given Bean.
2. Once find, container will create the instance of Bean(Using reflection API).
3. After instance creation dependency will be injected(DI).

*If Bean Class implements any of the below highlighted interface then corresponding method will be invoked in below order(Point 4 – 13).*

 4. setBeanName method of BeanNameAware Interface. It sets the name of the bean in the bean factory that created this bean.
 5. setBeanClassLoader method of BeanClassLoaderAware Interface. Callback that supplies the bean to a bean instance.
 6. setBeanFactory  method of BeanFactoryAware Interface. Callback that supplies the owning factory to a bean instance.

*Below method execution will be applicable when running in an application context. (Points 7 – 11)*

7. setResourceLoader  method of ResourceLoaderAware Interface. It set the ResourceLoader that this object runs in.
8. setApplicationEventPublisher  method of ApplicationEventPublisherAware Interface. Set the ApplicationEventPublisher that this object runs in.
9. setMessageSource method of MessageSourceAware Interface. Set the MessageSource that this object runs in.
10. setApplicationContext method of ApplicationContextAware Interface. Set the ApplicationContext that this object runs in.
11. setServletContext method of ServletContextAware Interface. Set the ServletContext that this object runs in.

12. postProcessBeforeInitialization method of BeanPostProcessor Interface. Apply this BeanPostProcessor to the given new bean instance before any bean initialization callbacks.
13. afterPropertiesSet method of InitializingBean Interface. Invoked by a BeanFactory after it has set all bean properties supplied.

*In case Bean class has custom init method defined(via init-method attribute)*

14. Custom init method will be invoked.
15. postProcessAfterInitialization methods of BeanPostProcessors. Apply this BeanPostProcessor to the given new bean instance after any bean initialization callbacks

*When Bean Factory is getting shut down following lifecycle methods will be executed.*

16. DisposableBean’s destroy method. Invoked by a BeanFactory on destruction of a singleton.
17. Custome destroy method will be executed if there is any defined via destroy-method attributes


# Usage in Spring

CoreConfig.java
```
import com.mongodb.MongoClient;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import java.net.UnknownHostException;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.avengers")
@PropertySource(value = {
        "classpath:application.properties",
        "classpath:server.properties",
        "classpath:db.properties",
        "classpath:log4j.properties"})
public class CoreConfig {

    @Bean
    public MongoClient MongoClient(Environment environment) throws UnknownHostException {
        final String mongoUrl = environment.getProperty("mongo.url");
        final String mongoPort = environment.getProperty("mongo.port");
        return new MongoClient(mongoUrl, Integer.parseInt(mongoPort));
    }

}
```

Application.java
```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = "com.avengers")
@PropertySource(value = {
        "classpath:application.properties",
        "classpath:server.properties",
        "classpath:db.properties",
        "classpath:log4j.properties"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

Use with annotaions
```
@Autowired
@Service
@Bean
...
```


