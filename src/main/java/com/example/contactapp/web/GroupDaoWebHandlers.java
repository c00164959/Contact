package com.example.contactapp.web;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import com.britesnow.snow.web.param.annotation.PathVar;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.britesnow.snow.web.rest.annotation.WebDelete;
import com.britesnow.snow.web.rest.annotation.WebGet;
import com.britesnow.snow.web.rest.annotation.WebPost;
import com.example.contactapp.dao.ContactDao;
import com.example.contactapp.dao.GroupDao;
import com.example.contactapp.entity.Contact;
import com.example.contactapp.entity.ContactGroup;
import com.google.inject.Singleton;


@Singleton
public class GroupDaoWebHandlers {

    @Inject
    private GroupDao groupDao;
    
    @Inject
    private ContactDao contactDao;


    @WebGet("/api/group-{groupId}")
    public ContactGroup daoGroupGet( @PathVar("groupId") Long groupId){
    	ContactGroup contactGroup = groupDao.get(groupId);
    	return contactGroup;
    }

    @WebGet("/api/groups")
    public List<ContactGroup> daoGroupList(){
        return groupDao.list(0,300,null,null);
    }
    
    
    @WebPost("/api/create-group")
    public ContactGroup daoGroupCreate(@WebParam("groupName")String groupName){
        ContactGroup contactGroup = new ContactGroup(groupName);
        groupDao.save(contactGroup);
    	return contactGroup;
    }
    
    
    @WebPost("/api/update-group")
    public ContactGroup daoGroupUpdate(@WebParam("groupId")Long groupId, @WebParam("groupName")String groupName){
        ContactGroup contactGroup = groupDao.get(groupId);
        String oldName = contactGroup.getGroupName();
        contactGroup.setGroupName(groupName);
        groupDao.update(contactGroup);
        UpdateContactGroup(groupId, false);
    	return contactGroup;
    }

    private void UpdateContactGroup(Long groupId, boolean needDeleteId) {
        List<Contact> allContacts = contactDao.list(0, 300, null, null);
        for (Contact contact : allContacts){
            ArrayList<Long> groupIds = contact.getGroupIds();

            boolean needUpdate = false;
            if(groupIds != null) {
                for (Long selectGroupId : groupIds) {
                    if (selectGroupId.equals(groupId)) {
                        needUpdate = true;
                        break;
                    }
                }
            }
            if(needUpdate){
                if(needDeleteId){
                    contact.getGroupIds().remove(groupId);
                    contactDao.save(contact);
                    groupIds = contact.getGroupIds();
                }
                if(groupIds != null) {
                    String groupNames = "";
                    for (Long selectGroupId : groupIds) {
                        ContactGroup group = groupDao.get(selectGroupId);
                        groupNames += group.getGroupName() + "; ";
                    }
                    contact.setGroupNames(groupNames);
                    contactDao.save(contact);
                }
            }
        }
    }

    private void UpdateGroupsByDelete(Long groupId) {
        List<Contact> allContacts = contactDao.list(0, 300, null, null);
        for (Contact contact : allContacts){
            ArrayList<Long> groupIds = contact.getGroupIds();
            boolean needUpdate = false;
            if(groupIds != null) {
                for (Long selectGroupId : groupIds) {
                    if (selectGroupId.equals(groupId)) {
                        needUpdate = true;
                        break;
                    }
                }
            }
            if(needUpdate){
                contact.getGroupIds().remove(groupId);
                String groupNames = "";
                for (Long selectGroupId : groupIds) {
                    ContactGroup group = groupDao.get(selectGroupId);
                    if(groupId != group.getId()) {
                        groupNames += group.getGroupName() + "; ";
                    }
                }
                contact.setGroupNames(groupNames);
                contactDao.save(contact);
            }
        }
    }

    @WebDelete("/api/group-{groupId}")
    public ContactGroup daoGroupDelete(@PathVar("groupId") Long groupId){
        ContactGroup contactGroup = groupDao.get(groupId);
        groupDao.delete(groupId);
        UpdateContactGroup(groupId,true);
        return contactGroup;
    }
}
