import java.util.ArrayList;
import java.util.Scanner;
public class ClientTest {

    public static void main(String []args){

        int numberOfMills;
        int millCount;
        int pulseCount;
        int numberOfDays;

        Scanner in=new Scanner(System.in);
        System.out.print("Enter the number of Mills which would run\t");
        numberOfMills=in.nextInt();
        System.out.print("Enter the number of days\t");
        numberOfDays=in.nextInt();
        ArrayList<MillThreadHandler> millThreadList=new ArrayList<MillThreadHandler>();

        for(millCount=0; millCount<numberOfMills; millCount++){
            millThreadList.add(new MillThreadHandler(numberOfDays));
            new Thread(millThreadList.get(millCount)).start();
        }
        try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(millCount=0; millCount<numberOfMills; millCount++){
            pulseCount=0;
            System.out.println("\nMill\t"+(millCount+1));
            System.out.println("Total quantity produced: ");
            System.out.print("Rice    : \t");
            System.out.println(millThreadList.get(millCount).mill.pulses[pulseCount++].getConsumptionQty()+" kgs");
            System.out.print("Wheat   : \t");
            System.out.println(millThreadList.get(millCount).mill.pulses[pulseCount++].getConsumptionQty()+" kgs");
            System.out.print("Rawa    : \t");
            System.out.println(millThreadList.get(millCount).mill.pulses[pulseCount++].getConsumptionQty()+" kgs");
            System.out.print("Millet  : \t");
            System.out.println(millThreadList.get(millCount).mill.pulses[pulseCount++].getConsumptionQty()+" kgs");
            System.out.print("Corn    : \t");
            System.out.println(millThreadList.get(millCount).mill.pulses[pulseCount++].getConsumptionQty()+" kgs");

        }
    }
}
