# OnlineClassbook

Platform for the interaction with a University's csvFiles. It started out as a class book, but it might contain 
this functionality as well, sometime in the future.

Classes available:
* *Faculty* - singleton; has the role of a service class
    * :: has interval class *Specialization*
* *Menu* - menu class for organising the output data
* *Person* - abstract class; represents a person
    * *Student*
    * *Professor*
* *Group* - represents a group of students (a "classroom")
* *Series* - represents a group of groups
* *Curriculum* - it's a class for organizing Subjects (implementation still ongoing)
* *Subject*
    * :: has internal class: *StudyClass*
    * *Optional Subject* - subject that is not mandatory, has a limited capacity and its grade can contribute
      to the GPA or not

## Action permitted as of this moment:
Online Classbook:
The startup menu asks in which context you would like to run the application (CSV or DB - with MySQL), with DB by default
### The CSV menu can be run without additional files (evertything is in the project).
1. Add a new item in the database
   1. Add a new student
   2. Add a new subject
   3. Add a new optional subject
   4. Add a new professor
   5. Add a new group
   6. Add a new series
   7. Add a new curriculum
2. Add some information for an existing item
   1. Add a subject to a student
   2. Set a professor to a study class of a subject
   3. Set a group to a student
   4. Set a series to a group
   5. Add a subject to a curriculum
   6. Add an optional subject to a curriculum
   7. Set a curriculum to a student
3. Remove some information from an existing item
   1. Remove a subject from a student
   2. Remove a professor from a study class of a subject
   3. Remove a student from a group
   4. Remove a group from a series
   5. Remove a subject from a curriculum
   6. Remove an optional subject from a curriculum
4. Show specific information of an item
   1. Print all groups of a series in a sorted matter (by name)
   2. Print all students of a group in a sorted matter (by firstName and lastName)
   3. Print all subjects of a student
   4. Print all subjects of a curriculum
5. Remove an item from the database
   1. Remove a student
   2. Remove a subject
   3. Remove an optional subject
   4. Remove a professor
   5. Remove a group
   6. Remove a series
   7. Remove a curriculum
6. Show all elements of a category
   1. Print all students
   2. Print all subjects
   3. Print all optional subjects
   4. Print all professors
   5. Print all groups
   6. Print all series
   7. Print all curricula
9. Rollback to backup database

### The DB menu needs a local database with MySQL.

1. Series menu
   1. Insert a series to DB
   2. Fetch a series from DB
   3. Update a series name
   4. Delete a series
   5. Print all series
   6. Print all groups of series by id
2. Group menu
   1. Insert a group to DB
   2. Fetch a group from DB
   3. Update the name of a group
   4. Set the group to a series
   5. Delete a group
   6. Print all groups
   7. Print students of a group by ID
3. Person menu
   1. Fetch a string of a person from DB
   2. Update the name of a person
   3. Delete a person
   4. Print all persons
4. Student menu
   1. Insert a student to DB
   2. Fetch a student from DB
   3. Update the year and semester of a student
   4. Delete a student
   5. Print all students
   6. Set a student to a group by ID
