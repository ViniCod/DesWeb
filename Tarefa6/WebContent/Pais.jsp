<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Pais" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Cadastro de Países</title>
		<link href="style.css" rel="stylesheet">
	</head>
	<body>
		<%Pais pais = (Pais)request.getAttribute("pais"); %>
		Id: <%=pais.getId() %> <br>
		Nome: <%=pais.getNome() %> <br>
		População: <%=pais.getPopulacao() %> <br>
		Area: <%=pais.getArea() %> <br>
	</body>
</html>