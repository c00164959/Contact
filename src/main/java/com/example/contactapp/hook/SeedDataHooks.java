package com.example.contactapp.hook;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.contactapp.dao.ContactDao;
import com.example.contactapp.dao.GroupDao;
import com.example.contactapp.entity.Contact;
import com.example.contactapp.entity.ContactGroup;
import org.hibernate.jdbc.Work;

import com.britesnow.snow.web.db.hibernate.HibernateDaoHelper;
import com.britesnow.snow.web.db.hibernate.HibernateSessionInViewHandler;
import com.britesnow.snow.web.hook.AppPhase;
import com.britesnow.snow.web.hook.On;
import com.britesnow.snow.web.hook.annotation.WebApplicationHook;
import com.google.inject.Singleton;

@Singleton
public class SeedDataHooks {

    /**
     * This will be called to see the database (for demo only)
     *
     * @param contactDao
     *            will be injected by Snow with the Guice binding
     * @param groupDao
     *            will be injected by Snow with the Guice binding
     * @param inView
     *            will be injected by Snow with the Guice binding (needed to open the connection for this thread to use
     *            daoHelper)
     *
     */
    @WebApplicationHook(phase = AppPhase.INIT)
    public static void seedStore(ContactDao contactDao, GroupDao groupDao, HibernateSessionInViewHandler inView) {
        inView.openSessionInView();
        for (String[] contact : contacts) {
            if(contactDao.getContactByName(contact[0],contact[1]) == null){
                Contact newContact = new Contact(contact[0],contact[1]);
                contactDao.save(newContact);
            }
        }

        for(String[] group : groups) {
            if(groupDao.getGroupByName(group[0]) == null){
                ContactGroup newGroup = new ContactGroup(group[0]);
                groupDao.save(newGroup);
            }
        }
        inView.closeSessionInView();
    }

    /**
     * Using HSQLDB we need to shutdown the database to be written in the file system.
     *
     * Note that if you do not shutdown your webapp gracefully, the data won't be written to disk.
     *
     * @param inView
     * @param daoHelper
     */
    @WebApplicationHook(phase = AppPhase.SHUTDOWN,on=On.BEFORE)
    public void shutdownDb(HibernateSessionInViewHandler inView, HibernateDaoHelper daoHelper){
        try {
            inView.openSessionInView();
            daoHelper.getSession().doWork(new Work() {
                public void execute(Connection con) throws SQLException {
                    con.prepareStatement("shutdown compact").execute();
                }
            });
            inView.closeSessionInView();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // --------- Some Seed Data --------- //
    private static String[][] contacts    = { { "Rachel", "Green" },
            { "Ross","Geller" },
            {"Monica","Geller"},
            {"Chandler","Bing"},
            {"Phoebe","Buffay"},
            {"Joey","Tribbiani"}};

    private static String[][] groups = {
            { "Group1" },
            { "Group2" }};
    // --------- Some Seed Data --------- //


}
