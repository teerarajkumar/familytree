package com.homeprojects.familytree;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.homeprojects.familytree.Person.sexes;

public class Family {

	private ArrayList<Person> family_members;

	public Family(){
		family_members = new ArrayList<Person>();
	}
	public Family(ArrayList<Person> persons) {
		family_members = persons;
	}

	public void print() {
		for (Person p : family_members) {
			p.print();
		}
	}

	public Person findByName(String name) {
		for (Person p : family_members) {
			if (p.hasName(name))
				return p;
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File a = new File("res/json/familytree.json");
		JSONParser parser = new JSONParser();
		JSONArray jsa = null;
		Family f = new Family();
		ArrayList<Person> persons = new ArrayList<Person>();
		if (!a.exists()) {
			System.err.println("Json file not found");
			return;
		}
		try {
			jsa = (JSONArray) parser.parse(new FileReader(a));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("Unable to parse json file");
		}
		for (Object o : jsa) {
			JSONObject jso = (JSONObject) o;
			f.family_members.add(f.createPersonFromJson(jso));
			System.out.println(jso.toString());
		}
		f = new Family(persons);
		f.print();
	}

	private Person createPersonFromJson(JSONObject jso) {
		sexes sex;
		String name = (String) jso.get("name");
		int age = Integer.parseInt((String) jso.get("age"));
		String sexTemp = (String) jso.get("sex");
		String spouseName = (String) jso.get("spouse");
		if (sexTemp.charAt(0) == 'm')
			sex = Person.sexes.MALE;
		else
			sex = Person.sexes.FEMALE;
		Person spouse = null;
		if (spouseName != null) {
			spouse = this.findByName(spouseName);
		}
		Person p = new Person(name, age, sex, spouse);
		return p;
	}
}
