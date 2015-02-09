// --------- ContactDaoHandler --------- //
(function($) {

	var defaultOpts = {
		idName : "id"
	}

	function ContactDaoHandler(entityType,seed, opts) {
		init.call(this,entityType,seed, opts);
	}


	function init(entityType,seed,opts) {
		this._entityType = entityType;
		this._opts = $.extend({}, defaultOpts, opts);
		this._idName = this._opts.idName;
	}


	// --------- DAO Info Methods --------- //
	ContactDaoHandler.prototype.entityType = function() {
		return this._entityType;
	}	
	
	ContactDaoHandler.prototype.get = function(contactId) {
			return $.ajax({
				type : "GET",
				url : "/api/contact-" + contactId,
				//data : data,
				dataType : "json"
			}).pipe(function(val) {
				return val;
			});
	}
	
	ContactDaoHandler.prototype.getGroups = function(contactId) {

			return $.ajax({
				type : "GET",
                url : "/api/contact-getGroups-" + contactId,
				dataType : "json"
			}).pipe(function(val) {
				return val;
			});
	}
	
	
	ContactDaoHandler.prototype.list = function() {
		return $.ajax({
			type : "GET",
			url : "/api/contacts",
			dataType : "json"
		}).pipe(function(val) {
			return val;
		});
	}
	
	ContactDaoHandler.prototype.create = function(firstName, lastName) {
		var data = {
				firstName : firstName,
				lastName : lastName
			};
		return $.ajax({
			type : "POST",
			url : "/api/create-contact",
			data : data,
			dataType : "json"
		}).pipe(function(val) {
			return val;
		});
	}
	
	
	ContactDaoHandler.prototype.update = function(contactId, firstName, lastName) {
		var data = {
				contactId : contactId,
				firstName : firstName,
				lastName : lastName
			};
		return $.ajax({
			type : "POST",
			url : "/api/update-contact",
			data : data,
			dataType : "json"
		}).pipe(function(val) {
			return val;
		});
	}
	
	ContactDaoHandler.prototype.setGroup = function(contactId, groupIds) {
		groupIds = foramtIds(groupIds);
		var data = {
				contactId : contactId,
				groupIds : groupIds
			};
		return $.ajax({
			type : "POST",
			url : "/api/contact-setGroups",
			data : data,
			dataType : "json"
		}).pipe(function(val) {
			return val;
		});
	}

	
	ContactDaoHandler.prototype.deletec = function(contactId) {
		return $.ajax({
			type : "DELETE",
			url : "/api/contact-" + contactId,
			dataType : "json"
		}).pipe(function(val) {
			return val;
		});
	}
	
	function foramtIds(groupIds) {
		var result;
		for(var i=0 ; i < groupIds.length; i++) {
			if(i == 0) {
				result = groupIds[i].toString()
			} else {
				result = result + "|" +groupIds[i].toString();
			}
		}
		return result;
	}
	

	brite.ContactDaoHandler = ContactDaoHandler;

})(jQuery);


//--------- GroupDaoHandler --------- //
(function($) {

	var defaultOpts = {
		idName : "id"
	}

	function GroupDaoHandler(entityType,seed, opts) {
		init.call(this,entityType,seed, opts);
	}


	function init(entityType,seed,opts) {
		this._entityType = entityType;
		this._opts = $.extend({}, defaultOpts, opts);
		this._idName = this._opts.idName;
	}


	// --------- DAO Info Methods --------- //
	GroupDaoHandler.prototype.entityType = function() {
		return this._entityType;
	}	
	
	GroupDaoHandler.prototype.get = function(groupId) {
			return $.ajax({
				type : "GET",
                url : "/api/group-" + groupId,
				dataType : "json"
			}).pipe(function(val) {
				return val;
			});
	}


    GroupDaoHandler.prototype.list = function() {
        return $.ajax("/api/groups",{
            type : "GET",
            dataType : "json"
        }).pipe(function(val) {
            return val;
        });
    }
	
	GroupDaoHandler.prototype.update = function(groupId, groupName) {
		var data = {
				groupId : groupId,
				groupName : groupName
			};

			return $.ajax({
				type : "POST",
				url : "/api/update-group",
				data : data,
				dataType : "json"
			}).pipe(function(val) {
				return val;
			});
	}


	
	GroupDaoHandler.prototype.create = function(groupName) {
		return $.ajax({
			type : "POST",
			url : "/api/create-group",
			data : {groupName: groupName},
			dataType : "json"
		}).pipe(function(val) {
			return val;
		});
	}
	
	
	GroupDaoHandler.prototype.deleteg = function(groupId) {
		return $.ajax({
			type : "DELETE",
			url : "/api/group-" + groupId,
			dataType : "json"
		}).pipe(function(val) {
			return val;
		});
	}


	brite.GroupDaoHandler = GroupDaoHandler;

})(jQuery);






