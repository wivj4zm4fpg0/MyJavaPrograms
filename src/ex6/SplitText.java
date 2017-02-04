package ex6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SplitText{
  public static void main(String[] args){
    try{
      BufferedReader reader = new BufferedReader(new FileReader("english.txt"));
      String line;
      while((line = reader.readLine()) != null) {
        String[] text = line.split(" ");
        for(int i = 0;i < text.length; i++) {
          System.out.println(text[i]);
        }
      }
      reader.close();
    }catch (FileNotFoundException e){
      System.out.println(e);
    }catch (IOException e){
      System.out.println(e);
    }
  }
}
