# Java Reflection

Reflection enables Java code to discover information about the fields, methods and constructors of loaded classes.<br>
It's an API to use reflected fields, methods, and constructors to operate on their underlying counterparts at runtime.

Reflection can be used to get information about
1. **Class**
2. **Constructors** 
3. **Fields**
4. **Methods** 

The Reflection API is mainly used in: 
1. IDE (Integrated Development Environment) e.g. Eclipse, MyEclipse, NetBeans etc.
2. Debugger
3. Testing Tools
4. Frameworks like Spring and Structs2 with configurations.
    ```
    <action name="login"
            class="org.ScZyhSoft.test.action.SimpleLoginAction"
            method="execute">
        <result>/shop/shop-index.jsp</result>
        <result name="error">login.jsp</result>
    </action>
    ```
    If there is request send to login.action, <br>
    the StrutsPrepareAndExecuteFilter will parse the struts.xml file, <br>
    and create the SimpleLoginAction instance dynamically according to the action class name, and then invoke the execute method to reponse.

Advantages:
1. Extensibility Features.
2. Debugging and testing tools.

Drawbacks:
1. Challenging the design of application.
2. Can induce security problem.
2. Bad performance.
    Because it has to inspect the metadata in the bytecode instead of just using pre compiled addresses and constants.

# Example

```java
public class RootObj {

    private String rootField;

    public RootObj()  {
        this.rootField = "This is root field";
    }

    private RootObj(String text){   //note this is a private constructor
        this.rootField = text;
    }

    public void rootMethod1()  {
        System.out.println("This is root method1(public)");
    }

    private void rootMethod2() {    //note this is a private method
        System.out.println("This is root method2(private)");
    }
}
```

```java
public class ChildObj extends RootObj{

    private String childfield1;
    private String childfield2;

    public ChildObj()  {             
        this.childfield1 = "This is child field1";
        this.childfield2 = "This is child field2";
    }

    private ChildObj(String text)  {    //note this is a private constructor
        System.out.println("This is child constructor with para");
    }

    public void childMethod1()  {
        System.out.println("This is child method1(public)");
    }

    public void childMethod2(int n)  {
        System.out.println("This is child method2(public) with int para: " + n);
    }

    private void childMethod3() {       //note this is a private method
        System.out.println("This is child method3(private)");
    }
}
```

### Access Class
```java
// 1. Class.forName() method, used in db driver
//    If java.lang.ClassNotFoundException => require the full class name
Class way1 = Class.forName("com.avengers.core.demo.JavaBasic.reflection.ChildObj"); 

// 2. Class.class
Class way2 = ChildObj.class;

// 2. obj.getClass()
ChildObj obj = new ChildObj();
Class way3 = obj.getClass();
```

### Create Instance
```java
// 1. Class.newInstance() => Object
ChildObj copy1 = (ChildObj)way3.newInstance();

// 2. Constructor.newInstance() => Object
Constructor constructor = way3.getConstructor();
ChildObj copy2 = (ChildObj)constructor.newInstance();
```

### Access Class Member
**(Class class).getDeclaredXxx()**<br>
Returns the counterpart reference(s) only for declared by the class or interface, excludes inherited counterparts.<br>
Regardless of the access level, includes public, protected, default (package) access, and private counterparts, <br>

**(Class class).getXxx()**<br>
Returns the counterpart reference(s) only for a public modifier.
Includes inherited counterparts.

### Access Constructor
Get a exact constructor
```java
// getConstructor()  ==>  public ChildObj()  {}
Constructor constructor = way3.getConstructor();
// getDeclaredConstructor()  ==>  private ChildObj(String text) {}
Constructor declaredConstructor = way3.getDeclaredConstructor(String.class);

// note if you need to use this declaredConstructor(private) to create a new instance
declaredConstructor.setAccessible(true);    // <= mark
declaredConstructor.newInstance("A");

// or
Constructor[] constructors = way3.getConstructors();
for (Constructor one:constructors) System.out.println("Constructor Name is "+one.getName());

Constructor[] declaredConstructors = way3.getDeclaredConstructors();
for (Constructor one:declaredConstructors) System.out.println("declaredConstructor Name is "+one.getName());

// output:
// Constructor Name is com.avengers.core.demo.JavaBasic.reflection.ChildObj  => the public constructor
// declaredConstructor Name is com.avengers.core.demo.JavaBasic.reflection.ChildObj => the public constructor
// declaredConstructor Name is com.avengers.core.demo.JavaBasic.reflection.ChildObj => the private constructor
```

### Access Method
```java
// getMethods()
Method[] methods = way3.getMethods();
for (Method method:methods) System.out.println("Method Name is "+method.getName());

// output:
// Method Name is rootMethod1  => inherited public method
// Method Name is childMethod1 => public method1
// Method Name is childMethod2 => public method2
// Method Name is wait
// Method Name is wait
// Method Name is wait
// Method Name is equals
// Method Name is toString
// Method Name is hashCode
// Method Name is getClass
// Method Name is notify
// Method Name is notifyAll

// getDeclaredMethods()
Method[] declaredMethods = way3.getDeclaredMethods();
for (Method method:declaredMethods) System.out.println("DeclaredMethods Name is "+method.getName());

// output:
// DeclaredMethods Name is childMethod1 => public childMethod1
// DeclaredMethods Name is childMethod2 => public childMethod2
// DeclaredMethods Name is childMethod3 => private childMethod3

// invoke a exactly method by knowing it's name and para types
Method method2 = way3.getDeclaredMethod("childMethod2", int.class);
Method method3 = way3.getDeclaredMethod("childMethod3");
method3.setAccessible(true);

// invokes the method at runtime => invoke(obj,para)
method2.invoke(obj, 19);
method3.invoke(obj);

// output
// This is child method2(public) with int para: 19
// This is child method3(private)
```


### Access Fields
```java
StringBuffer sb = new StringBuffer();
sb.append(Modifier.toString(way3.getModifiers()) + " class " + way3.getSimpleName() +"{\n");
Field[] fs = way3.getDeclaredFields();
for(Field field:fs){
    sb.append("\t");
    sb.append(Modifier.toString(field.getModifiers())+" "); // Modifier
    sb.append(field.getType().getSimpleName() + " ");       // Type
    sb.append(field.getName()+";\n");                       // Name
}
sb.append("}");
System.out.println(sb);

// output:
// public class ChildObj {
// 	    private String childfield1;
// 	    private String childfield2;
// }

// if way3.getFields();  => public class ChildObj { }
// cause no piblic fields.
```