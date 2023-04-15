create table "contact_msg" (
                               "id" serial primary key not null ,
                               "name" varchar(30) not null,
                               "mobile_num" varchar(50) NOT NULL,
                               "email" varchar(100) NOT NULL,
                               "subject" varchar(100) NOT NULL,
                               "message" varchar(500) NOT NULL,
                               "status" varchar(10) NOT NULL,
                               "created_at" TIMESTAMP NOT NULL,
                               "created_by" varchar(50) NOT NULL,
                               "updated_at" TIMESTAMP DEFAULT NULL,
                               "updated_by" varchar(50) DEFAULT NULL


);
