



create table Category(
Category_id int primary key, 
Category_name varchar(15) NOT NULL,
Category_image varchar(250) NOT NULL
);

INSERT into Category VALUES(1,'Fiction','cat.jpg');

create table Books(
Book_id int primary key,
fk_Category_id int NOT NULL,
Book_ISBN int NOT NULL,
Book_title varchar(30) NOT NULL,
Book_author varchar(20) NOT NULL,
Book_price decimal(4,2) NOT NULL,
Book_publisher varchar(20) NOT NULL,
Book_rating int,
Book_image varchar(250),
foreign key(fk_Category_id) references Category(Category_id)
);
INSERT INTO Books
VALUES (1, 1, 1234567890, 'Book Title', 'Book Author', 19.99, 'Book Publisher', 4, 'book_image.jpg');
INSERT INTO Books
VALUES (2, 1, 1234567890, 'XYZ', 'Book Author', 24.99, 'ABC', 3, 'book_image1.jpg');
INSERT INTO Books
VALUES (3, 1, 1234567890, 'PQR', 'AAA', 24.99, 'CCC', 5, 'book_image3.jpg');




Insert Into Customer values (1,'admin@gmail.com','admin','admin','F','1111111111','1','1');

CREATE TABLE Customer (
  Customer_id INT AUTO_INCREMENT PRIMARY KEY,
  Customer_emailid VARCHAR(40) NOT NULL,
  Customer_password VARCHAR(40) NOT NULL,
  Customer_name VARCHAR(20) NOT NULL,
  Customer_gender VARCHAR(10) NOT NULL,
  Customer_mobile_no VARCHAR(10) NOT NULL,
  User_type VARCHAR(10) DEFAULT '0' NOT NULL,
  Customer_address VARCHAR(300) NOT NULL
);


CREATE SEQUENCE customer_seq START WITH 1 INCREMENT BY 1;

CREATE TRIGGER customer_trigger
BEFORE INSERT ON Customer
FOR EACH ROW
BEGIN
    SELECT customer_seq.NEXTVAL INTO :new.customer_id FROM dual;
END;
/

-- Drop the trigger
DROP TRIGGER customer_trigger;

-- Drop the sequence
DROP SEQUENCE customer_seq;

-- Drop the table
DROP TABLE Customer1;




create table Cart(
Cart_id int primary key,
 fk_Customer_id int NOT NULL , 
 fk_Book_id int NOT NULL, 
 Cart_quantity int DEFAULT 1 NOT NULL,
 foreign key(fk_Customer_id) references Customer(Customer_id),
 foreign key(fk_Book_id) references Books(Book_id)
);


create table Orders(
Order_id int primary key,
 fk_Customer_id  int NOT NULL,
 Order_date  date NOT NULL,
 order_status varchar(15) default 'Placed' NOT NULL,
 Order_total  decimal(4,2) NOT NULL,
 foreign key(fk_Customer_id) references Customer(Customer_id)
 );

create table OrderDetails(
Orderdetail_id int primary key,
 fk_Order_id  int NOT NULL, 
 fk_Book_id  int NOT NULL, 
 Orderdetails_quantity  int NOT NULL,
 foreign key(fk_Order_id) references Orders(Order_id),
 foreign key(fk_Book_id) references Books(Book_id)
);

