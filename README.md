# Testing_Project

This project is collective effort from Meet Shah(MT2021074) and Shubham Verma(MT2021132).
This project involves Client-side web application testing (bypass testing).
The tools used are Selenium and TestNG for testing. Build automation tool used is Maven. 
The source code repository can be found here: https://github.com/Meet0298/BLOG_MERN.
We have designed test cases for registration, login, publishing post and account deletion.
Meet Shah(MT2021074) has worked on test cases involving login and account deletion.
Shubham Verma(MT2021132) has worked on cases involving registration and publishing post.

The test cases and their respective screenshots of methods are as follows:
Test Case 1: Testing if registration occurs successfully on giving correct inputs.
![image](https://user-images.githubusercontent.com/63227838/204259812-305e5b25-b101-4282-8876-ce9b28e02673.png)
![image](https://user-images.githubusercontent.com/63227838/204259876-153320f6-7922-42d5-a9e9-ddfc0b59aa01.png)
Successfully registered.

Test Case 2: Testing invalid username during registration.
![image](https://user-images.githubusercontent.com/63227838/204260124-3c69310e-528f-4204-b40f-4d3c83c44b87.png)

Test Case 3: Testing invalid email during registration.
![image](https://user-images.githubusercontent.com/63227838/204260374-991be616-ce27-4309-be58-f80fe9f42edf.png)

Test Case 4: Testing invalid password during registration.
![image](https://user-images.githubusercontent.com/63227838/204260656-fbfbe9a2-d432-4030-bd13-5c66974f524b.png)

Test Case 5: Testing to register with the same user name but different email.
![image](https://user-images.githubusercontent.com/63227838/204262004-f4e1b2c1-f574-4a30-a72f-ecc0d2daf0bb.png)
![image](https://user-images.githubusercontent.com/63227838/204262413-1108b8c0-5676-48f4-ab23-f2088a5810fc.png)

Test Case 6: Testing to register with the different user name but same email.
![image](https://user-images.githubusercontent.com/63227838/204262710-413c702e-4ebe-4401-96cf-8fa1df164ac7.png)
![image](https://user-images.githubusercontent.com/63227838/204262870-f7d69b21-2b2e-42fd-89e8-e659b01a622d.png)

Test Case 7: Testing valid login.
![image](https://user-images.githubusercontent.com/63227838/204263174-94111195-ab7f-405b-b830-698b371e540a.png)
![image](https://user-images.githubusercontent.com/63227838/204263438-f5cf958c-855c-406d-9075-2e2d6c96f4bc.png)
![image](https://user-images.githubusercontent.com/63227838/204263704-139c9125-f714-4382-b88f-d5f2f0d78e62.png)

Test Case 8: Testing invalid user name during login.
![image](https://user-images.githubusercontent.com/63227838/204272745-63a1c0c0-0596-42cc-92dc-7daadc411d21.png)
User name used below is different from the one used in registration above.
![image](https://user-images.githubusercontent.com/63227838/204273235-2f2f7600-08d5-4acd-a2ea-78290fa1841a.png)
Login will be unsucessful after entering the details and clicking on the Login button. It will be stuck on the same login page as seen above.

Test Case 9: Testing invalid password during login.
![image](https://user-images.githubusercontent.com/63227838/204266203-613ddb54-3948-4de1-959f-430bc42543cc.png)
Password below is different from the one used in registration above.
![image](https://user-images.githubusercontent.com/63227838/204273545-7966c25d-e8ff-46ab-9d6c-8367890cee5b.png)
Login will be unsuccessful after entering the details and clicking on the Login button. It will be stuck on the same login page as seen above. 

Test Case 10: Testing if publishing post gives error if no title or content is provided
Registering a valid user:
![image](https://user-images.githubusercontent.com/63227838/204274110-a4a6df85-62dd-45f7-b862-6944509f1146.png)
Logging in (same user as above):
![image](https://user-images.githubusercontent.com/63227838/204274550-e938ea00-a7a2-44e7-867e-a636eeeade79.png)
Clicking on WRITE:
![image](https://user-images.githubusercontent.com/63227838/204275393-0a640e39-7cf1-4103-9a65-062f557014b9.png)
Clicking on Publish with blank title and body:
![image](https://user-images.githubusercontent.com/63227838/204276832-12ecbb80-7d27-4e25-8dca-732d8fbc36b4.png)
It will be stuck on the same page as we have not written anything in the blog.

Test Case 11: This is to check account deletion functionality.
Registering a valid user:
![image](https://user-images.githubusercontent.com/63227838/204277278-ab4ef2b2-cbc7-42e0-b085-b61240fb1265.png)
Logging in (same user as above):
![image](https://user-images.githubusercontent.com/63227838/204277461-03596acd-889e-4688-a050-d4789ac24631.png)
Clicking on profile image icon to open account settings:
![image](https://user-images.githubusercontent.com/63227838/204277750-29e16193-7d78-4d18-9bab-4387b1435731.png)
Clicking on Delete Account:
![image](https://user-images.githubusercontent.com/63227838/204278074-1bb73385-ee57-4374-bb70-1f244902e5d0.png)
After clicking on delete account, we will be redirected to the Register page. Clicking on LOGIN:
![image](https://user-images.githubusercontent.com/63227838/204278593-50278f17-3795-4521-b8b2-9002e7a519d2.png)
Entering the details that were used at the time of registration and clicking on Login button:
![image](https://user-images.githubusercontent.com/63227838/204278825-b2eed120-e513-47c4-a53b-24e0ed7dd161.png)
It will be stuck on the same page as the account has been deleted and we cannot login using the same credentials again.
