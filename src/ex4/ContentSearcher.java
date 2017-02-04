package ex4;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ContentSearcher {
    private String word;
    private String urlWord;

    public static void main(String[] args) {
        ContentSearcher viewer = new ContentSearcher("title", "http://mainichi.jp/rss/etc/flash.rss");
        try {
            // InputStreamの用意
            URL url = new URL(viewer.getUrlWord());
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            // DOMツリーの構築
            Document document = viewer.buildDocument(inputStream, "utf-8");
            viewer.showTree(document.getDocumentElement());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ContentSearcher(String word, String urlWord) {
        this.word = word;
        this.urlWord = urlWord;
    }

    public String getWord() {
        return word;
    }

    public String getUrlWord() {
        return urlWord;
    }

    /**
     * DOM Tree の構築
     */
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 引数 node 以下の tree を表示
     */
    public void showTree(Node node) {
        for (Node current = node.getFirstChild();
             current != null;
             current = current.getNextSibling()) {
            if (current.getNodeType() == Node.ELEMENT_NODE) { // ノードは要素?
                //String nodeName = current.getNodeName();
                // 中括弧 { } を使って階層を表現
                //System.out.println(nodeName + " {");
                showChild(current); // さらに子ノードを見る (再帰)
                //System.out.println("}");
            } else if (current.getNodeType() == Node.TEXT_NODE // ノードはテキスト?
                    && current.getNodeValue().trim().length() != 0) {
                System.out.println(current.getNodeValue());
            } else if (current.getNodeType() == Node.CDATA_SECTION_NODE) { // ノードはCDATA?
                System.out.println(current.getNodeValue());
            } // HTMLタグなどを含む
            ; // 上記以外のノードでは何もしない
        }
    }

    public void showChild(Node node) {
        for (Node current = node.getFirstChild();
             current != null;
             current = current.getNextSibling()) {
            if (current.getNodeType() == Node.ELEMENT_NODE) { // ノードは要素?
                if (current.getNodeName().equals(getWord())) {
                    String nodeName = current.getNodeName();
                    // 中括弧 { } を使って階層を表現
                    System.out.println(nodeName + " {");
                    showChild(current); // さらに子ノードを見る (再帰)
                    System.out.println("}");
                }
            } else if (current.getNodeType() == Node.TEXT_NODE // ノードはテキスト?
                    && current.getNodeValue().trim().length() != 0) {
                System.out.println(current.getNodeValue());
            } else if (current.getNodeType() == Node.CDATA_SECTION_NODE) { // ノードはCDATA?
                System.out.println(current.getNodeValue());
            } // HTMLタグなどを含む
            ; // 上記以外のノードでは何もしない
        }
    }
}