<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Todos for ${name}</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
    rel="stylesheet">
</head>
<body>
	<H1>Your Todos</H1>
	<div class="container">
		<table class="table table-striped">
			<caption>Your Todos are</caption>

			<thead>
				<tr>
					<th>Description</th>
					<th>Date</th>
					<th>Completed</th>
					<th>Remove</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${todoList}" var="todo">
					<tr>
						<td>${todo.desc}</td>
						<td>${todo.target}</td>
						<td>${todo.done}</td>
						<td><a type="button" class="btn btn-warning" 
		href="/spring-mvc-practice/todoapp/delete.mvc?id=${todo.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<a class="button" href="/spring-mvc-practice/todoapp/addNew.mvc">Add</a>
	</div>
	
	<script src="/webjars/jquery/1.9.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>