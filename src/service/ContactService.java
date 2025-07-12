package service;

import model.entities.Contact;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public boolean saveContacts() {
        Path file = Paths.get(System.getProperty("user.home"), "AgendaContatos", "agenda.csv");

        try {
            Files.createDirectories(file.getParent());  //Cria diretorio inexistente(é necessario tratar)
            //trata e fecha BufferedWriter com finally
            try (BufferedWriter bw = Files.newBufferedWriter(file)) {
                //insere dados do Contato ao arquivo agenda.csv
                bw.write("NOME,E-MAIL,NUMERO TELEFONE");
                for (Contact x : agenda) {
                    bw.newLine();
                    String line = x.getName() + "," + x.getEmail() + "," + x.getPhoneNumber();
                    bw.write(line);
                }
                return true;
            }
        } catch (IOException e) {
            return false;
        }
    }

    public void listContacts() {
        for (Contact x : agenda) {
            System.out.println(x);
        }
    }

    public void searchContact(String name) {
        boolean found = false;
        for (Contact x : agenda) {
            if (x.getName().equalsIgnoreCase(name)) {
                System.out.print("📞Contato encontrado: ");
                System.out.println(x);
                found = true;
            }
        }
        //Se found=true; o if não executa, pois a condicao inverte para false.
        if (!found) {
            System.out.println("Contato não encontrado!");
        }
    }

    public boolean removeContact(String name) {
        return agenda.removeIf(x -> x.getName().equalsIgnoreCase(name));
    }

    //Pendente!
    public void updateContact() {

    }
}



