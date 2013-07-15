import java.sql.SQLException;
import java.sql.Statement;


public class MillThreadHandler implements Runnable {
    private int numberOfDays;
    private Area area;
    public Mill mill=new Mill();
    private int millNumber;
    private Connect connector;
    private Thread thread;
    public MillThreadHandler(Area area, int numberOfDays,int millNumber,Connect connector){
        this.numberOfDays=numberOfDays;
        this.millNumber=millNumber;
        this.connector=connector;
        this.area = area;
    }
   
	public void collectThread(Thread thread){
    	this.thread=thread;
    }

    @Override
    public void run(){
    	Statement stmt;
		try {
			stmt = connector.conn.createStatement();
			String tablename="CREATE TABLE area_"+area.areaId+"_mill_"+millNumber +" (Rice integer,Wheat int , Rawa int, Millet int, Corn int)";
			stmt.executeUpdate(tablename);
			stmt.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       int dayCount;
        for(dayCount=0; ; ){
        	  mill.generate(area, millNumber,connector,thread);
        }
        
    }

}
