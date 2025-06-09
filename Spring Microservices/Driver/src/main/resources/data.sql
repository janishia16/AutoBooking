INSERT INTO driver(autono,license_no,name,id,phone,gender,email,password,confirmpassword)
VALUES
    ('KA10S7777', 'KA00 20110012345', 'Ram', 'ATRAM01', 7890654331,'Male', 'ram@gmail.com','123456','123456'),
    ('TN10ST76362','TN00 20110019945','Vivek','ATVIK01',9087654321,'Male','vivek@gmail.com','123456','123456');


INSERT INTO seatbooking(booking_id,no_of_seats,date,time,autono,driver,id,status)
VALUES
    ('BKWE00','2','2024-12-03','10:30','KA10ST7777','ATRAM01','JAN001','Active'),
    ('BKQR87','4','2024-12-03','10:30','KA10ST7777','ATRAM01','JAN001','Completed');