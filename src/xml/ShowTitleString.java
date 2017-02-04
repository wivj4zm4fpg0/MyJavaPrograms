package xml;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
public class ShowTitleString {
  public static void main(String[] args) {
    String urlString = "http://www.mlab.im.dendai.ac.jp/wiki/index.php";
    try {
      URL url = new URL(urlString);
      URLConnection connection = url.openConnection();
      connection.connect();
      InputStream inputStream = connection.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
      BufferedReader reader = new BufferedReader(inputStreamReader);
      String line;
      int a = 0;
      int b = 0;
      String c;
      while ((line = reader.readLine()) != null) {
        a = line.indexOf("<title>");
        b = line.indexOf("</title>");
        if(a > -1){
          c = line.substring(a + 7,b);
          System.out.println(c);
        }
      }
      while ((line = reader.readLine()) != null) {
      }
      reader.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
