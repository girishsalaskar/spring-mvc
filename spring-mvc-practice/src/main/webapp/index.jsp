<html>
<head>
	<script>
		function displayName(){
			var name = prompt("Please enter your name!", "Girish");
			location.href = "/spring-mvc-practice/login.do?name="+name;
		}
	</script>
</head>
<body>
<h2>Hello World!</h2>
<a href="#" onclick="return displayName();">Display Name</a>
</body>
</html>
