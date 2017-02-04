package ex6;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

  public static void main(String[] args){
    Set<String> set = new HashSet<String>();

    set.add("Alice");
    set.add("Bob");
    set.add("Chris");
    set.add("Diana");
    set.add("Elmo");
    set.add("Fred");
    set.add("Diana");
    set.add("Bob");

    for(String name : set){
      System.out.println(name);
    }

    if(set.contains("Alice")){
      System.out.println("setにAliceは含まれています。");
    }else{
      System.out.println("setにAliceは含まれません。");
    }

  }
}
