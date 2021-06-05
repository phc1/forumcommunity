create table comment
(
	id bigint auto_increment,
	parent_id bigint not null,
	type int not null,
	commentator int not null,
	gmt_create bigint,
	gmt_modified bigint,
	like_count bigint,
	content varchar(1024),
	constraint comment_pk
		primary key (id)
);