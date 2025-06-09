CREATE TABLE driver(
    autono varchar(50),
    license_no VARCHAR(100),
    name varchar(50),
    id varchar(50) PRIMARY KEY,
    email varchar(50),
    gender varchar(10),
    password varchar(10),
    confirmpassword varchar(10),
    phone long

);

--Creation of table is necessary when u r creating data.sql
CREATE TABLE seatbooking(
    booking_id varchar(10) PRIMARY KEY,
    no_of_seats varchar(50),
    date varchar(50),
    time varchar(50),
    autono varchar(50),
    driver varchar(50),
    id varchar(50),
    status varchar(15),
    foreign key(driver) references driver(id)

);


