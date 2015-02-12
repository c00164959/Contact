package com.example.contactapp.dao;

import com.example.contactapp.entity.ContactGroup;
import com.google.inject.Singleton;

@Singleton
public class GroupDao extends BaseHibernateDao<ContactGroup>{

    public ContactGroup getGroupByName(String groupName){
        return (ContactGroup) daoHelper.findFirst("from " + entityClass.getSimpleName() + " where groupName = ?", groupName);
    }
}
