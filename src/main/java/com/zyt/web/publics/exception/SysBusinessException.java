package com.zyt.web.publics.exception;
/**
 * attention : Haven't work for its distribution
 * @author Hadoop
 *
 */
public class SysBusinessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6648127031776991401L;

	public SysBusinessException(String exceptionMsg) {
		super(exceptionMsg);
	}

	public SysBusinessException(Throwable throwable) {
		super(throwable);
	}

	public SysBusinessException(String exceptionMsg, Throwable throwable) {
		super(exceptionMsg, throwable);
	}

	@Override
	public String toString() {
		return this.getMessage();
	}
	
}
