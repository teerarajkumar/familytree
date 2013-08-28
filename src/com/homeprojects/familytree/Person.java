package com.homeprojects.familytree;

import java.util.ArrayList;

/**
 * 
 * @author Rajkumar
 * 
 */
public class Person {

	private String name;
	private int age;
	private sexes sex;
	private Person spouse;
	private ArrayList<Person> siblings;
	private Parents parents;

	public enum sexes {
		MALE, FEMALE;
	}

	public static Person newPerson(String name, int age, sexes sex) {
		return new Person(name, age, sex, null, null, null, null);
	}

	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 * @param age
	 * @param sex
	 * @param spouse
	 * @param siblings
	 */
	public Person(String name, int age, sexes sex, Person spouse,
			ArrayList<Person> siblings, Parents parents,
			ArrayList<Person> children) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.spouse = spouse;
		this.siblings = siblings;
		this.parents = parents;
	}

	public void print() {
		System.out.println(name);
		System.out.println(age);
		System.out.println(sex);
		if (spouse != null)
			System.out.println("Spouse : " + spouse.name);
		else
			System.out.println("Single");
		if (siblings != null) {
			System.out.print("Siblings :");
			String delimiter = " ";
			for (Person a : siblings) {
				System.out.print(delimiter + a.name);
				delimiter = ", ";
			}
			System.out.println();
		} else
			System.out.println("Single Child");
		if (parents != null)
			parents.print();
		System.out.println("****************************");
	}

	public void connectAllSiblings(Person b) {
		Person a = this;
		if (a.siblings == null)
			a.siblings = new ArrayList<Person>();
		if (b.siblings == null)
			b.siblings = new ArrayList<Person>();

		updateAllSiblingsInTheList(a, b);
		updateAllSiblingsInTheList(b, a);

		if (!a.siblings.contains(b))
			a.siblings.add(b);
		if (!b.siblings.contains(a))
			b.siblings.add(a);
	}

	/**
	 * Makes siblings of Person 'a' as siblings of Person 'b'
	 * 
	 * @param a
	 * @param b
	 */
	private void updateAllSiblingsInTheList(Person a, Person b) {
		for (Person x : a.siblings) {
			if (!b.siblings.contains(x) && !b.equals(x)) {
				b.siblings.add(x);
				if (!x.siblings.contains(b))
					x.siblings.add(b);
			}
		}
	}

	private void simpleParentConnect(Person x) {
		if (parents == null) {
			if (x.sex == sexes.MALE)
				parents = new Parents(x, x.spouse);
			else
				parents = new Parents(x.spouse, x);
		}
	}

	public boolean notLinkedWithParents() {
		if (parents == null)
			return true;
		return false;
	}

	public Person getSpouse() {
		return spouse;
	}

	/**
	 * Creates spouse link between two persons
	 * 
	 * @param b
	 *            person to which the caller will be married to
	 */
	public void marry(Person b) {
		Person a = this;
		if (a.spouse != null || b.spouse != null) {
			System.err.println("Link thappache!");
		}
		a.spouse = b;
		b.spouse = a;
	}

	public boolean notMarriedTo(Person b) {
		Person a = this;
		if (a.spouse == b && b.spouse == a)
			return false;
		if (a.spouse != null || b.spouse != null) {
			System.err.println("Kudumbathil Kuzhappam");
		}
		return true;
	}

	public boolean notSiblingOf(Person b) {
		Person a = this;
		if (a.siblings == null || b.siblings == null)
			return true;
		if (a.siblings.contains(b) && b.siblings.contains(a))
			return false;
		if (a.siblings.contains(b) || b.siblings.contains(a)) {
			if (a.siblings.contains(b))
				System.err.println(a.name + "(A) contains " + b.name
						+ " but not vice versa");
			else
				System.err.println(b.name + "(B) contains " + a.name
						+ " but not vice versa");
			System.err.println("Annan thangachi akka thambi kulla kozhappam");
		}
		return true;
	}

	public void connectWithParent(Person x) {
		simpleParentConnect(x);
		if (siblings != null)
			for (Person sibling : siblings) {
				sibling.simpleParentConnect(x);
			}
	}

}
