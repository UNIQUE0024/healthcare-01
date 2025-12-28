CREATE DATABASE IF NOT EXISTS healthcare_db;
USE healthcare_db;
DROP TABLE IF EXISTS patients;
CREATE TABLE patients (
id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(100) NOT NULL UNIQUE,
phone VARCHAR(20) NOT NULL,
date_of_birth DATE NOT NULL,
gender ENUM('Male', 'Female', 'Other') NOT NULL,
address TEXT NOT NULL,
blood_group ENUM('A+', 'A-', 'B+', 'B-', 'O+', 'O-', 'AB+', 'AB-') NOT NULL,
registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
INDEX idx_name (first_name, last_name),
INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
INSERT INTO patients (first_name, last_name, email, phone, date_of_birth, gender, address, blood_group) VALUES
('John', 'Doe', 'john.doe@email.com', '+1-555-0101', '1985-06-15', 'Male', '123 Main St, New York, NY 10001', 'O+'),
('Jane', 'Smith', 'jane.smith@email.com', '+1-555-0102', '1990-03-22', 'Female', '456 Oak Ave, Los Angeles, CA 90001', 
'A+'),
('Michael', 'Johnson', 'michael.j@email.com', '+1-555-0103', '1978-11-08', 'Male', '789 Pine Rd, Chicago, IL 60601', 'B+'),
('Emily', 'Williams', 'emily.w@email.com', '+1-555-0104', '1995-09-14', 'Female', '321 Elm St, Houston, TX 77001', 'AB+'),
('David', 'Brown', 'david.brown@email.com', '+1-555-0105', '1982-01-30', 'Male', '654 Maple Dr, Phoenix, AZ 85001', 'O');
