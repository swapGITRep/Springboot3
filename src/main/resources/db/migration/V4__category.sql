drop table if exists category CASCADE;
drop table if exists beer_category CASCADE;

create table category
(
    id                 varchar(36) NOT NULL PRIMARY KEY,
    description        varchar(50),
    created_date       timestamp,
    last_modified_date timestamp DEFAULT NULL,
    version            bigint      DEFAULT NULL
) ;

create table beer_category
(
    beer_id     varchar(36) NOT NULL,
    category_id varchar(36) NOT NULL,
    primary key (beer_id, category_id),
    constraint fk_beer FOREIGN KEY (beer_id) references beer (id),
    constraint fk_category FOREIGN KEY (category_id) references category (id)
) ;

