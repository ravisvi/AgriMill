
public class Pulse {
    private int consumption;
    private String name;
    public Pulse(String pulseName){
        consumption=0;
        name= pulseName;
    }
    public void addConsumptionBy(int qty){
        this.consumption+=qty;
    }
    public int getConsumptionQty(){
        return consumption;
    }
}
