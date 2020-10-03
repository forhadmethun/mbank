CREATE TABLE account
(
    account_id bigint NOT NULL,
    customer_id bigint,
    CONSTRAINT account_pkey PRIMARY KEY (account_id)
)

CREATE TABLE balance
(
    balance_id bigint NOT NULL,
    account_id bigint,
    balance numeric(19,2),
    currency varchar(255),
    CONSTRAINT balance_pkey PRIMARY KEY (balance_id)
)

CREATE TABLE customer
(
    customer_id bigint NOT NULL,
    country varchar(255),
    CONSTRAINT customer_pkey PRIMARY KEY (customer_id)
)

CREATE TABLE transaction
(
    transaction_id bigint NOT NULL,
    account_id bigint,
    amount numeric(19,2),
    currency varchar(255),
    description varchar(255) ,
    direction_of_transaction varchar(255),
    CONSTRAINT transaction_pkey PRIMARY KEY (transaction_id)
)

