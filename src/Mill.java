import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;


public class Mill{
	//Contains 5 pulses for rice, wheat corn millet and ragi
	public Pulse[] pulses = new Pulse[AmConstants.numberOfPulses];
	String[] name = {"Rice", "Wheat", "Rawa", "Millet", "Corn"};

	private int timeTaken;
    private int dayCount;
    //private MongoClient mongoClient;
    DBObject dbObject;
	public Mill(){
        dayCount=1;
		for(int pulseCount = 0; pulseCount < AmConstants.numberOfPulses; pulseCount++){
			pulses[pulseCount] = new Pulse(this.name[pulseCount]);
		}
	}

	public void generate(Area area, int millNumber, Thread thread, DB db, MongoClient mongoClient)
	{

        //System.out.println(Area.insertCount);

		//put db insertion here.
		int numberOfWorkingHours=AmConstants.millWorkingHours;
		int pulseCount;
        String insertion;
		int tempConsumption[]=new int[AmConstants.numberOfPulses];
        DBCollection collection = db.getCollection("area_"+area.areaId+"_mill_"+millNumber);
         insertion = "{'Day ':"+dayCount+"}";

        dbObject = (DBObject)JSON.parse(insertion);
        collection.insert(dbObject);
		int randomElectricDelay=AmUtils.random.nextInt(2);
		//Add a random Electric Delay because of power cut
		numberOfWorkingHours=reduceTime(randomElectricDelay, numberOfWorkingHours, thread);
		while(numberOfWorkingHours>0){
			pulseCount=AmUtils.random.nextInt(AmConstants.numberOfPulses);
			//Get a random pulse.



			int randomQuantity = AmUtils.generateByProbablity(pulseCount,rangeDistribution(area.population) );

			this.pulses[pulseCount].addConsumptionBy(randomQuantity);

			//Add a random consumption quantity to that pulse.

			tempConsumption[pulseCount]+=randomQuantity;
			if(pulseCount==0){
				timeTaken = (int)((this.pulses[pulseCount].getConsumptionQty())/AmConstants.pulseProductionTime[0]);
				numberOfWorkingHours=reduceTime(timeTaken, numberOfWorkingHours, thread); //rice = 100/hour
                insertion="{'rice':"+tempConsumption[pulseCount]+"}";
                dbObject = (DBObject)JSON.parse(insertion);
                collection.insert(dbObject);
                Area.insertCount++;
			}

			else if(pulseCount==1){
				timeTaken = (int)((this.pulses[pulseCount].getConsumptionQty())/AmConstants.pulseProductionTime[1]);
				numberOfWorkingHours=reduceTime(timeTaken, numberOfWorkingHours, thread);//wheat = 100/hour
                insertion="{'wheat':"+tempConsumption[pulseCount]+"}";
                dbObject = (DBObject)JSON.parse(insertion);
                collection.insert(dbObject);
                Area.insertCount++;
			}

			else if(pulseCount==2){
				timeTaken = (int)((this.pulses[pulseCount].getConsumptionQty())/AmConstants.pulseProductionTime[2]);
				numberOfWorkingHours=reduceTime(timeTaken, numberOfWorkingHours, thread);//rawa = 200/hour
                insertion="{'rawa':"+tempConsumption[pulseCount]+"}";
                dbObject = (DBObject)JSON.parse(insertion);
                collection.insert(dbObject);
                Area.insertCount++;
			}

			else if(pulseCount==3){
				timeTaken = (int)((this.pulses[pulseCount].getConsumptionQty())/AmConstants.pulseProductionTime[3]);
				numberOfWorkingHours=reduceTime(timeTaken, numberOfWorkingHours, thread);  //millet = 70/hour
                insertion="{'millet':"+tempConsumption[pulseCount]+"}";
                dbObject = (DBObject)JSON.parse(insertion);
                collection.insert(dbObject);
                Area.insertCount++;
			}

			else{
				timeTaken = (int)((this.pulses[pulseCount].getConsumptionQty())/AmConstants.pulseProductionTime[4]);
				numberOfWorkingHours=reduceTime(timeTaken, numberOfWorkingHours, thread);   //ragi = 200/hour
                insertion="{'ragi':"+tempConsumption[pulseCount]+"}";
                dbObject = (DBObject)JSON.parse(insertion);
                collection.insert(dbObject);
                Area.insertCount++;
			}
           //  System.out.println(db.command("ServerStatus"));
          //  System.out.println();
            if(db.eval("db.serverStatus().connections.available").toString().equals(800))
            {
                System.out.println("hello world");
                for(Thread th : area.millThread)
                {
                    try {
                        th.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                mongoClient.close();
                try {

                    mongoClient = new MongoClient();
                    Area.insertCount = 0;

                } catch (UnknownHostException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }


            }

		}
        dayCount++;

    }

	public int[] rangeDistribution(long population)
	{
		int [] distribution;
		if(population<=AmConstants.areaRange1)
			distribution  = new int[]{7,9,10};
		else if(population>AmConstants.areaRange1 && population<=AmConstants.areaRange2)
			distribution  = new int[]{6,9,10};
		else if(population>AmConstants.areaRange2 && population<=AmConstants.areaRange3)
			distribution  = new int[]{5,9,10};
		else if(population>AmConstants.areaRange3 && population<=AmConstants.areaRange4)
			distribution  = new int[]{5,8,10};
		else if(population>AmConstants.areaRange4 && population<=AmConstants.areaRange5)
			distribution  = new int[]{7,9,10};
		else
			distribution  = new int[]{7,9,10};
		return distribution;
	}
	@SuppressWarnings("static-access")
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