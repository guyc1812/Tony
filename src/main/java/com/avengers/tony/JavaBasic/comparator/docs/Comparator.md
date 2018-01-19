# Comparator

* Java-7

    ```
    Comparator<Avenger> ComparatorDemo = new Comparator<Avenger>(){
        public int compare(UTRSuite a1, UTRSuite a2) {
            return a1.getName().compareTo(a2.getName());
        }
    };
    ```
    
* Java-8 with lambda

    ```
    Comparator<Avenger> comparatorByName 
        = Comparator.comparing(Avenger::getName);
    ```
    
* Comparator in list

    ```
    public List<Avenger> AvengersByName(List<Avenger> avengers) {
        Comparator<Avenger> comparatorByName = Comparator.comparing(Avenger::getName);
        avengers.sort(comparatorByName);
        return avengers;
    }

    public List<Avenger> AvengersByNameReversed(List<Avenger> avengers) {
        Comparator<Avenger> comparatorByName = Comparator.comparing(Avenger::getName).reversed();
        avengers.sort(comparatorByName);
        return avengers;
    }

    public List<Avenger> AvengersByAge(List<Avenger> avengers) {
        Comparator<Avenger> comparatorByAge = Comparator.comparing(Avenger::getAge);
        avengers.sort(comparatorByAge);
        return avengers;
    }

    public List<Avenger> AvengersByAgeReversed(List<Avenger> avengers) {
        Comparator<Avenger> comparatorByAge = Comparator.comparing(Avenger::getAge).reversed();
        avengers.sort(comparatorByAge);
        return avengers;
    }
    ```
    

* Comparator in TreeSet

    ```
    TreeSet<Avenger> treeSet = new TreeSet<>(Comparator.comparing(Avenger::getName));
    ```


* Comparator in TreeMap

    ```
    TreeMap<Avenger,String> treeMap = new TreeMap<>(Comparator.comparing(Avenger::getName));
    ```
    

[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/comparator/code)
