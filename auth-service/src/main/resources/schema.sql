CREATE TABLE IF NOT EXISTS user_credentials
(
    id bigint GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    email varchar(254)  UNIQUE NOT NULL,
    password varchar(60) NOT NULL,
    CONSTRAINT pk_user_credentials PRIMARY KEY (
        id
    )
);

CREATE TABLE IF NOT EXISTS role
(
    id bigint GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name varchar(50)  NOT NULL UNIQUE,
    CONSTRAINT pk_role PRIMARY KEY (
        id
    )
);

CREATE TABLE IF NOT EXISTS user_role
(
    user_id bigint,
    role_id bigint,
    CONSTRAINT pk_user_role PRIMARY KEY (
        user_id,
        role_id
    ),
    CONSTRAINT fk_user_id FOREIGN KEY (
        user_id
    ) REFERENCES user_credentials (id),
    CONSTRAINT fk_role_id FOREIGN KEY (
        role_id
    ) REFERENCES role (id)
);
