# Spring

* DI  dependency Injection
* IOC  Inversion of Control
* AOP  aspect oriented programming
* OOP  object oriented programming

### 建立spring
```java
//1. 创建 Spring 的 IOC 容器
ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

//2. 从 IOC 容器中获取 bean 的实例，该方法需要强转
HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld3");
//或
HelloWorld helloWorld1 = ctx.getBean(HelloWorld.class);     //当同类型 bean 只有一个时，若非唯一则抛出异常

//3. 使用 bean
helloWorld.hello();
```
1. ApplicationContext 的主要实现类：
    ClassPathXmlApplicationContext：从 类路径下加载配置文件
    FileSystemXmlApplicationContext: 从文件系统中加载配置文件

2. ConfigurableApplicationContext 扩展于 ApplicationContext
    新增加两个主要方法：refresh() 和 close()， 让 ApplicationContext 具有启动、刷新和关闭上下文的能力

3. ApplicationContext 在初始化上下文时就实例化所有单例的 Bean。

### Spring 的依赖注入

1. 属性注入（getter）
```xml
<bean id="helloWorld" class="com.spring.helloworld.HelloWorld">
    <!-- 为属性赋值, user对应于类中setUser方法 -->
    <property name="user" value="Jerry"></property>
</bean>
```

2. 构造器注入 (constructor)
```xml
<!--定位信息可使用 index 或者 type ,也可以混合使用-->
<bean id="car" class="com.spring.helloworld.Car">
    <constructor-arg value="audi" index="0"></constructor-arg>
    <constructor-arg value="yiqi" index="1"></constructor-arg>
    <constructor-arg value="500000" index="2" ></constructor-arg>
</bean>

<bean id="car" class="com.atguigu.spring.helloworld.Car">
    <constructor-arg value="audi" type="java.lang.String" ></constructor-arg>
    <constructor-arg value="yiqi" type=" java.lang.String " ></constructor-arg>
    <constructor-arg value="250000" type="float"></constructor-arg>
</bean>
```

3. 工厂方法注入（很少使用，不推荐）type

### 关于属性注入的细节

* 基本数据类型及其封装类、String 等类型都可以采取字面值注入的方式，即直接赋值value
* 若字面值中包含特殊字符，可以使用 `<![CDATA[...]]>` 把字面值包裹起来
* Bean中某属性赋值为空须使用`<null/>`标签：`<constructor-arg><null/></constructor-arg>`
* 当一个bean中的一个属性为另一个bean时（外部bean）
    ```xml
    <bean id="dao" class="com.spring.ref.Dao"></bean>
    <bean id="service" class="com.spring.ref.Service">
        <!-- 通过 ref 属性值指定当前属性指向哪一个bean -->
        <property name="dao" ref="dao"></property>
    </bean>
    ```
* 当一个bean中的一个属性为另一个bean时（内部bean），在bean的内部，以property包裹, 此时并不需要id或name
    ```xml
    <bean id="service2" class="com.spring.ref.Service">
        <property name="dao">
            <!-- 内部 bean, 类似于匿名内部类对象. 不能被外部的 bean 来引用, 也没有必要设置 id 属性 -->
            <bean class="com.spring.ref.Dao">
                <property name="dataSource" value="c3p0"></property>
            </bean>
        </property>
    </bean>
    ```
* Bean中赋值级联属性：
    ```xml
    <bean id="action" class="com.spring.ref.Action">
        <!-- 设置级联属性，需先创建其内部 bean，再对其中属性赋值 -->
        <property name="service" ref="service2"></property>
        <property name="service.dao.dataSource" value="DBCP"></property>
    </bean>
    ```
* Bean中赋值集合属性：`<list>, <set>`
    ```xml
    <bean id="user" class="com.spring.helloworld.User">
        <property name="name" value="Jack"></property>
        <!-- cars 属性为 list -->
        <property name="cars">
            <list>
                <ref bean="car1"/>
                <ref bean="car2"/>
            </list>
        </property>
    </bean>
    ```
* Bean中赋值集合属性：`<map>`
    ```xml
    <bean id="user" class="com.spring.helloworld.User">
        <property name="name" value="Rose"></property>
        <property name="age" value="20"></property>
        <property name="cars">
            <map>
                <entry key="A" value-ref="car1"/>
                <entry key="B" value-ref="car2"/>
            </map>
        </property>
    </bean>
    ```

