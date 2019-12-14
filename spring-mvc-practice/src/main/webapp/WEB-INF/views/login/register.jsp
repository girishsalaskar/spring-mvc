<%@include file="/common/header.jspf"%>
<sForm:form method="POST" modelAttribute="userModel">
	<div styleclass="container">
		<h1>Register</h1>
		<p>Please fill in this form to create an account.</p>
		<hr />

		<table>
			<tr>
				<td><sForm:label path="userName">
						<span styleclass="b">Full Name</span>
					</sForm:label></td>
				<td><sForm:input path="userName" type="text"
						placeholder="Enter Full Name" required="required"></sForm:input> <sForm:errors
						path="userName"></sForm:errors></td>
			</tr>
			<tr>
				<td><sForm:label path="userId">
						<span styleclass="b">User ID</span>
					</sForm:label></td>
				<td><sForm:input path="userId" type="text"
						placeholder="Enter User ID" required="required"></sForm:input> <sForm:errors
						path="userId"></sForm:errors></td>
			</tr>
			<tr>
				<td><sForm:label path="password">
						<span styleclass="b">Password</span>
					</sForm:label></td>
				<td><sForm:input path="password" type="password"
						required="required"></sForm:input> <sForm:errors path="password"></sForm:errors></td>
			</tr>
		</table>
		<hr />
		<p>
			By creating an account you agree to our <a href="#">Terms &amp;
				Privacy</a>.
		</p>

		<button type="submit">Register</button>
	</div>

	<div>
		<p>
			Already have an account? <a href="/todoapp/login.mvc">Sign in</a>.
		</p>
	</div>
</sForm:form>
<%@include file="/common/footer.jspf"%>
