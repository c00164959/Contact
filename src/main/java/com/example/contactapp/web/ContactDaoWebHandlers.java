package com.example.contactapp.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.britesnow.snow.web.handler.annotation.WebActionHandler;
import com.britesnow.snow.web.param.annotation.PathVar;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.britesnow.snow.web.rest.annotation.WebDelete;
import com.britesnow.snow.web.rest.annotation.WebGet;
import com.britesnow.snow.web.rest.annotation.WebPost;
import com.example.contactapp.dao.ContactDao;
import com.example.contactapp.dao.GroupDao;
import com.example.contactapp.entity.Contact;
import com.example.contactapp.entity.Group;
import com.google.inject.Singleton;


@Singleton
public class ContactDaoWebHandlers {

    @Inject
    private ContactDao contactDao;
    
    @Inject
    private GroupDao groupDao;
    
    
    private Comparator<Group> groupComparator =new Comparator<Group>(){

		@Override
		public int compare(Group o1, Group o2) {
			return (int) (o1.getId() - o2.getId()) ;
		}
        
    };

    @WebGet("/api/contact-{contactId}")
    public Contact daoContactGet(@PathVar("contactId")Long contactId){
    	System.out.println("contactId:" + contactId);
    	Contact contact = contactDao.get(contactId);
    	return contact;
    }
    
    @WebGet("/api/contact-getGroups-{contactId}}")
    public List<Group> daoContactGetGroups(@PathVar("contactId")Long contactId){
    	Contact contact = contactDao.get(contactId);
    	ArrayList<Group> includeGroups = contact.getGroups();
    	ArrayList<Long> allGroupIds = groupDao.getGroupIds();
    	List<Group> returnGroups = new ArrayList<Group>();
    	Map<Long,Group> nochoseGroups = new HashMap<Long, Group>();
    	
    	if(includeGroups.size() > 0 ) {
    		
    		for (int j = 0; j < allGroupIds.size(); j++) {
    			boolean flag = false;
    			Long aid = allGroupIds.get(j);
    			for (int i = 0; i < includeGroups.size(); i++) {
        			Long iid = includeGroups.get(i).getId();
        			if(aid == iid) {
        				flag = true;
        			}
    				System.out.println(includeGroups.get(i).getId());
        		}
    			if(!flag) {
                    nochoseGroups.put(aid, groupDao.get(aid));
    			}
			}
    		returnGroups = new ArrayList<Group>(nochoseGroups.values());
    		Collections.sort(returnGroups, groupComparator);
    	} else {
    		returnGroups = groupDao.list();
    	}
    	
    	return returnGroups;
    }
    
    @WebGet("/api/contacts")
    public List<Contact> daoContactList(){
    	return contactDao.list();
    }
    
    @WebPost("/api/create-contact")
    public Contact daoContactCreate(@WebParam("firstName")String firstName, @WebParam("lastName")String lastName){
    	Contact contact = contactDao.create(firstName, lastName);
    	return contact;
    }
    
    @WebPost("/api/update-contact")
    public Contact daoContactUpdate(@WebParam("contactId")Long contactId, @WebParam("firstName")String firstName, 
    		@WebParam("lastName")String lastName){
    	
    	Contact contact = contactDao.update(contactId, firstName, lastName);
    	return contact;
    }

    @WebDelete("/api/contact-{contactId}")
    public Contact daoContactDelete(@PathVar("contactId")Long contactId){
    	Contact contact = contactDao.delete(contactId);
    	return contact;
    }
    
    @WebPost("/api/contact-setGroups")
    public Contact daoContactSetGroup(@WebParam("contactId")Long contactId, @WebParam("groupIds")String groupIds){
    	if(groupIds != null) {
    		String stringarray[] = groupIds.split("\\|");
    		ArrayList<Group> groups = new ArrayList<Group>();
        	for (int i = 0; i < stringarray.length; i++) {
        		Long id = Long.valueOf(stringarray[i]);
        		groups.add(groupDao.get(id));
    		}
        	
        	contactDao.setGroups(contactId, groups);
    	} else {
    		contactDao.setGroups(contactId, null);
    	}
    	
    	return contactDao.get(contactId);
    }


}
