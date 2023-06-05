
    alter table beer_category 
       drop constraint FK7o42knkmhb44bhnsb804o16ch;

    alter table beer_category 
       drop constraint FKrmk65j5tao1q8mp3v4mkpesie;

    alter table beer_order 
       drop constraint FK52c52110ft36i9l66u0fqh1s2;

    alter table beer_order 
       drop constraint FK5siih2e7vpx70nx4wexpxpji;

    alter table beer_order_line 
       drop constraint FKslskqsf79v6iekvb6d3gcc1l4;

    alter table beer_order_line 
       drop constraint FKhkgofxhwx8yw9m3vat8mgtnxs;

    alter table beer_order_shipment 
       drop constraint FKqry3snbsxuavowegq87xn9gd6;

    drop table beer cascade;

    drop table beer_category cascade;

    drop table beer_order cascade;

    drop table beer_order_line cascade;

    drop table beer_order_shipment cascade;

    drop table category cascade;

    drop table customer cascade;

    create table beer (
       id varchar(36) not null,
        beer_name varchar(50) not null,
        beer_style smallint not null,
        created_date timestamp(6),
        price numeric(38,2) not null,
        quantity_on_hand integer,
        upc varchar(255) not null,
        update_date timestamp(6),
        version integer,
        primary key (id)
    );

    create table beer_category (
       category_id varchar(36) not null,
        beer_id varchar(36) not null,
        primary key (category_id, beer_id)
    );

    create table beer_order (
       id varchar(36) not null,
        created_date timestamp(6),
        customer_ref varchar(255),
        last_modified_date timestamp(6),
        version bigint,
        beer_order_shipment_id varchar(36),
        customer_id varchar(36),
        primary key (id)
    );

    create table beer_order_line (
       id varchar(36) not null,
        created_date timestamp(6),
        last_modified_date timestamp(6),
        order_quantity integer,
        quantity_allocated integer,
        version bigint,
        beer_id varchar(36),
        beer_order_id varchar(36),
        primary key (id)
    );

    create table beer_order_shipment (
       id varchar(36) not null,
        created_date timestamp(6),
        last_modified_date timestamp(6),
        tracking_number varchar(255),
        version bigint,
        beer_order_id varchar(36),
        primary key (id)
    );

    create table category (
       id varchar(36) not null,
        created_date timestamp(6),
        description varchar(255),
        last_modified_date timestamp(6),
        version bigint,
        primary key (id)
    );

    create table customer (
       id varchar(36) not null,
        created_date timestamp(6),
        email varchar(255),
        name varchar(255),
        update_date timestamp(6),
        version integer,
        primary key (id)
    );

    alter table beer_category 
       add constraint FK7o42knkmhb44bhnsb804o16ch 
       foreign key (beer_id) 
       references beer;

    alter table beer_category 
       add constraint FKrmk65j5tao1q8mp3v4mkpesie 
       foreign key (category_id) 
       references category;

    alter table beer_order 
       add constraint FK52c52110ft36i9l66u0fqh1s2 
       foreign key (beer_order_shipment_id) 
       references beer_order_shipment;

    alter table beer_order 
       add constraint FK5siih2e7vpx70nx4wexpxpji 
       foreign key (customer_id) 
       references customer;

    alter table beer_order_line 
       add constraint FKslskqsf79v6iekvb6d3gcc1l4 
       foreign key (beer_id) 
       references beer;

    alter table beer_order_line 
       add constraint FKhkgofxhwx8yw9m3vat8mgtnxs 
       foreign key (beer_order_id) 
       references beer_order;

    alter table beer_order_shipment 
       add constraint FKqry3snbsxuavowegq87xn9gd6 
       foreign key (beer_order_id) 
       references beer_order;

    alter table beer_category 
       drop constraint FK7o42knkmhb44bhnsb804o16ch;

    alter table beer_category 
       drop constraint FKrmk65j5tao1q8mp3v4mkpesie;

    alter table beer_order 
       drop constraint FK52c52110ft36i9l66u0fqh1s2;

    alter table beer_order 
       drop constraint FK5siih2e7vpx70nx4wexpxpji;

    alter table beer_order_line 
       drop constraint FKslskqsf79v6iekvb6d3gcc1l4;

    alter table beer_order_line 
       drop constraint FKhkgofxhwx8yw9m3vat8mgtnxs;

    alter table beer_order_shipment 
       drop constraint FKqry3snbsxuavowegq87xn9gd6;

    drop table beer cascade;

    drop table beer_category cascade;

    drop table beer_order cascade;

    drop table beer_order_line cascade;

    drop table beer_order_shipment cascade;

    drop table category cascade;

    drop table customer cascade;

    create table beer (
       id varchar(36) not null,
        beer_name varchar(50) not null,
        beer_style smallint not null,
        created_date timestamp(6),
        price numeric(38,2) not null,
        quantity_on_hand integer,
        upc varchar(255) not null,
        update_date timestamp(6),
        version integer,
        primary key (id)
    );

    create table beer_category (
       category_id varchar(36) not null,
        beer_id varchar(36) not null,
        primary key (category_id, beer_id)
    );

    create table beer_order (
       id varchar(36) not null,
        created_date timestamp(6),
        customer_ref varchar(255),
        last_modified_date timestamp(6),
        version bigint,
        beer_order_shipment_id varchar(36),
        customer_id varchar(36),
        primary key (id)
    );

    create table beer_order_line (
       id varchar(36) not null,
        created_date timestamp(6),
        last_modified_date timestamp(6),
        order_quantity integer,
        quantity_allocated integer,
        version bigint,
        beer_id varchar(36),
        beer_order_id varchar(36),
        primary key (id)
    );

    create table beer_order_shipment (
       id varchar(36) not null,
        created_date timestamp(6),
        last_modified_date timestamp(6),
        tracking_number varchar(255),
        version bigint,
        beer_order_id varchar(36),
        primary key (id)
    );

    create table category (
       id varchar(36) not null,
        created_date timestamp(6),
        description varchar(255),
        last_modified_date timestamp(6),
        version bigint,
        primary key (id)
    );

    create table customer (
       id varchar(36) not null,
        created_date timestamp(6),
        email varchar(255),
        name varchar(255),
        update_date timestamp(6),
        version integer,
        primary key (id)
    );

    alter table beer_category 
       add constraint FK7o42knkmhb44bhnsb804o16ch 
       foreign key (beer_id) 
       references beer;

    alter table beer_category 
       add constraint FKrmk65j5tao1q8mp3v4mkpesie 
       foreign key (category_id) 
       references category;

    alter table beer_order 
       add constraint FK52c52110ft36i9l66u0fqh1s2 
       foreign key (beer_order_shipment_id) 
       references beer_order_shipment;

    alter table beer_order 
       add constraint FK5siih2e7vpx70nx4wexpxpji 
       foreign key (customer_id) 
       references customer;

    alter table beer_order_line 
       add constraint FKslskqsf79v6iekvb6d3gcc1l4 
       foreign key (beer_id) 
       references beer;

    alter table beer_order_line 
       add constraint FKhkgofxhwx8yw9m3vat8mgtnxs 
       foreign key (beer_order_id) 
       references beer_order;

    alter table beer_order_shipment 
       add constraint FKqry3snbsxuavowegq87xn9gd6 
       foreign key (beer_order_id) 
       references beer_order;
