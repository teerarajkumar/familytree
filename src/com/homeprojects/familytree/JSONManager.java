package com.homeprojects.familytree;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONManager {
	
	private JSONParser parser;
	private static JSONArray jsa;
	private final static File a = new File("res/json/familytree.json");

	public JSONManager(){
		parser = new JSONParser();
		if (!a.exists()) {
			System.err.println("Json file not found");
			return;
		}
		jsa = null;
		try {
			jsa = (JSONArray) parser.parse(new FileReader(a));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("Unable to parse json file");
		} catch (Exception e) {
			System.err.println("Unknown error in JSONmanager");
			e.printStackTrace();
		}
	}
	
	
	public JSONArray getPersonsAsJSONArray(){
		return jsa;
	}
}
