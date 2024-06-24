package com.meeting.manager.dialect;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.usertype.DynamicParameterizedType;

import java.util.Properties;

/**
 * @author 叶子忺
 */
public class StringArrayType
		extends AbstractSingleColumnStandardBasicType<String[]>
		implements DynamicParameterizedType {
	public StringArrayType() {
		super( ArraySqlTypeDescriptor.INSTANCE, StringArrayTypeDescriptor.INSTANCE );
	}
	public String getName() {
		return "string-array";
	}
	@Override
	protected boolean registerUnderJavaType() {
		return true;
	}
	@Override
	public void setParameterValues(Properties parameters) {
		((StringArrayTypeDescriptor) getJavaTypeDescriptor()).setParameterValues(parameters);
	}
}