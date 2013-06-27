import java.sql.SQLException;
import java.sql.Statement;


public class Mill{
    //Contains 5 pulses for rice, wheat corn millet and rawa
    public Pulse[] pulses = new Pulse[AmConstants.numberOfPulses];
    String[] name = {"Rice", "Wheat", "Rawa", "Millet", "Corn"};

    public Mill(){
        for(int pulseCount = 0; pulseCount < AmConstants.numberOfPulses; pulseCount++){
        pulses[pulseCount] = new Pulse(this.name[pulseCount]);
        }
    }

    public void generate(int millNumber,Connect connector)
    {
        //put db insertion here.
        int numberOfWorkingHours=AmConstants.millWorkingHours;
        int pulseCount;
        int tempConsumption[]=new int[AmConstants.numberOfPulses];
        while(numberOfWorkingHours>0){
            pulseCount=AmUtils.random.nextInt(AmConstants.numberOfPulses);
            //Get a random pulse.
            
            this.pulses[pulseCount].addConsumptionBy(AmUtils.random.nextInt(32));
            //Add a random consumption quantity to that pulse.

            tempConsumption[pulseCount]+=this.pulses[pulseCount].getConsumptionQty();
            if(pulseCount==0){
                numberOfWorkingHours-=(int)((this.pulses[pulseCount].getConsumptionQty())/6);  //rice= 6/hour
                
            }

            else if(pulseCount==1){
                numberOfWorkingHours-=(int)((this.pulses[pulseCount].getConsumptionQty())/10);  //wheat= 10/hour
            }

            else if(pulseCount==2){
                numberOfWorkingHours-=(int)((this.pulses[pulseCount].getConsumptionQty())/30);  //rawa= 30/hour
            }

            else if(pulseCount==3){
                numberOfWorkingHours-=(int)((this.pulses[pulseCount].getConsumptionQty())/4);  //millet= 4/hour
            }

            else{
                numberOfWorkingHours-=(int)((this.pulses[pulseCount].getConsumptionQty())/5);  //corn= 5/hour
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
}
