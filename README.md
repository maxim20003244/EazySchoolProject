# EazySchoolProject
In process building custom validation with Spring, I create a list with weak Passwords , which is contains a simple and easy password which user can not 
be use for create an account.But my spring application cannot find this function and invoke in my spring context . Function name " initializer " in Password Strength Validator class. 
For now need to fix this issues

The issues was fixed , the PasswordValidation.class for default is was perfom by compile time. 
But now is change this just add anotation @Retention(RetentionPolicy.RUNTIME) in my class, and this is mean  that my class will be perfom at compile time

I want to save the details which user is insert in registration form in to dataBase. And I'm creating 3 new table inside to dataBase. ('person', 'addres','roles'). In table 'person' I'm making foreign kei with role_id  from role table , and with 'address' table the same . If in the future I want to identify what is particular role user have i can make this easy.

In the next step I created a new Pojo classes and new Repository . Which is represent a table Person,Address and Roles.I make also a validation in class Person. For fututre i will be  make a simple web page where a new user can be easyli change details. And when user insert deatails i want theyre  in the form to be some validation
