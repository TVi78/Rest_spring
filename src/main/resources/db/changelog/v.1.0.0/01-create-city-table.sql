CREATE TABLE IF NOT EXISTS city (
     id serial primary key ,
     name varchar(80),
     population integer,
     metro boolean
    );

GO

INSERT INTO city(name,population, metro) values
    ('Moskow', 100000, true),
    ('Omsk', 10000, false),
    ('London', 20000, true)
GO

