CREATE TABLE `fund` (
                        `id` bigint(128) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                        `code` varchar(6) NOT NULL COMMENT '基金代码',
                        `name` varchar(32) NOT NULL COMMENT '基金名称',
                        `distribute` VARCHAR(256) NOT NULL COMMENT '投资分布',
                        `stock_distribute` text NOT NULL COMMENT '基金持股',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='基金表';