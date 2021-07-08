package com.techllenapps.cardealershipconsoleapp.functions;


import java.util.Scanner;
import java.io.*;
import java.io.Serializable;

public class Test implements Serializable{

	public String id;
	public String name;
	public String grade;
	public static String filePath = ("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//test.txt");

	public static void main(String argv[]) throws IOException, ClassNotFoundException
	{
		Test b = new Test();
		Scanner sc = new Scanner(System.in);
		System.out.printf("Enter student id: ");
		b.id = sc.nextLine();
		System.out.printf("Enter student name: ");
		b.name = sc.next();
		System.out.printf("Enter student grade: ");
		b.grade = sc.nextLine();
		ObjectOutputStream bin = new ObjectOutputStream(new FileOutputStream(filePath,true));
		bin.writeObject(b);
		bin.close();

		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));
		Test b2 = (Test)in.readObject();
		System.out.println("Student ID: " + b2.id);
		System.out.println("Student Name: " + b2.name);
		System.out.println("Student Grade: " + b2.grade);
		in.close();
	}
}