* Bean中赋值集合属性：`<prop>`
    ```xml
    <bean id="dataSource" class=" com.spring.helloworld. dataSource ">
        <property name=" properties ">
            <props>
                <prop key="uesr">root</prop>
                <prop key="password">1234</prop>
                <prop key="jdbcUrl">jdbc:mysql:///test</prop>
                <prop key="driverClass">com.mysql.jdbc.driver</prop>
            </props>
        </property>
    </bean>
    ```
* Bean中赋值，使用p命名空间
    ```xml
    <bean id="user" class="com.spring.helloworld.User" p:cars-ref="cars" p:userName="Jack"></bean>
    ```
* 类型bean，例如公用的集合bean，不必再每次配置在bean的内部
    ```xml
    <!-- 声明集合类型的 bean -->
    <util:list id="cars">
        <ref bean="car"/>
        <ref bean="car2"/>
    </util:list>
    <bean id="user2" class="com.spring.helloworld.User">
        <property name="userName" value="Rose"></property>
        <!-- 引用外部声明的 list -->
        <property name="cars" ref="cars"></property>
    </bean>
    ```
* Bean的继承
    * 继承
    * 依赖
        Spring 允许用户通过 depends-on 属性设定 Bean 前置依赖的Bean，前置依赖的 Bean 会在本 Bean 实例化之前创建好<br>
        如果前置依赖于多个 Bean，则可以通过逗号，空格或的方式配置 Bean 的名称
        ```xml
        <!--此为模板类，并不需要指定其class，无法被IOC实例，只能被继承-->
        <bean id="address" p:city="Beijing" p:street="ChanganSt." abstract="true"></bean>
        <!--继承模板类-->
        <bean id="address1" class="com.spring.helloworld.Address" parrent="address" p:street="Renminst.">
        <!--person类依赖于 car 类，需要使用depends-on -->
        <bean id="person" class=" com.spring.helloworld.Person" p:name="Tom" p:address-ref="address1" depends-on="car"></bean>
        <bean id="car" class=" com.spring.helloworld.car" p:brand="audi" p:price="500000">
        ```

### Bean 的自动装配：使用autowire 属性里指定自动装配的模式
* 自动装配: 只声明 bean, 而把 bean 之间的关系交给 IOC 容器来完成
* byType: 根据类型进行自动装配. 但要求 IOC 容器中只有一个类型对应的 bean, 若有多个则无法完成自动装配.
* byName: 若属性名和某一个 bean 的 id 名一致, 即可完成自动装配. 若没有 id 一致的, 则无法完成自动装配.
* 在使用 XML 配置时, 自动转配用的不多. 但在基于 注解 的配置时, 自动装配使用的较多.
    ```xml
    <bean id="dao" class="com.atguigu.spring.ref.Dao" scope="prototype"></bean>
    <!-- service 类中存在 setDao方法 -->
    <bean id="service" class="com.atguigu.spring.ref.Service" autowire="byName"></bean>
    ```

### Bean 的作用域

默认情况下 bean 是单例的<br>
但有的时候, bean 就不能使单例的. 例如: Struts2 的 Action 就不是单例的! 可以通过 scope 属性来指定 bean 的作用域
```xml
<bean id="dao2" class="com.atguigu.spring.ref.Dao" scope="prototype"></bean>
```
* prototype:   原型的. 每次调用 getBean 方法都会返回一个新的 bean. 且在第一次调用 getBean 方法时才创建实例
* singleton:     单例的. 每次调用 getBean 方法都会返回同一个 bean. 且在 IOC 容器初始化时即创建 bean 的实例. 默认值
* request:        每次请求创建新实例，该作用域仅适用于WebApplicationContext环境
* session:         同一个 http session 共享一个 bean ，不同 http session 使用不同的bean，作用域同上。


### 使用外部属性文件：

Spring 提供了一个 PropertyPlaceholderConfigurer 的 BeanFactory 后置处理器,<br>
这个处理器允许用户将 Bean 配置的部分内容外移到属性文件中. 可以在 Bean 配置文件里使用形式为 ${var}的变量
```xml
<!-- 导入外部的资源文件 -->
<context:property-placeholder location="classpath:db.properties"/>
<!-- 配置数据源 -->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="user" value="${jdbc.user}"></property>
    <property name="password" value="${jdbc.password}"></property>
    <property name="driverClass" value="${jdbc.driverClass}"></property>
    <property name="jdbcUrl" value="${jdbc.jdbcUrl}"></property>
    <property name="initialPoolSize" value="${jdbc.initPoolSize}"></property>
    <property name="maxPoolSize" value="${jdbc.maxPoolSize}"></property>
</bean>
```

