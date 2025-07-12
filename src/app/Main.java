package app;

import service.ContactService;
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
            System.out.println("[4] Buscar Contato");
            System.out.println("[5] Salvar agenda em arquivo CSV");
            System.out.println("[6] Carregar agenda de arquivo CSV");  //PENDENTE
            System.out.println("[7] Sair");
            System.out.println();

            System.out.print("Escolha uma opção: ");
            int x = sc.nextInt();

            if (x == 7) {
                break;
            }

            String name;
            switch (x) {
                case 1:
                    //Adicionar contato na agenda
                    System.out.print("Nome: ");
                    sc.nextLine();
                    name = sc.nextLine();
                    System.out.print("E-mail: ");
                    String email = sc.next();
                    System.out.print("Numero de telefone: ");
                    String phoneNumber = sc.next();
                    agenda.addContact(name,email,phoneNumber);//Adiciona a agenda

                    System.out.println("✅Contato adicionado com sucesso!");
                    break;
                case 2:
                    //Listar contatos da agenda
                    agenda.listContacts();
                    break;
                case 3:
                    //Remover contato da agenda
                    System.out.print("Qual contato deseja remover da agenda? ");
                    sc.nextLine();
                    name = sc.nextLine();

                    if(agenda.removeContact(name)){
                        System.out.println("✅Contato removido com sucesso!");
                        System.out.println("=== LISTA ATUALIZADA ==='");
                        agenda.listContacts();
                    }else{
                        System.out.println("❌Erro ao remover contato!");
                    }
                    break;
                case 4:
                    //Buscar contato por nome
                    System.out.print("Nome do contato que deseja buscar: ");
                    sc.nextLine();
                    name = sc.nextLine();

                    agenda.searchContact(name);
                    break;
                case 5:
                    //Gerar arquivo agenda.CSV
                    if(agenda.saveContacts()){
                        System.out.println("✅Arquivo gerado com sucesso!");
                        System.out.println("📂Verifique seu diretório: "+System.getProperty("user.home")+"\\AgendaContatos");
                    }else{
                        System.out.println("❌Erro ao gerar arquivo!");
                    }
                case 6:
                    //Carregar agenda de arquivo csv
                    System.out.println("Digite o diretório do arquivo, EX: C:\\Users\\henri\\Documentos");
                    String directory = sc.next();
                    break;

            }
            System.out.println();
        }
        System.out.println("Agenda Fechada");
        sc.close();
    }
}