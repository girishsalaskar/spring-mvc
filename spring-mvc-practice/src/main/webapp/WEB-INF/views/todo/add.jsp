<%@include file="/common/header.jspf" %>
	<sForm:form method="POST" modelAttribute="todoApp">
		<sForm:label path="desc">Description</sForm:label> : <sForm:input path="desc" type="text" required="required" />
		<br/><sForm:errors path="desc"></sForm:errors>
		<br/><sForm:label path="target">Target Date</sForm:label> : <sForm:input path="target" type="text" required="required" />
		<br/><sForm:errors path="target"></sForm:errors>
		<br/><button type="submit" >Add</button>
		<sForm:input path="id" type="hidden"/>
	</sForm:form>
<%@include file="/common/footer.jspf" %>