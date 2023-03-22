import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Crawler> bots=new ArrayList<>();
        bots.add(new Crawler("https://www.imdb.com/",1));
        bots.add(new Crawler("https://www.npr.org",2));
        bots.add(new Crawler("https://www.nytimes.com",3));
        for(Crawler c: bots){
            try {
                c.getThread().join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
