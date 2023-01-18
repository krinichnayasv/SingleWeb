package main;

import model.ToDoList;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {

    private static int currentId = 1;
    private static HashMap<Integer, ToDoList> businesses = new HashMap<Integer, ToDoList>();


    public static ArrayList<ToDoList> getAllBusinesses() {
        ArrayList<ToDoList> lists = new ArrayList<ToDoList>();
        lists.addAll(businesses.values());
        return lists;
    }

    public static ToDoList getBusiness (int businessId) {
        if(businesses.containsKey(businessId)) {
            return businesses.get(businessId);
        }
        return null;
    }


    public synchronized static int addBusiness (ToDoList business) {
        int id = currentId++;
        business.setId(id);
        businesses.put(id, business);
        return id;
    }

    public synchronized static ArrayList<ToDoList> deleteBusiness(int businessId) {
        if(businesses.containsKey(businessId)) {
            ToDoList business = businesses.get(businessId);
            businesses.remove(businessId);
            ArrayList<ToDoList> lists = new ArrayList<ToDoList>();
            lists.addAll(businesses.values());
            return lists;
            }
        return null;
            }

            public static void deleteAll ()
            {
                businesses.clear();
            }

            public synchronized static ToDoList updateBusiness(int businessId, String name, String date) {
           ToDoList newBusiness = new ToDoList();
                newBusiness.setId(businessId);
                newBusiness.setName(name);
                newBusiness.setDate(date);
                if(businesses.containsKey(businessId)) {
                    businesses.remove(businessId);
                    businesses.put(businessId, newBusiness);
                    return newBusiness;
                }
             return null;
            }

}
