CREATE TABLE users (id           UUID NOT NULL,
                    created_at   timestamp without time zone NULL,
                    updated_at   timestamp without time zone NULL,
                    email        TEXT NOT NULL,
                    name         TEXT NOT NULL,
                    password     TEXT NOT NULL,
                    username     TEXT NOT NULL
);

ALTER TABLE users ADD CONSTRAINT uc_users_pkey PRIMARY KEY (id);

ALTER TABLE users ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE users ADD CONSTRAINT uc_users_email UNIQUE (email);
