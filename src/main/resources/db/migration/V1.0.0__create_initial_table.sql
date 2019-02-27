CREATE TABLE anky_user (
  id VARCHAR(255) NOT NULL,
  mail_address VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  status int(1) NOT NULL,
  role int(1) NOT NULL,
  created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE book (
  id VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  sort_number int(11) NOT NULL,
  created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE category (
  id VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  sort_number int(11) NOT NULL,
  created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE question (
  id VARCHAR(255) NOT NULL,
  title VARCHAR(255) NOT NULL,
  answer LONGTEXT NOT NULL,
  sort_number int(11) NOT NULL,
  created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE answer_result (
  id VARCHAR(255) NOT NULL,
  score int(11) NOT NULL,
  created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE relate_book_to_user (
  book_id VARCHAR(255) NOT NULL,
  user_id VARCHAR(255) NOT NULL,
  PRIMARY KEY (book_id),
  FOREIGN KEY (book_id)
  REFERENCES book(id)
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE relate_category_to_book (
  category_id VARCHAR(255) NOT NULL,
  book_id VARCHAR(255) NOT NULL,
  PRIMARY KEY (category_id),
  FOREIGN KEY (category_id)
  REFERENCES category(id)
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE relate_question_to_category (
  question_id VARCHAR(255) NOT NULL,
  category_id VARCHAR(255) NOT NULL,
  PRIMARY KEY (question_id),
  FOREIGN KEY (question_id)
  REFERENCES question(id)
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE relate_answer_result_to_question (
  answer_result_id VARCHAR(255) NOT NULL,
  question_id VARCHAR(255) NOT NULL,
  PRIMARY KEY (answer_result_id),
  FOREIGN KEY (answer_result_id)
  REFERENCES answer_result(id)
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE relate_user_hash_to_user (
  id VARCHAR(255) NOT NULL,
  hash VARCHAR(255) NOT NULL,
  created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
