<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${empty patient ? 'Add' : 'Edit'} Patient</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
<header>
<h1>
🏥
 Patient Management</h1>
</header>
<main>
<h2>${empty patient ? 'Add New' : 'Edit'} Patient</h2>
            
            <div class="form-container">
                <form action="${pageContext.request.contextPath}/patients" method="post" class="patient-form">
                    <input type="hidden" name="action" value="${empty patient ? 'add' : 'update'}">
                    <c:if test="${not empty patient}">
                        <input type="hidden" name="id" value="${patient.id}">
                    </c:if>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="firstName">First Name *</label>
                            <input type="text" id="firstName" name="firstName" 
                                   value="${patient.firstName}" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="lastName">Last Name *</label>
                            <input type="text" id="lastName" name="lastName" 
                                   value="${patient.lastName}" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="email">Email *</label>
                            <input type="email" id="email" name="email" 
                                   value="${patient.email}" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="phone">Phone Number *</label>
                            <input type="tel" id="phone" name="phone" 
                                   value="${patient.phone}" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="dateOfBirth">Date of Birth *</label>
                            <input type="date" id="dateOfBirth" name="dateOfBirth" 
                                   value="${patient.dateOfBirth}" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="gender">Gender *</label>
                            <select id="gender" name="gender" required>
                                <option value="">Select Gender</option>
<option value="Male" ${patient.gender == 'Male' ? 'selected' : ''}>Male</option>
<option value="Female" ${patient.gender == 'Female' ? 'selected' : ''}>Female</option>
<option value="Other" ${patient.gender == 'Other' ? 'selected' : ''}>Other</option>
</select>
</div>
</div>
<div class="form-row">
<div class="form-group">
<label for="bloodGroup">Blood Group *</label>
<select id="bloodGroup" name="bloodGroup" required>
<option value="">Select Blood Group</option>
<option value="A+" ${patient.bloodGroup == 'A+' ? 'selected' : ''}>A+</option>
<option value="A-" ${patient.bloodGroup == 'A-' ? 'selected' : ''}>A-</option>
<option value="B+" ${patient.bloodGroup == 'B+' ? 'selected' : ''}>B+</option>
<option value="B-" ${patient.bloodGroup == 'B-' ? 'selected' : ''}>B-</option>
<option value="O+" ${patient.bloodGroup == 'O+' ? 'selected' : ''}>O+</option>
<option value="O-" ${patient.bloodGroup == 'O-' ? 'selected' : ''}>O-</option>
<option value="AB+" ${patient.bloodGroup == 'AB+' ? 'selected' : ''}>AB+</option>
<option value="AB-" ${patient.bloodGroup == 'AB-' ? 'selected' : ''}>AB-</option>
</select>
</div>
</div>
<div class="form-group">
<label for="address">Address *</label>
<textarea id="address" name="address" rows="3" required>${patient.address}</textarea>
</div>
<div class="form-actions">
<button type="submit" class="btn btn-success">
${empty patient ? 'Add Patient' : 'Update Patient'}
</button>
<a href="${pageContext.request.contextPath}/patients?action=list" class="btn btn-secondary">
Cancel
</a>
</div>
</form>
</div>
</main>
</div>
</body>
</html>
