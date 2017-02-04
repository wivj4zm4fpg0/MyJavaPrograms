package ex3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
public class ContextFinder {
  public void findContext(String urlString, String word){
    try {
      URL url = new URL(urlString);
      URLConnection connection = url.openConnection();
      connection.connect();
      InputStream inputStream = connection.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
      BufferedReader reader = new BufferedReader(inputStreamReader);
      String line;
      int start = 0;
      int end = 0;
      String result;
      String startword = "<" + word + ">";
      String endword = "</" + word + ">";
      while ((line = reader.readLine()) != null) {
        start = line.indexOf(startword);
        end = line.indexOf(endword);
        if(start > -1 && end > -1){
          result = line.substring(start + word.length() + 2,end);
          System.out.println(result);
        }
      }
      reader.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  public static void main(String[] args) {
    ContextFinder context = new ContextFinder();
    context.findContext("http://www.mlab.im.dendai.ac.jp/wiki/index.php","p");
  }
}
