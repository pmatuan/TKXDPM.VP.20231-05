-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-01-01 10:06:44.814

-- tables
-- Table: Book
CREATE TABLE Book (
    media_id int  NOT NULL,
    authors text  NOT NULL,
    cover_type enum  NOT NULL,
    publisher varchar(100)  NOT NULL,
    publication_date timestamp  NOT NULL,
    pages int  NOT NULL,
    language varchar(100)  NOT NULL,
    type varchar(100)  NOT NULL,
    CONSTRAINT Book_pk PRIMARY KEY (media_id)
);

-- Table: CD
CREATE TABLE CD (
    media_id int  NOT NULL,
    artists text  NOT NULL,
    record_label varchar(100)  NOT NULL,
    track_list text  NOT NULL,
    genres varchar(100)  NOT NULL,
    release_date timestamp  NOT NULL,
    CONSTRAINT CD_pk PRIMARY KEY (media_id)
);

-- Table: DVD
CREATE TABLE DVD (
    media_id int  NOT NULL,
    disc_format enum  NOT NULL,
    director varchar(100)  NOT NULL,
    run_time double(10,2)  NOT NULL,
    studio varchar(100)  NOT NULL,
    language varchar(100)  NOT NULL,
    subtitles text  NOT NULL,
    release_date timestamp  NOT NULL,
    genre varchar(100)  NOT NULL,
    CONSTRAINT DVD_pk PRIMARY KEY (media_id)
);

-- Table: LP
CREATE TABLE LP (
    media_id int  NOT NULL,
    artists text  NOT NULL,
    record_label varchar(100)  NOT NULL,
    track_list text  NOT NULL,
    genre varchar(100)  NOT NULL,
    release_date timestamp  NOT NULL,
    CONSTRAINT LP_pk PRIMARY KEY (media_id)
);

-- Table: User
CREATE TABLE User (
    id int  NOT NULL,
    name varchar(255)  NOT NULL,
    email varchar(255)  NOT NULL,
    password varchar(255)  NOT NULL,
    phone_number char(10)  NOT NULL,
    is_blocked int  NOT NULL,
    role varchar(20)  NOT NULL,
    CONSTRAINT User_pk PRIMARY KEY (id)
);

-- Table: cart
CREATE TABLE cart (
    id varchar(255)  NOT NULL,
    CONSTRAINT cart_pk PRIMARY KEY (id)
);

-- Table: cart_media
CREATE TABLE cart_media (
    id int  NOT NULL,
    cart_id varchar(255)  NOT NULL,
    media_id int  NOT NULL,
    quantity int  NOT NULL,
    CONSTRAINT cart_media_pk PRIMARY KEY (id)
);

-- Table: changelog
CREATE TABLE changelog (
    id int  NOT NULL,
    timestamp timestamp  NOT NULL,
    is_price_change int  NOT NULL,
    change_media_id int  NOT NULL,
    author_id int  NOT NULL,
    CONSTRAINT changelog_pk PRIMARY KEY (id)
);

-- Table: delivery_info
CREATE TABLE delivery_info (
    id int  NOT NULL,
    customer_name nvarchar(255)  NOT NULL,
    email varchar(255)  NOT NULL,
    phone_number char(10)  NOT NULL,
    province enum  NOT NULL,
    address text  NOT NULL,
    CONSTRAINT delivery_info_pk PRIMARY KEY (id)
);

-- Table: media
CREATE TABLE media (
    id int  NOT NULL,
    title varchar(100)  NOT NULL,
    category varchar(100)  NOT NULL,
    value double(10,2)  NOT NULL,
    price double(10,2)  NOT NULL,
    quantity_in_stock int  NOT NULL,
    is_able_to_rush_delivery bool  NOT NULL,
    weight double(10,2)  NOT NULL,
    image_url text  NOT NULL,
    barcode_url text  NOT NULL,
    description text  NOT NULL,
    import_date timestamp  NOT NULL,
    CONSTRAINT media_pk PRIMARY KEY (id)
);

-- Table: order
CREATE TABLE `order` (
    id varchar(255)  NOT NULL,
    transaction_id char(8)  NOT NULL,
    delivery_info_id int  NOT NULL,
    state varchar(255)  NOT NULL,
    subtotal double(10,2)  NOT NULL,
    delivery_fee double(10,2)  NOT NULL,
    VAT int  NOT NULL,
    total double(10,2)  NOT NULL,
    CONSTRAINT order_pk PRIMARY KEY (id)
);

