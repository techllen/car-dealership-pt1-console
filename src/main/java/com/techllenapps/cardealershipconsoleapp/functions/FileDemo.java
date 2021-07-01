package com.techllenapps.cardealershipconsoleapp.functions;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techllenapps.cardealershipconsoleapp.entities.User;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileDemo implements Serializable {
//    static class FileList implements Serializable {
//        List<FileData> list = new ArrayList<>();
        static class UserList implements Serializable {
            List<User> list = new ArrayList<>();
    }

    static class FileData implements Serializable { }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.ser");
        //FileList list = new FileList();
        UserList users = new UserList();
        
        Scanner scn = new Scanner(System.in);
        while (true) {
            System.out.print("MENU\n" +
                    "Enter 1 to add new object to file\n" +
                    "Enter 2 to display list size\n" +
                    "Enter 3 to exit\n" +
                    "Enter your choice: ");
            int option = scn.nextInt();
            switch (option) {
                case 1:	
                    Scanner in = new Scanner(System.in);
                	System.out.println("Password");
            		String passcode = in.nextLine();
            		System.out.println("Username");
            		String name = in.nextLine();
            		//list.list.add(new FileData());
                	users.list.add(new User(name,passcode));
                    File tmp = new File("//media//techllen//01D5CEDF6FF7FE50//Development//5.PROJECTS//car-dealership-pt1-console//src//main//resources//users.ser");
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tmp))) {
                        oos.writeObject(users);
                    }
                    Files.move(tmp.toPath(), file.toPath(), REPLACE_EXISTING);
                    System.out.println("Object Added");
                    break;
                case 2:
                    System.out.println(users.list.size());
                    System.out.println(users.list);

                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        
    }
    
}
