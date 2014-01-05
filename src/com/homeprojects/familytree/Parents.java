package com.homeprojects.familytree;

public class Parents {
    Person dad;
    Person mom;

    public Parents(Person dad, Person mom) {
        this.dad = dad;
        this.mom = mom;
    }

    public void print() {
        System.out.print("Parents: ");
        System.out.println(dad.getName() + " , " + mom.getName());
    }
}
