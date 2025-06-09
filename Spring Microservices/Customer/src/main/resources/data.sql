INSERT INTO customer(id,name,phone,email,gender,password,confirmpassword)
VALUES
    ('SA00011','Akil',9901789444,'akil@gmail.com','Male','abc','abc'),
    ('JAN001','Jani',9901789444,'jani@gmail.com','Female','123456','123456');;

INSERT INTO payment(payment_id,upiid,amount,date,custid,phone)
VALUES
    ('020f1bc0-d70a-432a-82ca-f714cd20dfae','sda@upi',1000,'2024-12-03','SA00011','9087654321'),
    ('220f1bc0-d70a-432a-82ca-f714cd20dfae','pay@upi',5000,'2024-12-03','SA00011','9087654321');

INSERT INTO users (email, password, role, is_login, userid) VALUES
('jani@gmail.com','123456','USER',false,'JAN001'),
('ram@gmail.com','123456','DRIVER',true,'ATRAM01'),
('vivek@gmail.com','123456','DRIVER',true,'ATVIK01');