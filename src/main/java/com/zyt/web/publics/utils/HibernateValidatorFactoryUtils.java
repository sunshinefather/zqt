package com.zyt.web.publics.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author maliang 目前支持两种验证，实体验证，属性验证，如需要自行扩展
 */
public class HibernateValidatorFactoryUtils {

	private static ValidatorFactory factory = Validation
			.buildDefaultValidatorFactory();

	private static Logger log = LoggerFactory
			.getLogger(HibernateValidatorFactoryUtils.class);

	/**
	 * 私有构造函数 maliang 下午8:26:09
	 */
	private HibernateValidatorFactoryUtils() {
	};

	/**
	 * @描述 验证实体
	 * @auto maliang
	 * @time 2014-3-3 下午8:32:43
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> verifyObject(Object object) {
		Map<String, Object> maps = null;
		try {
			Set<ConstraintViolation<Object>> constraintViolations = factory
					.getValidator().validate(object, new Class<?>[0]);
			if (!constraintViolations.isEmpty()) {
				maps = new HashMap<String, Object>();
				for (ConstraintViolation<?> constraintViolation : constraintViolations) {
					maps.put(constraintViolation.getPropertyPath().toString(),
							constraintViolation.getMessage());
				}
				// 统一异常返回结果
				maps.put("result", false);
				maps.put("message", "实体验证未通过");
			}
		} catch (Exception e) {
			log.error("验证实体错误: " + object.getClass().getName());
			e.printStackTrace();
		}
		return maps;
	}

	/**
	 * @描述 验证实体指定字段
	 * @auto maliang
	 * @time 2014-3-3 下午8:41:24
	 * @param object
	 * @param propertyName
	 *            字段名称
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> verifyObjectAtField(Object object,
			String propertyName) {
		Map<String, Object> maps = null;
		try {
			Set<ConstraintViolation<Object>> constraintViolations = factory
					.getValidator().validateProperty(object, propertyName,
							new Class<?>[0]);
			if (!constraintViolations.isEmpty()) {
				maps = new HashMap<String, Object>();
				for (ConstraintViolation<?> constraintViolation : constraintViolations) {
					maps.put(constraintViolation.getPropertyPath().toString(),
							constraintViolation.getMessage());
				}
			}
		} catch (Exception e) {
			log.error("验证实体错误: " + object.getClass().getName());
			e.printStackTrace();
		}
		return maps;
	}

}