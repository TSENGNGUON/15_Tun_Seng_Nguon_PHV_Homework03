import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        List<StaffMember> staffMembers = new ArrayList<StaffMember>();
        staffMembers.add(new Volunteer("Meng Sea", "BMC",0));
        staffMembers.add(new SalariedEmployee(" Sea", "BMC",300, 20));
        staffMembers.add(new HourlyEmployee(" Sea", "BMC",8, 10));
        while (true){
            Table menu = new Table(1, BorderStyle.UNICODE_BOX, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
            menu.setColumnWidth(0, 80, 80);
            CellStyle headerStyle = new CellStyle(CellStyle.HorizontalAlign.center);
            menu.addCell("Staff Management System", headerStyle);
            CellStyle defaultStyle = new CellStyle();
            menu.addCell("1. Insert Employee", defaultStyle);
            menu.addCell("2. Update Employee", defaultStyle);
            menu.addCell("3. Display Employee", defaultStyle);
            menu.addCell("4. Remove Employee", defaultStyle);
            menu.addCell("5. Exit", defaultStyle);

            System.out.println(menu.render());
            System.out.println("------------------------------------");

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your choice(1-5): ");
            String option = sc.nextLine();
            switch (option){
                case "1":
                    EmployeeDisplay.insertEmployee(staffMembers);
                    break;
                case "2":
                    EmployeeDisplay.updateEmployee(staffMembers);
                    break;
                case "3":
                    EmployeeDisplay.displayEmployee(staffMembers);
                    break;
                case "4":
                    EmployeeDisplay.removeEmployee(staffMembers);
                    break;
                case "5":
                    System.exit(0);
            }

        }

    }
}