package app;

import service.ContactService;

import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        ContactService agenda = new ContactService();

        while (true) {
            System.out.println("=== AGENDA DE CONTATOS ===");
            System.out.println("[1] Adicionar Contato");
            System.out.println("[2] Listar Contatos");
            System.out.println("[3] Remover Contato");
            System.out.println("[4] Sair");

            System.out.print("Escolha uma opção: ");
            int x = sc.nextInt();

            if (x == 4) {
                break;
            }

            switch (x) {
                case 1:
                    System.out.println("adiciona contato");
                    break;
                case 2:
                    System.out.println("listar contato");
                    break;
                case 3:
                    System.out.println("remover contato");
                    break;
            }
        }
        sc.close();
    }
}