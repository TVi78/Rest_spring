CREATE TABLE IF NOT EXISTS landmark_service (
    landmark_id integer REFERENCES landmark (id),
    service_id integer REFERENCES service (id)
    );

GO

INSERT INTO landmark_service(landmark_id, service_id) values
    (1, 2),
    (1, 1),
    (2, 1),
    (2, 3);


GO