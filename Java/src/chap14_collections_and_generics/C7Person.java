package chap14_collections_and_generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class C7Person implements Comparable<C7Person> {
    int age;
    String name;

    public C7Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(C7Person other) {
        return Integer.compare(this.age, other.age);
    }

    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    Comparator<C7Person> nameComparator = new Comparator<C7Person>() {
        @Override
        public int compare(C7Person p1, C7Person p2) {
            return p1.getName().compareTo(p2.getName());
        }
    };

    Comparator<C7Person> nameComparatorLambda = (p1, p2) -> p1.getName().compareTo(p2.getName());

    TreeSet<C7Person> personTreeSetByName = new TreeSet<>(nameComparatorLambda);
    TreeMap<C7Person, Integer> personTreeMapByName = new TreeMap<>(nameComparatorLambda);

    public static void main(String[] args) {
        List<C7Person> personList = new ArrayList<>();
        personList.add(new C7Person("Huub", 1));
        personList.add(new C7Person("Joep", 4));
        personList.add(new C7Person("Anne1", 3));
        personList.add(new C7Person("Anne23", 3));
        personList.add(new C7Person("Anne345", 3));
        personList.add(new C7Person("Znne1", 7));
        personList.add(new C7Person("Mnne1", 5));
        /* Collections.sort(personList);
        /*
        for (C7Person person : personList) {
            System.out.println(person.getName());
        }
        */
        /*
        Comparator<C7Person> nameComparator = (p1, p2) -> 
        p1.getName().compareTo(p2.getName());
        Collections.sort(personList, nameComparator);
        
        for (C7Person person : personList) {
        System.out.println(person.getName());
        }
        System.out.println();
        */

        /*
        Collections.sort(personList, nameComparator);
        
        for (C7Person person : personList) {
        System.out.println(person.getName());
        }
        */

        Comparator<C7Person> nameLengthComparator = (p1, p2) -> 
        Integer.compare(p1.getName().length(), p2.getName().length());
        Collections.sort(personList, nameLengthComparator);

        TreeSet<C7Person> personTreeSet = new TreeSet<>();
        personTreeSet.add(new C7Person("Huub", 1));
        personTreeSet.add(new C7Person("Joep", 4));
        personTreeSet.add(new C7Person("Anne", 3));
        /*
        for (C7Person person : personTreeSet) {
        System.out.println("Name: " + person.getName() + ", Age: " + person.getAge());
        }
        System.out.println();
        */

        Comparator<C7Person> nameComparatorLambda = (p1, p2) -> 
        p1.getName().compareTo(p2.getName());
        TreeSet<C7Person> personTreeSetByName = new TreeSet<>(nameComparatorLambda);
        personTreeSetByName.add(new C7Person("Huub", 1));
        personTreeSetByName.add(new C7Person("Joep", 4));
        personTreeSetByName.add(new C7Person("Anne", 3));
        personTreeSetByName.add(new C7Person("Gnne", 8));
        
        for (C7Person person : personTreeSetByName) {
        System.out.println("Name: " + person.getName() + ", Age: " + person.getAge());
        }
        System.out.println();
        
        
        Comparator<C7Person> nameComparatorTreeMap = (p1, p2) -> 
        p1.getName().compareTo(p2.getName());
        TreeMap<C7Person, Integer> personTreeMapByName = new TreeMap<>(nameComparatorTreeMap);
        personTreeMapByName.put(new C7Person("Huub", 1), 1);
        personTreeMapByName.put(new C7Person("Joep", 4), 4);
        personTreeMapByName.put(new C7Person("Anne", 3), 3);
        personTreeMapByName.put(new C7Person("Gnne", 8), 8);
        for (C7Person person : personTreeMapByName.keySet()) {
            System.out.println("Name: " + person.getName() + ", Age: " + person.getAge());
        }
    }
 
}