create table event (
    id integer not null primary key auto_increment,
    active bit not null,
    cabin_capacity integer not null,
    cabin_ticket_price decimal(19,2) not null,
    capacity integer not null,
    created_at datetime(6) not null,
    date date not null,
    end_time time not null,
    external_id varchar(36) not null unique,
    name varchar(255) not null,
    normal_ticket_price decimal(19,2) not null,
    place varchar(255) not null,
    start_time time not null,
    updated_at datetime(6),
    vip_ticket_price decimal(12,2) not null
)