-- DROP TABLE IF EXITS films cascade;
-- DROP TABLE IF EXITS film_genre cascade;
-- DROP TABLE IF EXITS genre cascade;
-- DROP TABLE IF EXITS likes cascade;
-- DROP TABLE IF EXITS mpa cascade;
CREATE TABLE IF NOT EXISTS Users
(
    id
    INTEGER
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
    PRIMARY
    KEY,
    email
    VARCHAR
(
    100
) NOT NULL,
    login VARCHAR
(
    100
) NOT NULL,
    name VARCHAR
(
    50
) NOT NULL,
    birthday DATE NOT NULL
    );
CREATE TABLE IF NOT EXISTS Friendship
(
    userId
    INTEGER,
    friendId
    INTEGER,
    status
    BOOLEAN,
    PRIMARY
    KEY
(
    userId,
    friendId
),
    FOREIGN KEY
(
    userId
) REFERENCES Users
(
    id
) ON DELETE CASCADE,
    FOREIGN KEY
(
    friendId
) REFERENCES Users
(
    id
)
  ON DELETE CASCADE
    );
CREATE TABLE IF NOT EXISTS RatingMpa
(
    id
    INTEGER
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
    PRIMARY
    KEY,
    name
    VARCHAR
(
    50
)
    );
CREATE TABLE IF NOT EXISTS Genre
(
    id
    INTEGER
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
    PRIMARY
    KEY,
    name
    VARCHAR
(
    100
) NOT NULL
    );
CREATE TABLE IF NOT EXISTS Films
(
    id
    INTEGER
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
    PRIMARY
    KEY,
    name
    VARCHAR
(
    50
) NOT NULL,
    description VARCHAR
(
    250
),
    releaseDate DATE NOT NULL,
    duration INTEGER NOT NULL,
    ratingMpaId INTEGER NOT NULL,
    FOREIGN KEY
(
    ratingMpaId
) REFERENCES RatingMpa
(
    id
) ON DELETE CASCADE

    );
CREATE TABLE IF NOT EXISTS FilmGenre
(
    filmId
    INTEGER,
    genreId
    INTEGER,
    PRIMARY
    KEY
(
    filmId,
    genreId
),
    FOREIGN KEY
(
    filmId
) REFERENCES Films
(
    id
) ON DELETE CASCADE,
    FOREIGN KEY
(
    genreId
) REFERENCES Genre
(
    id
)
  ON DELETE CASCADE
    );
CREATE TABLE IF NOT EXISTS Likes
(
    filmId
    INTEGER,
    userId
    INTEGER,
    PRIMARY
    KEY
(
    filmId,
    userId
),
    FOREIGN KEY
(
    filmId
) REFERENCES Films
(
    id
) ON DELETE CASCADE,
    FOREIGN KEY
(
    userId
) REFERENCES Users
(
    id
)
  ON DELETE CASCADE
    );
-- DROP TABLE IF EXITS users cascade;
-- DROP TABLE IF EXITS friends cascade;