CREATE TABLE IF NOT EXISTS service (
    id serial primary key ,
    name varchar(80),
    description varchar(200)
);

GO

INSERT INTO service(name,description) values
    ('Service1', 'description1'),
    ('Service2', 'description2')

GO