import java.util.Random;
public class AmUtils {
	static Random random = new Random();
<<<<<<< HEAD
	static int generatePulseByProbablity(int pulseNumber) {

		int[] distribution = {5, 8, 10}; 
		/* probability distribution array for pulse generation
		 * 50% for 1-15 kg
		 * 30% for 16-40 kg
		 * 20% for 40+ kg
		 */
=======
	static int generateByProbablity(int pulseNumber, int[] distribution) {
>>>>>>> ConstraintsBranch
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

	static int generateElectricityCutByProbablity() {

		int[] distribution = {2, 8, 10}; 
		/* probability distribution array for electricity cut
		 * 20% for 0 hours (no electricity cut that day)
		 * 60% for 1-3 hours
		 * 20% for 4-10 hours
		 */
		int randomKey = 1 + random.nextInt(9);
		if (randomKey <= distribution[0]) {
			return 0;
		} 
		else if(randomKey > distribution[0] && randomKey <= distribution[1]){
			return (1 + random.nextInt(3));
		}
		else if (randomKey > distribution[1] && randomKey <= distribution[2]) {
			return (5 + random.nextInt(11));
		}
		else {
			return 0; //ideally should never come here
		}		
	}
}
