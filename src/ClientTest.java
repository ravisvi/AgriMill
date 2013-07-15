import java.util.ArrayList;
import java.util.Scanner;
public class ClientTest {

    int numberOfMills;

    ArrayList<Area> areaList=new ArrayList<Area>();
    ArrayList<Thread> millThread=new ArrayList<Thread>();
    Connect connector =new Connect();
    
    public ClientTest(){
        

        Scanner in=new Scanner(System.in);
        System.out.print("Enter the number of areas\t");
        int numberOfAreas;
        numberOfAreas = in.nextInt();

        for(int iterator = 1; iterator <= numberOfAreas; iterator++){
            areaList.add(new Area(iterator));
        }

    }
}
