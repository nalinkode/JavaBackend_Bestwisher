


insert into app.app_role (id, role) values (1, 'SUPERADMIN');
insert into app.app_role (id, role) values (2, 'ADMIN');
insert into app.app_role (id, role) values (3, 'USER');

ALTER TABLE app.app_user_role
DROP CONSTRAINT uk_2j333g1ur8pffgiqts1vmn1vr;


-- Assign privilege to roles

insert into app.app_privilege (id, privilege_key, privilege_name) values 
(1, 'app_add_friendship', 'Add Friendship')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 1)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 1)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 1)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(2, 'app_confirmed_friendship', 'Confirmed Friendship')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 2)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 2)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 2)

insert into app.app_privilege (id, privilege_key, privilege_name) values 
(3, 'app_read_friend_request', 'Read all Friend Request')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 3)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 3)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 3)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(4, 'app_read_all_roles', 'Read all Roles')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 4)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 4)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(5, 'app_read_role_by_id', 'Read Roles by id')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 5)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 5)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 5)



insert into app.app_privilege (id, privilege_key, privilege_name) values 
(6, 'app_update_role_record', 'Update Role by id')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 6)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 6)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(7, 'app_create_role_record', 'Create Roles')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 7)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 7)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(8, 'app_delete_role_record', 'Delete Roles')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 8)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(9, 'app_create_user_profile', 'Create user profile')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 9)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 9)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 9)



insert into app.app_privilege (id, privilege_key, privilege_name) values 
(10, 'app_fetch_user_profile', 'Fetch user profile')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 10)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 10)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 10)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(11, 'app_read_user_records', 'Fetch user profile')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 11)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 11)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(12, 'app_disable_user_records', 'Fetch user profile')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 12)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 12)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(13, 'app_fetch_user_profile', 'Fetch user profile')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 13)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 13)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 13)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(14, 'app_create_user_post', 'Create user post')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 14)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 14)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 14)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(15, 'app_read_user_post', 'Read user post')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 15)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 15)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 15)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(16, 'app_read_all_user_post', 'Read all user post')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 16)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 16)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 16)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(17, 'app_like_user_post', 'Like user post')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 17)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 17)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 17)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(18, 'app_create_user_profile', 'Create user post')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 18)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 18)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 18)


insert into app.app_privilege (id, privilege_key, privilege_name) values 
(19, 'app_fetch_user_profile', 'Fetch user profile')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 19)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 19)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 19)







-- 


insert into app.app_privilege (id, privilege_key, privilege_name) values (10, 'app_create_user_post', 'create post by user')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 10)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 10)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 10)

insert into app.app_privilege (id, privilege_key, privilege_name) values (11, 'app_read_user_post', 'read post by user id')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 11)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 11)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 11)

insert into app.app_privilege (id, privilege_key, privilege_name) values (12, 'app_render_user_post', 'render post on UI')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 12)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 12)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 12)


insert into app.app_privilege (id, privilege_key, privilege_name) values (13, 'app_read_all_user_post', 'Read post for all user')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 13)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 13)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 13)


insert into app.app_privilege (id, privilege_key, privilege_name) values (14, 'app_like_user_post', 'Like post for user')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 14)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 14)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 14)


insert into app.app_privilege (id, privilege_key, privilege_name) values (15, 'app_create_user_profile', 'Create profile for user')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 15)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 15)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 15)


insert into app.app_privilege (id, privilege_key, privilege_name) values (16, 'app_fetch_user_profile', 'Fetch profile for user')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 16)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 16)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 16)



insert into app.app_privilege (id, privilege_key, privilege_name) values (17, 'app_add_friendship', 'add friend request')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 17)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 17)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 17)


insert into app.app_privilege (id, privilege_key, privilege_name) values (18, 'app_read_friend_request', 'get received friend request')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 18)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 18)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 18)

insert into app.app_privilege (id, privilege_key, privilege_name) values (19, 'app_confirmed_friendship', 'Confirmed friendship request')
insert into app.app_role_privileges (role_id, privilege_id) values(1, 19)
insert into app.app_role_privileges (role_id, privilege_id) values(2, 19)
insert into app.app_role_privileges (role_id, privilege_id) values(3, 19)

