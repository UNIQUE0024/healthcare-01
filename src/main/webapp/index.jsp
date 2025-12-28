<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Healthcare Management System</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
<header>
<h1>
🏥
 Healthcare Management System</h1>
<p>Manage patient records efficiently</p>
</header>
<main>
<div class="welcome-section">
<h2>Welcome to Healthcare Portal</h2>
<p>This system helps healthcare providers manage patient information securely and efficiently.</p>
<div class="button-group">
<a href="${pageContext.request.contextPath}/patients?action=list" class="btn btn-primary">
View All Patients
</a>
<a href="${pageContext.request.contextPath}/patients?action=add" class="btn btn-success">
Add New Patient
</a>
</div>
</div>
</main>
<footer>
<p>&copy; 2025 Healthcare Management System. All rights reserved.</p>
</footer>
</div>
</body>
</html>
