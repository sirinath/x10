package polyglot.ext.x10.ast;

import polyglot.ast.Cast;

public interface X10Cast extends Cast {

	public boolean notNullRequired();

	public boolean isPrimitiveCast();
	
	public boolean isToTypeNullable();

	public void setToTypeNullable(boolean b);

	public void setPrimitiveCast(boolean b);

	public void setNotNullRequired(boolean b);
}
