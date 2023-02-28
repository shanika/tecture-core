CREATE TABLE software_system
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    uuid        UUID                                    NOT NULL,
    name        VARCHAR(255)                            NOT NULL,
    description VARCHAR(255),
    CONSTRAINT pk_software_system PRIMARY KEY (id)
);

ALTER TABLE software_system
    ADD CONSTRAINT uc_software_system_uuid UNIQUE (uuid);


CREATE TABLE decision_log
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    software_system_id BIGINT,
    timestamp          BIGINT                                  NOT NULL,
    CONSTRAINT pk_decision_log PRIMARY KEY (id)
);

ALTER TABLE decision_log
    ADD CONSTRAINT FK_DECISION_LOG_ON_SOFTWARE_SYSTEM FOREIGN KEY (software_system_id) REFERENCES software_system (id);

CREATE TABLE connection_type
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_connection_type PRIMARY KEY (id)
);

CREATE TABLE element_type
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_element_type PRIMARY KEY (id)
);


CREATE TABLE element_type_in_connection_type
(
    connection_type_id BIGINT NOT NULL,
    element_type_id    BIGINT NOT NULL
);

CREATE TABLE element_type_out_connection_type
(
    connection_type_id BIGINT NOT NULL,
    element_type_id    BIGINT NOT NULL
);

ALTER TABLE element_type_in_connection_type
    ADD CONSTRAINT fk_eletypincontyp_on_connection_type_entity FOREIGN KEY (connection_type_id) REFERENCES connection_type (id);

ALTER TABLE element_type_in_connection_type
    ADD CONSTRAINT fk_eletypincontyp_on_element_type_entity FOREIGN KEY (element_type_id) REFERENCES element_type (id);

ALTER TABLE element_type_out_connection_type
    ADD CONSTRAINT fk_eletypoutcontyp_on_connection_type_entity FOREIGN KEY (connection_type_id) REFERENCES connection_type (id);

ALTER TABLE element_type_out_connection_type
    ADD CONSTRAINT fk_eletypoutcontyp_on_element_type_entity FOREIGN KEY (element_type_id) REFERENCES element_type (id);

