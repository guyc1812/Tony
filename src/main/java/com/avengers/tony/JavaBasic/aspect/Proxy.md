# JAVA Dynamic Proxy

Proxy is a design pattern. <br>
A proxy objects is used to add or modify some functionality of an already existing class. <br>
Usually the proxy objects have the same methods as the original one and in Java proxy classes usually extend the original class. <br>
The proxy has a handle to the original object and can call the method on that.


### UseFor

Proxy classes can implement many things in a convenient way without modifying the original code of the class. 

* logging when a method starts and stops
* perform extra checks on arguments
* mocking the behavior of the original class
* implement lazy access to costly resources


### Demo

* Proxy Class

    ```
    class CalculatorLogByProxy {
    
        // target to be proxied
        private Calculator target;
    
        public CalculatorLogByProxy(Calculator target) {
            this.target = target;
        }
    
        // return the target
        public Calculator CalcLoggingProxy() {
            ...
            return proxy;
        }
    }
    ```

* implement the InvocationHandler Interface

    ```
    public Calculator CalcLoggingProxy() {
    
        /*
         * loader:      classLoader of the target。
         * interfaces:  type of the target, in order to get it's methods
         * handler:     invoke method of InvocationHandler to handle the specific methods of target
         */
        ClassLoader loader = target.getClass().getClassLoader();
        Class[] interfaces = new Class[]{Calculator.class};
        InvocationHandler handler = new InvocationHandler() {
    
            /**
             * proxy:   proxy object
             * method:  method to be proxied
             * args:    args passed to the method
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    
                Object result = null;
    
                try {
                    // Before advice
                    System.out.println("[-- before advice -----------] The method " + method.getName() + " begins with " + Arrays.asList(args));
    
                    // invoke the specific method
                    result = method.invoke(target, args);
    
                    // After returning advice, with return value
                    System.out.println("[-- After returning advice --] The method " + method.getName() + " ends with " + result);
    
                } catch (Exception e) {
    
                    // After throwing advice
                    System.out.println("[-- After throwing advice ---] The method " + method.getName() + " throws exception");
    
                } finally {
    
                    // After (finally) advice, without return value or exception
                    System.out.println("[-- after advice ------------] The method " + method.getName() + " finish.");
    
                }
    
                return result;
            }
        };
    
        Calculator proxy = (Calculator) Proxy.newProxyInstance(loader, interfaces, handler);
        return proxy;
    }
    ```

* Test

    ```
    @Test
    public void ArithmeticCalculatorLoggingProxy() {
    
        Calculator calculator = new CalculatorImpl();
    
        calculator = new CalculatorLogByProxy(calculator).CalcLoggingProxy();
    
        int result1 = calculator.add(11, 12);
            //[-- before advice -----------] The method add begins with [11, 12]
            //[-- After returning advice --] The method add ends with 23
            //[-- after advice ------------] The method add finish.
        
        int result2 = calculator.div(21, 0);
            //[-- before advice -----------] The method div begins with [21, 0]
            //[-- After throwing advice ---] The method div throws exception
            //[-- after advice ------------] The method div finish.
    
    }
    ```

[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/aspect/code)


### Reference:

[Java Deep - Java Deep, Peter Verhas](https://javax0.wordpress.com/2016/01/20/java-dynamic-proxy/)


