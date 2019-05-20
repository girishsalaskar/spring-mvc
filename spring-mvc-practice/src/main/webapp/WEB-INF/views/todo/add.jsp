<%@ taglib uri="http://www.springframework.org/tags/form" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${userName} - Add new Todo</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<c:form method="POST" action="/spring-mvc-practice/todoapp/addNew.mvc" modelAttribute="todo">
		<c:label path="desc">Description</c:label> : <c:input path="desc" type="text" required="required" />
		<br/><c:errors path="desc"></c:errors>
		<br/><button type="submit" >Add</button>
	</c:form>
	<form action="/spring-mvc-practice/todoapp/addNew.mvc" method="POST">
        Description : <input name="desc" type="text" /> <input type="submit" value="add" />
    </form>
</body>
</html>