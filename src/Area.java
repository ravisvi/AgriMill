import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author   : Ravisvi <ravitejasvi@gmail.com>
 * Date     : 12/7/13
 */
public class Area {
    int areaId;
    long population;
    int electricityCutHours;
    int numberOfMills;

    int numberOfDays;
    ArrayList<MillThreadHandler> millThreadList=new ArrayList<MillThreadHandler>();
    ArrayList<Thread> millThread=new ArrayList<Thread>();
    Connect connector =new Connect();


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

        connector.connect();



        for(int millCount=0; millCount<numberOfMills; millCount++)
        {
            millThreadList.add(new MillThreadHandler(areaId, numberOfDays,millCount+1,connector));
            Thread tempThread= new Thread(millThreadList.get(millCount));
            millThread.add(tempThread);
            millThreadList.get(millCount).collectThread(tempThread);
            tempThread.start();


        }
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



}
