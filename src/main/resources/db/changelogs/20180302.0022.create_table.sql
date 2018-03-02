CREATE TABLE user_statistic(
  user_id INTEGER,
  path VARCHAR,
  count_visit INTEGER DEFAULT 1,
  date DATE DEFAULT now(),
  CONSTRAINT user_statistic_user_id_path_date_key UNIQUE (user_id, path, date)
);