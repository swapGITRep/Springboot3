drop table if exists beer_order_line CASCADE;
drop table if exists beer_order CASCADE;

CREATE TABLE beer_order
(
    id                 varchar(36) NOT NULL,
    created_date       timestamp   DEFAULT NULL,
    customer_ref       varchar(255) DEFAULT NULL,
    last_modified_date timestamp   DEFAULT NULL,
    version            bigint       DEFAULT NULL,
    customer_id        varchar(36)  DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
) ;

CREATE TABLE beer_order_line
(
    id                 varchar(36) NOT NULL,
    beer_id            varchar(36) DEFAULT NULL,
    created_date       timestamp  DEFAULT NULL,
    last_modified_date timestamp  DEFAULT NULL,
    order_quantity     int         DEFAULT NULL,
    quantity_allocated int         DEFAULT NULL,
    version            bigint      DEFAULT NULL,
    beer_order_id      varchar(36) DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_beer_oder FOREIGN KEY (beer_order_id) REFERENCES beer_order (id),
    CONSTRAINT fk_beer FOREIGN KEY (beer_id) REFERENCES beer (id)
) ;