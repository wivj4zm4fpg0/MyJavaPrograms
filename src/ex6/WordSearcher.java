package ex6;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordSearcher {
  public static void main(String[] args){
    Map<String,String> map = new HashMap<String,String>();

    map.put("tomato","トマト");
    map.put("strawberry","苺");
    map.put("orange","蜜柑");
    map.put("onion","玉葱");
    map.put("apple","林檎");
    map.put("banana","バナナ");

    System.out.println("英単語を入力してください");
    Scanner scan = new Scanner(System.in);
    while(true){
      String val = scan.next();
      int a = 0;
      for(Map.Entry<String,String> entry : map.entrySet()){
        if(val.equals(entry.getKey())){
          System.out.println(val + "の意味は" + entry.getValue() + "です。");
          a = 1;
        }
      }
      if(a == 0){
        System.out.println(val + "という単語はない");
      }
    }
  }
}
