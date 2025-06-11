INSERT INTO stores (store_name, phone, email, street, city, zip_code) VALUES
('Main Warehouse', '123-456-7890', 'main@store.com', '123 Market St', 'New York', '10001'),
('Downtown Branch', '987-654-3210', 'downtown@store.com', '456 Center Rd', 'Chicago', '60601');


INSERT INTO staffs (first_name, last_name, email, phone, active, store_id, manager_id) VALUES
('Alice', 'Smith', 'alice@store.com', '555-1111', 1, 1, NULL),
('Bob', 'Jones', 'bob@store.com', '555-2222', 1, 2, 1);


INSERT INTO customers (first_name, last_name, phone, email, street, city, state, zip_code) VALUES
('John', 'Doe', '555-3333', 'john@example.com', '789 Park Ave', 'Los Angeles', 'CA', '90001'),
('Jane', 'Miller', '555-4444', 'jane@example.com', '321 Elm St', 'Houston', 'TX', '77001');


INSERT INTO categories (category_name) VALUES
('Electronics'),
('Clothing'),
('Furniture');


INSERT INTO brands (brand_name) VALUES
('Sony'),
('Nike'),
('IKEA');


INSERT INTO products (product_name, brand_id, category_id, model_year, list_price) VALUES
('PlayStation 5', 1, 1, 2023, 499.99),
('Air Max Sneakers', 2, 2, 2024, 129.99),
('Office Desk', 3, 3, 2022, 199.99);


INSERT INTO stocks (store_id, product_id, quantity) VALUES
(1, 1, 20),
(1, 2, 50),
(2, 3, 10);


INSERT INTO orders (customer_id, order_status, required_date, store_id, staff_id) VALUES
(1, 1, GETDATE(), 1, 1),
(2, 1, GETDATE(), 2, 2);


INSERT INTO order_items (order_id, item_id, product_id, quantity, list_price, discount) VALUES
(1, 1, 1, 1, 499.99, 0.10),
(2, 1, 2, 2, 129.99, 0.00);

-----------------------------------------------------------------------------------------------------
-------------------------------- end of first insertion ---------------------------------------------
-----------------------------------------------------------------------------------------------------

INSERT INTO brands (brand_name) VALUES
('Samsung'), ('Adidas'), ('Apple'), ('Dell'), ('LG');


INSERT INTO categories (category_name) VALUES
('Home Appliances'), ('Footwear'), ('Computers'), ('Mobile Phones');

INSERT INTO categories (category_name) VALUES
('Books'),
('Toys'),
('Kitchenware'),
('Sports Equipment'),
('Beauty & Personal Care');



INSERT INTO products (product_name, brand_id, category_id, model_year, list_price) VALUES
('Galaxy S22', 4, 9, 2023, 799.99),
('iPhone 14', 6, 10, 2023, 999.99),
('Running Shoes', 5, 2, 2024, 89.99),
('Smart TV 55"', 7, 8, 2023, 699.99),
('Gaming Laptop', 6, 11, 2022, 1299.99),
('Office Chair', 3, 3, 2022, 149.99),
('T-Shirt Pack', 2, 2, 2024, 29.99),
('Bluetooth Speaker', 1, 1, 2023, 59.99),
('Microwave Oven', 7, 8, 2023, 149.99),
('Workstation PC', 6, 11, 2024, 1999.99);


INSERT INTO customers (first_name, last_name, phone, email, street, city, state, zip_code) VALUES
('Honald', 'Johnson', '555-6661', 'honald.j@example.com', '741 Maple Dr', 'Seattle', 'WA', '98101'),
('Carlos', 'Lopez', '555-6662', 'carlos.li@example.com', '159 Pine St', 'Miami', 'FL', '33101'),
('Emily', 'Clark', '555-6663', 'emily.cj@example.com', '246 Oak Ln', 'Boston', 'MA', '02101'),
('Tom', 'Baker', '555-6664', 'tom.bs@example.com', '864 Cedar Rd', 'Denver', 'CO', '80201');


INSERT INTO stocks (store_id, product_id, quantity) VALUES
(1, 4, 30),
(1, 5, 10),
(2, 6, 25),
(2, 7, 60),
(1, 8, 40),
(2, 9, 15),
(1, 10, 5),
(2, 11, 20),
(2, 12, 35),
(1, 13, 50);


INSERT INTO orders (customer_id, order_status, required_date, store_id, staff_id) VALUES
(3, 1, GETDATE(), 1, 1),
(4, 1, GETDATE(), 2, 2),
(5, 1, GETDATE(), 1, 1),
(6, 1, GETDATE(), 2, 2);


INSERT INTO order_items (order_id, item_id, product_id, quantity, list_price, discount) VALUES
(3, 1, 4, 1, 799.99, 0.05),
(3, 2, 8, 2, 59.99, 0.00),
(4, 1, 6, 1, 149.99, 0.15),
(5, 1, 10, 1, 1999.99, 0.10),
(5, 2, 7, 3, 29.99, 0.00),
(6, 1, 5, 2, 1299.99, 0.20);

-----------------------------------------------------------------------------------------------------
-------------------------------- end of second insertion --------------------------------------------
-----------------------------------------------------------------------------------------------------