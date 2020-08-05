INSERT INTO customer (id, first_name, last_name, email, phone_number) VALUES
(1, 'firstname-1', 'lastname-1', '1@email.com', '905554443321'),
(2, 'firstname-2', 'lastname-2', '2@email.com', '905554443322'),
(3, 'firstname-3', 'lastname-3', '3@email.com', '905554443323');


INSERT INTO address (id, type, country, city, line, customer_id) VALUES
(1, 1, 'turkey', 'ankara', 'line1', 1),
(2, 2, 'united arab emirates', 'dubai', 'line2', 2),
(3, 1, 'sweden', 'stockholm', 'line3', 3);