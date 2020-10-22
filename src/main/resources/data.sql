INSERT INTO users (name,login,password)
VALUES ('Vasia Pupkin','vasia','pass1'), ('John Doe','john', 'pass2');
INSERT INTO roles (id,name) VALUES (1,'ADMIN'), (2, 'USER');
INSERT INTO user_role (user_login, role_id) VALUES ('vasia',1) , ('vasia',2), ('john',2);