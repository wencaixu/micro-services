CREATE DATABASE  ad_seckill;

USE ad_seckill;

create table t_goods
(
  id           int auto_increment comment '商品ID'
    primary key,
  goods_name   varchar(255)   null comment '商品名称',
  goods_title  varchar(255)   null comment '商品标题',
  goods_img    varchar(255)   null comment '商品图片',
  goods_detail varchar(255)   null comment '商品详情',
  goods_price  decimal(10, 2) null comment '商品价格',
  goods_stock  int            null comment '商品库存'
);

create table t_order
(
  id               int auto_increment comment '订单id'
    primary key,
  user_id          bigint                      null comment '用户id',
  delivery_addr_id bigint                      null comment '收货地址id',
  goods_name       varchar(100)                null comment '商品名称，冗余',
  goods_count      int            default 0    null comment '商品个数',
  goods_price      decimal(10, 2) default 0.00 null comment '善品单价',
  order_channel    tinyint                     null comment '1pc 2android 3 ios',
  status           tinyint        default 0    null comment '订单状态，0，新建未支付，1已支付，2已发货，4已退款5已完成',
  create_date      datetime                    null comment '订单创建时间',
  pay_date         datetime                    null comment '支付时间',
  goods_id         bigint                      null comment '商品id'
)
  comment '订单表';

create table t_seckill_goods
(
  id            bigint auto_increment comment '秒杀商品id'
    primary key,
  goods_id      bigint         null comment '商品id',
  seckill_price decimal(10, 2) null comment '秒杀价格',
  stock_count   int            null comment '库存数量',
  start_date    datetime       null comment '秒杀开始时间',
  end_date      datetime       null comment '秒杀结束时间'
);

create table t_seckill_order
(
  id       int auto_increment comment '订单ID'
    primary key,
  user_id  bigint null comment '用户ID',
  order_id bigint null comment '订单ID',
  goods_id bigint null comment '商品ID'
);

create table t_user
(
  id              bigint        not null comment '用户id,手机号'
    primary key,
  nickname        varchar(255)  not null,
  password        varchar(32)   not null comment 'MD5(MD5(pass明文+固定salt)+salt)',
  salt            varchar(10)   null,
  head            varchar(128)  null comment '头像',
  register_data   datetime      null comment '注册时间',
  last_login_data datetime      null comment '最后一次登录时间',
  login_count     int default 0 null comment '登录次数'
)
  comment '用户表';

INSERT INTO ad_seckill.t_goods (id, goods_name, goods_title, goods_img, goods_detail, goods_price, goods_stock) VALUES (1, 'IPHONE 12 64GB', 'IPHONE 12 64GB', '/img/iphone12.png', 'IPHONE 12 64GB', 6299.00, 100);
INSERT INTO ad_seckill.t_goods (id, goods_name, goods_title, goods_img, goods_detail, goods_price, goods_stock) VALUES (2, 'IPHONE 12 pro 64GB', 'IPHONE 12 64GB', '/img/iphone12pro.png', 'IPHONE 12 pro 64GB', 6299.00, 100);

INSERT INTO ad_seckill.t_order (id, user_id, delivery_addr_id, goods_name, goods_count, goods_price, order_channel, status, create_date, pay_date, goods_id) VALUES (1, 15837812297, 0, 'IPHONE 12 64GB', 1, 629.00, 1, 0, '2021-08-08 13:44:26', null, 1);
INSERT INTO ad_seckill.t_order (id, user_id, delivery_addr_id, goods_name, goods_count, goods_price, order_channel, status, create_date, pay_date, goods_id) VALUES (2, 15837812297, 0, 'IPHONE 12 64GB', 1, 629.00, 1, 0, '2021-08-08 13:45:57', null, 1);
INSERT INTO ad_seckill.t_order (id, user_id, delivery_addr_id, goods_name, goods_count, goods_price, order_channel, status, create_date, pay_date, goods_id) VALUES (3, 15837812297, 0, 'IPHONE 12 64GB', 1, 629.00, 1, 0, '2021-08-08 13:48:22', null, 1);
INSERT INTO ad_seckill.t_order (id, user_id, delivery_addr_id, goods_name, goods_count, goods_price, order_channel, status, create_date, pay_date, goods_id) VALUES (4, 15837812297, 0, 'IPHONE 12 64GB', 1, 629.00, 1, 0, '2021-08-08 13:58:29', null, 1);

INSERT INTO ad_seckill.t_seckill_goods (id, goods_id, seckill_price, stock_count, start_date, end_date) VALUES (1, 1, 629.00, 10, '2021-08-10 15:10:06', '2021-08-11 15:10:08');
INSERT INTO ad_seckill.t_seckill_goods (id, goods_id, seckill_price, stock_count, start_date, end_date) VALUES (2, 2, 629.00, 10, '2021-07-28 15:10:06', '2021-07-29 15:10:08');

INSERT INTO ad_seckill.t_seckill_order (id, user_id, order_id, goods_id) VALUES (2, 15837812297, 4, 1);

INSERT INTO ad_seckill.t_user (id, nickname, password, salt, head, register_data, last_login_data, login_count) VALUES (15837812297, '才哥有点胖', 'cf93a9fce6f40291403b114a020a4075', 'xwcxwj1111', '123', '2021-07-26 14:52:41', '2021-07-26 14:52:44', 1);