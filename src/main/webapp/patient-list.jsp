<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Patient List - Healthcare System</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
<header>
<h1>
🏥
 Patient Management</h1>
<nav>
                <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
                <a href="${pageContext.request.contextPath}/patients?action=list">All Patients</a>
                <a href="${pageContext.request.contextPath}/patients?action=add">Add Patient</a>
            </nav>
        </header>
        
        <main>
            <h2>Patient List</h2>
            
            <div class="search-section">
                <form action="${pageContext.request.contextPath}/patients" method="get">
                    <input type="hidden" name="action" value="search">
                    <input type="text" name="searchTerm" placeholder="Search by name..." 
                           value="${searchTerm}" class="search-input">
                    <button type="submit" class="btn btn-primary">
🔍
 Search</button>
                </form>
            </div>
            
            <div class="table-container">
                <table class="patient-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Gender</th>
                            <th>Blood Group</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty patients}">
                                <tr>
                                    <td colspan="7" class="no-data">No patients found</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="patient" items="${patients}">
                                    <tr>
                                        <td>${patient.id}</td>
                                        <td>${patient.firstName} ${patient.lastName}</td>
                                        <td>${patient.email}</td>
                                        <td>${patient.phone}</td>
                                        <td>${patient.gender}</td>
                                        <td>${patient.bloodGroup}</td>
<td class="action-buttons">
<a href="${pageContext.request.contextPath}/patients?action=view&id=${patient.id}" 
class="btn btn-sm btn-info">View</a>
<a href="${pageContext.request.contextPath}/patients?action=edit&id=${patient.id}" 
class="btn btn-sm btn-warning">Edit</a>
<a href="${pageContext.request.contextPath}/patients?action=delete&id=${patient.id}" 
class="btn btn-sm btn-danger" 
onclick="return confirm('Are you sure?')">Delete</a>
</td>
</tr>
</c:forEach>
</c:otherwise>
</c:choose>
</tbody>
</table>
</div>
</main>
<footer>
<p>&copy; 2025 Healthcare Management System</p>
</footer>
</div>
</body>
</html>
