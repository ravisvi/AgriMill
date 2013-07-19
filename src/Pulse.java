public class Pulse {
    private int consumption;
    public Pulse(String pulseName){
        consumption=0;
    }
    public void addConsumptionBy(int qty){
        this.consumption+=qty;
    }
    public int getConsumptionQty(){
        return consumption;
    }
}
