DROP TABLE Users CASCADE;

CREATE TABLE IF NOT EXISTS Users (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username character varying(20), password character varying(20),
    age int, birth_date date, createdAt date, balance decimal
);

CREATE TABLE IF NOT EXISTS Purchase_history (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id int, FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE IF NOT EXISTS Operation (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    sum decimal, type text, date date, purchase_history_id int,
    FOREIGN KEY (purchase_history_id) REFERENCES Purchase_history(id)
)