drop table if exists beer cascade ;
drop table if exists beer_category cascade ;
drop table if exists beer_order cascade ;
drop table if exists beer_order_line cascade ;
drop table if exists beer_order_shipment cascade ;
drop table if exists category cascade ;
drop table if exists customer cascade ;
create table beer (id varchar(36) not null, beer_name varchar(50) not null, beer_style smallint not null, created_date timestamp(6), price numeric(38,2) not null, quantity_on_hand integer, upc varchar(255) not null, update_date timestamp(6), version integer, primary key (id));
create table beer_category (category_id varchar(36) not null, beer_id varchar(36) not null, primary key (category_id, beer_id));
create table beer_order (id varchar(36) not null, created_date timestamp(6), customer_ref varchar(255), last_modified_date timestamp(6), version bigint, beer_order_shipment_id varchar(36), customer_id varchar(36), primary key (id));
create table beer_order_line (id varchar(36) not null, created_date timestamp(6), last_modified_date timestamp(6), order_quantity integer, quantity_allocated integer, version bigint, beer_id varchar(36), beer_order_id varchar(36), primary key (id));
create table beer_order_shipment (id varchar(36) not null, created_date timestamp(6), last_modified_date timestamp(6), tracking_number varchar(255), version bigint, beer_order_id varchar(36), primary key (id));
create table category (id varchar(36) not null, created_date timestamp(6), description varchar(255), last_modified_date timestamp(6), version bigint, primary key (id));
create table customer (id varchar(36) not null, created_date timestamp(6), email varchar(255), name varchar(255), update_date timestamp(6), version integer, primary key (id));
alter table if exists beer_category add constraint FK7o42knkmhb44bhnsb804o16ch foreign key (beer_id) references beer;
alter table if exists beer_category add constraint FKrmk65j5tao1q8mp3v4mkpesie foreign key (category_id) references category;
alter table if exists beer_order add constraint FK52c52110ft36i9l66u0fqh1s2 foreign key (beer_order_shipment_id) references beer_order_shipment;
alter table if exists beer_order add constraint FK5siih2e7vpx70nx4wexpxpji foreign key (customer_id) references customer;
alter table if exists beer_order_line add constraint FKslskqsf79v6iekvb6d3gcc1l4 foreign key (beer_id) references beer;
alter table if exists beer_order_line add constraint FKhkgofxhwx8yw9m3vat8mgtnxs foreign key (beer_order_id) references beer_order;
alter table if exists beer_order_shipment add constraint FKqry3snbsxuavowegq87xn9gd6 foreign key (beer_order_id) references beer_order;
drop table if exists beer cascade ;
drop table if exists beer_category cascade ;
drop table if exists beer_order cascade ;
drop table if exists beer_order_line cascade ;
drop table if exists beer_order_shipment cascade ;
drop table if exists category cascade ;
drop table if exists customer cascade ;
create table beer (id varchar(36) not null, beer_name varchar(50) not null, beer_style smallint not null, created_date timestamp(6), price numeric(38,2) not null, quantity_on_hand integer, upc varchar(255) not null, update_date timestamp(6), version integer, primary key (id));
create table beer_category (category_id varchar(36) not null, beer_id varchar(36) not null, primary key (category_id, beer_id));
create table beer_order (id varchar(36) not null, created_date timestamp(6), customer_ref varchar(255), last_modified_date timestamp(6), version bigint, beer_order_shipment_id varchar(36), customer_id varchar(36), primary key (id));
create table beer_order_line (id varchar(36) not null, created_date timestamp(6), last_modified_date timestamp(6), order_quantity integer, quantity_allocated integer, version bigint, beer_id varchar(36), beer_order_id varchar(36), primary key (id));
create table beer_order_shipment (id varchar(36) not null, created_date timestamp(6), last_modified_date timestamp(6), tracking_number varchar(255), version bigint, beer_order_id varchar(36), primary key (id));
create table category (id varchar(36) not null, created_date timestamp(6), description varchar(255), last_modified_date timestamp(6), version bigint, primary key (id));
create table customer (id varchar(36) not null, created_date timestamp(6), email varchar(255), name varchar(255), update_date timestamp(6), version integer, primary key (id));
alter table if exists beer_category add constraint FK7o42knkmhb44bhnsb804o16ch foreign key (beer_id) references beer;
alter table if exists beer_category add constraint FKrmk65j5tao1q8mp3v4mkpesie foreign key (category_id) references category;
alter table if exists beer_order add constraint FK52c52110ft36i9l66u0fqh1s2 foreign key (beer_order_shipment_id) references beer_order_shipment;
alter table if exists beer_order add constraint FK5siih2e7vpx70nx4wexpxpji foreign key (customer_id) references customer;
alter table if exists beer_order_line add constraint FKslskqsf79v6iekvb6d3gcc1l4 foreign key (beer_id) references beer;
alter table if exists beer_order_line add constraint FKhkgofxhwx8yw9m3vat8mgtnxs foreign key (beer_order_id) references beer_order;
alter table if exists beer_order_shipment add constraint FKqry3snbsxuavowegq87xn9gd6 foreign key (beer_order_id) references beer_order;
drop table if exists beer cascade ;
drop table if exists beer_category cascade ;
drop table if exists beer_order cascade ;
drop table if exists beer_order_line cascade ;
drop table if exists beer_order_shipment cascade ;
drop table if exists category cascade ;
drop table if exists customer cascade ;
create table beer (id varchar(36) not null, beer_name varchar(50) not null, beer_style smallint not null, created_date timestamp(6), price numeric(38,2) not null, quantity_on_hand integer, upc varchar(255) not null, update_date timestamp(6), version integer, primary key (id));
create table beer_category (category_id varchar(36) not null, beer_id varchar(36) not null, primary key (category_id, beer_id));
create table beer_order (id varchar(36) not null, created_date timestamp(6), customer_ref varchar(255), last_modified_date timestamp(6), version bigint, beer_order_shipment_id varchar(36), customer_id varchar(36), primary key (id));
create table beer_order_line (id varchar(36) not null, created_date timestamp(6), last_modified_date timestamp(6), order_quantity integer, quantity_allocated integer, version bigint, beer_id varchar(36), beer_order_id varchar(36), primary key (id));
create table beer_order_shipment (id varchar(36) not null, created_date timestamp(6), last_modified_date timestamp(6), tracking_number varchar(255), version bigint, beer_order_id varchar(36), primary key (id));
create table category (id varchar(36) not null, created_date timestamp(6), description varchar(255), last_modified_date timestamp(6), version bigint, primary key (id));
create table customer (id varchar(36) not null, created_date timestamp(6), email varchar(255), name varchar(255), update_date timestamp(6), version integer, primary key (id));
alter table if exists beer_category add constraint FK7o42knkmhb44bhnsb804o16ch foreign key (beer_id) references beer;
alter table if exists beer_category add constraint FKrmk65j5tao1q8mp3v4mkpesie foreign key (category_id) references category;
alter table if exists beer_order add constraint FK52c52110ft36i9l66u0fqh1s2 foreign key (beer_order_shipment_id) references beer_order_shipment;
alter table if exists beer_order add constraint FK5siih2e7vpx70nx4wexpxpji foreign key (customer_id) references customer;
alter table if exists beer_order_line add constraint FKslskqsf79v6iekvb6d3gcc1l4 foreign key (beer_id) references beer;
alter table if exists beer_order_line add constraint FKhkgofxhwx8yw9m3vat8mgtnxs foreign key (beer_order_id) references beer_order;
alter table if exists beer_order_shipment add constraint FKqry3snbsxuavowegq87xn9gd6 foreign key (beer_order_id) references beer_order;
