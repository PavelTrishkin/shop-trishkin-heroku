INSERT INTO users_tbl (user_id, archive_fld, email_fld, name_fld, password_fld, role_fld, bucket_bucket_id)
values (1, false, 'admin@mail.ru', 'admin', '$2a$10$SaKk1lNhCUqtJL65WUG/jOEkwfzuJxxiSpXk9cpEPAgU5Px83RD8m', 'ADMIN', null);
INSERT INTO users_tbl (user_id, archive_fld, email_fld, name_fld, password_fld, role_fld, bucket_bucket_id)
values (2, false, 'user@mail.ru', 'User', '$2a$10$TQL7RlBS99axofPzJsYuvOwTxDoa.2gz6.S/1oOJVW0hMHL3Cqxwm', 'CLIENT', null);
ALTER SEQUENCE user_seq RESTART WITH 3;