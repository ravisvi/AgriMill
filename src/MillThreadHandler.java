import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class MillThreadHandler implements Runnable {
	private Area area;
	public Mill mill=new Mill();
	private int millNumber;
	private Thread thread;
    private MongoClient mongoClient ;
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
            try {
                mongoClient=new MongoClient();
                db=mongoClient.getDB("mill");
            } catch (UnknownHostException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            mill.generate(area, millNumber,thread,db);
		}
	}
}
