-- CREATE TYPE list AS ENUM ('Palace','Park','Museum','Archaeological site', 'Nature Reserves');

CREATE TYPE type_landmark as ENUM (
    'PALACE',
    'PARK',
    'MUSEUM',
    'ARCHEOLOGICAL',
    'RESERVES'
    );

GO

CREATE TABLE IF NOT EXISTS landmark (
    id serial primary key ,
    name varchar(80),
    data date,
    description varchar(200),
    type type_landmark
);

GO

INSERT INTO landmark(name,data,description,type) values
    ('Place2', '2024-01-08', 'description2', 'PALACE'),
    ('Place1','2024-02-08', 'description1', 'MUSEUM'),
    ('Place3', '2024-01-08', 'description3', 'PALACE');

GO

