package ex6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {
  public static void main(String[] args) {
    try{
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      BufferedReader reader = new BufferedReader(new FileReader("english.txt"));
      String line;
      int count;
      while((line = reader.readLine()) != null) {
        String[] text = line.split(" ");
        for(int i = 0; i < text.length; i++){
          count = 1;
          if(map.containsKey(text[i])){
            count += map.get(text[i]);
          }
          map.put(text[i], count);
        }
      }
      for(Map.Entry<String,Integer> entry : map.entrySet()){
        System.out.println(entry.getKey() + " => " + entry.getValue());
      }
      reader.close();
    }catch (FileNotFoundException e){
      System.out.println(e);
    }catch (IOException e){
      System.out.println(e);
    }
  }
}
