insert into member (email, password, name, birth, join_date, activated) values ('admin@admin.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', '1997-02-05', '2023-03-03 09:33:33', 1);
# insert into member (email, password, name, activated) values ('user@user.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);

insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into member_authority (member_id, authority_name) values (1, 'ROLE_USER');
insert into member_authority (member_id, authority_name) values (1, 'ROLE_ADMIN');
# insert into member_authority (member_id, authority_name) values (2, 'ROLE_USER');

drop table if exists housedeal;
drop table if exists houseinfo;
drop table if exists dongcode;
