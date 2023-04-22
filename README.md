# EazySchoolProject
In process building custom validation with Spring I create a list with weak Passwords , which is contains a simple and easy password which user can not 
be use for create an account.But my spring application cannot find this function and invoke in my spring context . Function name " initializer " in Password Strength Validator class. 
For now need to fix this issues

The issues was fixed , the PasswordValidation.class for default is was perfom by compile time. 
But now is change this just add anotation @Retention(RetentionPolicy.RUNTIME) in my class, and this is mean  that my class will be perfom at compile time

