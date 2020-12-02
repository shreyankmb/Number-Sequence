# Number-Sequence
Sequence generation Springboot App

## Instructions:
1. Clone the Repository in you local machine.
2. Choose your ideal IDE(Recommended STS/intellij).
3. Open up the IDE and import the project under `Existing gradle project`.
4. Clean the project.
5. Navigate to NumbersAppication.class and run it as `java Application/Springboot Application`.

**Note:** By default server starts on `8080` port.

## Database(Mysql)
1. Create a database with name `vmware`.
2. Create a table `vm_data` using below script:

**create table vm_data(
   id INT NOT NULL AUTO_INCREMENT,
   uuid VARCHAR(255) NOT NULL,
   result VARCHAR(255) NOT NULL,
   PRIMARY KEY ( id )
);**

## Endpoints:

<img width="405" alt="Screenshot 2020-12-02 at 8 58 57 PM" src="https://user-images.githubusercontent.com/75328788/100893379-513a6700-34e1-11eb-81a6-3e0b9c14f954.png">
