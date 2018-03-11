CREATE TABLE communs.arquivo_upload (
	id int8 NOT NULL,
	file bytea NULL,
	proprietario_id int8 NULL,
	CONSTRAINT arquivo_upload_pkey PRIMARY KEY (id)
)