import java.util.ArrayList;
import java.util.Scanner;

public class Area {
	int areaId;
	long population;
	int electricityCutHours;
	int numberOfMills;
	int numberOfDays;
	ArrayList<MillThreadHandler> millThreadList=new ArrayList<MillThreadHandler>();
	ArrayList<Thread> millThread=new ArrayList<Thread>();
	public Area(int areaId){
		this.areaId = areaId;
		Scanner in=new Scanner(System.in);
		System.out.print("Enter the population of area "+ areaId + "\t");
		this.population = in.nextInt();
		//TODO: Generate electriciy data;
		System.out.print("Enter the number of Mills which would run\t");
		numberOfMills=in.nextInt();
		//        System.out.print("Enter the number of days\t");
		//        numberOfDays=in.nextInt();
		for(int millCount=0; millCount<numberOfMills; millCount++)
		{
			millThreadList.add(new MillThreadHandler(this, numberOfDays,millCount+1));
			Thread tempThread= new Thread(millThreadList.get(millCount));
			millThread.add(tempThread);
			millThreadList.get(millCount).collectThread(tempThread);
			tempThread.start();
        }
	}
}
