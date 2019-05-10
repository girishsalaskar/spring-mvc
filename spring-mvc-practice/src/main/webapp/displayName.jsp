<html>
<head>
<meta charset="UTF-8">
<title>Displaying name</title>
</head>
<body>
	<h1>Hi there, my name is ${name}</h1>
	<%
		out.println("Name : "+request.getParameter("name"));
	%>
</body>
</html>