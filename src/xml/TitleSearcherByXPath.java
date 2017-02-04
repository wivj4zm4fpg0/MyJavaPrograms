package xml;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

/**
 * 検索語を含むタイトルの表示 (XPath版)
 */
public class TitleSearcherByXPath {
    public static void main(String[] args) {
        TitleSearcherByXPath searcher = new TitleSearcherByXPath();
        String word = "話題";
        try {
            // InputStreamの用意
            URL url = new URL("http://japan.zdnet.com/info/feed/");
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            // DOMツリーの構築
            Document document = searcher.buildDocument(inputStream, "utf-8");
            // リンクの表示
            //searcher.showTree(document.getDocumentElement(), word); // root要素を与える
            searcher.showTitleList(document, word); // 引数に root要素を与える
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            parser.getDomConfig().setParameter("namespaces", false);
            // DOMの構築
            document = parser.parse(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * word を含むタイトルを表示
     */
    public void showTitleList(Document document, String word) {
        try {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xPath = factory.newXPath();
            NodeList list = (NodeList) xPath.evaluate(
                    "/rss/channel/item/title[contains(. , '" + word + "')]", // 自身(.)の内容にwordを含むtitle要素
                    document, // document に対して XPath を適用
                    XPathConstants.NODESET); // 戻り値はノードの集合
            for (int i = 0; i < list.getLength(); i++) {
                System.out.println(list.item(i).getFirstChild().getNodeValue()); // 内容を表示
                // System.out.println(list.item(i).getTextContent()); // これでも可
            }
        } catch (DOMException e) {
            System.err.println("DOMエラー:" + e);
        } catch (XPathExpressionException e) { // 追加
            System.err.println("XPath 表現のエラー:" + e);
        }
    }
}
