
public class MillThreadHandler implements Runnable {
    private int numberOfDays;
    public Mill mill=new Mill();

    public MillThreadHandler(int numberOfDays){
        this.numberOfDays=numberOfDays;
    }

    @Override
    public void run(){
       int dayCount;
       // long daysInMs;
    	long final_time;
       System.out.println(System.currentTimeMillis());
       // daysInMs=(this.numberOfDays*(AmConstants.daysToMillisecs))+System.currentTimeMillis();
       final_time=this.numberOfDays*1000+System.currentTimeMillis();

        while(System.currentTimeMillis()<=final_time){
            mill.generate();
        }
        System.out.println(System.currentTimeMillis());
        
        //for normal execution, will need this to test out for database first as it will take less time.
        /*for(dayCount=0; dayCount<numberOfDays; dayCount++){
        mill.generate();
        }*/
        
    }

}
