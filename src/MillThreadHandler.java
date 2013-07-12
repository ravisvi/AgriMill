import java.sql.SQLException;
import java.sql.Statement;


public class MillThreadHandler implements Runnable {
    private int numberOfDays;
    private int areaId;
    public Mill mill=new Mill();
    private int millNumber;
    private Connect connector;
    private Thread thread;
    public MillThreadHandler(int areaId, int numberOfDays,int millNumber,Connect connector){
        this.numberOfDays=numberOfDays;
        this.millNumber=millNumber;
        this.connector=connector;
        this.areaId = areaId;
    }
    public void collectThread(Thread thread){
    	this.thread=thread;
    }

    @Override
    public void run(){
    	Statement stmt;
		try {
			stmt = connector.conn.createStatement();
			String tablename="CREATE TABLE area_"+areaId+"_mill_"+millNumber +" (Rice integer,Wheat int , Rawa int, Millet int, Corn int)";
			stmt.executeUpdate(tablename);
			stmt.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       int dayCount;
       // long daysInMs;
       /*long final_time;
       System.out.println(System.currentTimeMillis());
       // daysInMs=(this.numberOfDays*(AmConstants.daysToMillisecs))+System.currentTimeMillis();
       final_time=this.numberOfDays*1000+System.currentTimeMillis();

        while(System.currentTimeMillis()<=final_time){
            mill.generate(millNumber,connector);
        }
        System.out.println(System.currentTimeMillis());
        */
        //for normal execution, will need this to test out for database first as it will take less time.
        for(dayCount=0; ; ){
        	  mill.generate(areaId, millNumber,connector,thread);
        }
        
    }

}
