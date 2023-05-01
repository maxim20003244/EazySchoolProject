# EazySchoolProject
In process building custom validation with Spring, I create a list with weak Passwords , which is contains a simple and easy password which user can not 
be use for create an account.But my spring application cannot find this function and invoke in my spring context . Function name " initializer " in Password Strength Validator class. 
For now need to fix this issues

The issues was fixed , the PasswordValidation.class for default is was perfom by compile time. 
But now is change this just add anotation @Retention(RetentionPolicy.RUNTIME) in my class, and this is mean  that my class will be perfom at compile time

I want to save the details which user is insert in registration form in to dataBase. And I'm creating 3 new table inside to dataBase. ('person', 'addres','roles'). In table 'person' I'm making foreign kei with role_id  from role table , and with 'address' table the same . If in the future I want to identify what is particular role user have i can make this easy.

In the next step I created a new Pojo classes and new Repository . Which is represent a table Person,Address and Roles.I make also a validation in class Person. For fututre i will be  make a simple web page where a new user can be easyli change details. And when user insert deatails i want theyre  in the form to be some validation.

With helps of AuthenticationProvider I created a custom Authentication where a new user can to login in ourWebApplication.When someone try to register like a new user they are for default get the role("Student") which I mention for default in table person. And they get default possibilities for in the use of ousr web application. If I want lo logint fiwth admin credentials, forn now I direclty insert in to my DB ("person") a user how have a role admin . And whe admim is login he have a access to dashboard messages and he can see all our messages which is was sent from contact form in page Contact.

I creating allready a authetication , and in application.properties  i'm disable a JPA authentication. Beacuse in that time I save all tha passwords in my DB in hashing code. When i try to login in my application the Spring boot data JPA try to compare a field with password with password in DB .And I'm disable a validation for JPA .Beacause in my DM the password is save in hashing code but in my field it is a String .

Creating new table in DB , table (classes). Make a realtionship between tabel 'person' and 'classes'. Configuration entity classes Person and I created new entity 'EazyClasses' for table 'classes'. 
After that I create new web page Class Details where i can create new classes and save in DB. For now I don't explain any loggic for this.In future need to add new method in AdminController for all this actions.

In Classes web page I can create new classes for our students and this all classes will be save in to DB. We also can wiew and delete this classes. In each classes I can add any number of students witch already have registration in our web app. We have action delete students from classes. All this have integrations with data bases. 
For now I want to create new web page for admin where we can to see how many users we have in our web app.There it well be a small form where it's display a information about our users.
I create alreay a 'User' web page wher we can see how many users is register in our EazyScool app. And also I want to add more functionality , fro example to delete users directly from web page. For now if I want to delete I should to go in to DB and execute a query for this ...Let's do this from web page :)  
