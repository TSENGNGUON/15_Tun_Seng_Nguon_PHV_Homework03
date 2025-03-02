public class SalariedEmployee extends StaffMember{
    private double salary;
    private double bonus;

    public SalariedEmployee(String name, String address, double salary, double bunus) {
            super(name, address);
            this.salary = salary;
            this.bonus = bunus;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "ID : " + id + "\tName : " + name + "\tAddress : " + address + "\tSalary : " + salary + "\tBonus : " + bonus + "\tPayment : " + pay();
    }

    @Override
    double pay() {
        return salary + bonus ;
    }
}
