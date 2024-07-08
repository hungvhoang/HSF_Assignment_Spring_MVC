

-- Insert into Account table
INSERT INTO accounts(account_name, Role) VALUES 
('admin', 'ADMIN'),
('user1', 'Customer');

-- Insert into Customer table
INSERT INTO customers(customer_name, mobile, birthdate, identity_card, licence_number, licence_date, Email, Password, accountid) VALUES 
('John Doe', '1234567890', '1985-07-08', 'ID12345', 'LN67890', '2010-05-20', 'johndoe@example.com', 'password123', 2);

-- Insert into CarProducer table
INSERT INTO car_producer(producer_name, Address, Country) VALUES 
('Toyota', '123 Main St', 'Japan'),
('Honda', '456 Elm St', 'Japan');

-- Insert into Category table
INSERT INTO category(category_name) VALUES 
('Sedan'),
('SUV');

-- Insert into Car table
INSERT INTO cars (car_name, car_model_year, Color, Capacity, Description, import_date, producerid, rent_price, Status, CategoryID, image_link, is_available, Transmission, rated_star) VALUES 
('Camry', 2022, 'Red', 5, 'A comfortable sedan', '2023-01-15', 1, 50.0, 'Available', 1, 'link_to_image', 1, 1, 4),
('Civic', 2021, 'Blue', 4, 'A compact car', '2022-08-10', 2, 40.0, 'Available', 1, 'link_to_image', 1, 1, 4);

-- Insert into CarRental table
INSERT INTO car_rental(CarID, CustomerID, pickup_date, return_date, rent_price, Status) VALUES 
(1, 1, '2023-07-01', '2023-07-10', 500.0, 'Completed');


-- Insert into Review table
INSERT INTO reviews (car_carid, customer_customerid, review_star, Comment) VALUES 
(1, 1, 5, 'Great car!'),
(2, 1, 4, 'Good performance.');

-- Ensure all foreign key relations are correct
-- Verify with actual table names and constraints if needed
