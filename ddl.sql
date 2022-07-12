create table event_tb (
                          event_id bigint not null,
                          mod_date timestamp,
                          reg_date timestamp,
                          action_event_id varchar(255),
                          action_type varchar(255),
                          event_type varchar(255),
                          primary key (event_id)
);

create table place_tb (
                          place_id varchar(255) not null,
                          mod_date timestamp,
                          reg_date timestamp,
                          first_review_id varchar(255),
                          name varchar(255),
                          version bigint,
                          primary key (place_id)
);

create table point_history_tb (
                                  point_history_id bigint not null,
                                  mod_date timestamp,
                                  reg_date timestamp,
                                  description varchar(255),
                                  point bigint,
                                  point_event_type varchar(255),
                                  point_type varchar(255),
                                  user_id varchar(255),
                                  primary key (point_history_id)
);

create table review_tb (
                           review_id varchar(255) not null,
                           mod_date timestamp,
                           reg_date timestamp,
                           content varchar(255),
                           photo_size integer not null,
                           place_id varchar(255),
                           user_id varchar(255),
                           primary key (review_id)
);

create table user_tb (
                         user_id varchar(255) not null,
                         mod_date timestamp,
                         reg_date timestamp,
                         name varchar(255),
                         point_score bigint,
                         primary key (user_id)
);

alter table point_history_tb
    add constraint fk_point_user
        foreign key (user_id)
            references user_tb;

alter table review_tb
    add constraint fk_review_place
        foreign key (place_id)
            references place_tb;

alter table review_tb
    add constraint fk_review_user
        foreign key (user_id)
            references user_tb;

create index REVIEW_PLACE_ID_IDX on review_tb (place_id);