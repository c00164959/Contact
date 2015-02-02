<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Contact</title>


	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-responsive.min.css">
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/brite.js"></script>
    <script type="text/javascript" src="js/brite.DaoHandler.js"></script>
    <script type="text/javascript" src="js/handlebars-v2.0.0.js"></script>

	[@webBundle path="/js/" type="js" /]
	[@webBundle path="/css/" type="css" /]

  </head>

  <body>

    <div id = "mainview" ></div>
		<script type="text/javascript">

			brite.registerDao(new brite.ContactDaoHandler("Contact"));
			brite.registerDao(new brite.GroupDaoHandler("Group"));

			var $mainview = $("body").find("#mainview");
			$(document).ready(function(){
				brite.display("MainView",$mainview);
			});
		</script>


  </body>
</html>