<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
	<form action="/action_page.php">
		<div class="container">
			<h1>Register</h1>
			<p>Please fill in this form to create an account.</p>
			<hr />

			<table>
				<tr>
					<td><label for="name"><b>Full Name</b></label></td>
					<td><input type="text" placeholder="Enter Full Name"
						name="name" required></td>
				</tr>
				<tr>
					<td><label for="userid"><b>User ID</b></label></td>
					<td><input type="text" placeholder="User ID" name="userid"
						required></td>
				</tr>
				<tr>
					<td><label for="password"><b>Password</b></label></td>
					<td><input type="password" placeholder="Password"
						name="password" required></td>
				</tr>
			</table>
			<hr />
			<p>
				By creating an account you agree to our <a href="/todoapp/login.mvc">Terms &amp; Privacy</a>.
			</p>

			<button type="submit">Register</button>
		</div>

		<div>
			<p>
				Already have an account? <a
					href="/todoapp/login.mvc">Sign in</a>.
			</p>
		</div>
	</form>
</body>
</html>