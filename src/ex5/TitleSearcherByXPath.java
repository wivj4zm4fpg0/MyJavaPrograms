package ex5;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TitleSearcherByXPath {
    private String word;
    private String urlWord;

    public static void main(String[] args) {
        TitleSearcherByXPath viewer = new TitleSearcherByXPath("あ", "http://www.nicovideo.jp/ranking/fav/daily/all?rss=2.0");
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

    public TitleSearcherByXPath(String word, String urlWord) {
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
            XPathFactory factory = XPathFactory.newInstance();
            XPath xPath = factory.newXPath();
            NodeList itemNodeList = (NodeList) xPath.evaluate("/RDF/item/title",
                    document, XPathConstants.NODESET);
            if (itemNodeList.getLength() == 0)
                itemNodeList = (NodeList) xPath.evaluate("/rss/channel/item/title",
                        document, XPathConstants.NODESET);
            for (int i = 0; i < itemNodeList.getLength(); i++) {
                Node node = itemNodeList.item(i);
                String linkString = node.getFirstChild().getNodeValue();
                Pattern p = Pattern.compile(getWord());
                Matcher m = p.matcher(linkString);
                if (m.find()) {
                    System.out.println(linkString + i);
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return document;
    }
}