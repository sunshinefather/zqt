package com.zyt.web.publics.base.mybatis.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zyt.web.publics.base.mybatis.SQLKit;
import com.zyt.web.publics.base.mybatis.pagination.PaginationAble;
import com.zyt.web.publics.utils.ReflectHelper;

/**
 * 分页拦截器
 * 
 * @author Hadoop
 * 
 */
/**
 * 拦截器标签声明是一个拦截器，拦截的目标类型是StatementHandler且type只能为借口类型，
 * 拦截的方法是名称为prepare参数为Connection类型的方法
 * 拦截器可用于Executor,ParameterHandler,ResultSetHandler和StatementHandler这些类上
 * 
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {
	private final Logger PaginationLogger = LoggerFactory
			.getLogger(PaginationInterceptor.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation
				.getTarget();
		BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper
				.getValueByFieldName(statementHandler, "delegate");
		DefaultParameterHandler defaultParameterHandler = (DefaultParameterHandler) ReflectHelper
				.getValueByFieldName(delegate, "parameterHandler");
		BoundSql boundSql = statementHandler.getBoundSql();
		RowBounds rowBounds = (RowBounds) ReflectHelper.getValueByFieldName(
				delegate, "rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}
		String originalSql = boundSql.getSql();
		ReflectHelper.setValueByFieldName(
				boundSql,
				"sql",
				getMysqlPageSql(originalSql, rowBounds.getOffset(),
						rowBounds.getLimit()));
		ReflectHelper.setValueByFieldName(rowBounds, "offset",
				RowBounds.NO_ROW_OFFSET);
		ReflectHelper.setValueByFieldName(rowBounds, "limit",
				RowBounds.NO_ROW_LIMIT);
		Object parameterObject = defaultParameterHandler.getParameterObject();
		// 查询参数对象
		PaginationAble paginationAble = null;
		if (parameterObject != null
				&& !(parameterObject instanceof PaginationAble)) {
			return invocation.proceed();
		} else {

			if (parameterObject == null) {
				paginationAble = PaginationAble.getPaginationAble();
			} else {
				paginationAble = (PaginationAble) parameterObject;
			}
		}

		Connection connection = (Connection) invocation.getArgs()[0];
		String statSQL = SQLKit.getTotalResultSQL(originalSql);

		PreparedStatement preparedStatement = connection
				.prepareStatement(statSQL);
		PaginationLogger.debug("总数SQL : " + statSQL);
		defaultParameterHandler.setParameters(preparedStatement);
		ResultSet rs = preparedStatement.executeQuery();
		int totalResults = 0;
		int totalPages = 0;
		if (rs.next()) {
			totalResults = rs.getInt(1);
		}
		rs.close();
		preparedStatement.close();
		paginationAble.setTotalResults(totalResults);
		paginationAble.setTotalPages(totalPages);
		PaginationLogger.debug("分页SQL : " + boundSql.getSql());
		return invocation.proceed();
	}

	/**
	 * 
	 * @param originalSql
	 *            原始SQL
	 * @param offset
	 *            偏移位置
	 * @param limit
	 *            偏移量
	 * @return
	 */
	private String getMysqlPageSql(String originalSql, Integer offset,
			Integer limit) {
		// 计算第一条记录的位置，Mysql中记录的位置是从0开始的。
		StringBuilder sb = new StringBuilder(originalSql);
		if (sb.lastIndexOf(";") == sb.length() - 1) {
			sb.deleteCharAt(sb.length() - 1);
		}
		originalSql = sb.toString() + " LIMIT " + offset + " , " + limit;
		// 改变原来的方式
		/*
		 * originalSql = originalSql.concat(" LIMIT ")
		 * .concat(String.valueOf(offset)).concat(",")
		 * .concat(String.valueOf(limit));
		 */
		return originalSql;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}
}