create table category (created_at datetime(6), id bigint not null, updated_at datetime(6), name varchar(255), primary key (id)) engine=InnoDB;
create table category_seq (next_val bigint) engine=InnoDB;
insert into category_seq values ( 1 );
create table product (price float(53) not null, category_id bigint, created_at datetime(6), id bigint not null, updated_at datetime(6), description varchar(255), image_url varchar(255), title varchar(255), primary key (id)) engine=InnoDB;
create table product_seq (next_val bigint) engine=InnoDB;
insert into product_seq values ( 1 );
alter table product add constraint FK1mtsbur82frn64de7balymq9s foreign key (category_id) references category (id);