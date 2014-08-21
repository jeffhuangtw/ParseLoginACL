Parse issue demo code:

Bug summary: user must login each time, when APP is force closed.

Bug trigger info:
1. must enable LocalDatastore;
2. Parse user must setACL without public ACL read/write, (only read/write with userself)

ex: There are 3 user in this APP

user "lFtyGuY7vh"   ACL: {"lFtyGuY7vh":{"read":true,"write":true}}
user "8NS6kPwb0A"   ACL: {"*":{"read":true},"8NS6kPwb0A":{"read":true,"write":true}}
user "jha8JJLhQ6"             ACL:(undefined)

user "lFtyGuY7vh" must re-login every time after App is closed.
user "8NS6kPwb0A" and "8NS6kPwb0A" can keep the login credential and don't need to re-login. 

