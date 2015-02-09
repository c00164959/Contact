package com.example.contactapp.web;

import java.util.List;
import javax.inject.Inject;
import com.britesnow.snow.web.handler.annotation.WebActionHandler;
import com.britesnow.snow.web.param.annotation.PathVar;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.britesnow.snow.web.rest.annotation.WebDelete;
import com.britesnow.snow.web.rest.annotation.WebGet;
import com.britesnow.snow.web.rest.annotation.WebPost;
import com.example.contactapp.dao.ContactDao;
import com.example.contactapp.dao.GroupDao;
import com.example.contactapp.entity.Group;
import com.google.inject.Singleton;


@Singleton
public class GroupDaoWebHandlers {

    @Inject
    private GroupDao groupDao;
    
    @Inject
    private ContactDao contactDao;


    @WebGet("/api/group-{groupId}")
    public Group daoGroupGet( @PathVar("groupId") Long groupId){
    	Group group = groupDao.get(groupId);
    	return group;
    }

    @WebGet("/api/groups")
    public List<Group> daoGroupList(){
        return groupDao.list();
    }
    
    
    @WebPost("/api/create-group")
    public Group daoGroupCreate(@WebParam("groupName")String groupName){
    	Group group = groupDao.create(groupName);
    	return group;
    }
    
    
    @WebPost("/api/update-group")
    public Group daoGroupUpdate(@WebParam("groupId")Long groupId, @WebParam("groupName")String groupName){
    	Group group = groupDao.update(groupId, groupName);
    	return group;
    }

    @WebDelete("/api/group-{groupId}")
    public Group daoGroupDelete(@PathVar("groupId") Long groupId){
        Group group = groupDao.delete(groupId);
        contactDao.deleteGroup(groupId);
        return group;
    }
}
