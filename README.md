# CourseScheduler
**A Course Scheduling application for a College**

The application will enable two types of Users to perform their necessary functions to schedule courses by semester. The Admin User will perform multiple functions to set up the database so that Students may schedule courses. 

* This application has a GUI interface and is a database driven application. 

* The database used is Apache Derby. 

* This application and the database uses good Object-Oriented Design and Programming. 

* The database access is in the classes that correlate with the database tables.

* All of the updates to the database are done using SQL statements.

**This project implements the following Admin functions:**

**Add Semester**
* A semester is added to the database. The semester is identified by one name.

**Add Course**
* A new course is added to the database. The course is identified by the code for the 
course, and the description of the course.

**Add Class**
* A new class is added to the database. The class is identified by the semester the course 
is offered, the code for the course, and the maximum number of students the course 
will contain that semester.

**Add Student**
* A student is added to the database. The student is identified by a studentID, the 
student’s first name, and the student’s last name.

**Display Class List of Students**
* Display all the students who are scheduled or waitlisted for the specified class for the 
current semester. The scheduled students should be displayed first and then the 
waitlisted students in waitlist order.

**Drop Student**
* Remove the student from the list of students and remove them from any classes
they are scheduled for in all semesters. For each course they were scheduled in, 
schedule the first student on the waitlist into the course. Display all of the changes that 
are made because of dropping the student.

**Drop Class**
* Remove the specified course from the current semester. Remove all students 
scheduled and waitlisted for the class from the Schedule Table. Display all of the 
changes that are made because of dropping the class.

**The project also implements the following Student functions:**

**Schedule Class**
* The student will be scheduled in the class for the specified semester if there are 
seats available. If there are no seats available, the student will be put in a wait status 
for that Class. The waiting status must be maintained in the order the students tried to 
schedule the class. It will display whether the student gets scheduled for the class or is 
waitlisted.

**Display Schedule**
* The Display Schedule function will display the current schedule of classes for a 
specified student for the current semester. It will display the course code and the status 
of the student in the class, whether scheduled or waitlisted.

**Display Classes**
* The Display Classes function will display a complete list of classes for the current 
semester showing the course code, description, and number of seats.

**Drop Class**
* Remove the specified class from the student’s schedule for the current semester. The 
student may be scheduled for the class or on the waitlist. Schedule the first student 
waiting for the class into the class. Display all of the changes that are made because of 
dropping the class.
