package com.homeprojects.familytree;

import java.util.ArrayList;

import org.json.simple.JSONArray;

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

	public enum sexes {
		MALE, FEMALE;
	}

	public Person(String name, int age, sexes sex) {
		new Person(name, age, sex, null, null);
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
			ArrayList<Person> siblings) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.spouse = spouse;
		this.siblings = siblings;
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
			for(Person a : siblings){
				System.out.print(delimiter+a.name);
				delimiter = ", ";
			}
			System.out.println();
		} else
			System.out.println("Single Child");
		System.out.println("****************************");
	}

	public boolean hasName(String name) {
		if (this.name.equals(name))
			return true;
		return false;
	}

	public static boolean notLinked(Person a, Person b) {
		if (a.spouse == b && b.spouse == a)
			return false;
		if (a.spouse != null || b.spouse != null) {
			System.err.println("Kudumbathil Kuzhappam");
		}
		return true;
	}

	public static void formLink(Person a, Person b) {
		if (a.spouse != null || b.spouse != null) {
			System.err.println("Link thappache!");
		}
		a.spouse = b;
		b.spouse = a;
	}

	public static boolean notSiblings(Person a, Person b) {
		if(a.siblings == null || b.siblings == null)
			return true;
		if (a.siblings.contains(b) && b.siblings.contains(a))
			return false;
		if (a.siblings.contains(b) || b.siblings.contains(a)) {
			if(a.siblings.contains(b))
				System.err.println(a.name+"(A) contains "+b.name+" but not vice versa");
			else
				System.err.println(b.name+"(B) contains "+a.name+" but not vice versa");
			System.err.println("Annan thangachi akka thambi kulla kozhappam");
		}
		return true;
	}

	public static void connectAllSiblings(Person a, Person b) {
		if(a.siblings == null)
			a.siblings = new ArrayList<Person>();
		if(b.siblings == null)
			b.siblings = new ArrayList<Person>();
		for (Person x : a.siblings) {
			if (!b.siblings.contains(x) && !b.equals(x)){
				b.siblings.add(x);
				if(!x.siblings.contains(b))
					x.siblings.add(b);
			}
		}
		for (Person x : b.siblings) {
			if(!a.siblings.contains(x) && !a.equals(x)){
				a.siblings.add(x);
				if(!x.siblings.contains(b))
					x.siblings.add(b);
			}
		}
		if(!a.siblings.contains(b))
			a.siblings.add(b);
		if(!b.siblings.contains(a))
			b.siblings.add(a);
	}

}
