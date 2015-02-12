package com.example.contactapp.entity;

import javax.persistence.Entity;

@Entity
public class ContactGroup extends BaseEntity{

    private String groupName;
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
    public ContactGroup(){}
    public ContactGroup(String name){
        setGroupName(name);
    }
}
