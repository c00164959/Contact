brite.registerView("ContactView", {emptyParent:true}, {
	create: function(data,config){
	   var contactList = data.contactList;
       return render("tmpl-ContactView", {contactList:contactList});
   },
   
   postDisplay: function(){
	    var view = this;
   },
   
   events: {

       "click; button.addc": function(event){
           var contactCreate = this;
           var firstName = contactCreate.$el.find("#firstName").val();
           var lastName = contactCreate.$el.find("#lastName").val();
           var contactDao = brite.dao("Contact");

           if(firstName.length > 0 && lastName.length > 0 ) {

               contactDao.create(firstName,lastName).pipe(function(contact) {
                   brite.display("MainView",$mainview);
               })
           }
       },
       "click; button.edit": function(event){
    	   var contactId = $(event.target).closest("tr").attr("data-contact-id");
    	   var contactDao = brite.dao("Contact");
    	   
    	   contactDao.get(contactId).pipe(function(contact) {
    		   brite.display("ContactCreate",$mainview,{contact : contact});
    	   })
       },
       "click; button.delete": function(event){
    	   var contactId = $(event.target).closest("tr").attr("data-contact-id");
    	   var contactDao = brite.dao("Contact");
    	   
    	   contactDao.deletec(contactId).pipe(function(contact) {
    		   brite.display("MainView",$mainview);
    	   })
       },
       "click; button.setGroup": function(event){
    	   var contactId = $(event.target).closest("tr").attr("data-contact-id");
    	   var groupDao = brite.dao("Group");
    	   
    	   groupDao.list().pipe(function(groupList) {
    		   brite.display("AddGroupView", $mainview, { groupList : groupList, contactId : contactId});
    	   });
    	   
       }
       
   }
 
 });