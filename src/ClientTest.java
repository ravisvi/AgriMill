import java.util.ArrayList;
import java.util.Scanner;

public class ClientTest {

	static int numberOfMills;
    static int numberOfAreas;

	ArrayList<Area> areaList=new ArrayList<Area>();
	ArrayList<Thread> millThread=new ArrayList<Thread>();

	public ClientTest(){


		Scanner in=new Scanner(System.in);
		System.out.print("Enter the number of areas\t");
		numberOfAreas = in.nextInt();

        dbWriter.foo();
		for(int iterator = 1; iterator <= numberOfAreas; iterator++){
			areaList.add(new Area(iterator));
		}

	}
}
