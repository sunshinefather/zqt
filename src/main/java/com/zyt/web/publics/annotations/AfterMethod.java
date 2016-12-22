package com.zyt.web.publics.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @描述 用于标注在aspectj的使用方法上面
 * @author maliang
 *         <p>
 *         该annotation只适用于方法上面,在进入目标方法之前进行
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface AfterMethod {
	// 描述
	String value() default "";

	LogType type();
}
