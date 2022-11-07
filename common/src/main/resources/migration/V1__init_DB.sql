create sequence bucket_seq start 1 increment 1;
create sequence category_seq start 1 increment 1;
create sequence order_details_seq start 1 increment 1;
create sequence order_seq start 1 increment 1;
create sequence product_seq start 1 increment 1;
create sequence user_seq start 1 increment 1;
create table buckets
(
    id      int8 not null,
    user_id int8,
    primary key (id)
);
create table buckets_products
(
    buckets_id int8 not null,
    product_id int8 not null
);
create table categories
(
    id    int8 not null,
    title varchar(255),
    primary key (id)
);
create table orders
(
    id      int8 not null,
    address varchar(255),
    created timestamp,
    status  varchar(255),
    sum     numeric(19, 2),
    updated timestamp,
    user_id int8,
    primary key (id)
);
create table orders_details
(
    id         int8 not null,
    amount     numeric(19, 2),
    price      numeric(19, 2),
    order_id   int8,
    product_id int8,
    details_id int8 not null,
    primary key (id)
);
create table products
(
    id    int8 not null,
    price numeric(19, 2),
    title varchar(255),
    primary key (id)
);
create table products_categories
(
    product_id  int8 not null,
    category_id int8 not null
);
create table users
(
    id        int8    not null,
    archive   boolean not null,
    email     varchar(255),
    name      varchar(255),
    password  varchar(255),
    role      varchar(255),
    primary key (id)
);
alter table if exists orders_details
    add constraint orders_details
        unique (details_id);
alter table if exists buckets
    add constraint buckets_fk_user
        foreign key (user_id) references users;
alter table if exists buckets_products
    add constraint buckets_products_fk_products
        foreign key (product_id) references products;
alter table if exists buckets_products
    add constraint buckets_products_fk_buckets
        foreign key (buckets_id) references buckets;

alter table if exists orders
    add constraint orders_details_fk_users
        foreign key (user_id) references users;

alter table if exists orders_details
    add constraint orders_details_fk_order
        foreign key (order_id) references orders;

alter table if exists orders_details
    add constraint orders_details_fk_products
        foreign key (product_id) references products;

alter table if exists orders_details
    add constraint orders_details_fk_orders_details
        foreign key (details_id) references orders_details;

alter table if exists products_categories
    add constraint products_categories_fk_categories
        foreign key (category_id) references categories;

alter table if exists products_categories
    add constraint products_categories_fk_products
        foreign key (product_id) references products;


alter table if exists orders_details drop constraint if exists orders_details;

alter table if exists orders_details add constraint orders_details unique (details_id)