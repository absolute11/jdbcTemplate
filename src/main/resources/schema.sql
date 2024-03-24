CREATE TABLE CUSTOMERS(
                          id serial PRIMARY KEY,
                          name varchar(50),
                          surname varchar(50),
                          age int,
                          phone_number varchar(20)
);
CREATE TABLE ORDERS (
                        id serial PRIMARY KEY,
                        date TIMESTAMP,
                        customer_id int,
                        product_name varchar(70),
                        amount int,
                        FOREIGN KEY (customer_id) REFERENCES CUSTOMERS(id)
);