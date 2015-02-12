package com.example.contactapp.dao;

import com.example.contactapp.entity.Contact;
import com.google.inject.Singleton;

@Singleton
public class ContactDao  extends BaseHibernateDao<Contact>{

    public Contact getContactByName(String firstName, String lastName){
        return (Contact) daoHelper.findFirst("from " + entityClass.getSimpleName() + " where firstName = ? and lastName = ?", firstName, lastName);
    }
}
