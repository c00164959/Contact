package com.example.contactapp.entity;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Contact  extends BaseEntity{

    private String firstName;
    private String lastName;
    private ArrayList<Long> groupIds;
    private String groupNames;
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(ArrayList<Long> groupIds) {
        this.groupIds = groupIds;
    }

    public String getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(String groupNames) {
        this.groupNames = groupNames;
    }

    public Contact(){}

    public Contact(String firstName,String lastName){
        setFirstName(firstName);
        setLastName(lastName);
    }
}
