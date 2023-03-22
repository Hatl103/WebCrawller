import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class Crawler implements Runnable{

    private void crawl(int level,String url,ArrayList<String> visited){
        if(level<=5){
            Document doc=request(url,visited);

            if(doc!=null){
                for(Element link : doc.select("a[href]")){
                    String next_link=link.absUrl("href");
                    if(!visited.contains(next_link)){
                        crawl(level++,next_link,visited);
                    }
                }
            }
        }
    }
    private Document request(String url, ArrayList<String> list){
        try {
            Connection con= Jsoup.connect(url);
            Document doc=con.get();

            if(con.response().statusCode()==200){
                System.out.println("\n**Bot ID: "+ID+" Received Webpage at "+url);
                System.out.println(doc.title());
                list.add(url);

                return doc;
            }
            return null;
        }catch (IOException e){
            return null;
        }
    }
    private static final int Max_Depth=3;
    private Thread thread;
    private String first_Link;
    private ArrayList<String> visitedLinks=new ArrayList<>();
    private int ID;

    public Crawler(String first_Link, int ID) {
        System.out.println("WebCrawler created");
        this.first_Link = first_Link;
        this.ID = ID;

        thread=new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
       crawl(1,first_Link,visitedLinks);
    }
    public Thread getThread(){
        return thread;
    }
}
