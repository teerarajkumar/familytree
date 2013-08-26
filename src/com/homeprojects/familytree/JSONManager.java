package com.homeprojects.familytree;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONManager {
	
	private JSONParser parser;
	private final static File a = new File("res/json/familytree.json");

	public JSONManager(){
		parser = new JSONParser();
		if (!a.exists()) {
			System.err.println("Json file not found");
			return;
		}
	}
	
	
	public JSONArray getPersonsAsJSONArray(){
		JSONArray jsa = null;
		try {
			jsa = (JSONArray) parser.parse(new FileReader(a));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("Unable to parse json file");
		}
		return jsa;
	}
}
