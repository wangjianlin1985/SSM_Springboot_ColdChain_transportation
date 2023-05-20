# SSM_Springboot_ColdChain_transportation
基于SSM冷链运输温度监控系统可升级SpringBoot毕业源码案例设计

## 前台框架： Bootstrap(一个HTML5响应式框架)
## 后台框架: SSM(SpringMVC + Spring + Mybatis)
## 开发环境：myEclipse/Eclipse/Idea + mysql数据库

(1)提供一个友好、美观、方便，快捷易用的网页界面和操作界面。

(2)提供稳定的查询功能，用户可以很快的通过关键字搜索自己想要知道的产品实时温度。

(3)冷藏车实时温度数据采集(数据库模拟数据)。

(4)数据上传至互联网服务器。

(5)服务器对数据进行分析判断，对温度超值发出预警。(红色警告)
## 实体ER属性：
用户: 用户名,登录密码,姓名,性别,出生日期,用户照片,联系电话,邮箱,家庭地址,注册时间

产品分类: 产品分类id,产品分类名称

产品: 产品id,产品分类,产品名称,产品照片,产品介绍,最低温度,最高温度,发布时间

冷藏车: 车辆编号,车辆种类,品牌,排量,车辆照片,冷藏车介绍,当前温度,备注信息

冷藏车商品: 记录id,产品名称,所在冷藏车,产品数量,备注信息

实时温度: 记录id,冷藏车,采集温度,采集的用户,采集时间

新闻公告: 公告id,标题,公告内容,发布时间