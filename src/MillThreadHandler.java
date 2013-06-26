
public class MillThreadHandler implements Runnable {
    private int numberOfDays;
    public Mill mill=new Mill();

    public MillThreadHandler(int numberOfDays){
        this.numberOfDays=numberOfDays;
    }

    @Override
    public void run(){
        //int dayCount;
        long daysInMs;

        daysInMs=(this.numberOfDays*(AmConstants.daysToMillisecs))+System.currentTimeMillis();

        while(System.currentTimeMillis()!=daysInMs){
            mill.generate();
        }

        /*
        //for normal execution, will need this to test out for database first as it will take less time.
        for(dayCount=0; dayCount<numberOfDays; dayCount++){
        mill.generate();
        }
        */
    }

}
