package x10.types.constraints;

import polyglot.types.Def;
import polyglot.types.FieldDef;
import polyglot.types.MethodDef;
import polyglot.types.Type;
import polyglot.types.Types;
import polyglot.types.VarDef;
import x10.constraint.XField;
import x10.constraint.XTerm;
import x10.constraint.XVar;
import x10.types.X10ClassDef;
import x10.types.X10FieldDef;



public class QualifiedVar extends XField<Type> {
    private static final long serialVersionUID = -407228981450822754L;
    
    // lazily initialized
    private String string;
    private String getString() {
        if (string == null) {
            string = field + "." + receiver;
        }
        return string;
    }
    
    public QualifiedVar(Type fi, XVar r) {
        super(r, fi, false);
    }
   
    @Override
    public QualifiedVar copyReceiver(XVar newReceiver) {
        if (newReceiver == receiver)
            return this;
        return new QualifiedVar(field, newReceiver);
    }
    /**
     * Return the type of this term. The type of a qualified term
     * is the type carried by the qualifier. i.e. the type of A.this
     * is A.
     * @return
     */
    public Type type() {
      return field;
    }
    public XVar var() {
        return receiver;
    }
    
    @Override
    public XTerm subst(XTerm y, XVar x, boolean propagate) {
        return equals(x) ? y : 
            receiver.equals(x) 
            ? copyReceiver((XVar) y)
                    : super.subst(y, x, propagate);
    }
    
    public boolean equals(Object x) {
        if (! (x instanceof QualifiedVar)) {
            return false;
        }
        QualifiedVar o = (QualifiedVar) x;
        return receiver.equals(o.receiver) && field == o.field;
    }
  /*  public XVar thisVar() {
        if (field instanceof X10FieldDef) {
            return ((X10ClassDef) Types.get(((X10FieldDef) field).container()).toClass().def()).thisVar();
        }
        return null;
    }
  */
  
    @Override
    public String toString() {
        return getString();
    }
    
}
