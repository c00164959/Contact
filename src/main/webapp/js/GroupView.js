brite.registerView("GroupView", {emptyParent:true}, {
	create: function(data,config){
		var groupList = data.groupList;
       return render("tmpl-GroupView", {groupList:groupList});
   },
   postDisplay: function(){
	    var groupView = this;
   },
   events: {
       "click; button.addg": function(event){
           var groupCreate = this;
           var groupName = groupCreate.$el.find("#groupName").val();
           var groupDao = brite.dao("Group");

           if(groupName.length > 0) {
               groupDao.create(groupName).pipe(function(group) {
                   brite.display("MainView",$mainview);
               })
           }
       },
       
       "click; button.edit": function(event){
    	   var groupId = $(event.target).closest("tr").attr("data-group-id");
    	   var groupDao = brite.dao("Group");
    	   
    	   groupDao.get(groupId).pipe(function(group) {
    		   brite.display("GroupCreate",$mainview,{group : group});
    	   })
       },
         
       "click; button.delete": function(event){
    	   var groupId = $(event.target).closest("tr").attr("data-group-id");
    	   var groupDao = brite.dao("Group");
    	   
    	   groupDao.delete(groupId).pipe(function(group) {
    		   brite.display("MainView",$mainview);
    	   })
       }
   }
   
});


