import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class MillThreadHandler implements Runnable {
    private Area area;
    public Mill mill=new Mill();
    private int millNumber;
    private Thread thread;
    //private MongoClient mongoClient ;
    private DB db ;
    public MillThreadHandler(Area area, int numberOfDays,int millNumber){
        this.millNumber=millNumber;
        this.area = area;
    }

    public void collectThread(Thread thread){
        this.thread=thread;
    }

    @Override
    public void run(){
        while(true){
            //mongoClient=new MongoClient();
             System.out.println(area.areaId - 1);
            db=dbWriter.mongoClient[area.areaId - 1].getDB("mill");
            mill.generate(area, millNumber,thread,db, dbWriter.mongoClient[area.areaId - 1]);

        }
    }
}

class dbWriter {
    public static MongoClient [] mongoClient;
    static void foo()
    {
        try{
            mongoClient = new MongoClient[ClientTest.numberOfAreas];
            System.out.println(ClientTest.numberOfAreas);
            for (int i = 0; i < ClientTest.numberOfAreas; i++) {
                mongoClient[i] = new MongoClient();
            }

        }
        catch (UnknownHostException e){
            e.printStackTrace();
        }
    }
}


