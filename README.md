# REST API PT Budi Jaya

## Description
The REST API for PT Budi Jaya is designed to manage and facilitate access to employee data within the company. This API provides structured access to various important information related to employees, including details about positions, departments, attendance, and salaries. This API is built using **Spring Boot**, ensuring a robust and scalable architecture.

## Tables

The API has five main tables:

1. **Karyawan**
   - Stores basic information about each employee, such as name, employee ID, and date of birth.

2. **Departemen**
   - Records the various departments within the company, including department name and description.

3. **Jabatan**
   - Details the roles and responsibilities of each employee within the company.

4. **Absensi**
   - Tracks employee presence and absences, including date and attendance status.

5. **Gaji**
   - Holds information related to employee compensation, such as base salary and allowances.

## Features

- **CRUD Operations**: Allows users to create, read, update, and delete data in all tables.
- **Dynamic URLs**: All endpoints support dynamic parameters for easy access to specific records.
- **Response Format**: All API responses use JSON format, and appropriate HTTP status codes are returned.

## Endpoints

Here are some available endpoints in this API:

### Karyawan
- `POST /api/karyawan`
  - **Description**: Create a new employee.
  - **Request Body**: `Karyawan` object in JSON format.
  - **Response**: `201 Created` with the created `Karyawan` object.

- `GET /api/karyawan`
  - **Description**: Retrieve a list of all employees or filter by optional parameters.
  - **Response**: `200 OK` with a list of `Karyawan` objects.

- `PUT /api/karyawan/update`
  - **Description**: Update an existing employee's information by ID.
  - **Request Parameter**: `id` (Integer) - ID of the employee.
  - **Request Body**: Updated `Karyawan` object in JSON format.
  - **Response**: `200 OK` with the updated `Karyawan` object, or `404 Not Found` if not found.

- `DELETE /api/karyawan/delete`
  - **Description**: Delete an employee by ID.
  - **Request Parameter**: `id` (Integer) - ID of the employee.
  - **Response**: `204 No Content` on success, or `404 Not Found` if not found.

### Departemen, Jabatan, Absensi, Gaji
- Similar structure as the Karyawan endpoints, with respective request bodies and response types.

## Usage

1. **Requirements**: Ensure you have access to the required server and database, and that Maven is installed on your machine.
2. **Clone the Repository**: 
   ```bash
   git clone <repository-url>
   cd <repository-directory>
3. **Build the Project**: Run the following command to build the project:
   ````bash
   mvn clean install
4. **Run the Application**: Start the application using:
   ````bash
   mvn spring-boot:run
5. **Testing**: Use Postman or a similar tool to test the API endpoints.

## Error Handling
The API includes comprehensive error handling to ensure users receive meaningful feedback. The following HTTP status codes are used:

- **200 OK**: The request was successful.
- **201 Created**: A new resource has been successfully created.
- **204 No Content**: The resource was successfully deleted.
- **400 Bad Request**: The request could not be understood or was missing required parameters. This may occur if the request body is malformed or if required fields are not provided.
- **404 Not Found**: The requested resource could not be found. This occurs when an ID does not exist in the database.
- **500 Internal Server Error**: A generic error message indicating an unexpected condition was encountered on the server.

Each response, when applicable, includes a message in the response body explaining the error in more detail.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information. This allows for reuse within proprietary software, as long as the license is included in the software.
