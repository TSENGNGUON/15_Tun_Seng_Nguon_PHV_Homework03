public class HourlyEmployee extends StaffMember{
    private int hourWorked;
    private double rate;


    public HourlyEmployee(String name, String address, int hourWorked, double rate) {
        super(name, address);
        this.hourWorked = hourWorked;
        this.rate = rate;
    }

    public int getHourWorked() {
        return hourWorked;
    }

    public void setHourWorked(int hourWorked) {
        this.hourWorked = hourWorked;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    double pay() {
        return hourWorked * rate;
    }

    @Override
    public String toString() {
        return "ID : " + id + "\tName : " + name + "\tAddress : " + address + "\tHoursWorked : " + hourWorked + "\trate : " + rate + "\tPayment : " + pay();
    }
}
