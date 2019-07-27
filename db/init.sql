insert into SYS_USER(name_, login_name_, password_, gender_)
VALUES ('管理员', 'admin', '$2a$10$C5Gf4PUSLh2vjiiF1MsOFOuUjmFl7cdAbTHtMILfX.B0dPCj6aH3q', null);
insert into SYS_USER_ROLE(sys_user_id_, role_)
values (1, 'ROLE_ADMIN');