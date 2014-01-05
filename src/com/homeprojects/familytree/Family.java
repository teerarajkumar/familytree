package com.homeprojects.familytree;

import java.util.ArrayList;
import com.homeprojects.familytree.JSONManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.homeprojects.familytree.Person.sexes;

public class Family {

    private ArrayList<Person> family_members;

    public Family() {
        family_members = new ArrayList<Person>();
    }

    public Family(ArrayList<Person> persons) {
        family_members = persons;
    }

    public void scanPersons(JSONArray jsa) {
        for (Object o : jsa) {
            JSONObject jso = (JSONObject) o;
            family_members.add(createPersonFromJson(jso));
        }
    }

    public void print() {
        for (Person p : family_members) {
            p.print();
        }
    }

    public Person findByName(String name) {
        for (Person p : family_members) {
            if (p.getName().equals(name))
                return p;
        }
        System.err.println("Unable to find person with name :" + name);
        return null;
    }

    private void matchSpouses(JSONArray jsa) {
        for (Object o : jsa) {
            JSONObject jso = (JSONObject) o;
            String spouseName = (String) jso.get("spouse");
            if (spouseName != null) {
                String name = (String) jso.get("name");
                Person currentPerson = findByName(name);
                Person spouse = findByName(spouseName);
                if (spouse == null) {
                    System.err.println("Invalid spouse name " + spouseName);
                    return;
                }
                if (currentPerson.notMarriedTo(spouse))
                    currentPerson.marry(spouse);
            }
        }
    }

    private void matchSiblings(JSONArray jsa) {
        for (Object o : jsa) {
            JSONObject jso = (JSONObject) o;
            JSONArray siblingsArray = (JSONArray) jso.get("siblings");
            if (siblingsArray == null)
                continue;
            String name = (String) jso.get("name");
            Person currentPerson = findByName(name);
            for (Object s : siblingsArray) {
                String siblingName = (String) s;
                Person sibling = findByName(siblingName);
                if (sibling == null) {
                    System.err.println("Invalid sibling name " + siblingName);
                    return;
                }
                if (currentPerson.notSiblingOf(sibling))
                    currentPerson.connectAllSiblings(sibling);
            }
            connectWithinSiblings(siblingsArray);
        }
    }

    private void matchParents(JSONArray jsa) {
        for (Object o : jsa) {
            JSONObject jso = (JSONObject) o;
            String name = (String) jso.get("name");
            Person currentPerson = findByName(name);
            String dadName = (String) jso.get("dad");
            String momName = (String) jso.get("mom");
            if (dadName != null) {
                Person dad = findByName(dadName);
                if (currentPerson.notLinkedWithParents())
                    currentPerson.connectWithParent(dad);
            } else if (momName != null) {
                Person mom = findByName(momName);
                if (currentPerson.notLinkedWithParents())
                    currentPerson.connectWithParent(mom);
            }
        }
    }

    private void connectWithinSiblings(JSONArray siblingsArray) {
        for (Object o1 : siblingsArray) {
            String name = (String) o1;
            Person a = findByName(name);
            for (Object o2 : siblingsArray) {
                name = (String) o2;
                Person b = findByName(name);
                if (a.equals(b))
                    continue;
                if (a.notSiblingOf(b))
                    a.connectAllSiblings(b);
            }
        }
    }

    private Person createPersonFromJson(JSONObject jso) {
        sexes sex;
        String name = (String) jso.get("name");
        int age = Integer.parseInt((String) jso.get("age"));
        String sexTemp = (String) jso.get("sex");
        if (sexTemp.charAt(0) == 'm')
            sex = Person.sexes.MALE;
        else
            sex = Person.sexes.FEMALE;
        Person p = Person.newPerson(name, age, sex);
        return p;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        JSONManager jm = new JSONManager();
        JSONArray jsa = jm.getPersonsAsJSONArray();
        Family f = new Family();
        f.scanPersons(jsa);
        f.matchSpouses(jsa);
        f.matchSiblings(jsa);
        f.matchParents(jsa);
        f.print();
    }
}
