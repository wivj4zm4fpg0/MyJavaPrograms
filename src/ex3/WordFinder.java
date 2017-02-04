package ex3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class WordFinder {
  public void findContext(String urlString,String word){
    try {
      URL url = new URL(urlString);
      URLConnection connection = url.openConnection();
      connection.connect();
      InputStream inputStream = connection.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
      BufferedReader reader = new BufferedReader(inputStreamReader);
      String line;
      int flag = 0;
      while ((line = reader.readLine()) != null) {
        Pattern p = Pattern.compile(word);
        Matcher m = p.matcher(line);
        if(m.find()){
          System.out.println(word + "が見つかりました");
          flag = 1;
          break;
        }
      }
      if(flag == 0){
        System.out.println(word + "は見つかりませんでした");
      }
      reader.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  public static void main(String[] args) {
    WordFinder context = new WordFinder();
    context.findContext("http://www.mlab.im.dendai.ac.jp/wiki/index.php","title");
  }
}
