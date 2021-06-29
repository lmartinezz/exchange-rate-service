DROP TABLE IF EXISTS exchange_rate;

CREATE TABLE exchange_rate (
  id INTEGER IDENTITY NOT NULL,
  origin_currency VARCHAR(250) NOT NULL,
  destination_currency VARCHAR(250) NOT NULL,
  value DECIMAL(15, 2) DEFAULT NULL,
  active INTEGER DEFAULT NULL,
  PRIMARY KEY(id)
);

INSERT INTO exchange_rate (origin_currency, destination_currency, value, active) VALUES
  ('USD', 'PEN', 3.58, 1),
  ('XYZ', 'ABC', 4.54, 1);