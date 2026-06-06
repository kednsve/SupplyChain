## Customer

​	Customer selectById

​		通过id查询客户

​	List<Customer> list

​		通过姓名模糊查询，类别精确查询，分页

​	void deleteByCustomerId

​		通过id(数组)删除客户

​		同时删除该客户订单

​	void update

​		更新客户信息

## Order

​	Order getOrderById

​		通过id精确查询

​	List<Order> getOrders

​		通过日期限定，销售额限定，客户id，订单状态，订单地区、国家、城市查询订单，分页

​	void deleteByCustomerId

​		通过客户id(数组)删除订单

​		同时删除订单物品和订单物流

​	void update

​		更新订单信息

​	void deleteByOrderId

​		通过订单id(数组)删除订单

​		同时删除订单物品和订单物流

​	Integer[] getIdByCustomerId

​		通过客户id(数组)获取订单id(数组)

​	void updateSales

​		更新销售额，用于更新订单物品时同时更新销售额

## Logistics

​	List<Logistics> getLogistics

​		通过实际和计划物流天数限定，发货日期限定，物流状态，延误风险，物流类型，订单id查询物流信息

​	Logistics getByOrderId

​		通过订单id查询物流信息

​	void delByOrderId

​		通过订单id(数组)删除物流信息

​	void update

​		更新物流信息

## OrderItems

​	void delByOrderId

​		通过订单id删除物品

​	void update

​		更新订单物品，同时更新订单销售额

​	List<OrderItems> getByOrderId

​		通过订单id查询订单物品

## Product

​	void delById

​		通过产品id删除产品

​	void update

​		更新产品信息

​	Product getById

​		根据id查询产品信息

​	List<Product> getProducts

​		通过姓名模糊查询，价格限定，部门id和分类id匹配查询产品信息

## Department

​	void delById

​		通过id删除部门

​	void update

​		更新部门信息

​	Department getById

​		通过id查询部门信息

​	List<Department> getDepartments

​		通过姓名模糊查询部门