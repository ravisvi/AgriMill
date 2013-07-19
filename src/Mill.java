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

	public Mill(){
		for(int pulseCount = 0; pulseCount < AmConstants.numberOfPulses; pulseCount++){
			pulses[pulseCount] = new Pulse(this.name[pulseCount]);
		}
	}

	public void generate(Area area, int millNumber, Thread thread)
	{
		//put db insertion here.
		int numberOfWorkingHours=AmConstants.millWorkingHours;
		int pulseCount;
		int tempConsumption[]=new int[AmConstants.numberOfPulses];

		//Adding minimum consumption first.
		for(int randomQty=0;randomQty<5;randomQty++){
			int getQuantity=AmUtils.random.nextInt(10)+1;
			tempConsumption[randomQty]=getQuantity;
			numberOfWorkingHours-=(int)((getQuantity/AmConstants.pulseProductionTime[randomQty]));
		}

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
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient();
			DB db = mongoClient.getDB("mill");
			DBCollection collection = db.getCollection("area_"+area.areaId+"_mill_"+millNumber);
			String insertion="{'rice':"+tempConsumption[0]+", 'wheat':"+tempConsumption[1]+", 'rawa':"+tempConsumption[2]+", 'millet':"+tempConsumption[3]+", 'ragi':"+tempConsumption[4]+"}";
			DBObject dbObject = (DBObject)JSON.parse(insertion);			 
			collection.insert(dbObject);
		} 
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
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