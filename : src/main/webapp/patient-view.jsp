<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Patient Details</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
<header>
<h1>
🏥
 Patient Details</h1>
</header>
<main>
<c:choose>
<c:when test="${empty patient}">
<div class="alert alert-error">Patient not found!</div>
</c:when>
<c:otherwise>
<div class="patient-details">
<div class="detail-card">
<h3>Personal Information</h3>
<div class="detail-grid">
<div class="detail-item">
<span class="detail-label">Patient ID:</span>
<span class="detail-value">${patient.id}</span>
</div>
<div class="detail-item">
<span class="detail-label">Full Name:</span>
<span class="detail-value">${patient.firstName} ${patient.lastName}</span>
</div>
<div class="detail-item">
<span class="detail-label">Date of Birth:</span>
<span class="detail-value">${patient.dateOfBirth}</span>
</div>
<div class="detail-item">
<span class="detail-label">Gender:</span>
<span class="detail-value">${patient.gender}</span>
</div>
<div class="detail-item">
<span class="detail-label">Blood Group:</span>
<span class="detail-value">${patient.bloodGroup}</span>
</div>
<div class="detail-item">
<span class="detail-label">Email:</span>
<span class="detail-value">${patient.email}</span>
</div>
<div class="detail-item">
<span class="detail-label">Phone:</span>
<span class="detail-value">${patient.phone}</span>
</div>
<div class="detail-item full-width">
<span class="detail-label">Address:</span>
<span class="detail-value">${patient.address}</span>
</div>
</div>
</div>
<div class="button-group">
<a href="${pageContext.request.contextPath}/patients?action=edit&id=${patient.id}" 
class="btn btn-warning">Edit Patient</a>
<a href="${pageContext.request.contextPath}/patients?action=list" 
class="btn btn-secondary">Back to List</a>
</div>
</div>
</c:otherwise>
</c:choose>
</main>
</div>
</body>
</html>
