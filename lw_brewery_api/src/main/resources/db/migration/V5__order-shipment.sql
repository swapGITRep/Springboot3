drop table if exists beer_order_shipment CASCADE;

CREATE TABLE beer_order_shipment
(
    id                 VARCHAR(36) NOT NULL PRIMARY KEY,
    beer_order_id            VARCHAR(36) UNIQUE,
    tracking_number    VARCHAR(50),
    created_date       TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT NULL,
    version            BIGINT      DEFAULT NULL,
    CONSTRAINT fk_bos FOREIGN KEY (beer_order_id) REFERENCES beer_order (id)
) ;

ALTER TABLE beer_order
    ADD COLUMN beer_order_shipment_id VARCHAR(36);

ALTER TABLE beer_order
    ADD CONSTRAINT fk_bos_shipment
        FOREIGN KEY (beer_order_shipment_id) REFERENCES beer_order_shipment (id);