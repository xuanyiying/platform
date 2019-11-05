 Auth:
    登录： POST localhost:8083/loin 参数:  username password
    RETURN {"success" : true}
    退出：DELETE localhost:8083/logout  参数: id(用户id)
 User:
    注册：POST localhost:8083/user/register user 
    json:{
        "invitedNum": "WG5V78",
        "password": "123456",
        "phone": "15529328373",
        "username": "张三"
        "avatar_data":"二进制数据";
    }
    RETURN {"success" : true}
        修改密码：PUT localhost:8083/user/{id} 参数: password 
            RETURN {"success" : true}
    获取用户：GET localhost:8083/user/{id} 
            return user:{
                    "id": 1,
                    "invitedNum": "WG5V78",
                    "password": "123456",
                    "phone": "15529328373",
                    "username": "张三"
                    "avatar_data":"二进制数据";
                }
                
    获取用户级别：GET localhost:8083/user/priorities
                return {0,2,4,6}
            
    修改用户级别：
        PUT localhost:8083/user/priority/{id}   priority(0,2,4,6) RETURN {"success" : true}
        /**
         * 普通用户
         */
        ORDINARY("ORDINARY",0),
        /**
         *  二级代理
         */
        SECONDARY("SECONDARY",2),
        /**
         * 特级代理
         */
        SPECIAL("SPECIAL",4),
        /**
         * 超级代理
         */
        SUPER("SUPER",6);   
 Product:
    获取单个商品：
        GET localhost:8083/product/{id} 
        RETURN {"id" : 1,"name":"","description":"","saleNum":"","unitPrice":"","discount":"","stock":"","status":"Online","imageData":"二进制数据"}
    获取商品列表：
        GET localhost:8083/products 参数:  page页码 limit 每页数据大小 sort 排序属性 direction  排序方向 search 条件查询(json)
        return {
               	"limit":0,
               	"list":[
               		{
                    	"id": 1,
                    	"name": "",
                    	"description": "",
                    	"saleNum": "",
                    	"unitPrice": "",
                    	"discount": "",
                    	"stock": "",
                    	"status": "Online",
                    	"imageData": "二进制数据"
                    },
                    {
                    	"id": 2,
                    	"name": "",
                    	"description": "",
                    	"saleNum": "",
                    	"unitPrice": "",
                    	"discount": "",
                    	"stock": "",
                    	"status": "Online",
                    	"imageData": "二进制数据"
                    }

               	],
               	"page":0,
               	"pages":1,
               	"total":2
               }
 Order:
       下单：POST  localhost:8083/order
                 RequestBody: {
                              	"address": "",
                              	"phone": "",
                              	"owner": "",
                              	"totalMoney": "",
                              	"items":"[
                                         {"productId"："",
                                          "num": "",
                                          "unitPrice": "",
                                          "discount": ""
                                          }
                                		 ]"
                              }
             return       {"success" : true}         
       审批订单：PUT  localhost:8083/order/approval/{id}   
                 return       {"success" : true}     
       提交订单：PUT  localhost:8083/order/commit/{id}   
                 return       {"success" : true}      
       取消订单：PUT  localhost:8083/order/cancel/{id}   
                 return       {"success" : true}     
                 
       根据用户所以订单：GET   localhost:8083/order 参数: username
                    return             [  {
                                            "id": "",
                                            "address": "",
                                            "phone": "",
                                            "owner": "",
                                            "totalMoney": "",
                                            "createdDate": "",
                                            "items":"[
                                                   {"productId"："",
                                                    "num": "",
                                                    "unitPrice": "",
                                                    "discount": ""
                                                    }
                                                 ]"
                                            },
                                            {
                                              "id": "",
                                              "address": "",
                                              "phone": "",
                                              "owner": "",
                                              "totalMoney": "",
                                              "createdDate": "",
                                              "items":"[
                                                     {"productId"："",
                                                      "num": "",
                                                      "unitPrice": "",
                                                      "discount": ""
                                                      }
                                                   ]"
                                            }
                                         ]
       根据id获取订单：GET  localhost:8083/order/{id}
                    return               {
                                                    	"id": "",
                                                    	"address": "",
                                                    	"phone": "",
                                                    	"owner": "",
                                                    	"totalMoney": "",
                                                    	"createdDate": "",
                                                    	"items":"[
                                                               {"productId"："",
                                                                "num": "",
                                                                "unitPrice": "",
                                                                "discount": ""
                                                                }
                                                      		 ]"
                                                    }