##框架使用说明:
	1.框架整体目录结构说明
		a:框架大体上分4个编译目录（src/main/java,src/main/resources,src/test/java,src/test/resources)
		其中两个是测试文件所在。
		
		b:项目的开发文件以及配置文件存放在(src/main/java,src/main/resources)两个目录下
		
		c:src/main/java-该目录是开发目录，(com.zyt.web 这个包前缀)在这个下面主要分三个大块
			c.1:after---后台管理系统维护模块，这个里面主要存放后台管理系统controller,service,serviceImpl的编写
			c.2:api---远程调用接口
			c.3:front---前端系统维护模块，编码逻辑同上。
			c.4:openfire---系统消息
			c.5:publis--- 前后端系统公用模块，将一些公用的模块存放在这里，这样可以做到 after-publics | front-publics
				可以拆分为两套独立的系统部署
				publics:目前包含exception,listener,module,servlet,utils
				其中module是实体跟mapper.xml的集合
			c.6:security---spring安全模块
		d:src/main/resources
			包含 mybatis spring ehcache mysql-config 这些配置文件，需要可在下面找到
			
	2.系统目前包含的功能
	session监听，容器监听,实体验证，缓存存取，验证码获取，异常拦截
		
	3.目前框架集成了jsp,httl，json,xml 模板解析技术(如需集成其他模板可自行扩展)
	
	4.对于controller url的地址规范 spring restful
	示例:
	web:项目名称
	blog:模块名称
	1:操作ID
	尽量避免使用&参数连接方式
	http://127.0.0.1/web/blog/    method.post :  (add)
	http://127.0.0.1/web/blog/1   method.get :  (get)
	http://127.0.0.1/web/blog/1   method.put :  (update)
	http://127.0.0.1/web/blog/1   method.delete :  (delete)
	5.远程调用Api的URL
	http://[localhost:8080]/Framework/api/msg []中的内容待项目发布之后需要更改
	msg:为模块名称
	
	
	分布式Session：
		1. 实现入口 :SessionFilter, 在dofilter之前根据cookie id从memcached 获取值后加载到SessionContext中， dofilter之后如果SessionContext的值改变，则重新写回memcached
		2. 使用：注入SessionContext使用，例子(参照BaseController)：
		sessionContext.set(Key.stringKey(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY), JSONConverter.toString(SecurityContextHolder.getContext()));
		String userStr = session.get(Key.stringKey(SystemConfig.SystemConstant.SESSIONUSER));
		User userDetails =  JSONConverter.fromString(User.class, userStr);
		
		
	分布式缓存:
		1.实现入口：MemcachedCache, MemcachedProvider, CacheConfig
		2.增加缓存：
		添加缓存块 CacheConfig#addCaches(CacheRegistry registry) 
		在需要加缓存的方法上添加 @Cacheable(CacheModuleConstants.###)
		需要加缓存的方法的参数必须重写toString()方法，返回值实现Serializable接口
		3.清楚缓存
		在需要触发清除缓存的方法上添加@CacheEvict(values={CacheModuleConstants.###},allEntries=true)
		
	分布式锁：
		1. 注入DistributeLock;
		try {
		    lock.lock(LockStack.ADDSKULOCK, "lockActionName");
		    #your business code goes here
		    } finally {
		    try {
                lock.unLock(LockStack.ADDSKULOCK);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
		LockStack定义被锁定的资源, lockActionName定义改资源上的行为
		
		配置缓存服务器，在hosts中添加：
		112.124.48.180 sessionserver
		112.124.48.180 cacheserver
		112.124.48.180 lockserver