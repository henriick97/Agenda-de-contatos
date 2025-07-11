package service;

import model.entities.Contact;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ContactService {
    List<Contact> agenda = new ArrayList<>();

    public void addContact(String name, String email, String phoneNumber) {
        agenda.add(new Contact(name, email, phoneNumber));
    }

    public void loadContacts(Path file) {
        List<String> line = new ArrayList<>();

        try {
            while ((line = Files.readAllLines(file)) != null) {

                for (String x : line) {
                    String vect[] = x.split(",\\s*");
                    String name = vect[0];
                    String email = vect[1];
                    String phoneNumber = vect[2];
                    addContact(name, email, phoneNumber);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveContacts(Path file) {
        try {
            for (Contact x : agenda) {
                Files.writeString(file, x.getName() + ", " + x.getEmail() + ", " + x.getPhoneNumber(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listContacts(){
        for(Contact x : agenda){
            System.out.println("Name: "+x.getName()
                    +" Email: "+x.getEmail()
                    +" Phone Number: "+x.getPhoneNumber());
        }
    }

    public void searchContact(String name){
        for(Contact x : agenda){
            if(x.getName().equalsIgnoreCase(name)){
                System.out.println(x);
            }
        }
    }

    public void removeContact(String name){
        for(Contact x : agenda){
            if(x.getName().equalsIgnoreCase(name)){
                agenda.remove(x);
            }
        }
    }

    //Pendente!
    public void updateContact(){

    }
}



