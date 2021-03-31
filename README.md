"# OnlineClassbook" 

Platform for the interaction with a University's database. It started out as a class book, but it might contain 
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

Action permitted as of this moment:
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
3. Show specific information of an item
    1. Print all groups of a series in a sorted matter
    2. Print all students of a group in a sorted matter
    3. Print all subjects of a student
4. Remove an item from the database
   1. Remove a student
   2. Remove a subject
   3. Remove an optional subject
   4. Remove a professor
   5. Remove a group
   6. Remove a series
   7. Remove a curriculum
5. Show all elements of a category
    1. Print all students
    2. Print all subjects
    3. Print all optional subjects
    4. Print all professors
    5. Print all groups
    6. Print all series
    7. Print all curricula