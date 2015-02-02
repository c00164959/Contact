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
		var data = {
				contactId : contactId
			};

			return $.ajax({
				type : "POST",
				url : "daoContactGet.do",
				data : data,
				dataType : "json"
			}).pipe(function(val) {
				return val;
			});
	}
	
	ContactDaoHandler.prototype.getGroups = function(contactId) {
		var data = {
				contactId : contactId
			};
			
			return $.ajax({
				type : "POST",
				url : "daoContactGetGroups.do",
				data : data,
				dataType : "json"
			}).pipe(function(val) {
				return val;
			});
	}
	
	
	ContactDaoHandler.prototype.list = function() {
		return $.ajax({
			type : "POST",
			url : "daoContactList.do",
			data : {},
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
			url : "daoContactCreate.do",
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
			url : "daoContactUpdate.do",
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
			url : "daoContactSetGroup.do",
			data : data,
			dataType : "json"
		}).pipe(function(val) {
			return val;
		});
	}

	
	ContactDaoHandler.prototype.delete = function(contactId) {
		return $.ajax({
			type : "POST",
			url : "daoContactDelete.do",
			data : {contactId: contactId},
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
	
	GroupDaoHandler.prototype.get = function(id) {
		var data = {
				obj_id : id
			};

			return $.ajax({
				type : "POST",
				url : "daoGroupGet.do",
				data : data,
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
				url : "daoGroupUpdate.do",
				data : data,
				dataType : "json"
			}).pipe(function(val) {
				return val;
			});
	}
	
	
	GroupDaoHandler.prototype.list = function() {
		return $.ajax({
			type : "POST",
			url : "daoGroupList.do",
			data : {},
			dataType : "json"
		}).pipe(function(val) {
			return val;
		});
	}
	
	GroupDaoHandler.prototype.create = function(groupName) {
		return $.ajax({
			type : "POST",
			url : "daoGroupCreate.do",
			data : {groupName: groupName},
			dataType : "json"
		}).pipe(function(val) {
			return val;
		});
	}
	
	
	GroupDaoHandler.prototype.delete = function(groupId) {
		return $.ajax({
			type : "POST",
			url : "daoGroupDelete.do",
			data : {groupId: groupId},
			dataType : "json"
		}).pipe(function(val) {
			return val;
		});
	}


	brite.GroupDaoHandler = GroupDaoHandler;

})(jQuery);






