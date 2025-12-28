package com.healthcare.servlet;
import com.healthcare.dao.PatientDAO;
import com.healthcare.model.Patient;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@WebServlet("/patients")
public class PatientServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
private PatientDAO patientDAO;
@Override
public void init() throws ServletException {
patientDAO = new PatientDAO();
}
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
String action = request.getParameter("action");
if (action == null) action = "list";
switch (action) {
case "list":
listPatients(request, response);
break;
case "view":
viewPatient(request, response);
break;
case "add":
showAddForm(request, response);
break;
case "edit":
showEditForm(request, response);
break;
case "delete":
                deletePatient(request, response);
                break;
            case "search":
                searchPatients(request, response);
                break;
            default:
                listPatients(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            addPatient(request, response);
        } else if ("update".equals(action)) {
            updatePatient(request, response);
        }
    }
    
    private void listPatients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Patient> patients = patientDAO.getAllPatients();
        request.setAttribute("patients", patients);
        request.getRequestDispatcher("patient-list.jsp").forward(request, response);
    }
    
    private void viewPatient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Patient patient = patientDAO.getPatientById(id);
        request.setAttribute("patient", patient);
        request.getRequestDispatcher("patient-view.jsp").forward(request, response);
    }
    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("patient-form.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Patient patient = patientDAO.getPatientById(id);
        request.setAttribute("patient", patient);
        request.getRequestDispatcher("patient-form.jsp").forward(request, response);
    }
    
    private void addPatient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Patient patient = extractPatientFromRequest(request);
            patient.setRegistrationDate(new Date());
            
            boolean success = patientDAO.addPatient(patient);
            if (success) {
                response.sendRedirect(request.getContextPath() + "/patients?action=list");
            } else {
                request.setAttribute("error", "Failed to add patient!");
                request.getRequestDispatcher("patient-form.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("patient-form.jsp").forward(request, response);
        }
    }
    
    private void updatePatient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Patient patient = extractPatientFromRequest(request);
            int id = Integer.parseInt(request.getParameter("id"));
            patient.setId(id);
            
            boolean success = patientDAO.updatePatient(patient);
            if (success) {
                response.sendRedirect(request.getContextPath() + "/patients?action=list");
            } else {
                request.setAttribute("error", "Failed to update patient!");
                request.getRequestDispatcher("patient-form.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("patient-form.jsp").forward(request, response);
        }
    }
    
    private void deletePatient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        patientDAO.deletePatient(id);
}
response.sendRedirect(request.getContextPath() + "/patients?action=list");
private void searchPatients(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
String searchTerm = request.getParameter("searchTerm");
List<Patient> patients = patientDAO.searchPatients(searchTerm);
request.setAttribute("patients", patients);
request.setAttribute("searchTerm", searchTerm);
request.getRequestDispatcher("patient-list.jsp").forward(request, response);
}
private Patient extractPatientFromRequest(HttpServletRequest request) throws ParseException {
Patient patient = new Patient();
patient.setFirstName(request.getParameter("firstName"));
patient.setLastName(request.getParameter("lastName"));
patient.setEmail(request.getParameter("email"));
patient.setPhone(request.getParameter("phone"));
String dobString = request.getParameter("dateOfBirth");
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date dob = dateFormat.parse(dobString);
patient.setDateOfBirth(dob);
patient.setGender(request.getParameter("gender"));
patient.setAddress(request.getParameter("address"));
patient.setBloodGroup(request.getParameter("bloodGroup"));
return patient;
}
}
