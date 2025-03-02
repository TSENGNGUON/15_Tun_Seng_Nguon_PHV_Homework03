public class Volunteer extends StaffMember{
    private double salary;

    public Volunteer(String name, String address,double salary) {
            super(name, address);
            this.salary = salary;
    }

    @Override
    double pay() {
        return salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID : " + id + "\tName : " + name + "\tAddress : " + address + "\tSalary : " + salary;
    }

}
