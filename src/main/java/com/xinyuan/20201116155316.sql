/*
PostgreSQL Backup
Database: ylj_db/public
Backup Time: 2020-11-16 15:53:19
*/

DROP SEQUENCE IF EXISTS "public"."sys_user_id_seq";
DROP SEQUENCE IF EXISTS "public"."tb_answer_id_seq";
DROP SEQUENCE IF EXISTS "public"."tb_classify_id_seq";
DROP SEQUENCE IF EXISTS "public"."tb_course_id_seq";
DROP SEQUENCE IF EXISTS "public"."tb_option_id_seq";
DROP SEQUENCE IF EXISTS "public"."tb_person_id_seq";
DROP SEQUENCE IF EXISTS "public"."tb_question_id_seq";
DROP SEQUENCE IF EXISTS "public"."tb_question_type_id_seq";
DROP SEQUENCE IF EXISTS "public"."tb_status_id_seq";
DROP SEQUENCE IF EXISTS "public"."tb_t_option_id_seq";
DROP SEQUENCE IF EXISTS "public"."tb_topic_course_id_seq";
DROP SEQUENCE IF EXISTS "public"."tb_topic_id_seq";
DROP TABLE IF EXISTS "public"."company";
DROP TABLE IF EXISTS "public"."course";
DROP TABLE IF EXISTS "public"."score";
DROP TABLE IF EXISTS "public"."student";
DROP TABLE IF EXISTS "public"."sys_user";
DROP TABLE IF EXISTS "public"."tb_answer";
DROP TABLE IF EXISTS "public"."tb_classify";
DROP TABLE IF EXISTS "public"."tb_course";
DROP TABLE IF EXISTS "public"."tb_option";
DROP TABLE IF EXISTS "public"."tb_person";
DROP TABLE IF EXISTS "public"."tb_question";
DROP TABLE IF EXISTS "public"."tb_question_type";
DROP TABLE IF EXISTS "public"."tb_status";
DROP TABLE IF EXISTS "public"."tb_t_option";
DROP TABLE IF EXISTS "public"."tb_topic";
DROP TABLE IF EXISTS "public"."tb_topic_course";
DROP TABLE IF EXISTS "public"."teacher";
DROP TABLE IF EXISTS "public"."use";
CREATE SEQUENCE "public"."sys_user_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "public"."tb_answer_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "public"."tb_classify_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "public"."tb_course_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "public"."tb_option_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "public"."tb_person_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "public"."tb_question_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "public"."tb_question_type_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "public"."tb_status_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "public"."tb_t_option_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "public"."tb_topic_course_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE SEQUENCE "public"."tb_topic_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
CREATE TABLE "public"."company" (
  "id" int4 NOT NULL,
  "name" text COLLATE "pg_catalog"."default" NOT NULL,
  "age" int4 NOT NULL,
  "address" char(50) COLLATE "pg_catalog"."default",
  "salary" float4
)
;ALTER TABLE "public"."company" OWNER TO "postgres";
CREATE TABLE "public"."course" (
  "c_id" varchar(10) COLLATE "pg_catalog"."default",
  "c_name" varchar(20) COLLATE "pg_catalog"."default",
  "t_id" varchar(10) COLLATE "pg_catalog"."default"
)
;ALTER TABLE "public"."course" OWNER TO "postgres";
CREATE TABLE "public"."score" (
  "s_id" varchar(10) COLLATE "pg_catalog"."default",
  "c_id" varchar(10) COLLATE "pg_catalog"."default",
  "score" int4
)
;ALTER TABLE "public"."score" OWNER TO "postgres";
CREATE TABLE "public"."student" (
  "s_id" varchar(10) COLLATE "pg_catalog"."default",
  "s_name" varchar(20) COLLATE "pg_catalog"."default",
  "s_age" date,
  "s_sex" varchar(10) COLLATE "pg_catalog"."default"
)
;ALTER TABLE "public"."student" OWNER TO "postgres";
CREATE TABLE "public"."sys_user" (
  "id" int8 NOT NULL DEFAULT nextval('sys_user_id_seq'::regclass),
  "age" int4,
  "collect_topic_num" int4 DEFAULT 0,
  "collected_num" int4 DEFAULT 0,
  "concert_user_num" int4 DEFAULT 0,
  "concerted_user_num" int4 DEFAULT 0,
  "create_time" timestamp(6),
  "deleted" int4,
  "icon" varchar(255) COLLATE "pg_catalog"."default",
  "introduction" varchar(255) COLLATE "pg_catalog"."default",
  "liked_num" int4 DEFAULT 0,
  "login_count" int4 DEFAULT 0,
  "last_login_time" timestamp(6),
  "money" float8 DEFAULT 0,
  "nickname" varchar(255) COLLATE "pg_catalog"."default",
  "open_id" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "phone" varchar(255) COLLATE "pg_catalog"."default",
  "sex" int4,
  "status" int4,
  "username" varchar(255) COLLATE "pg_catalog"."default",
  "vip" int4 DEFAULT 1
)
;ALTER TABLE "public"."sys_user" OWNER TO "postgres";
CREATE TABLE "public"."tb_answer" (
  "id" int8 NOT NULL DEFAULT nextval('tb_answer_id_seq'::regclass),
  "o_id" int4,
  "q_id" int4,
  "u_id" int4,
  "deleted" int4
)
;ALTER TABLE "public"."tb_answer" OWNER TO "postgres";
CREATE TABLE "public"."tb_classify" (
  "id" int8 NOT NULL DEFAULT nextval('tb_classify_id_seq'::regclass),
  "deleted" int4,
  "last" int4,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "s_id" int8,
  "sort" int4
)
;ALTER TABLE "public"."tb_classify" OWNER TO "postgres";
CREATE TABLE "public"."tb_course" (
  "id" int8 NOT NULL DEFAULT nextval('tb_course_id_seq'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "deleted" int4
)
;ALTER TABLE "public"."tb_course" OWNER TO "postgres";
CREATE TABLE "public"."tb_option" (
  "id" int8 NOT NULL DEFAULT nextval('tb_option_id_seq'::regclass),
  "content" varchar(255) COLLATE "pg_catalog"."default",
  "deleted" int4,
  "q_id" int8,
  "right_answer" int4
)
;ALTER TABLE "public"."tb_option" OWNER TO "postgres";
CREATE TABLE "public"."tb_person" (
  "id" int8 NOT NULL DEFAULT nextval('tb_person_id_seq'::regclass),
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "user_name" varchar(255) COLLATE "pg_catalog"."default"
)
;ALTER TABLE "public"."tb_person" OWNER TO "postgres";
CREATE TABLE "public"."tb_question" (
  "id" int8 NOT NULL DEFAULT nextval('tb_question_id_seq'::regclass),
  "deleted" int4,
  "title" varchar(255) COLLATE "pg_catalog"."default"
)
;ALTER TABLE "public"."tb_question" OWNER TO "postgres";
CREATE TABLE "public"."tb_question_type" (
  "id" int8 NOT NULL DEFAULT nextval('tb_question_type_id_seq'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "deleted" int4
)
;ALTER TABLE "public"."tb_question_type" OWNER TO "postgres";
CREATE TABLE "public"."tb_status" (
  "id" int8 NOT NULL DEFAULT nextval('tb_status_id_seq'::regclass),
  "deleted" int4,
  "status" varchar(255) COLLATE "pg_catalog"."default"
)
;ALTER TABLE "public"."tb_status" OWNER TO "postgres";
CREATE TABLE "public"."tb_t_option" (
  "id" int8 NOT NULL DEFAULT nextval('tb_t_option_id_seq'::regclass),
  "content" varchar(255) COLLATE "pg_catalog"."default",
  "right_answer" int4,
  "t_id" int8,
  "deleted" int8
)
;ALTER TABLE "public"."tb_t_option" OWNER TO "postgres";
CREATE TABLE "public"."tb_topic" (
  "id" int8 NOT NULL DEFAULT nextval('tb_topic_id_seq'::regclass),
  "analysis" varchar(255) COLLATE "pg_catalog"."default",
  "answer" varchar(255) COLLATE "pg_catalog"."default",
  "classify_id" int8,
  "complexity" varchar(255) COLLATE "pg_catalog"."default",
  "question_stem" varchar(255) COLLATE "pg_catalog"."default",
  "question_type_id" int8,
  "deleted" int8,
  "status" int8
)
;ALTER TABLE "public"."tb_topic" OWNER TO "postgres";
CREATE TABLE "public"."tb_topic_course" (
  "id" int8 NOT NULL DEFAULT nextval('tb_topic_course_id_seq'::regclass),
  "c_id" int8,
  "t_id" int8,
  "deleted" int4
)
;ALTER TABLE "public"."tb_topic_course" OWNER TO "postgres";
CREATE TABLE "public"."teacher" (
  "t_id" varchar(10) COLLATE "pg_catalog"."default",
  "t_name" varchar(20) COLLATE "pg_catalog"."default"
)
;ALTER TABLE "public"."teacher" OWNER TO "postgres";
CREATE TABLE "public"."use" (
  "id" int4 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "pwd" varchar(255) COLLATE "pg_catalog"."default"
)
;ALTER TABLE "public"."use" OWNER TO "postgres";
BEGIN;
LOCK TABLE "public"."company" IN SHARE MODE;
DELETE FROM "public"."company";
INSERT INTO "public"."company" ("id","name","age","address","salary") VALUES (1, 'Paul', 32, 'California                                        ', 20000),(2, 'Allen', 25, 'Texas                                             ', 15000),(3, 'Teddy', 23, 'Norway                                            ', 20000),(4, 'Mark', 25, 'Rich-Mond                                         ', 65000);
COMMIT;
BEGIN;
LOCK TABLE "public"."course" IN SHARE MODE;
DELETE FROM "public"."course";
INSERT INTO "public"."course" ("c_id","c_name","t_id") VALUES ('01', '语文', '02'),('02', '数学', '01'),('03', '英语', '03');
COMMIT;
BEGIN;
LOCK TABLE "public"."score" IN SHARE MODE;
DELETE FROM "public"."score";
INSERT INTO "public"."score" ("s_id","c_id","score") VALUES ('01', '01', 80),('01', '02', 90),('01', '03', 99),('02', '01', 70),('02', '02', 60),('02', '03', 80),('03', '01', 80),('03', '02', 80),('03', '03', 80),('04', '01', 50),('04', '02', 30),('04', '03', 20),('05', '01', 76),('05', '02', 87),('06', '01', 31),('06', '03', 34),('07', '02', 89),('07', '03', 98);
COMMIT;
BEGIN;
LOCK TABLE "public"."student" IN SHARE MODE;
DELETE FROM "public"."student";
INSERT INTO "public"."student" ("s_id","s_name","s_age","s_sex") VALUES ('01', '赵雷', '1990-01-01', '男'),('02', '钱电', '1990-12-21', '男'),('03', '孙风', '1990-05-20', '男'),('04', '李云', '1990-08-06', '男'),('05', '周梅', '1991-12-01', '女'),('06', '吴兰', '1992-03-01', '女'),('07', '郑竹', '1989-07-01', '女'),('08', '王菊', '1990-01-20', '女');
COMMIT;
BEGIN;
LOCK TABLE "public"."sys_user" IN SHARE MODE;
DELETE FROM "public"."sys_user";
INSERT INTO "public"."sys_user" ("id","age","collect_topic_num","collected_num","concert_user_num","concerted_user_num","create_time","deleted","icon","introduction","liked_num","login_count","last_login_time","money","nickname","open_id","password","phone","sex","status","username","vip") VALUES (23, 18, 0, 0, 0, 0, '2018-04-12 08:00:00', 0, '/upload/afd5c1ee-409e-43b2-86b4-b239e0e91fd6.jpg', '简介', 0, 0, '2018-04-12 08:00:00', 0, '龙傲天', 'wxAAAAAA123456789', 'password', '15666666666', 1, 0, '嗷嗷嗷3', 1),(24, 18, 0, 0, 0, 0, '2018-04-12 08:00:00', 0, '/upload/afd5c1ee-409e-43b2-86b4-b239e0e91fd6.jpg', '简介', 0, 0, '2018-04-12 08:00:00', 0, '龙傲天', 'wxAAAAAA123456789', 'password', '15666666666', 1, 0, '嗷嗷嗷4', 1),(25, NULL, 0, 0, 0, 0, NULL, 0, NULL, NULL, 0, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),(18, 23, 0, 0, 0, 0, '2018-04-12 08:00:00', 0, '/upload/afd5c1ee-409e-43b2-86b4-b239e0e91fd6.jpg', '简介', 0, 0, '2018-04-12 08:00:00', 0, '龙傲天', 'wxAAAAAA123456789', 'password', '15666666666', 1, 0, NULL, 1),(13, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '简介1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),(16, 18, 0, 0, 0, 0, '2018-04-12 08:00:00', 0, '/upload/afd5c1ee-409e-43b2-86b4-b239e0e91fd6.jpg', '简介', 0, 0, '2018-04-12 08:00:00', 0, '龙傲天', 'wxAAAAAA123456789', 'password', '15666666666', 1, 0, 'username', 1),(26, NULL, 0, 0, 0, 0, NULL, 0, NULL, NULL, 0, 0, NULL, 0, 'aaa啊啊啊', NULL, NULL, NULL, NULL, NULL, NULL, 1),(27, 18, 0, 0, 0, 0, NULL, 0, NULL, NULL, 0, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),(28, 18, 0, 0, 0, 0, NULL, 0, NULL, NULL, 0, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),(10, NULL, 0, 0, 0, 0, NULL, 1, NULL, NULL, 0, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),(11, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '简介1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),(12, NULL, 0, 0, 0, 0, NULL, 1, NULL, NULL, 0, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),(15, 18, 0, 0, 0, 0, '2018-04-12 08:00:00', 0, '/upload/afd5c1ee-409e-43b2-86b4-b239e0e91fd6.jpg', '简介', 0, 0, '2018-04-12 08:00:00', 0, '嗷嗷嗷', 'wxAAAAAA123456789', 'password', '15666666666', 1, 0, NULL, 1),(19, 18, 0, 0, 0, 0, '2018-04-12 08:00:00', 0, '/upload/afd5c1ee-409e-43b2-86b4-b239e0e91fd6.jpg', '简介', 0, 0, '2018-04-12 08:00:00', 0, '龙傲天', 'wxAAAAAA123456789', 'password', '15666666666', 1, 0, '嗷嗷嗷', 1),(1, 18, 0, 0, 0, 0, '2018-04-12 08:00:00', 0, '/upload/afd5c1ee-409e-43b2-86b4-b239e0e91fd6.jpg', '简介', 0, 0, '2018-04-12 08:00:00', 0, '龙傲天', 'wxAAAAAA123456789', 'password', '15666666666', 1, 0, NULL, 0),(21, 18, 0, 0, 0, 0, '2018-04-12 08:00:00', 0, '/upload/afd5c1ee-409e-43b2-86b4-b239e0e91fd6.jpg', '简介', 0, 0, '2018-04-12 08:00:00', 0, '龙傲天', 'wxAAAAAA123456789', 'password', '15666666666', 1, 0, '嗷嗷嗷1', 1);
COMMIT;
BEGIN;
LOCK TABLE "public"."tb_answer" IN SHARE MODE;
DELETE FROM "public"."tb_answer";
INSERT INTO "public"."tb_answer" ("id","o_id","q_id","u_id","deleted") VALUES (1, 16, 7, 1, 0),(3, 15, 11, 1, NULL),(4, 11, 7, 2, NULL),(5, 15, 11, 2, NULL),(6, 0, 0, 0, 0);
COMMIT;
BEGIN;
LOCK TABLE "public"."tb_classify" IN SHARE MODE;
DELETE FROM "public"."tb_classify";
INSERT INTO "public"."tb_classify" ("id","deleted","last","name","s_id","sort") VALUES (5, 0, 1, '试卷2', 4, 2),(3, 0, 1, '试卷1', 4, 1),(4, 0, 0, '英语科目试卷', 0, 3),(2, 0, 1, '数学科目试卷', 0, 2),(1, 0, 1, '语文课科目试卷', 0, 1);
COMMIT;
BEGIN;
LOCK TABLE "public"."tb_course" IN SHARE MODE;
DELETE FROM "public"."tb_course";
INSERT INTO "public"."tb_course" ("id","name","deleted") VALUES (3, '英语', NULL),(4, '化学', NULL),(1, '语文1', NULL),(2, '数学1', NULL);
COMMIT;
BEGIN;
LOCK TABLE "public"."tb_option" IN SHARE MODE;
DELETE FROM "public"."tb_option";
INSERT INTO "public"."tb_option" ("id","content","deleted","q_id","right_answer") VALUES (15, '第五题选项2', 0, 11, 0),(14, '第五题选项1', 0, 11, 0),(31, 'content', 0, NULL, 1),(32, 'content0', 0, 22, 1),(30, 'content2', 0, 22, 1),(33, 'content0', 0, 22, 1),(34, 'content0', 0, 22, 1),(28, 'content1', 1, 22, 1),(35, 'content7', 0, 7, 1),(16, '第四题选项1', 1, 7, 0),(11, 'content7', 0, 7, 1);
COMMIT;
BEGIN;
LOCK TABLE "public"."tb_person" IN SHARE MODE;
DELETE FROM "public"."tb_person";
INSERT INTO "public"."tb_person" ("id","password","user_name") VALUES (1, 'lijy', '123');
COMMIT;
BEGIN;
LOCK TABLE "public"."tb_question" IN SHARE MODE;
DELETE FROM "public"."tb_question";
INSERT INTO "public"."tb_question" ("id","deleted","title") VALUES (20, 0, '第二十题'),(11, 0, '第五题'),(22, 0, 'title'),(7, 1, 'title7'),(16, 0, '第七题'),(17, 0, '第八题'),(18, 0, '第九题');
COMMIT;
BEGIN;
LOCK TABLE "public"."tb_question_type" IN SHARE MODE;
DELETE FROM "public"."tb_question_type";
INSERT INTO "public"."tb_question_type" ("id","name","deleted") VALUES (1, '选择题', 0),(2, '填空题', 0),(3, '判断题', 0),(4, '论述题', 0);
COMMIT;
BEGIN;
LOCK TABLE "public"."tb_status" IN SHARE MODE;
DELETE FROM "public"."tb_status";
INSERT INTO "public"."tb_status" ("id","deleted","status") VALUES (1, 0, '删除'),(2, 0, '启用'),(3, 0, '停用');
COMMIT;
BEGIN;
LOCK TABLE "public"."tb_t_option" IN SHARE MODE;
DELETE FROM "public"."tb_t_option";
INSERT INTO "public"."tb_t_option" ("id","content","right_answer","t_id","deleted") VALUES (1, '选a', 1, 0, 0),(2, '选项a', 1, 4, NULL),(3, '选项a', 1, 5, NULL);
COMMIT;
BEGIN;
LOCK TABLE "public"."tb_topic" IN SHARE MODE;
DELETE FROM "public"."tb_topic";
INSERT INTO "public"."tb_topic" ("id","analysis","answer","classify_id","complexity","question_stem","question_type_id","deleted","status") VALUES (3, '因为', '答案是2', 3, '简单', '1+1=', 2, 0, 3),(1, '因为', '根据', 3, '困难', '三氯化铁鉴别反应是根据什么基团', 2, 0, 2),(2, '用为', '答案是1', 4, '普通', '三氯化铁鉴别反应是根据什么基团', 1, 0, 1),(8, '解析', '答案', 4, '简单', '三氯化铁鉴别反应是根据什么基团', 3, 0, 2);
COMMIT;
BEGIN;
LOCK TABLE "public"."tb_topic_course" IN SHARE MODE;
DELETE FROM "public"."tb_topic_course";
INSERT INTO "public"."tb_topic_course" ("id","c_id","t_id","deleted") VALUES (8, 2, 8, 0),(3, 1, 1, 0),(4, 2, 2, 0),(6, 2, 3, 0),(7, 1, 3, 0),(9, 3, 8, 0);
COMMIT;
BEGIN;
LOCK TABLE "public"."teacher" IN SHARE MODE;
DELETE FROM "public"."teacher";
INSERT INTO "public"."teacher" ("t_id","t_name") VALUES ('01', '张三'),('02', '李四'),('03', '王五');
COMMIT;
BEGIN;
LOCK TABLE "public"."use" IN SHARE MODE;
DELETE FROM "public"."use";
INSERT INTO "public"."use" ("id","name","pwd") VALUES (1, '1', '1'),(2, '2', '2'),(3, 'l', 'j');
COMMIT;
ALTER TABLE "public"."company" ADD CONSTRAINT "company_pkey" PRIMARY KEY ("id");
ALTER TABLE "public"."sys_user" ADD CONSTRAINT "uk_51bvuyvihefoh4kp5syh2jpi4" UNIQUE ("username");
ALTER TABLE "public"."sys_user" ADD CONSTRAINT "sys_user_pkey" PRIMARY KEY ("id");
ALTER TABLE "public"."tb_answer" ADD CONSTRAINT "tb_answer_pkey" PRIMARY KEY ("id");
ALTER TABLE "public"."tb_classify" ADD CONSTRAINT "tb_classify_pkey" PRIMARY KEY ("id");
ALTER TABLE "public"."tb_course" ADD CONSTRAINT "tb_course_pkey" PRIMARY KEY ("id");
ALTER TABLE "public"."tb_option" ADD CONSTRAINT "tb_option_pkey" PRIMARY KEY ("id");
ALTER TABLE "public"."tb_person" ADD CONSTRAINT "tb_person_pkey" PRIMARY KEY ("id");
ALTER TABLE "public"."tb_question" ADD CONSTRAINT "uk_5jt9abs29umsnes1iqkb50433" UNIQUE ("title");
ALTER TABLE "public"."tb_question" ADD CONSTRAINT "tb_question_pkey" PRIMARY KEY ("id");
ALTER TABLE "public"."tb_question_type" ADD CONSTRAINT "tb_question_type_pkey" PRIMARY KEY ("id");
ALTER TABLE "public"."tb_status" ADD CONSTRAINT "tb_status_pkey" PRIMARY KEY ("id");
ALTER TABLE "public"."tb_t_option" ADD CONSTRAINT "tb_t_option_pkey" PRIMARY KEY ("id");
ALTER TABLE "public"."tb_topic" ADD CONSTRAINT "tb_topic_pkey" PRIMARY KEY ("id");
ALTER TABLE "public"."tb_topic_course" ADD CONSTRAINT "tb_topic_course_pkey" PRIMARY KEY ("id");
ALTER SEQUENCE "public"."sys_user_id_seq"
OWNED BY "public"."sys_user"."id";
SELECT setval('"public"."sys_user_id_seq"', 29, true);
ALTER SEQUENCE "public"."sys_user_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "public"."tb_answer_id_seq"
OWNED BY "public"."tb_answer"."id";
SELECT setval('"public"."tb_answer_id_seq"', 7, true);
ALTER SEQUENCE "public"."tb_answer_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "public"."tb_classify_id_seq"
OWNED BY "public"."tb_classify"."id";
SELECT setval('"public"."tb_classify_id_seq"', 45, true);
ALTER SEQUENCE "public"."tb_classify_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "public"."tb_course_id_seq"
OWNED BY "public"."tb_course"."id";
SELECT setval('"public"."tb_course_id_seq"', 2, false);
ALTER SEQUENCE "public"."tb_course_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "public"."tb_option_id_seq"
OWNED BY "public"."tb_option"."id";
SELECT setval('"public"."tb_option_id_seq"', 36, true);
ALTER SEQUENCE "public"."tb_option_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "public"."tb_person_id_seq"
OWNED BY "public"."tb_person"."id";
SELECT setval('"public"."tb_person_id_seq"', 2, false);
ALTER SEQUENCE "public"."tb_person_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "public"."tb_question_id_seq"
OWNED BY "public"."tb_question"."id";
SELECT setval('"public"."tb_question_id_seq"', 23, true);
ALTER SEQUENCE "public"."tb_question_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "public"."tb_question_type_id_seq"
OWNED BY "public"."tb_question_type"."id";
SELECT setval('"public"."tb_question_type_id_seq"', 2, false);
ALTER SEQUENCE "public"."tb_question_type_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "public"."tb_status_id_seq"
OWNED BY "public"."tb_status"."id";
SELECT setval('"public"."tb_status_id_seq"', 2, false);
ALTER SEQUENCE "public"."tb_status_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "public"."tb_t_option_id_seq"
OWNED BY "public"."tb_t_option"."id";
SELECT setval('"public"."tb_t_option_id_seq"', 4, true);
ALTER SEQUENCE "public"."tb_t_option_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "public"."tb_topic_course_id_seq"
OWNED BY "public"."tb_topic_course"."id";
SELECT setval('"public"."tb_topic_course_id_seq"', 9, true);
ALTER SEQUENCE "public"."tb_topic_course_id_seq" OWNER TO "postgres";
ALTER SEQUENCE "public"."tb_topic_id_seq"
OWNED BY "public"."tb_topic"."id";
SELECT setval('"public"."tb_topic_id_seq"', 9, true);
ALTER SEQUENCE "public"."tb_topic_id_seq" OWNER TO "postgres";
