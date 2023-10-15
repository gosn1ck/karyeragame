CREATE TABLE IF NOT EXISTS Avatars (
        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        url varchar(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS Users (
        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        username varchar(20) NOT NULL,
        email varchar(100) NOT NULL,
        password varchar(100) NOT NULL,
        avatar_id BIGINT,
        role varchar(15) NOT NULL,
        created_on timestamp NOT NULL,
        CONSTRAINT FK_USERS_ON_AVATARS FOREIGN KEY (avatar_id) REFERENCES Avatars (id),
        CONSTRAINT UQ_USERS_EMAIL UNIQUE (email)
);
CREATE TABLE IF NOT EXISTS Password_Reset_Token (
        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        token varchar(60) NOT NULL,
        expiry_date timestamp NOT NULL,
        user_id BIGINT NOT NULL,
        CONSTRAINT FK_Password_Reset_Token_ON_USERS FOREIGN KEY (user_id) REFERENCES users (id)
);

