package com.company;

import java.util.*;

public class TeleBook implements  Iterable<Contact>{

    private Map<String, Contact> cotnacts = new TreeMap<>();

    public TeleBook(){}

    public TeleBook(Map<String, Contact> cotnacts){
        this.cotnacts = cotnacts;
    }

    public boolean add(String name, String telephone){
        if(name == null || telephone == null){
            throw new NullPointerException("Name and telephone cannot be null");
        }
        if(name.isEmpty() || telephone.isEmpty()){
            throw new IllegalArgumentException("Name and telephone cannot be empty");
        }
        if(!cotnacts.containsKey(name)){
            cotnacts.put(name, new Contact(name, telephone));
            return true;
        } else {
            return false;
        }

    }

    public boolean remove(String name){
        if(cotnacts.containsKey(name)){
            cotnacts.remove(name);
            return true;
        } else {
            return false;
        }
    }

    public List<Contact> finByName(String name){
        List<Contact> result = new ArrayList<>();
        for(Map.Entry<String, Contact> entry: cotnacts.entrySet() ){
            if(entry.getKey().contains(name)){
                result.add(entry.getValue());
            }
        }
        return result;
    }

    public List<Contact> findByTelephone(String telephone){
        List<Contact> result = new ArrayList<>();
        for(Contact contact : cotnacts.values()){
            if(contact.getTelephone().contains(telephone)){
                result.add(contact);
            }
        }
        return result;
    }

    public Iterator<Contact> iterator(){
        return cotnacts.values().iterator();
    }

}
