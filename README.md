# 20200529 整理出来一个纯净的版本下载可以修改数据库链接配置可以直接使用
# 管理账号15520449931 密码 188632
# 客户账号123456 123456

### 由于开放了两个账号数据库已经被删除了，点击地址预览也没什么东西，要看效果自己导入sql在本地看
### 由于开放了两个账号数据库已经被删除了，点击地址预览也没什么东西，要看效果自己导入sql在本地看
### 这是刚毕业的时候无聊弄的不管界面还是代码都不太美观_都不想再弄了_比较麻烦，以后不会更新了
### 随着经验的积累，自己会逐步完善和优化这项目的功能和代码美观度（为了自己以后能少复制粘贴）
### 感兴趣的可以发我邮箱it_tangzhang@163.com ，可以整理一份分享.


### 服务器不维护了，贴几张现在（202008版本的项目截图）
![image](https://ob-admin.oss-cn-beijing.aliyuncs.com/0.png)
![image](https://ob-admin.oss-cn-beijing.aliyuncs.com/1.png)
![image](https://ob-admin.oss-cn-beijing.aliyuncs.com/2.png)
![image](https://ob-admin.oss-cn-beijing.aliyuncs.com/3.png)
![image](https://ob-admin.oss-cn-beijing.aliyuncs.com/4.png)


--------------------------------------------------------------------------
# ob-admin

    客户后台管理系统  通用  springboot+shiro+redis+mysql +vue.js bootstrap(主) Layui前端框架 高度模仿微信小程序官方管理后台UI（看着简洁 偏白色调）

    目前主要针对小程序.......

    主要使用：开发者 客户

    开发者管理自己的客户以及客户的项目后台

    客户管理自己的项目后台 
    
主要目的:实现不同项目同一代码实现，为自己管理客户的后台系统，进行维护和升级方便！
    
--------------------------------------------------------------------------
配置redis参考:http://www.tzss.ink/toMessage?pid=116&pageNo=1


## 本来想做成通用框架，渐渐发现难度太大且可分享性不好（难以轻易复制项目），有时间的朋友可以看看或则copy一下里面现成的工具类java版本



##### 2019-1-11 完成基本架构+微信小程序用户统计

##### 2019-1-23 完成轮播图模块_可实现微信小程序navgator组件的核心功能_轮播图内的图片可自定义跳转   轮播图模块重复使用

##### 2019-1-25 完成微信商户平台资料管理模块


##### 2019-3-1 开始用在全V健康咨询项目框架上

      项目不再更新，每次优化代码都要同时该几处，不太方便，自己的有客户的项目又不方便开源，基本思想类似
      
#项目地址为一个demo展示效果用  __不要删除客户账号!!!   不要删除客户账号!!!   不要删除客户账号!!!

      账号  15520449931  123456  为 管理员账号
      账号  test         123456  为  客户账号   


--------------------------------------------------------------------------
### 存在BUG

1.登录时，如果用户账户下没有可管理的项目，再次进入会发生NULL异常，因为系统由账户缓存能力，只要不显示用户名或者密码错误都会进行登录验证缓存，下一次不管输入什么都能登录成功，登录成功会进入上一次管理的项目（多个项目，或则单个项目，避免每次都重新选择）（但是必须用户首次登录能正确的选择项目才行）_____可选择在合适的时机清除shiro的账户缓存