### IOC 容器中 Bean 的生命周期方法

Spring IOC 容器对 Bean 的生命周期进行管理的过程:

1. 通过构造器或工厂方法创建 Bean 实例（constructor）
2. 为 Bean 的属性设置值和对其他 Bean 的引用（setter）
3. public Object postProcessAfterInitialization(Object bean, String beanId)---- init-method之前被调用
4. 调用 Bean 的初始化方法(init)
5. public Object postProcessAfterInitialization(Object arg0, String arg1)---- init-method之后被调用
6. Bean 可以使用了
7. 当容器关闭时, 调用 Bean 的销毁方法

在 Bean 的声明里设置 init-method 和 destroy-method 属性, 为 Bean 指定初始化和销毁方法.
```xml
<bean id="car" class="com.spring.helloworld.car" init-method="init" destory-method="destory">
```

### Bean 的配置方式：
1. 通过全类名（反射）
2. 通过工厂方法（静态工厂方法 & 实例工厂方法）
3. 通过FactoryBean

### Bean的配置方式之工厂方法

1. 静态工厂方法
```java
public class StaticCarFactory{
    private static Map<String,Car> cars = new HashMap<>();
    static {
        cars.put("audi",new Car("audi",300000));
        cars.put("ford, new Car("ford",200000));
    }
    // 静态工厂方法: 类中有一个静态方法, 可以返回一个类的实例
    public static Car getCar(String name){
        return cars.get(name);
    }
}
```
```xml
<!-- 在 class 中指定静态工厂方法的全类名, 在 factory-method 中指定静态工厂方法的方法名 -->
<bean id="car" class="com.spring.beans.factory. StaticCarFactory" factory-method="getCar">
     <!-- 可以通过 constructor-arg 子节点为静态工厂方法指定参数 -->
     <constructor-arg value="audi"></constructor-arg>
</bean>
```

2. 实例工厂方法
```java
public class InstanceCarFactory{
     private static Map<String,Car> cars = null;
     public InstanceCarFactory(){
          cars = new HashMap<String, Car>();
          cars.put("audi",new Car("audi",300000));
          cars.put("ford, new Car("ford",200000));
     }
     public static Car getCar(String name){
          return cars.get(name);
     }
}
```
```xml
<!--配置工厂的实例-->
<bean id="carFactory" class=" com.spring.beans.factory.InstanceCarFactory ">
<!--通过实例工厂方法来配置bean-->
<bean id="car" factory-bean="carFactory" factory-method="getCar">
     <constructory-arg value="ford"></constructor-arg>
</bean>
```

3. 通过FactoryBean
```java
//自定义 FactoryBean 需要实现 FactoryBean接口
public class CarFactoryBean implements FactoryBean<Car>{

     private String brand;
     pubilc void setBrand(String brand) {
          this.brand = brand;
     }

     @Override     //返回bean的对象
     public Car getObject() throws Exception {
          return new Car("BMW",500000);
     }
     @Override      //返回bean的对象
     public Class<?> getObjectType() {
          return Car.class;
     }
     @Override          //是否为单例
     public boolean isSingleton() {
          return true;
     }
}
```
```xml
<!--指向自定义工厂类，返回getObject()方法返回的实例 -->
<bean id="car" class=" com.spring.beans.factory.CarFactoryBean ">
     <property name="brand" value="BMW"></property>
</bean>
```

### 使用注解来配置bean实例

* 特定组件包括:

    * @Component: 基本注解, 标识了一个受 Spring 管理的组件
    * @Respository: 标识持久层组件
    * @Service: 标识服务层(业务层)组件
    * @Controller: 标识表现层组件
    ```xml
    <context:component-scan
        base-package="com.spring.bean.annotation"
        resource-patten="service/*.class"
        use-default-filters="false">
            <!--当 use-default-filters中指定为false时使用include-filter-->
            <context:include-filter  type="annotation" expression="org.springframework.stereotype.repository">
            <context:exclude-filter  type="assinable"  expression="org.springframework.stereotype.repository.UserRepository">
    </ context:component-scan >
    ```

