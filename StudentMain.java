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
		scanner = new Scanner(System.in);
		roll_num = scanner.nextInt();
		Student student = null;
		try {
			factory = Persistence.createEntityManagerFactory("emp");
			manager = factory.createEntityManager();
			String queryFetchSingle = "from Student where roll= :r";
			Query query = manager.createQuery(queryFetchSingle);
			query.setParameter(1, roll_num);
			student = (Student) query.getSingleResult();
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
		scanner = new Scanner(System.in);
		roll_num = scanner.nextInt();
		name = scanner.next();
		contact = scanner.nextLong();
		standard = scanner.nextInt();
		Student student = null;
		try {
			factory = Persistence.createEntityManagerFactory("emp");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String queryUpdate = "update Student set name= :name, contact= :contact, standard= :standard where roll= :roll";
			Query query = manager.createQuery(queryUpdate);
			query.setParameter("name", name);
			query.setParameter("contact", Long.parseLong(Long.toString(contact)));
			query.setParameter("standard", Integer.parseInt(Integer.toString(standard)));
			query.setParameter("roll", Integer.parseInt(Integer.toString(roll_num)));
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
		scanner = new Scanner(System.in);
		roll_num = scanner.nextInt();
		try {
			factory = Persistence.createEntityManagerFactory("emp");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String queryDelete = "delete from Student where roll= :r";
			Query query = manager.createQuery(queryDelete);
			query.setParameter("r", Integer.parseInt(Integer.toString(roll_num)));
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

	public static void main(String[] args) {
		/*
		 * List<Student> fetchAll= StudentMain.hibernateFetchAll(); for (Student student
		 * : fetchAll) { System.out.println(student); }
		 */

		/*
		 * int roll = scanner.nextInt(); Student fetchByRoll =
		 * StudentMain.hibernateFetchSingle(roll); System.out.println("Name: " +
		 * fetchByRoll.getName()); System.out.println("Contact: " +
		 * fetchByRoll.getContact()); System.out.println("Standard: " +
		 * fetchByRoll.getStandard());
		 */
		while (!exit) {
			System.out.println(
					"Press 1 to insert the data\nPress 2 to display all data\nPress 3 to display by Roll\nPress 4 to update data by roll\nPress 5 to delete by roll\nPress 6 to Quit the program");
					String string= scanner.next().toLowerCase();
					switch(string) {
					case "1": 
						System.out.println("Enter the roll, name, contact, standard");
						stdRoll=scanner.nextInt();
						stdName=scanner.next();
						stdContact=scanner.nextLong();
						stdStandard=scanner.nextInt();
						StudentMain.hibernateInsert(stdRoll, stdName, stdContact, stdStandard);
						break;
					case "2":
						
						break;
					case "3":
						
						break;
					case "4":
						
						break;
					case "5":
						
						break;
					case "6":
						
						break;
					}
		}
	}

}
