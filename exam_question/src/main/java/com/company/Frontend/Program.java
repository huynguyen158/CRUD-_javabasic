package com.company.Frontend;

import java.sql.SQLException;

import com.company.Backend.Entity.User;
import com.company.Backend.Manage.DepartmentManager;
import com.company.Backend.Manage.ProjectManager;
import com.company.Backend.Manage.UserManager;
import com.company.Backend.Utils.ScannerUtils;

public class Program {
    public static void main(String[] args) throws SQLException {
        loadMenu();
    }

    private static void loadMenu() throws SQLException {
        UserManager userManager = new UserManager();
        ProjectManager projectManager = new ProjectManager();

        System.out.println("======================================================================");
        System.out.println("=================Welcome to our program================");

        User user = userManager.login();
        System.out.println("\n");

        if (user.getRole() == User.Role.ADMIN) {
            loadAdminMenu(user, userManager, projectManager );
        } else {
            loadEmployeeMenu(user, userManager);
        }
    }

    private static void loadEmployeeMenu(User user, UserManager userManager) {
        while (true) {
            System.out.println("=================Enter the feature which you wanna use================");
            System.out.println("=== 1. Display information ===");
            System.out.println("=== 2. Exit program ===");
            System.out.println("======================================================================");

            int choose = ScannerUtils.inputNumber(1, 2);
            switch (choose) {
                case 1:
                    userManager.displayInformation(user);
                    break;
                case 2:
                    System.out.println("Good bye!");
                    return;
            }
        }
    }

    private static void loadAdminMenu(User user, UserManager userManager, ProjectManager projectManager) throws SQLException {
        DepartmentManager departmentManager = new DepartmentManager();

        while (true) {
            System.out.println("=================Enter the feature which you wanna use================");
            System.out.println("=== 1. Display information ===");
            System.out.println("=== 2. Get All Departments ===");
            System.out.println("=== 3. Get All Users ===");
            System.out.println("=== 4. Get All Project By id ===");
            System.out.println("=== 5. Get All User By Department ===");
            System.out.println("=== 6. Get All User By Role ===");
            System.out.println("=== 7. Create User ===");
            System.out.println("=== 8. Search User By Keyword ===");
            System.out.println("=== 9. Exit program ===");
            System.out.println("======================================================================");

            int choose = ScannerUtils.inputNumber(1, 10);
            switch (choose) {
                case 1:
                    userManager.displayInformation(user);
                    break;
                case 2:
                    departmentManager.getAllDepartments();
                    break;
                case 3:
                    userManager.getAllUsers();
                    break;
                case 4:
                    projectManager.printProjectDetails(user.getDepartment_id());
                    break;
                case 5:
                    userManager.getAllUserByDepartmentId();
                    break;
                case 6:
                    userManager.getAllUserByRole();
                    break;
                case 7:
                    userManager.insertUser(user);
                    break;
                case 8:
                    userManager.searchUserByKeyword();
                    break;
                case 9:
                    System.out.println("Good bye!");
                    return;
            }

            System.out.println("\n");
        }
    }
}
