-- Sample product inventory so a test order can succeed out of the box.
-- The `product` table itself is created by Hibernate from the Product
-- entity (ddl-auto=create-drop, defer-datasource-initialization=true) -
-- this script only seeds rows, matching Day 1's data.sql pattern.
-- JpaInventoryRepository and ProductRepository both query this table.
INSERT INTO product (name, price, quantity) VALUES ('widget', 9.99, 25);
INSERT INTO product (name, price, quantity) VALUES ('gadget', 24.99, 10);
INSERT INTO product (name, price, quantity) VALUES ('gizmo', 14.99, 0);
