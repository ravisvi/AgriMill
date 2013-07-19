

public class MillThreadHandler implements Runnable {
	private Area area;
	public Mill mill=new Mill();
	private int millNumber;
	private Thread thread;
	public MillThreadHandler(Area area, int numberOfDays,int millNumber){
		this.millNumber=millNumber;
		this.area = area;
	}

	public void collectThread(Thread thread){
		this.thread=thread;
	}

	@Override
	public void run(){
		while(true){
			mill.generate(area, millNumber,thread);
		}
	}
}
