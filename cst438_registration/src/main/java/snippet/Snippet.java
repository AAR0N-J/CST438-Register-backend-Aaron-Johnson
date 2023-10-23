package snippet;

public class Snippet {
	insert into user_table
	(alias, email, password, role) values 
	('user', 'user@csumb.edu', '$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue','STUDENT'),
	('admin', 'admin@csumb.edu', '$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW' , 'ADMIN');
	
	reate table user_table (
		id identity primary key,  
		alias varchar(25) unique, 
		email varchar(25) unique,
		first_name varchar(25), 
		last_name varchar(25), 
		level varchar(25),
		password varchar(100), 
		role varchar(25)
	);
}

