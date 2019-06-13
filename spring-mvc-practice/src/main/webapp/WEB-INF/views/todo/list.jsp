<%@include file="/common/header.jspf" %>
	<H1><sTag:message code="list.yourTodos"></sTag:message></H1>
	<div class="container">
		<table class="table table-striped">
			<caption><sTag:message code="list.yourTodos"></sTag:message></caption>

			<thead>
				<tr>
					<th>Description</th>
					<th>Date</th>
					<th>Completed</th>
					<th>Update</th>
					<th>Remove</th>
				</tr>
			</thead>

			<tbody>
				<c:if test="${not empty todoList}">
					<c:forEach items="${todoList}" var="todo">
						<tr>
							<td>${todo.desc}</td>
							<td><fmt:formatDate value="${todo.target}" pattern="dd-MM-YYYY"/></td>
							<td><input type="checkbox" value="${todo.done}" onclick="updateStatus(${todo.id}, this.checked);"> </td>
							<td><a type="button" class="btn btn-warning" 
			href="/todoapp/update.mvc?id=${todo.id}">Update</a></td>
							<td><a type="button" class="btn btn-warning" 
			href="/todoapp/delete.mvc?id=${todo.id}">Delete</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<div>
		<a class="btn btn-primary" href="/todoapp/addNew.mvc">Add</a>
	</div>
	<script>
	function updateStatus(id, status) {
		$.ajax({
			url:"/todoapp/changestatus.mvc",
			method:"GET",
			data:{
					todoId:id,
					todoStatus:status
				},
			success:function(response) {
				console.log(response);
			}
		});
	}
	</script>
<%@include file="/common/footer.jspf" %>