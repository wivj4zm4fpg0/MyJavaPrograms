package xml;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

public class TitleSearcher {
    public static void main(String[] args) {
        TitleSearcher search = new TitleSearcher("http://feeds.japan.cnet.com/rss/cnet/all.rdf", "株式");
    }

    TitleSearcher(String searchURL, String searchWord) {
        String word = searchWord;
        try {
            URL url = new URL(searchURL);
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Document document = buildDocument(inputStream, "utf-8");
            showTree(document.getDocumentElement(), word); // root要素を与える
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Document buildDocument(InputStream inputStream, String encoding) {
        Document document = null;
        try {
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS implementation = (DOMImplementationLS) registry.getDOMImplementation("XML 1.0");
            LSInput input = implementation.createLSInput();
            input.setByteStream(inputStream);
            input.setEncoding(encoding);
            LSParser parser = implementation.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
            parser.getDomConfig().setParameter("namespaces", false);
            document = parser.parse(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    public void showTree(Node node, String word) {
        for (Node current = node.getFirstChild(); current != null; current = current
                .getNextSibling()) {
            if (current.getNodeType() == Node.ELEMENT_NODE) { // ノードは要素?
                String nodeName = current.getNodeName();
                if (nodeName.equals("channel")) {
                } else if (nodeName.equals("title")) {
                    Node firstChild = current.getFirstChild();
                    if (firstChild != null) {
                        String content = firstChild.getNodeValue(); // 子ノードの値がtitle要素の内容
                        System.out.println(content.replaceAll(word, " * "
                                + word + " * ")); // 検索語の前後に * を付加
                    }
                } else {
                    showTree(current, word);
                }
            } // HTMLタグなどを含む
        }
    }
}
