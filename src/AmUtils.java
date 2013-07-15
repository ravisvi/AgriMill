import java.util.Random;
public class AmUtils {
	static Random random = new Random();
	static int generateByProbablity(int pulseNumber, int[] distribution) {
		int randomKey = 1 + random.nextInt(9);
		if (randomKey <= distribution[0]) {
			return (1 + random.nextInt(15));
		} 
		else if(randomKey > distribution[0] && randomKey <= distribution[1]){
			return (16 + random.nextInt(25));
		}
		else if (randomKey > distribution[1] && randomKey <= distribution[2]) {
			return (41 + random.nextInt(AmConstants.pulseProductionTime[pulseNumber] + 1));
		}
		else {
			return 0; //ideally should never come here
		}			
	}
}
