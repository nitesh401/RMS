# Healthcare Rules Management System

This project implements a Healthcare Rules Management System where rules can be uploaded from an Excel file, and various operations can be performed to retrieve and manage rules.

## Getting Started

### Prerequisites

- Java 11
- Maven
- h2 db

## Usage
Uploading Rule File
## To upload a rule file, use the following endpoint:
POST /upload

Parameters:

file: The Excel file containing rules.
applicableDate: The applicable date for the rules in the format yyyy-MM-dd.

Getting All Rules

## To retrieve all rules, use the following endpoint:
GET /rule

Getting Rules by Doctor Type and Treatment Type
To retrieve rules based on doctor type and treatment type, use the following endpoint:
GET /rules?doctorType=yourDoctorType&treatmentType=yourTreatmentType

Parameters:

doctorType: The type of doctor.
treatmentType: The type of treatment.



