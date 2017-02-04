import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class XML_15FI057 {
    private ArrayList<String> word_group = new ArrayList<>();
    private ArrayList<String> word_group_or = new ArrayList<>();
    private boolean isExisting;
    private int search_result;
    private ArrayList<String> rss = new ArrayList<>();
    private ArrayList<String> rssName = new ArrayList<>();
    private ArrayList<String> more = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();

    public static void main(String[] args) {
        XML_15FI057 titleSearcher = new XML_15FI057();
        titleSearcher.Search();
    }

    private void Search() {
        fileLoad(); //ファイルを読み込む
        description(); //説明を表示する
        String val;
        ArrayList<String> field = new ArrayList<>();
        ArrayList<String> str = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long start, stop;
        while (true) {
            try {
                System.out.print("\n>");
                val = reader.readLine();
                Pattern p = Pattern.compile("[\\s|||　]+");
                String[] string = p.split(val); //入力された文字列を分割
                for (String s : string) {
                    if (!(s.equals(" ") || s.equals("|") || s.equals("　"))) {
                        field.add(s);
                    }
                }
                for (int i = 0; i < field.size() - 1; i++) { //2つの単語の間の文字を抜き出す
                    int sta = val.indexOf(field.get(i));
                    int end = val.indexOf(field.get(i + 1), field.get(i).length());
                    str.add(val.substring(sta, end));
                }
                word_group.add(field.get(0));
                for (int i = 1; i < field.size(); i++) {
                    if (str.get(i - 1).contains("|")) {
                        word_group_or.add(field.get(i));
                    } else {
                        word_group.add(field.get(i));
                    }
                }
                boolean wordSize = word_group.size() == 1 && word_group_or.size() == 0;
                start = System.currentTimeMillis();
                if (word_group.get(0).equals("-e")) {
                    System.exit(0);
                } else if (word_group.get(0).equals("") && wordSize) {
                    description();
                    word_group.clear();
                    field.clear();
                    continue;
                } else if (word_group.get(0).equals("-a") && wordSize) {
                    System.out.println("検索中");
                    for (String url : rss) {
                        titleSearch(url, word_group, word_group_or);
                    }
                } else if (word_group.get(0).equals("-l") && wordSize) {
                    for (int i = 0; i < rss.size(); i++) {
                        System.out.println("\n" + rssName.get(i));
                        System.out.println(rss.get(i));
                    }
                    word_group.clear();
                    field.clear();
                    continue;
                } else {
                    System.out.println("検索中");
                    for (String url : rss) {
                        titleSearch(url, word_group, word_group_or);
                    }
                }
                stop = System.currentTimeMillis();
                if (!isExisting) {
                    System.out.println("\n" + val + "に関する記事は見つかりませんでした。");
                }
                ArticlePrint();
                System.out.println("\n検索結果：" + search_result + "件");
                System.out.println("\n検索時間：" + (stop - start) + "[ms]");
                word_group.clear();
                word_group_or.clear();
                field.clear();
                str.clear();
                more.clear();
                title.clear();
                search_result = 0;
                isExisting = false;
            } catch (IndexOutOfBoundsException | IOException e) {
                description();
            } catch (NullPointerException e) {
                break;
            }
        }
    }

    private void fileLoad() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("config.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] field = line.split("\\s*,\\s*");
                rssName.add(field[0]);
                rss.add(field[1]);
            }
        } catch (Exception e) {
            System.out.println("ファイルの読み込みに失敗しました。");
            System.exit(0);
        }
    }

    private void description() {
        System.out.println("検索したい単語を入力してください。(Ctrl-dまたは-eで終了)");
        System.out.println("例 : apple");
        System.out.println("アンド検索とマイナス検索が使えます。");
        System.out.println("アンド検索するときはスペースで区切ってください。");
        System.out.println("マイナス検索するときは「|」で区切ってください。");
        System.out.println("単独で「-a」と検索すると全て表示します。");
        System.out.println("単独で「-l」と検索するとファイルから読み込んだサイトを表示します。");
    }

    private void titleSearch(String urlWord, ArrayList<String> word, ArrayList<String> word_or) {
        try {
            URL url = new URL(urlWord);
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Document document = buildDocument(inputStream);
            showTree(document.getDocumentElement(), word, word_or); // root要素を与える
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Document buildDocument(InputStream inputStream) {
        Document document = null;
        try {
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS implementation = (DOMImplementationLS) registry.getDOMImplementation("XML 1.0");
            LSInput input = implementation.createLSInput();
            input.setByteStream(inputStream);
            LSParser parser = implementation.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
            parser.getDomConfig().setParameter("namespaces", false);
            document = parser.parse(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    private void showTree(Node node, ArrayList<String> word, ArrayList<String> word_or) {
        boolean isArticle = false;
        for (Node current = node.getFirstChild(); current != null; current = current.getNextSibling()) {
            if (current.getNodeType() == Node.ELEMENT_NODE) { // ノードは要素?
                String nodeName = current.getNodeName();
                if (nodeName.equals("title")) {
                    Node firstChild = current.getFirstChild();
                    String content = firstChild.getNodeValue(); // 子ノードの値がtitle要素の内容
                    if (word.get(0).equals("-a") && word_group_or.size() == 1 && word_group_or.size() == 0) {
                        more.add(content);
                        title.add(content);
                        isExisting = true;
                        isArticle = true;
                        search_result++;
                    } else {
                        int count = 0;
                        int or = 0;
                        boolean isMatch;
                        for (String aWord_or : word_or) {
                            isMatch = isMatch(aWord_or, content);
                            if (isMatch) {
                                or = 1;
                            }
                        }
                        for (String aWord : word) {
                            boolean flag = aWord.startsWith("-");
                            if (flag) {
                                String str = aWord.substring(1);
                                isMatch = isMatch(str, content);
                                if (!isMatch) {
                                    count++;
                                } else {
                                    or = 2;
                                }
                            }
                            isMatch = isMatch(aWord, content);
                            if (isMatch) {
                                count++;
                            }
                        }
                        if ((count == word.size() || or == 1) && or != 2) {
                            more.add(content);
                            title.add(content);
                            isExisting = true;
                            isArticle = true;
                            search_result++;
                        }
                    }
                } else {
                    showTree(current, word, word_or);
                }
                if (isArticle) {
                    if (nodeName.equals("link")) {
                        Print(current);
                    }
                    if (nodeName.equals("dc:date")) {
                        Print(current);
                    }
                    if (nodeName.equals("pubDate")) {
                        Print(current);
                    }
                }
            } // HTMLタグなどを含む
        }
    }

    private boolean isMatch(String word, String content) {
        Pattern p = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);
        return m.find();
    }

    private void Print(Node current) {
        Node firstChild = current.getFirstChild();
        String content = firstChild.getNodeValue();
        more.add(content);
    }

    private void ArticlePrint() {
        int titleNumber = 0;
        boolean flag = false;
        for (String aMore : more) {
            if (aMore.equals(title.get(titleNumber))) {
                if (flag) {
                    if (titleNumber % 5 == 0) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            System.out.print("\nEnterキーで進みます");
                            String string = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                flag = true;
                System.out.println();
                if (titleNumber != title.size() - 1) {
                    titleNumber++;
                }
            }
            System.out.println(aMore);
        }
    }
}

