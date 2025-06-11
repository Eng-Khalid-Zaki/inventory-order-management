create table stores (
    store_id int primary key identity(1,1),
    store_name nvarchar(50) not null,
    phone nvarchar(25) unique,
    email nvarchar(50) unique,
    street nvarchar(50) not null,
    city nvarchar(25) not null,
    zip_code nvarchar(10)
);


create table staffs (
    staff_id int primary key identity(1,1),
    first_name nvarchar(25) not null,
    last_name nvarchar(25) not null,
    email nvarchar(50) unique not null,
    phone nvarchar(15) unique,
    active bit not null,
    store_id int,
    manager_id int,
    foreign key (store_id) references stores(store_id),
    foreign key (manager_id) references staffs(staff_id)
);

create table customers (
customer_id int primary key identity(1,1),
first_name nvarchar(25) not null,
last_name nvarchar(25) not null,
phone nvarchar(15) unique,
email nvarchar(50) unique not null,
street nvarchar(50) not null,
city nvarchar(25) not null,
state nvarchar(25) not null,
zip_code nvarchar(10),
created_at datetime default getdate(),
updated_at datetime null
)

create table orders (
order_id int primary key identity(1,1),
customer_id int,
order_status tinyint not null,
order_date datetime default getdate(),
required_date datetime not null,
shipped_date datetime,
store_id int,
staff_id int,
foreign key (store_id) references stores(store_id),
foreign key (customer_id) references customers(customer_id),
foreign key (staff_id) references staffs(staff_id)
)

create table categories (
category_id int primary key identity(1,1),
category_name nvarchar(50) not null
)

create table brands (
brand_id int primary key identity(1,1),
brand_name nvarchar(50) not null
)

create table products (
product_id int primary key identity(1,1),
product_name nvarchar(50) not null,
brand_id int not null,
category_id int not null,
model_year smallint default year(getdate()),
list_price decimal(10,2) not null,
foreign key (brand_id) references brands(brand_id),
foreign key (category_id) references categories(category_id)
)

create table stocks (
store_id int,
product_id int,
quantity int not null,
primary key (store_id, product_id),
foreign key (store_id) references stores(store_id),
foreign key (product_id) references products(product_id)
)

create table order_items (
order_id int,
item_id int not null,
product_id int,
quantity int not null,
list_price decimal(10,2) not null,
discount decimal(4,2) not null,
primary key(order_id, item_id),
foreign key (order_id) references orders(order_id),
foreign key (product_id) references products(product_id)
)





