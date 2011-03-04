$Recover
    ErrorId
$End

$Rules
    TypeName ::= TypeName . ErrorId
            /.$BeginJava
                        setResult(new Name(nf,
                                          ts,
                                          pos(getLeftSpan(), getRightSpan()),
                                          TypeName,
                                          "*"));
              $EndJava
            ./

    PackageName ::= PackageName . ErrorId
            /.$BeginJava
                        setResult(new Name(nf,
                                          ts,
                                          pos(getLeftSpan(), getRightSpan()),
                                          PackageName,
                                          "*"));
              $EndJava
            ./
    
    ExpressionName ::= AmbiguousName . ErrorId
            /.$BeginJava
                        setResult(new Name(nf,
                                          ts,
                                          pos(getLeftSpan(), getRightSpan()),
                                          AmbiguousName,
                                          "*"));
              $EndJava
            ./

    MethodName ::= AmbiguousName . ErrorId
            /.$BeginJava
                        setResult(new Name(nf,
                                          ts,
                                          pos(getLeftSpan(), getRightSpan()),
                                          AmbiguousName,
                                          "*"));
              $EndJava
            ./

    PackageOrTypeName ::= PackageOrTypeName . ErrorId
            /.$BeginJava
                        setResult(new Name(nf,
                                          ts,
                                          pos(getLeftSpan(), getRightSpan()),
                                          PackageOrTypeName,
                                          "*"));
              $EndJava
            ./

    AmbiguousName ::= AmbiguousName . ErrorId
            /.$BeginJava
                        setResult(new Name(nf,
                                          ts,
                                          pos(getLeftSpan(), getRightSpan()),
                                          AmbiguousName,
                                          "*"));
             $EndJava
            ./

    FieldAccess ::= Primary . ErrorId
        /.$BeginJava
                    setResult(nf.Field(pos(), Primary, "*"));
          $EndJava
        ./
                  | super . ErrorId
        /.$BeginJava
                    setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getLeftSpan())), "*"));
          $EndJava
        ./
                  | ClassName . super$sup . ErrorId
        /.$BeginJava
                    setResult(nf.Field(pos(getRightSpan()), nf.Super(pos(getRhsFirstTokenIndex($sup)), ClassName.toType()), "*"));
          $EndJava
        ./

    MethodInvocation ::= MethodPrimaryPrefix ( ArgumentListopt )
        /.$BeginJava
                    Expr Primary = (Expr) ((Object[]) MethodPrimaryPrefix)[0];
                    polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) ((Object[]) MethodPrimaryPrefix)[1];
                    setResult(nf.Call(pos(), Primary, identifier.getIdentifier(), ArgumentListopt));
          $EndJava
        ./
                       | MethodSuperPrefix ( ArgumentListopt )
        /.$BeginJava
                    polyglot.lex.Identifier identifier = MethodSuperPrefix;
                    setResult(nf.Call(pos(), nf.Super(pos(getLeftSpan())), identifier.getIdentifier(), ArgumentListopt));
          $EndJava
        ./
                       | MethodClassNameSuperPrefix ( ArgumentListopt )
        /.$BeginJava
                    Name ClassName = (Name) ((Object[]) MethodClassNameSuperPrefix)[0];
                    JPGPosition super_pos = (JPGPosition) ((Object[]) MethodClassNameSuperPrefix)[1];
                    polyglot.lex.Identifier identifier = (polyglot.lex.Identifier) ((Object[]) MethodClassNameSuperPrefix)[2];
                    setResult(nf.Call(pos(), nf.Super(super_pos, ClassName.toType()), identifier.getIdentifier(), ArgumentListopt));
          $EndJava
        ./

    MethodPrimaryPrefix ::= Primary . ErrorId$ErrorId
        /.$BeginJava
                    Object[] a = new Object[2];
                    a[0] = Primary;
                    a[1] = id(getRhsFirstTokenIndex($ErrorId));
                    setResult(a);
          $EndJava
        ./
    MethodSuperPrefix ::= super . ErrorId$ErrorId
        /.$BeginJava
                    setResult(id(getRhsFirstTokenIndex($ErrorId)));
          $EndJava
        ./
    MethodClassNameSuperPrefix ::= ClassName . super$sup . ErrorId$ErrorId
        /.$BeginJava
                    Object[] a = new Object[3];
                    a[0] = ClassName;
                    a[1] = pos(getRhsFirstTokenIndex($sup));
                    a[2] = id(getRhsFirstTokenIndex($ErrorId));
                    setResult(a);
          $EndJava
        ./
$End

$Types
    Object ::= MethodPrimaryPrefix
             | MethodClassNameSuperPrefix
    polyglot.lex.Identifier ::= MethodSuperPrefix
$End
