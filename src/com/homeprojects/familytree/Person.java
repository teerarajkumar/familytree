package com.homeprojects.familytree;

/**
 * 
 * @author Rajkumar
 * 
 */
public class Person {

	private String name;
	private int age;
	private Person.sexes sex;
	private Person spouse;

	public enum sexes {
		MALE, FEMALE;
	}

	/**
	 * 
	 * @param name
	 * @param age
	 * @param sex
	 * @param spouse
	 */
	public Person(String name, int age, Person.sexes sex, Person spouse) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.spouse = spouse;
	}

	public void print() {
		System.out.println(name);
		System.out.println(age);
		System.out.println(sex);
		if (spouse != null)
			System.out.println("Spouse : " + spouse.name);
	}

	public boolean hasName(String name) {
		if(this.name == name)
			return true;
		return false;
	}
}
