CREATE TABLE customer(
    id varchar(50) primary key,
    name varchar(50),
    email varchar(50),
    phone long,
    gender varchar(50),
    password varchar(50),
    confirmpassword varchar(50)
);

--Creation of table is necessary when u r creating data.sql
CREATE TABLE payment(
    payment_id varchar(50) primary key,
    upiid varchar(50),
    amount int,
    phone varchar(20),
    date varchar(50),
    custid varchar(50)
);

CREATE TABLE users (
    email VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    is_login BOOLEAN NOT NULL,
    userid VARCHAR(50) UNIQUE
);



