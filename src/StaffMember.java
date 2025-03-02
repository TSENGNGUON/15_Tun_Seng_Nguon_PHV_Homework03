abstract public class StaffMember {
    private static int idCounter = 1;
    protected int id;
    protected String name;
    protected String address;

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        StaffMember.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StaffMember(String name, String address) {
        this.id = idCounter++;
        this.name = name;
        this.address = address;
    }

    abstract double pay();

    @Override
    public String toString() {
        return "ID : " + id + "\nName : " + name + "\nAddress : " + address;
    }
}
