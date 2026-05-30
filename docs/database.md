# 数据库表

## 概念设计

customer

![customer](F:\MyProjects\SupplyChain\docs\imgs\customer.png)

department

![department](F:\MyProjects\SupplyChain\docs\imgs\department.png)

category

![category](F:\MyProjects\SupplyChain\docs\imgs\category.png)

logistics

![logistics](F:\MyProjects\SupplyChain\docs\imgs\logistics.png)

order

![order](F:\MyProjects\SupplyChain\docs\imgs\order.png)

order_itmes

![order_items](F:\MyProjects\SupplyChain\docs\imgs\order_items.png)

product

![product](F:\MyProjects\SupplyChain\docs\imgs\product.png)

整体

![E-R](F:\MyProjects\SupplyChain\docs\imgs\E-R.png)

## 逻辑设计

customer

| 字段名  | 数据类型    | 主键 | 非空 | 约束     | 注释     |
| ------- | ----------- | ---- | ---- | -------- | -------- |
| id      | int         | 是   | 是   |          | 客户id   |
| name    | varchar(30) | 否   | 是   |          | 客户姓名 |
| segment | varchar(20) | 否   | 是   | 检查约束 | 客户类别 |


order

| 字段名      | 数据类型      | 主键 | 非空 | 约束     | 注释     |
| ----------- | ------------- | ---- | ---- | -------- | -------- |
| id          | int           | 是   | 是   |          | 订单id   |
| date        | datetime      | 否   | 是   |          | 下单时间 |
| customer_id | int           | 否   | 是   | 外键约束 | 客户id   |
| sales       | decimal(10,2) | 否   | 是   | 检查约束 | 销售额   |
| profit      | decimal(10,2) | 否   | 是   |          | 利润     |
| status      | varchar(20)   | 否   | 是   | 检查约束 | 订单状态 |
| reion       | varchar(20) | 否   | 是   |          | 地区     |
| country     | varchar(40) | 否   | 是   |          | 国家     |
| city        | varchar(40) | 否   | 是   |          | 城市     |

logistics

| 字段名                  | 数据类型    | 主键 | 非空 | 约束     | 注释          |
| ----------------------- | ----------- | ---- | ---- | -------- | ------------- |
| id                      | int         | 是   | 是   |          | 物流          |
| shipping_days_real      | int         | 否   | 否   |          | 实际物流天数  |
| shipping_days_scheduled | int         | 否   | 是   |          | 计划物流天数  |
| delivery_status         | varchar(20) | 否   | 是   | 检查约束 | 物流状态      |
| late_risk               | tinyint     | 否   | 是   | 检查约束 | 延误风险(0/1) |
| shipping_date           | date        | 否   | 是   |          | 发货日期      |
| shipping_mode           | varchar(20) | 否   | 是   | 检查约束 | 物流类型      |
| order_id                | int         | 否   | 是   | 外键约束 | 订单id        |

product

| 字段名        | 数据类型      | 主键 | 非空 | 约束     | 注释     |
| ------------- | ------------- | ---- | ---- | -------- | -------- |
| id            | int           | 是   | 是   |          | 产品id   |
| name          | varchar(100)  | 否   | 是   |          | 产品名称 |
| image         | varchar(512)  | 否   | 是   |          | 产品图片 |
| price         | decimal(10,2) | 否   | 是   | 检查约束 | 产品单价 |
| category_id   | int           | 否   | 是   | 外键约束 | 类别id   |
| department_id | int           | 否   | 是   | 外键约束 | 部门id   |

order_items

| 字段名     | 数据类型      | 主键 | 非空 | 约束     | 注释            |
| ---------- | ------------- | ---- | ---- | -------- | --------------- |
| id         | int           | 是   | 是   |          | 主键            |
| order_id   | int           | 否   | 是   | 外键约束 | 订单id          |
| product_id | int           | 否   | 是   | 外键约束 | 产品id          |
| quantity   | int           | 否   | 是   | 检查约束 | 数量            |
| price      | decimal(10,2) | 否   | 是   | 检查约束 | 单价            |
| total      | decimal(10,2) | 否   | 是   |          | 总价(数量*单价) |

category

| 字段名 | 数据类型    | 主键 | 非空 | 约束 | 注释     |
| ------ | ----------- | ---- | ---- | ---- | -------- |
| id     | int         | 是   | 是   |      | 类别id   |
| name   | varchar(30) | 否   | 是   |      | 类别名称 |

department

| 字段名 | 数据类型    | 主键 | 非空 | 约束 | 注释     |
| ------ | ----------- | ---- | ---- | ---- | -------- |
| id     | int         | 是   | 是   |      | 部门id   |
| name   | varchar(30) | 否   | 是   |      | 部门名称 |

