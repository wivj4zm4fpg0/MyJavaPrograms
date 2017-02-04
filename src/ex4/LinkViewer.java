package ex4;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class LinkViewer {
    private String word;
    private String urlWord;

    public static void main(String[] args) {
        LinkViewer viewer = new LinkViewer("link", "http://mainichi.jp/rss/etc/flash.rss");
        try {
            // InputStreamの用意
            URL url = new URL(viewer.getUrlWord());
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            // DOMツリーの構築
            Document document = viewer.buildDocument(inputStream, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LinkViewer(String word, String urlWord) {
        this.word = word;
        this.urlWord = urlWord;
    }

    public String getWord() {
        return word;
    }

    public String getUrlWord() {
        return urlWord;
    }

    public Document buildDocument(InputStream inputStream, String encoding) {
        Document document = null;
        try {
            // DOM実装(implementation)の用意 (Load and Save用)
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS implementation = (DOMImplementationLS) registry.getDOMImplementation("XML 1.0");
            // 読み込み対象の用意
            LSInput input = implementation.createLSInput();
            input.setByteStream(inputStream);
            input.setEncoding(encoding);
            // 構文解析器(parser)の用意
            LSParser parser = implementation.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
            // DOMの構築
            document = parser.parse(input);
            NodeList list = document.getElementsByTagName(getWord());
            for (int i = 0; i < list.getLength(); i++) {
                Node current = list.item(i);
                System.out.println(current.getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }
}