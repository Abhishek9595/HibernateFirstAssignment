package com.te.hibernate.assignmentone;

import java.util.*;

import javax.persistence.*;

public class StudentMain {
	private static EntityManagerFactory factory = null;
	private static EntityManager manager = null;
	private static EntityTransaction transaction = null;
	private static Scanner scanner = new Scanner(System.in);
	private static boolean exit;
	private static int stdRoll;
	private static String stdName;
	private static long stdContact;
	private static int stdStandard;

	// method to insert data
	public static void hibernateInsert(int roll, String name, long contact, int standard) {
		try {
			factory = Persistence.createEntityManagerFactory("emp");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Student student = new Student();
			student.setRoll(roll);
			student.setName(name);
			student.setContact(contact);
			student.setStandard(standard);
			manager.persist(student);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null)
				transaction.rollback();
		} finally {
			try {
				if (factory != null)
					factory.close();
				if (manager != null)
					manager.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// method to fetch all data
	public static List<Student> hibernateFetchAll() {
		List<Student> list = null;
		try {
			factory = Persistence.createEntityManagerFactory("emp");
			manager = factory.createEntityManager();
			String queryFetchAll = "from Student";
			Query query = manager.createQuery(queryFetchAll);
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (factory != null)
					factory.close();
				if (manager != null)
					manager.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;

	}

	// method to fetch selected data
	public static Student hibernateFetchSingle(int roll_num) {
		Student student = null;
		try {
			factory = Persistence.createEntityManagerFactory("emp");
			manager = factory.createEntityManager();
			String queryFetchSingle = "from Student where roll= :r";
			Query query = manager.createQuery(queryFetchSingle);
			query.setParameter("r", roll_num);
			student = (Student) query.getSingleResult();
			int result = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (factory != null)
					factory.close();
				if (manager != null)
					manager.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return student;
	}

	// method to update data
	public static void hibernateUpdate(int roll_num, String name, long contact, int standard) {
		Student student = null;
		try {
			factory = Persistence.createEntityManagerFactory("emp");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String queryUpdate = "update Student set name= :name, "
					+ "contact= :contact, standard= :standard where roll= :roll";
			Query query = manager.createQuery(queryUpdate);
			query.setParameter("name", name);
			query.setParameter("contact", contact);
			query.setParameter("standard", standard);
			query.setParameter("roll", roll_num);
			int result = query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null)
				transaction.rollback();
		} finally {
			try {
				if (factory != null)
					factory.close();
				if (manager != null)
					manager.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// method to delete
	public static void hibernateDelete(int roll_num) {
		try {
			factory = Persistence.createEntityManagerFactory("emp");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String queryDelete = "delete from Student where roll= :r";
			Query query = manager.createQuery(queryDelete);
			query.setParameter("r", roll_num);
			int result = query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null)
				transaction.rollback();
		} finally {
			try {
				if (factory != null)
					factory.close();
				if (manager != null)
					manager.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static String display() {
		System.out.println("Press 1 to insert the data\n" + "Press 2 to display all data\n"
				+ "Press 3 to display by Roll\n" + "Press 4 to update data by roll\n" + "Press 5 to delete by roll\n"
				+ "Press 6 to Quit the program");
		String string = scanner.next().toLowerCase();
		return string;
	}

	public static void main(String[] args) {
		while (!exit) {
			String string = display();
			switch (string) {
			case "1":
				System.out.println("Enter the roll, name, contact, standard");
				stdRoll = scanner.nextInt();
				stdName = scanner.next();
				stdContact = scanner.nextLong();
				stdStandard = scanner.nextInt();
				StudentMain.hibernateInsert(stdRoll, stdName, stdContact, stdStandard);
				System.out.println("Data inserted succesfully");
				break;
			case "2":
				List<Student> list = StudentMain.hibernateFetchAll();
				for (Student student : list) {
					System.out.println(student);
				}
				break;
			case "3":
				System.out.println("Enter the roll number....");
				int roll = scanner.nextInt();
				Student student = StudentMain.hibernateFetchSingle(roll);
				System.out.println("Name: " + student.getName());
				System.out.println("Contact: " + student.getContact());
				System.out.println("Standard: " + student.getStandard());
				break;
			case "4":
				System.out.println("Enter the roll number:");
				int rollUpdate=scanner.nextInt();
				System.out.println("Set the name: ");
				String newName=scanner.next();
				System.out.println("Set the contact: ");
				long newContact=scanner.nextLong();
				System.out.println("Set the standard: ");
				int newStandard=scanner.nextInt();
				StudentMain.hibernateUpdate(rollUpdate, newName, newContact, newStandard);
				System.out.println("Data updated successfully....");
				break;
			case "5":
				System.out.println("Enter the roll number....");
				int rollDelete=scanner.nextInt();
				StudentMain.hibernateDelete(rollDelete);
				System.out.println("Data deleted successfully....");
				break;
			case "6":
				System.out.println("Exited the application");
				exit = true;
				break;
			default:
				System.err.println("Enter the correct input....");
				break;
			}
		}
	}

}