* 关于@Autowired

    1. 若使用的bean不存在，可以使用@Autowired(required="false")来避免报错
    2. 若使用的bean存在多个：     
        * @Autowired("xxxService") 指定使用的bean
        * @Autowired
        @Qualifier("xxxServiceImpl")指定具体是实现类

* 泛型依赖注入（新特性）

### 使用动态代理实现AOP
```java
package com.atguigu.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCalculatorLoggingProxy {

    //要代理的对象
    private ArithmeticCalculator target;

    public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
        super();
        this.target = target;
    }

    //使用动态代理，并返回代理的对象
    public ArithmeticCalculator getLoggingProxy(){

        ArithmeticCalculator proxy = null;

        ClassLoader loader = target.getClass().getClassLoader();
        Class [] interfaces = new Class[]{ArithmeticCalculator.class};
        InvocationHandler h = new InvocationHandler() {
            /**
            * proxy: 代理对象。 一般不使用该对象
            * method: 正在被调用的方法
            * args: 调用方法传入的参数
            */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                String methodName = method.getName();

                //打印日志
                System.out.println("[before] The method " + methodName + " begins with " + Arrays.asList(args));

                //调用目标方法
                Object result = null;

                try {
                    //前置通知
                    result = method.invoke(target, args);
                    //返回通知, 可以访问到方法的返回值
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    //异常通知, 可以访问到方法出现的异常
                }

                //后置通知. 因为方法可以能会出异常, 所以访问不到方法的返回值

                //打印日志
                System.out.println("[after] The method " + methodName + " ends with " + result);

                return result;
            }
        };

        /**
        * loader: 代理对象使用的类加载器。
        * interfaces: 指定代理对象的类型. 即代理代理对象中可以有哪些方法.
        * h: 当具体调用代理对象的方法时, 应该如何进行响应, 实际上就是调用 InvocationHandler 的 invoke 方法
        */
        proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
        return proxy;
        }
    }
```

### Spring AOP 实现

1. 加入 jar 包
    com.springsource.net.sf.cglib-2.2.0.jar
    com.springsource.org.aopalliance-1.0.0.jar
    com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
    spring-aspects-4.0.0.RELEASE.jar

2. 在 Spring 的配置文件中加入 aop 的命名空间。

3. 基于注解的方式来使用 AOP
    * 在配置文件中配置自动扫描的包:
        <context:component-scan base-package="com.atguigu.spring.aop"></context:component-scan>
    * 加入使 AspjectJ 注解起作用的配置:为匹配的类自动生成动态代理对象
        <aop:aspectj-autoproxy></aop:aspectj-autoproxy>

4. 编写切面类:
    * 一个一般的 Java 类
    * 在其中添加要额外实现的功能.

5. 配置切面
    * 切面必须是 IOC 中的 bean: 实际添加了 @Component 注解
    * 声明是一个切面: 添加 @Aspect
    * 声明通知: 即额外加入功能对应的方法.
        * 前置通知:
            @Before("execution(public int com.atguigu.spring.aop.ArithmeticCalculator.*(int, int))")
            @Before 表示在目标方法执行之前执行 @Before 标记的方法的方法体.
            @Before 里面的是切入点表达式:
        * 后置通知
            @After 表示后置通知: 在方法执行之后执行的代码<br>
            无法访问到目标的执行结果<br>
            若目标发生异常，依旧会执行
        * 返回通知
            @AfterReturning 表示返回通知: 在方法执行正常结束后执行的代码<br>
            可以访问到目标的执行结果
        * 异常通知
            @AfterThrowing 表示后置通知: 在方法执行之后执行的代码<br>
            若目标发生异常，执行该通知<br>
            可以在通知方法中指定 exception类型，当发生特定异常时才会执行
        * 环绕通知
            @Around 表示环绕通知，相当于整个动态代理<br>
            必须携带  ProceedingJoinPoint 类型的参数<br>
            必须有返回值，且为目标方法是返回值

6. 在通知中访问连接细节: 可以在通知方法中添加 JoinPoint 类型的参数, 从中可以访问到方法的签名和方法的参数.

7. 可以指定切面的优先级： @Order(1),值越小，优先级越高

8. 重用 execution表达式： 

    ```java
    @PointCut( "execution(public int com.atguigu.spring.aop.ArithmeticCalculator.*(int, int))" )
    public void declareJoinPointExpression(){}
    ==>  @Before(" declareJoinPointExpression () ")     //注意函数名以及 "()"
    ```
