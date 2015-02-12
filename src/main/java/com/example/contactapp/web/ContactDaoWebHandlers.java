package com.example.contactapp.web;

import java.util.*;

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
public class ContactDaoWebHandlers {

    @Inject
    private ContactDao contactDao;
    
    @Inject
    private GroupDao groupDao;
    
    
    private Comparator<ContactGroup> groupComparator =new Comparator<ContactGroup>(){

		@Override
		public int compare(ContactGroup o1, ContactGroup o2) {
			return (int) (o1.getId() - o2.getId()) ;
		}
        
    };

    @WebGet("/api/contact-{contactId}")
    public Contact daoContactGet(@PathVar("contactId")Long contactId){
    	Contact contact = contactDao.get(contactId);
    	return contact;
    }
    
    @WebGet("/api/contact-getGroups-{contactId}}")
    public List<ContactGroup> daoContactGetGroups(@PathVar("contactId")Long contactId){
    	Contact contact = contactDao.get(contactId);
    	ArrayList<Long> includeGroups = contact.getGroupIds();
    	List<ContactGroup> allGroups = groupDao.list(0, 300, null, null);
    	List<ContactGroup> returnGroups = new ArrayList<ContactGroup>();
    	Map<Long,ContactGroup> nochoseGroups = new HashMap<Long, ContactGroup>();

    	if(includeGroups.size() > 0 ) {

    		for (int j = 0; j < allGroups.size(); j++) {
    			boolean flag = false;
                ContactGroup group = allGroups.get(j);
                Long aid = group.getId();
    			for (int i = 0; i < includeGroups.size(); i++) {
        			Long iid = includeGroups.get(i);
        			if(aid == iid) {
        				flag = true;
        			}
        		}
    			if(!flag) {
                    nochoseGroups.put(aid, group);
    			}
			}
    		returnGroups = new ArrayList<ContactGroup>(nochoseGroups.values());
    		Collections.sort(returnGroups, groupComparator);
    	} else {
    		returnGroups = groupDao.list(0,300,null,null);
    	}

    	return returnGroups;
    }
    
    @WebGet("/api/contacts")
    public List<Contact> daoContactList(){
    	return contactDao.list(0,300,null,null);
    }
    
    @WebPost("/api/create-contact")
    public Contact daoContactCreate(@WebParam("firstName")String firstName, @WebParam("lastName")String lastName){
    	Contact contact = new Contact(firstName, lastName);
        contactDao.save(contact);
        return contact;
    }
    
    @WebPost("/api/update-contact")
    public Contact daoContactUpdate(@WebParam("contactId")Long contactId, @WebParam("firstName")String firstName, 
    		@WebParam("lastName")String lastName){
    	
    	Contact contact = contactDao.get(contactId);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contactDao.update(contact);
    	return contact;
    }

    @WebDelete("/api/contact-{contactId}")
    public Contact daoContactDelete(@PathVar("contactId")Long contactId){
    	Contact contact = contactDao.get(contactId);
        contactDao.delete(contactId);
    	return contact;
    }
    
    @WebPost("/api/contact-setGroups")
    public Contact daoContactSetGroup(@WebParam("contactId")Long contactId, @WebParam("groupIds")String groupIds){
        Contact contact = contactDao.get(contactId);
        if(groupIds != null) {
           String stringarray[] = groupIds.split("\\|");
    		ArrayList<Long> groups = new ArrayList<Long>();
            String groupNames = "";
        	for (int i = 0; i < stringarray.length; i++) {
        		Long id = Long.valueOf(stringarray[i]);
        		groups.add(id);
                ContactGroup group = groupDao.get(id);
                groupNames+=group.getGroupName()+ "; ";
    		}
            contact.setGroupIds(groups);
            contact.setGroupNames(groupNames);
    	} else {
            contact.setGroupIds( null);
            contact.setGroupNames(null);
        }
        contactDao.save(contact);
    	return contact;
    }


}
