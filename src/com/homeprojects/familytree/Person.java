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

	public enum sexes {
		MALE, FEMALE;
	}

	public Person(String name, int age, sexes sex) {
		new Person(name,age,sex,null,null);
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

		} else
			System.out.println("Single Child");
	}

	public boolean hasName(String name) {
		if (this.name.equals(name))
			return true;
		return false;
	}
	
	public static boolean notLinked(Person a, Person b) {
		if(a.spouse == b && b.spouse == a)
			return false;
		if( a.spouse!= null || b.spouse!= null){
			System.err.println("Kudumbathil Kuzhappam");
		}
		return true;
	}
	
	public static void formLink(Person a, Person b) {
		if( a.spouse != null || b.spouse != null ){
			System.err.println("Link thappache!");
		}
		a.spouse = b;
		b.spouse = a;
	}
}
