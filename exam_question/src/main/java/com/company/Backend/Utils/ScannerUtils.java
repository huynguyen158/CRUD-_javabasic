package com.company.Backend.Utils;

import java.sql.SQLException;
import java.util.Scanner;

import com.company.Backend.Repository.DepartmentRepository;

public class ScannerUtils {

	public static Scanner scanner = new Scanner(System.in);

	public static int inputNumber() {
		while (true) {
			try {
				String s = scanner.nextLine();
				int a = Integer.parseInt(s);
				return a;
			} catch (Exception e) {
				System.out.print("Wrong inputing! Please input a number as int! Input again:");
			}
		}
	}

	public static int inputNumber(int min, int max) {
		while (true) {
			int a = inputNumber();
			if (a < min || a > max) {
				System.out.printf("Please input a number from %d to %d! Input again: ", min, max);
				continue;
			}
			return a;
		}
	}

	public static int inputPositiveNumber() {
		while (true) {
			int a = inputNumber();
			if (a < 0) {
				System.out.print("Please input positive number! Input again: ");
				continue;
			}
			return a;
		}
	}

	public static int inputId() {
		while (true) {
			int a = inputNumber();
			if (a < 1) {
				System.out.print("Please input a id which is greater than or equal 1! Input again: ");
				continue;
			}
			return a;
		}
	}
	
	public static int inputExistsDepartmentId(DepartmentRepository departmentRepository) throws SQLException {
		while (true) {
			int id = inputId();
			
			if (!departmentRepository.isDepartmentExistsById(id)) {
				System.out.print("The id hasn't already exists! Input again: ");
				continue;
			}
			
			return id;
		}
	}

	public static String inputString() {
		return scanner.nextLine();
	}

	public static String inputUsername() {
		while (true) {
			String s = scanner.nextLine();

			if (s.length() < 5) {
				System.out.print("Your username is too short! Input again: ");
				continue;
			}

			if (s.length() > 50) {
				System.out.print("Your username is too long! Input again: ");
				continue;
			}

			return s;
		}
	}

	public static String inputPassword() {
		return inputString();
	}

	public static String inputKeyword() {
		String s = scanner.nextLine();
		s = removeSpaces(s);
		return s;
	}

	private static String removeSpaces(String s) {
		// remove before & after spaces
		s = s.trim();
		// remove middle spaces
		while (s.contains("  ")) {
			s = s.replace("  ", " ");
		}
		return s;
	}

	public static String inputRole() {
		while (true) {
			String s = scanner.nextLine();

			if (!s.equalsIgnoreCase("Admin") && !s.equalsIgnoreCase("Employee")) {
				System.out.print("Wrong format! Input again: ");
				continue;
			}

			return s;
		}
	}

	public static String inputDepartmentName(DepartmentRepository departmentRepository) throws SQLException {

		while (true) {
			String s = scanner.nextLine();

			if (s.length() < 2) {
				System.out.print("The department name is too short! Input again: ");
				continue;
			}

			if (s.length() > 50) {
				System.out.print("The department name is too long! Input again: ");
				continue;
			}

			if (departmentRepository.isDepartmentExistsByName(s)) {
				System.out.print("The name already exists! Input again: ");
				continue;
			}

			return s;
		}
	}

	public static String inputEmail() {
		while (true) {
			String s = scanner.nextLine();

			if (s.length() < 5) {
				System.out.print("Your email is too short! Input again: ");
				continue;
			}

			if (s.length() > 20) {
				System.out.print("Your email is too long! Input again: ");
				continue;
			}

			if (!s.contains("@") || !s.contains(".com")) {
				System.out.println("Wrong format! Input again: ");
				continue;
			}

			return s;
		}
	}
	public static String inputFullName() {
		while (true) {
			String s = scanner.nextLine();

			if (s.length() < 10) {
				System.out.print("Your fullname is too short! Input again: ");
				continue;
			}

			if (s.length() > 50) {
				System.out.print("Your fullname is too long! Input again: ");
				continue;
			}

			return s;
		}
	}
	public static String inputPhone() {
		while (true) {
			String s = scanner.nextLine();

			if (s.length() < 9) {
				System.out.print("Your phone is too short! Input again: ");
				continue;
			}

			if (s.length() > 12) {
				System.out.print("Your phone is too long! Input again: ");
				continue;
			}

			return s;
		}
	}

	public static boolean inputYesOrNo() {
		while (true) {
			String s = scanner.nextLine();

			if (s.equalsIgnoreCase("Y")) {
				return true;
			}

			if (s.equalsIgnoreCase("N")) {
				return false;
			}

			System.out.println("Please input Y or N !Input again: ");
		}
	}
}
