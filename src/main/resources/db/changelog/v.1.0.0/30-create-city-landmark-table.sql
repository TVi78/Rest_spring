CREATE TABLE IF NOT EXISTS city_landmark (
    city_id integer REFERENCES city (id),
    landmark_id integer REFERENCES landmark (id)
    );

GO

INSERT INTO city_landmark(city_id,landmark_id) values
    (1, 2),
    (1, 1),
    (2, 3);

GO