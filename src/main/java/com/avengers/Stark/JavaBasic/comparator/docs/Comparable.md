# Comparable

1. Implement the Comparable Interface and override the compareTo method
    
    ```java
    public class AvengerNext extends Avenger implements Comparable<AvengerNext> {
        
        AvengerNext(String name, int age){
            super(name,age);    
        }
        
        public int compareTo(AvengerNext avenger) {
            return Integer.compare(super.getAge(), avenger.getAge());
        }
    }
    ```
    
2. Use Collections.sort() method
    
    ```java
    AvengerNext IronMan = new AvengerNext("Tony", 40);
    AvengerNext Hulk = new AvengerNext("Banner", 44);
    AvengerNext Captain = new AvengerNext("Steve", 100);
    AvengerNext BlackWidow = new AvengerNext("Natasha", 30);

    List<AvengerNext> avengers = Lists.newArrayList(IronMan, Hulk, Captain, BlackWidow);

    Collections.sort(avengers);
    ``` 


[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/comparator/code)

