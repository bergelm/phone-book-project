package com.company;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TeleBookController {

    private TeleBook teleBook;

    public TeleBookController(){
        teleBook = FileUtils.read();
    }

    private Scanner input = new Scanner((System.in));

    private void showOptions(){
        System.out.println("Wybierz opcję:");
        for(Options option : Options.values()){
            System.out.println(option);
        }

    }


    private Options chooseOption(){
        int option = input.nextInt();
        input.nextLine();
        return Options.convertToOption(option);
    }

    private void executeOption(Options options){
        switch(options){
            case ADD_CONTACT: addContact(); break;
            case SEARCH_BY_NAME: searchByName(); break;
            case SEARCH_BY_TEL: searchByTelephone(); break;
            case DELETE: delete(); break;
            case CLOSE: close(); break;

        }
    }


    private void addContact() {
        System.out.println("Podaj nazwe kontatku:");
        String name = input.nextLine();
        System.out.println("Podaj numer telefonu:");
        String telephone = input.nextLine();

        try{
            boolean add = teleBook.add(name, telephone);
            if(add){
                System.out.println("Wpis dodany");
            } else {
                System.out.println("Kontakt o takiej nazwie już istnieje");
            }
        } catch(IllegalArgumentException e){
            System.out.println("Nazwa ani numer telefonu nie mogą byc puste");
        }
    }


    private void searchByName() {
        System.out.println("Podaj framgent nazwy");
        String nameFragment = input.nextLine();
        List<Contact> contacts = teleBook.finByName(nameFragment);
        if(contacts.isEmpty()){
            System.out.println("Brak wyników.");
        } else {
            System.out.println("Znalezione kontakty:");
            for(Contact contact: contacts){
                System.out.println(contact.toString());
            }
        }


    }


    private void searchByTelephone() {
        System.out.println("Podaj numer telefonu lub jego fragment");
        String telFragment = input.nextLine();
        List<Contact> contacts = teleBook.findByTelephone(telFragment);
        if(contacts.isEmpty()){
            System.out.println("Brak wyników.");
        } else {
            System.out.println("Znalezione kontakty:");
           for(Contact contact: contacts){
               System.out.println(contact.toString());
           }
        }
    }


    private void delete() {
        System.out.println("Podaj nazwę kontaktu do usunięcia:");
        String name = input.nextLine();
        boolean removed = teleBook.remove(name);
        if(removed){
            System.out.println("Wpis usunięty");
        } else {
            System.out.println("Nie ma takiego wpisu.");
        }
    }


    private void close() {
        input.close();

        try{
            FileUtils.save(teleBook);
            System.out.println("Zapisano zmiany");
        } catch(IOException e){
            System.out.println("Nie udało się zapisać zmian");
        }
        System.out.println("Do zobacznia!");

    }


    public void loop(){
        Options option = null;
        do {
            showOptions();

            try {
                option = chooseOption();
                executeOption(option);
            } catch (NoSuchElementException e){
                System.out.println("Nie ma takiej opcji");
            }
        } while(option != Options.CLOSE);

    }



}
