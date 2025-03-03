import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class EmployeeDisplay {
    private static final int PAGE_SIZE = 3;

    //Insert Employee Method
    public static void insertEmployee(@NotNull List<StaffMember> employees) {
        CellStyle centerAligned = new CellStyle(CellStyle.HorizontalAlign.center);
        while (true){
            Table table = new Table(4, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
            System.out.println("=====* Insert Employee *========");
            System.out.println("Choose Type : ");
            table.addCell("1. Volunteer", centerAligned);
            table.addCell("2. Salaries Employee", centerAligned);
            table.addCell("3. Hourly Employee", centerAligned);
            table.addCell("4. Back", centerAligned);
            System.out.println(table.render());

            Scanner sc = new Scanner(System.in);
            System.out.print("=> Enter Type Number : ");
            String typeNum = sc.nextLine();
            int idEmp = StaffMember.getIdCounter();
            String type;
            switch (typeNum){
                case "1":{
                    type = "Volunteer";
                    System.out.println("ID : " + StaffMember.getIdCounter());
                    System.out.print("=> Enter Name : ");
                    String name = sc.nextLine();
                    System.out.print("=> Enter Address : ");
                    String address = sc.nextLine();
                    System.out.print("=> Enter Salary  : ");
                    double salary = sc.nextDouble();
                    Volunteer v = new Volunteer(name, address, salary);
                    employees.add(v);
                    System.out.println("* You added " + name + " of type " + type + " Successfully!*");
                    System.out.println();
                    return;

                }
                case "2":{
                    type = " Salaries Employee";
                    System.out.println("ID : " + StaffMember.getIdCounter());
                    System.out.print("=> Enter Name : ");
                    String name = sc.nextLine();
                    System.out.print("=> Enter Address : ");
                    String address = sc.nextLine();
                    System.out.print("=> Enter Salary  : ");
                    double salary = sc.nextDouble();
                    System.out.print("=> Enter Bonus: ");
                    double bonus = sc.nextDouble();
                    SalariedEmployee s = new SalariedEmployee(name, address, salary,bonus);
                    employees.add(s);
                    System.out.println("* You added " + name + " of type " + type + " Successfully!*");
                    System.out.println();
                    return;
                }case "3":{
                    type = " Hourly Employee";
                    System.out.println("ID : " + StaffMember.getIdCounter());
                    System.out.print("=> Enter Name : ");
                    String name = sc.nextLine();
                    System.out.print("=> Enter Address : ");
                    String address = sc.nextLine();
                    System.out.print("=> Hour Worked : ");
                    int hourWorked = sc.nextInt();
                    System.out.print("=> Enter Rate : ");
                    double rate = sc.nextDouble();
                    HourlyEmployee h = new HourlyEmployee(name, address,hourWorked ,rate);
                    employees.add(h);
                    System.out.println("* You added " + name + " of type " + type + " Successfully!*");
                    System.out.println();
                    return;
                }
            }
        }



    }
    //Display Employee Method
    public static void displayEmployee(List<StaffMember> employees) {
        Scanner scanner = new Scanner(System.in);
        int totalPages = (int) Math.ceil((double) employees.size() / PAGE_SIZE);
        int currentPage = 1;

        while (true) {
            printPage(employees, currentPage, totalPages);
            System.out.println("Options: 1.First Page 2.Next Page 3.Previous Page 4.Last Page 5.Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: currentPage = 1; break;
                case 2: if (currentPage < totalPages) currentPage++; break;
                case 3: if (currentPage > 1) currentPage--; break;
                case 4: currentPage = totalPages; break;
                case 5: return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printPage(List<StaffMember> employees, int page, int totalPages) {
        int start = (page - 1) * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, employees.size());

        CellStyle centerAligned = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(9, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        table.setColumnWidth(0,20,20);
        table.setColumnWidth(1,20,20);
        table.setColumnWidth(2,20,20);
        table.setColumnWidth(3,20,20);
        table.setColumnWidth(4,20,20);
        table.setColumnWidth(5,20,20);
        table.setColumnWidth(6,20,20);
        table.setColumnWidth(7,20,20);
        table.setColumnWidth(8,20,20);

        table.addCell("Type", centerAligned);
        table.addCell("ID", centerAligned);
        table.addCell("Name", centerAligned);
        table.addCell("Address", centerAligned);
        table.addCell("Salary", centerAligned);
        table.addCell("Bonus", centerAligned);
        table.addCell("Hours Worked", centerAligned);
        table.addCell("Rate", centerAligned);
        table.addCell("Pay", centerAligned);

        for (int i = start; i < end; i++) {
            StaffMember employee = employees.get(i);
            String type = (employee instanceof Volunteer) ? "Volunteer" :
                    (employee instanceof SalariedEmployee) ? "Salaried Employee" :
                            (employee instanceof HourlyEmployee) ? "Hourly Employee" : "Unknown";

            table.addCell(type, centerAligned);
            table.addCell(String.valueOf(employee.getId()), centerAligned);
            table.addCell(employee.getName(), centerAligned);
            table.addCell(employee.getAddress(), centerAligned);

            if (employee instanceof Volunteer) {
                Volunteer v = (Volunteer) employee;
                table.addCell(formatCurrency(v.getSalary()), centerAligned);
                table.addCell("---", centerAligned);
                table.addCell("---", centerAligned);
                table.addCell("---", centerAligned);
                table.addCell(formatCurrency(v.pay()), centerAligned);
            } else if (employee instanceof SalariedEmployee) {
                SalariedEmployee s = (SalariedEmployee) employee;
                table.addCell(formatCurrency(s.getSalary()), centerAligned);
                table.addCell(formatCurrency(s.getBonus()), centerAligned);
                table.addCell("---", centerAligned);
                table.addCell("---", centerAligned);
                table.addCell(formatCurrency(s.pay()), centerAligned);
            } else if (employee instanceof HourlyEmployee) {
                HourlyEmployee h = (HourlyEmployee) employee;
                table.addCell("---", centerAligned);
                table.addCell("---", centerAligned);
                table.addCell(String.valueOf(h.getHourWorked()), centerAligned);
                table.addCell(formatCurrency(h.getRate()), centerAligned);
                table.addCell(formatCurrency(h.pay()), centerAligned);
            }
        }

        System.out.println(table.render());
        System.out.println("Page: " + page + "/" + totalPages);
    }

    //Update Employee Method
    public static void updateEmployee(@NotNull List<StaffMember> employees) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=====* Update Employee *=====");
        System.out.print("=> Enter or Search ID to Update Employee: ");
        String idInput = sc.nextLine();
        int id;

        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a numeric ID.");
            return;
        }

        Optional<StaffMember> optionalEmployee = employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();

        if (optionalEmployee.isPresent()) {
            StaffMember employee = optionalEmployee.get();
            displaySingleEmployee(employee);

            System.out.println("Choose a field to update:");
            System.out.print("1.Name    ");
            System.out.print("2. Address    ");

            if (employee instanceof Volunteer || employee instanceof SalariedEmployee) {
                System.out.println("3.Salary    ");
            }
            if (employee instanceof SalariedEmployee) {
                System.out.print("4.Bonus   ");
            }
            if (employee instanceof HourlyEmployee) {
                System.out.print("3.Hours Worked    ");
                System.out.print("4.Rate   ");
            }
            System.out.println("0.Cancel");
            System.out.print("Select option number: ");
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    System.out.print("=> Enter new Name: ");
                    String newName = sc.nextLine();
                    employee.setName(newName);
                    System.out.println("* Name has been updated successfully! *");
                    break;
                case "2":
                    System.out.print("=> Enter new Address: ");
                    String newAddress = sc.nextLine();
                    employee.setAddress(newAddress);
                    System.out.println("* Address has been updated successfully! *");
                    break;
                case "3":
                    if (employee instanceof Volunteer) {
                        System.out.print("=> Enter new Salary: ");
                        double newSalary = sc.nextDouble();
                        sc.nextLine();
                        ((Volunteer) employee).setSalary(newSalary);
                        System.out.println("* Salary has been updated successfully! *");
                    } else if (employee instanceof HourlyEmployee) {
                        System.out.print("=> Enter new Hours Worked: ");
                        int newHourWorked = sc.nextInt();
                        sc.nextLine();
                        ((HourlyEmployee) employee).setHourWorked(newHourWorked);
                        System.out.println("* Hours Worked has been updated successfully! *");
                    }
                    break;
                case "4":
                    if (employee instanceof SalariedEmployee) {
                        System.out.print("=> Enter new Bonus: ");
                        double newBonus = sc.nextDouble();
                        sc.nextLine();
                        ((SalariedEmployee) employee).setBonus(newBonus);
                        System.out.println("* Bonus has been updated successfully! *");
                    } else if (employee instanceof HourlyEmployee) {
                        System.out.print("=> Enter new Rate: ");
                        double newRate = sc.nextDouble();
                        sc.nextLine();
                        ((HourlyEmployee) employee).setRate(newRate);
                        System.out.println("* Rate has been updated successfully! *");
                    }
                    break;
                case "0":
                    System.out.println("Update canceled.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    return;
            }

            System.out.println("Employee updated successfully!");
            displaySingleEmployee(employee);
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    // Display Single Employee
    public static void removeEmployee(@NotNull List<StaffMember> employees) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=====* Remove Employee *=====");
        System.out.print("=> Enter ID to Remove: ");
        String idInput = sc.nextLine();
        int id;

        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a numeric ID.");
            return;
        }

        Optional<StaffMember> optionalStaffMember = employees.stream().filter(employee -> employee.getId() == id)
                .findFirst();

        if (optionalStaffMember.isPresent()) {
            StaffMember employee = optionalStaffMember.get();
            employees.remove(employee);
            System.out.println("* ID: "+employee.getId()+" has removed successfully! *");
        }



    }


    private static void displaySingleEmployee(StaffMember employee) {
        CellStyle centerAligned = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(9, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        table.setColumnWidth(0, 20, 20);
        table.setColumnWidth(1, 20, 20);
        table.setColumnWidth(2, 20, 20);
        table.setColumnWidth(3, 20, 20);
        table.setColumnWidth(4, 20, 20);
        table.setColumnWidth(5, 20, 20);
        table.setColumnWidth(6, 20, 20);
        table.setColumnWidth(7, 20, 20);
        table.setColumnWidth(8, 20, 20);
        table.addCell("Type", centerAligned);
        table.addCell("ID", centerAligned);
        table.addCell("Name", centerAligned);
        table.addCell("Address", centerAligned);
        table.addCell("Salary", centerAligned);
        table.addCell("Bonus", centerAligned);
        table.addCell("Hours Worked", centerAligned);
        table.addCell("Rate", centerAligned);
        table.addCell("Pay", centerAligned);

        // Determine the type of employee
        String type = Optional.of(employee)
                .map(emp -> {
                    if (emp instanceof Volunteer) return "Volunteer";
                    if (emp instanceof SalariedEmployee) return "Salaried Employee";
                    if (emp instanceof HourlyEmployee) return "Hourly Employee";
                    return "Unknown";
                })
                .orElse("Unknown");

        // Add common details
        table.addCell(type, centerAligned);
        table.addCell(String.valueOf(employee.getId()), centerAligned);
        table.addCell(employee.getName(), centerAligned);
        table.addCell(employee.getAddress(), centerAligned);

        // Add specific details based on employee type
        if (employee instanceof Volunteer) {
            Volunteer volunteer = (Volunteer) employee;
            table.addCell(formatCurrency(volunteer.getSalary()), centerAligned);
            table.addCell("---", centerAligned);
            table.addCell("---", centerAligned);
            table.addCell("---", centerAligned);
            table.addCell(formatCurrency(volunteer.pay()), centerAligned);
        } else if (employee instanceof SalariedEmployee) {
            SalariedEmployee salariedEmployee = (SalariedEmployee) employee;
            table.addCell(formatCurrency(salariedEmployee.getSalary()), centerAligned);
            table.addCell(formatCurrency(salariedEmployee.getBonus()), centerAligned);
            table.addCell("---", centerAligned);
            table.addCell("---", centerAligned);
            table.addCell(formatCurrency(salariedEmployee.pay()), centerAligned);
        } else if (employee instanceof HourlyEmployee) {
            HourlyEmployee hourlyEmployee = (HourlyEmployee) employee;
            table.addCell("---", centerAligned);
            table.addCell("---", centerAligned);
            table.addCell(String.valueOf(hourlyEmployee.getHourWorked()), centerAligned);
            table.addCell(formatCurrency(hourlyEmployee.getRate()), centerAligned);
            table.addCell(formatCurrency(hourlyEmployee.pay()), centerAligned);
        }

        // Print the table
        System.out.println(table.render());
    }
    // Remove Employee Method


    @Contract(pure = true)
    private static @NotNull String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }
}