-- Table: order_media
CREATE TABLE order_media (
    id int  NOT NULL,
    order_id varchar(255)  NOT NULL,
    media_id int  NOT NULL,
    quantity int  NOT NULL,
    is_order_for_rush_delivery bool  NOT NULL,
    CONSTRAINT order_media_pk PRIMARY KEY (id)
);

-- Table: payment_transaction
CREATE TABLE payment_transaction (
    id char(8)  NOT NULL,
    payment_method varchar(100)  NOT NULL,
    status bool  NOT NULL,
    amount double(10,2)  NOT NULL,
    timestamp timestamp  NOT NULL,
    content varchar(255)  NOT NULL,
    CONSTRAINT payment_transaction_pk PRIMARY KEY (id)
);

-- Table: rush_order
CREATE TABLE rush_order (
    id varchar(255)  NOT NULL,
    order_id varchar(255)  NOT NULL,
    delivery_time timestamp  NOT NULL,
    delivery_instruction text  NOT NULL,
    CONSTRAINT rush_order_pk PRIMARY KEY (id)
);

-- Table: sender
CREATE TABLE sender (
    id int  NOT NULL,
    provider varchar(255)  NOT NULL,
    config json  NOT NULL,
    CONSTRAINT sender_pk PRIMARY KEY (id)
);

-- Table: template
CREATE TABLE template (
    id int  NOT NULL,
    title varchar(255)  NOT NULL,
    content text  NOT NULL,
    requiredParameters varchar(255)  NOT NULL,
    CONSTRAINT template_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: Book_item (table: Book)
ALTER TABLE Book ADD CONSTRAINT Book_item FOREIGN KEY Book_item (media_id)
    REFERENCES media (id);

-- Reference: CD_item (table: CD)
ALTER TABLE CD ADD CONSTRAINT CD_item FOREIGN KEY CD_item (media_id)
    REFERENCES media (id);

-- Reference: DVD_item (table: DVD)
ALTER TABLE DVD ADD CONSTRAINT DVD_item FOREIGN KEY DVD_item (media_id)
    REFERENCES media (id);

-- Reference: LP_item (table: LP)
ALTER TABLE LP ADD CONSTRAINT LP_item FOREIGN KEY LP_item (media_id)
    REFERENCES media (id);

-- Reference: cart_media_cart (table: cart_media)
ALTER TABLE cart_media ADD CONSTRAINT cart_media_cart FOREIGN KEY cart_media_cart (cart_id)
    REFERENCES cart (id);

-- Reference: cart_media_media (table: cart_media)
ALTER TABLE cart_media ADD CONSTRAINT cart_media_media FOREIGN KEY cart_media_media (media_id)
    REFERENCES media (id);

-- Reference: changelog_User (table: changelog)
ALTER TABLE changelog ADD CONSTRAINT changelog_User FOREIGN KEY changelog_User (author_id)
    REFERENCES User (id);

-- Reference: changelog_media (table: changelog)
ALTER TABLE changelog ADD CONSTRAINT changelog_media FOREIGN KEY changelog_media (change_media_id)
    REFERENCES media (id);

-- Reference: order_delivery_info (table: order)
ALTER TABLE `order` ADD CONSTRAINT order_delivery_info FOREIGN KEY order_delivery_info (delivery_info_id)
    REFERENCES delivery_info (id);

-- Reference: order_item_item (table: order_media)
ALTER TABLE order_media ADD CONSTRAINT order_item_item FOREIGN KEY order_item_item (media_id)
    REFERENCES media (id);

-- Reference: order_item_order (table: order_media)
ALTER TABLE order_media ADD CONSTRAINT order_item_order FOREIGN KEY order_item_order (order_id)
    REFERENCES `order` (id);

-- Reference: order_transaction (table: order)
ALTER TABLE `order` ADD CONSTRAINT order_transaction FOREIGN KEY order_transaction (transaction_id)
    REFERENCES payment_transaction (id);

-- Reference: rush_order_order (table: rush_order)
ALTER TABLE rush_order ADD CONSTRAINT rush_order_order FOREIGN KEY rush_order_order (order_id)
    REFERENCES `order` (id);

-- End of file.

