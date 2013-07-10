import java.sql.SQLException;
import java.sql.Statement;


public class Mill{
    //Contains 5 pulses for rice, wheat corn millet and ragi
    public Pulse[] pulses = new Pulse[AmConstants.numberOfPulses];
    String[] name = {"Rice", "Wheat", "Rawa", "Millet", "Corn"};
	private int timeTaken;

    public Mill(){
        for(int pulseCount = 0; pulseCount < AmConstants.numberOfPulses; pulseCount++){
        pulses[pulseCount] = new Pulse(this.name[pulseCount]);
        }
    }

    public void generate(int millNumber,Connect connector, Thread thread)
    {
        //put db insertion here.
        int numberOfWorkingHours=AmConstants.millWorkingHours;
        int pulseCount;
        int tempConsumption[]=new int[AmConstants.numberOfPulses];
        while(numberOfWorkingHours>0){
            pulseCount=AmUtils.random.nextInt(AmConstants.numberOfPulses);
            //Get a random pulse.
            
            //Adding minimum consumption first.
           
            for(int randomQty=0;randomQty<5;randomQty++){
            	int getQuantity=AmUtils.random.nextInt(10)+1;
            	tempConsumption[randomQty]=getQuantity;
            	numberOfWorkingHours-=(int)((getQuantity/AmConstants.pulseProductionTime[randomQty]));
            	
            }
            int randomQuantity = AmUtils.generateByProbablity(pulseCount);
            this.pulses[pulseCount].addConsumptionBy(randomQuantity);
            
            //Add a random consumption quantity to that pulse.

            tempConsumption[pulseCount]+=randomQuantity;
            if(pulseCount==0){
                timeTaken = (int)((this.pulses[pulseCount].getConsumptionQty())/AmConstants.pulseProductionTime[0]);
                numberOfWorkingHours=reduceTime(timeTaken, numberOfWorkingHours, thread); //rice = 100/hour
            }

            else if(pulseCount==1){
            	timeTaken = (int)((this.pulses[pulseCount].getConsumptionQty())/AmConstants.pulseProductionTime[1]);
                numberOfWorkingHours=reduceTime(timeTaken, numberOfWorkingHours, thread);//wheat = 100/hour
            }

            else if(pulseCount==2){
            	timeTaken = (int)((this.pulses[pulseCount].getConsumptionQty())/AmConstants.pulseProductionTime[2]);
                numberOfWorkingHours=reduceTime(timeTaken, numberOfWorkingHours, thread);//rawa = 200/hour
            }

            else if(pulseCount==3){
            	timeTaken = (int)((this.pulses[pulseCount].getConsumptionQty())/AmConstants.pulseProductionTime[3]);
                numberOfWorkingHours=reduceTime(timeTaken, numberOfWorkingHours, thread);  //millet = 70/hour
            }

            else{
            	timeTaken = (int)((this.pulses[pulseCount].getConsumptionQty())/AmConstants.pulseProductionTime[4]);
                numberOfWorkingHours=reduceTime(timeTaken, numberOfWorkingHours, thread);   //ragi = 200/hour
            }

        }
        try {
			Statement statement=connector.conn.createStatement();
			String insertion="INSERT INTO mill"+millNumber+" values("+tempConsumption[0]+","+tempConsumption[1]+","+tempConsumption[2]+","+tempConsumption[3]+","+tempConsumption[4]+");";
			statement.executeUpdate(insertion);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    public int reduceTime(int timeTaken,int numberOfWorkingHours, Thread thread){
    	numberOfWorkingHours-=timeTaken; 
        try {
			thread.sleep(timeTaken*AmConstants.hoursToMillisecs);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	return numberOfWorkingHours;
    }
}