package xml;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
public class ShowFile1 {
  public static void main(String[] args) {
    //String filename = "ShoeFile1.java";
    String urlString = "https://portal.sa.dendai.ac.jp/up/faces/login/Com00505A.jsp";
    /*if (args.length != 1) {
        System.out.println("使用法：java ShowFile1 ファイル");
        System.out.println("例：java ShowFile1 ShowFile1.java");
        System.exit(0);
    }*/
    //String filename = args[0];
    try {
        // URLオブジェクトを生成
        URL url = new URL(urlString);
        // URLオブジェクトから、接続にいくURLConnectionオブジェクトを取得
        URLConnection connection = url.openConnection();
        // 接続
        connection.connect();
        // サーバからやってくるデータをInputStreamとして取得
        InputStream inputStream = connection.getInputStream();
        // 次に inputStream を読み込む InputStreamReader のインスタンス inputStreamReader を生成
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        // さらに inputStreamReader をラップする BufferedReader のインスタンス reader を生成
        //FileInputStream fileInputStream = new FileInputStream(filename);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        //BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    //} catch (FileNotFoundException e) {
    //    System.out.println(filename + "が見つかりません。");
    } catch (IOException e) {
        System.out.println(e);
    }
  }
}
