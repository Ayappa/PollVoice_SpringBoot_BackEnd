# PollVoice_SpringBoot_BackEnd
#REST API- Spring JPA ,Security , Jwt ,Authorization 
##Api for performing simple crud operation using spring boot technologies  which incubates Dependency Injection, IOC ,Application Context and Bean Factory.
JPA for performing sqlQuries and creating tables within JDBC and spring Seurity for cross origin resource sharing . JWT for Authorizing and authenticating users with valid token.
Spring filters for intercepting request for checking valid token.
Spring annotation for for IOC and dependency Injection.


#Register User - with email verification
## accessing the link registers the user
![registerUser](https://user-images.githubusercontent.com/22851920/83957868-73f10900-a839-11ea-95df-03df66c32fc6.PNG)


#Login user -with input validation and shows appropriate messages user is invalid
![loginUser](https://user-images.githubusercontent.com/22851920/83957866-73f10900-a839-11ea-973f-b77a8d02cb8d.PNG)


#Update Password 
##update password link sent to your email for security reasons
![updatePassword](https://user-images.githubusercontent.com/22851920/83957869-73f10900-a839-11ea-9a19-44cb7262b61c.PNG)
![updatePasswordMailSent](https://user-images.githubusercontent.com/22851920/83957870-73f10900-a839-11ea-8940-f6fd66eea524.PNG)


#Get All polls
##with "bearer token" in header
![getAllPolls](https://user-images.githubusercontent.com/22851920/83957864-73587280-a839-11ea-9272-326d72cab943.PNG)

#Get All Created Poll by you
##with "bearer token" in header
![getAllCreatedQuestion](https://user-images.githubusercontent.com/22851920/83957863-73587280-a839-11ea-846a-e5b9ab7bbae0.PNG)


#Get One Poll
##with "bearer token" in header and poll Id encoded in url
![getOnePoll](https://user-images.githubusercontent.com/22851920/83957865-73587280-a839-11ea-8b5b-565c827d036f.PNG)


#Post new Poll
##with "bearer token" in header
![postPoll](https://user-images.githubusercontent.com/22851920/83957867-73f10900-a839-11ea-98e6-972ac9a0ddc7.PNG)


##Delete the created Poll
##with "bearer token" in header
![deletePollAndValidation](https://user-images.githubusercontent.com/22851920/83957862-73587280-a839-11ea-8078-fd85d42243bb.PNG)


#Update your poll Response
##with "bearer token" in header
![updatePollResponse](https://user-images.githubusercontent.com/22851920/83957860-72bfdc00-a839-11ea-8288-1c03e0ce9d83.PNG)

#update randomly generated key
##with "bearer token" in header , can be used for who can access the poll in combination with your poll id
![updateSecret](https://user-images.githubusercontent.com/22851920/83957861-73587280-a839-11ea-8dd6-00994075cb4d.PNG)

####All request are validated 

