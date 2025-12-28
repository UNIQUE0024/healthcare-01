package com.healthcare.dao;
import com.healthcare.model.Patient;
import com.healthcare.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PatientDAO {
    
    public boolean addPatient(Patient patient) {
        String sql = "INSERT INTO patients (first_name, last_name, email, phone, date_of_birth, " +
                     "gender, address, blood_group, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, patient.getFirstName());
            pstmt.setString(2, patient.getLastName());
            pstmt.setString(3, patient.getEmail());
            pstmt.setString(4, patient.getPhone());
            pstmt.setDate(5, new java.sql.Date(patient.getDateOfBirth().getTime()));
            pstmt.setString(6, patient.getGender());
            pstmt.setString(7, patient.getAddress());
            pstmt.setString(8, patient.getBloodGroup());
            pstmt.setTimestamp(9, new Timestamp(patient.getRegistrationDate().getTime()));
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Patient getPatientById(int id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractPatientFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients ORDER BY registration_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                patients.add(extractPatientFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }
    
    public boolean updatePatient(Patient patient) {
        String sql = "UPDATE patients SET first_name=?, last_name=?, email=?, phone=?, " +
                     "date_of_birth=?, gender=?, address=?, blood_group=? WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, patient.getFirstName());
            pstmt.setString(2, patient.getLastName());
            pstmt.setString(3, patient.getEmail());
            pstmt.setString(4, patient.getPhone());
            pstmt.setDate(5, new java.sql.Date(patient.getDateOfBirth().getTime()));
            pstmt.setString(6, patient.getGender());
            pstmt.setString(7, patient.getAddress());
            pstmt.setString(8, patient.getBloodGroup());
            pstmt.setInt(9, patient.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deletePatient(int id) {
        String sql = "DELETE FROM patients WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
} catch (SQLException e) {
e.printStackTrace();
return false;
}
}
public List<Patient> searchPatients(String searchTerm) {
List<Patient> patients = new ArrayList<>();
String sql = "SELECT * FROM patients WHERE first_name LIKE ? OR last_name LIKE ?";
try (Connection conn = DatabaseConnection.getConnection();
PreparedStatement pstmt = conn.prepareStatement(sql)) {
String searchPattern = "%" + searchTerm + "%";
pstmt.setString(1, searchPattern);
pstmt.setString(2, searchPattern);
ResultSet rs = pstmt.executeQuery();
while (rs.next()) {
patients.add(extractPatientFromResultSet(rs));
}
} catch (SQLException e) {
e.printStackTrace();
}
return patients;
}
private Patient extractPatientFromResultSet(ResultSet rs) throws SQLException {
Patient patient = new Patient();
patient.setId(rs.getInt("id"));
patient.setFirstName(rs.getString("first_name"));
patient.setLastName(rs.getString("last_name"));
patient.setEmail(rs.getString("email"));
patient.setPhone(rs.getString("phone"));
patient.setDateOfBirth(rs.getDate("date_of_birth"));
patient.setGender(rs.getString("gender"));
patient.setAddress(rs.getString("address"));
patient.setBloodGroup(rs.getString("blood_group"));
patient.setRegistrationDate(rs.getTimestamp("registration_date"));
return patient;
}
}
