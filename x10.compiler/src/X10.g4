grammar X10;

@parser::header {
  package x10.parserGen;
  
  import x10.parser.antlr.ASTBuilder.Modifier;
  import polyglot.parse.*;
  import polyglot.ast.*;
  import x10.ast.*;
}

import X10_Lexer;

modifiersopt returns [List<Modifier> ast]:
        modifier*
    ;
modifier returns [Modifier ast]:
      'abstract'   #modifierAbstract
    | annotation   #modifierAnnotation
    | 'atomic'     #modifierAtomic
    | 'final'      #modifierFinal
    | 'native'     #modifierNative
    | 'private'    #modifierPrivate
    | 'protected'  #modifierProtected
    | 'public'     #modifierPublic
    | 'static'     #modifierStatic
    | 'transient'  #modifierTransient
    | 'clocked'    #modifierClocked
    ;
methodModifiersopt returns [List<Modifier> ast]:
        methodModifier*
    ;
methodModifier returns [Modifier ast]:
      modifier     #methodModifierModifier
    | 'property'   #methodModifierProperty
    ;
typeDefDeclaration returns [TypeDecl ast]:
      modifiersopt 'type' identifier typeParametersopt ('(' formalParameterList ')')? whereClauseopt '=' type ';'
    ;
propertiesopt returns [List<PropertyDecl> ast]:
        ('(' property (',' property)* ')')?
    ;
property returns [PropertyDecl ast]:
      annotationsopt identifier resultType
    ;
methodDeclaration returns [ProcedureDecl ast]:
      methodModifiersopt 'def' identifier typeParametersopt formalParameters whereClauseopt oBSOLETE_Offersopt throwsopt hasResultTypeopt methodBody    #methodDeclarationMethod
    | binaryOperatorDeclaration        #methodDeclarationBinaryOp
    | prefixOperatorDeclaration        #methodDeclarationPrefixOp
    | applyOperatorDeclaration         #methodDeclarationApplyOp
    | setOperatorDeclaration           #methodDeclarationSetOp
    | conversionOperatorDeclaration    #methodDeclarationConversionOp
    ;
binaryOperatorDeclaration returns [MethodDecl ast]:
      methodModifiersopt 'operator' typeParametersopt '(' fp1=formalParameter ')' binOp '(' fp2=formalParameter ')' whereClauseopt oBSOLETE_Offersopt throwsopt hasResultTypeopt methodBody     #binaryOperatorDecl
    | methodModifiersopt 'operator' typeParametersopt 'this' binOp '(' fp2=formalParameter ')' whereClauseopt oBSOLETE_Offersopt throwsopt hasResultTypeopt methodBody                          #binaryOperatorDeclThisLeft
    | methodModifiersopt 'operator' typeParametersopt '(' fp1=formalParameter ')' binOp 'this' whereClauseopt oBSOLETE_Offersopt throwsopt hasResultTypeopt methodBody                          #binaryOperatorDeclThisRight
    ;
prefixOperatorDeclaration returns [MethodDecl ast]:
      methodModifiersopt 'operator' typeParametersopt prefixOp '(' formalParameter ')' whereClauseopt oBSOLETE_Offersopt throwsopt hasResultTypeopt methodBody     #prefixOperatorDecl
    | methodModifiersopt 'operator' typeParametersopt prefixOp 'this' whereClauseopt oBSOLETE_Offersopt throwsopt hasResultTypeopt methodBody                      #prefixOperatorDeclThis
    ;
applyOperatorDeclaration returns [MethodDecl ast]:
      methodModifiersopt 'operator' 'this' typeParametersopt formalParameters whereClauseopt oBSOLETE_Offersopt throwsopt hasResultTypeopt methodBody
    ;
setOperatorDeclaration returns [MethodDecl ast]:
      methodModifiersopt 'operator' 'this' typeParametersopt formalParameters '=' '(' formalParameter ')' whereClauseopt oBSOLETE_Offersopt throwsopt hasResultTypeopt methodBody
    ;
conversionOperatorDeclaration returns [MethodDecl ast]:
      explicitConversionOperatorDeclaration     #conversionOperatorDeclarationExplicit
    | implicitConversionOperatorDeclaration     #conversionOperatorDeclarationImplicit
    ;
explicitConversionOperatorDeclaration returns [MethodDecl ast]:
      methodModifiersopt 'operator' typeParametersopt '(' formalParameter ')' 'as' type whereClauseopt oBSOLETE_Offersopt throwsopt methodBody                     #explicitConversionOperatorDecl0
    | methodModifiersopt 'operator' typeParametersopt '(' formalParameter ')' 'as' '?' whereClauseopt oBSOLETE_Offersopt throwsopt hasResultTypeopt methodBody     #explicitConversionOperatorDecl1
    ;
implicitConversionOperatorDeclaration returns [MethodDecl ast]:
      methodModifiersopt 'operator' typeParametersopt '(' formalParameter ')' whereClauseopt oBSOLETE_Offersopt throwsopt hasResultTypeopt methodBody
    ;
propertyMethodDeclaration returns [MethodDecl ast]:
      methodModifiersopt identifier typeParametersopt formalParameters whereClauseopt hasResultTypeopt methodBody     #propertyMethodDecl0
    | methodModifiersopt identifier whereClauseopt hasResultTypeopt methodBody                                        #propertyMethodDecl1
    ;
explicitConstructorInvocation returns [ConstructorCall ast]:
      'this' typeArgumentsopt '(' argumentListopt ')' ';'                  #explicitConstructorInvocationThis
    | 'super' typeArgumentsopt '(' argumentListopt ')' ';'                 #explicitConstructorInvocationSuper
    | primary '.' 'this' typeArgumentsopt '(' argumentListopt ')' ';'      #explicitConstructorInvocationPrimaryThis
    | primary '.' 'super' typeArgumentsopt '(' argumentListopt ')' ';'     #explicitConstructorInvocationPrimarySuper
    ;
interfaceDeclaration returns [ClassDecl ast]:
      modifiersopt 'interface' identifier typeParamsWithVarianceopt propertiesopt whereClauseopt extendsInterfacesopt interfaceBody
    ;
assignPropertyCall returns [Stmt ast]:
      'property' typeArgumentsopt '(' argumentListopt ')' ';'
    ;
type returns [TypeNode ast]:
      functionType         #typeFunctionType
    | constrainedType      #typeConstrainedType
    | void_                #typeVoid
    | type annotations     #typeAnnotations
    ;
functionType returns [TypeNode ast]:
      typeParametersopt '(' formalParameterList? ')' whereClauseopt oBSOLETE_Offersopt '=>' type
    ;
classType returns [TypeNode ast]:
      namedType
    ;
constrainedType returns [TypeNode ast]:
      namedType
    ;
void_ returns [CanonicalTypeNode ast]:
      'void'
    ;
simpleNamedType returns [AmbTypeNode ast]:
      typeName                                                                       #simpleNamedType0
    | primary '.' identifier                                                         #simpleNamedType1
    | simpleNamedType typeArgumentsopt argumentsopt depParameters? '.' identifier    #simpleNamedType2
    ;
parameterizedNamedType returns [AmbMacroTypeNode ast]:
      simpleNamedType arguments                  #parameterizedNamedType0
    | simpleNamedType typeArguments              #parameterizedNamedType1
    | simpleNamedType typeArguments arguments    #parameterizedNamedType2
    ;
depNamedType returns [TypeNode ast]:
      simpleNamedType depParameters              #depNamedType0
    | parameterizedNamedType depParameters       #depNamedType1
    ;
namedTypeNoConstraints returns [TypeNode ast]:
      simpleNamedType                            #namedTypeNoConstraints0
    | parameterizedNamedType                     #namedTypeNoConstraints1
    ;
namedType returns [TypeNode ast]:
      namedTypeNoConstraints                     #namedType0
    | depNamedType                               #namedType1
    ;
depParameters returns [DepParameterExpr ast]:
      '{' /* fUTURE_ExistentialList? */ constraintConjunctionopt '}'
    ;
typeParamsWithVarianceopt returns [List<TypeParamNode> ast]:
      ('[' typeParamWithVarianceList ']')?
    ;
typeParametersopt returns [List<TypeParamNode> ast]:
      ('[' typeParameterList ']')?
    ;
formalParameters returns [List<Formal> ast]:
      '(' formalParameterList? ')'
    ;
constraintConjunctionopt returns [List<Expr> ast]:
      (expression (',' expression)*)?
    ;
hasZeroConstraint returns [HasZeroTest ast]:
      type 'haszero'
    ;
isRefConstraint returns [IsRefTest ast]:
      type 'isref'
    ;
subtypeConstraint returns [SubtypeTest ast]:
      t1=type '<:' t2=type     #subtypeConstraint0
    | t1=type ':>' t2=type     #subtypeConstraint1
    ;
whereClauseopt returns [DepParameterExpr ast]:
      depParameters?
    ;
// fUTURE_ExistentialList:
//       formalParameter (';' formalParameter)*
//     ;
classDeclaration returns [ClassDecl ast]:
      modifiersopt 'class' identifier typeParamsWithVarianceopt propertiesopt whereClauseopt superExtendsopt interfacesopt classBody
    ;
structDeclaration returns [ClassDecl ast]:
      modifiersopt 'struct' identifier typeParamsWithVarianceopt propertiesopt whereClauseopt interfacesopt classBody
    ;
constructorDeclaration returns [ConstructorDecl ast]:
      modifiersopt 'def' id='this' typeParametersopt formalParameters whereClauseopt oBSOLETE_Offersopt throwsopt hasResultTypeopt constructorBody
    ;
superExtendsopt returns [TypeNode ast]:
      ('extends' classType)?
    ;
varKeyword returns [List<FlagsNode> ast]:
      'val'     #varKeyword0
    | 'var'     #varKeyword1
    ;
fieldDeclaration returns [List<ClassMember> ast]:
      modifiersopt varKeyword? fieldDeclarators ';'
    ;
statement returns [Stmt ast]:
      annotationStatement     #statement0
    | expressionStatement     #statement1
    ;
annotationStatement returns [Stmt ast]:
      annotationsopt nonExpressionStatement
    ;
nonExpressionStatement returns [Stmt ast]:
      block                        #nonExpressionStatemen0
    | emptyStatement               #nonExpressionStatemen1
    | assertStatement              #nonExpressionStatemen2
    | switchStatement              #nonExpressionStatemen3
    | doStatement                  #nonExpressionStatemen4
    | breakStatement               #nonExpressionStatemen5
    | continueStatement            #nonExpressionStatemen6
    | returnStatement              #nonExpressionStatemen7
    | throwStatement               #nonExpressionStatemen8
    | tryStatement                 #nonExpressionStatemen9
    | labeledStatement             #nonExpressionStatemen10
    | ifThenStatement              #nonExpressionStatemen11
    | ifThenElseStatement          #nonExpressionStatemen12
    | whileStatement               #nonExpressionStatemen13
    | forStatement                 #nonExpressionStatemen14
    | asyncStatement               #nonExpressionStatemen15
    | atStatement                  #nonExpressionStatemen16
    | atomicStatement              #nonExpressionStatemen17
    | whenStatement                #nonExpressionStatemen18
    | atEachStatement              #nonExpressionStatemen19
    | finishStatement              #nonExpressionStatemen20
    | assignPropertyCall           #nonExpressionStatemen21
    | oBSOLETE_OfferStatement      #nonExpressionStatemen22
    ;
oBSOLETE_OfferStatement returns [Offer ast]:
      'offer' expression ';'
    ;
ifThenStatement returns [If ast]:
      'if' '(' expression ')' statement
    ;
ifThenElseStatement returns [If ast]:
      'if' '(' expression ')' s1=statement 'else' s2=statement
    ;
emptyStatement returns [Empty ast]:
      ';'
    ;
labeledStatement returns [Labeled ast]:
      identifier ':' loopStatement
    ;
loopStatement returns [Stmt ast]:
      forStatement        #loopStatement0
    | whileStatement      #loopStatement1
    | doStatement         #loopStatement2
    | atEachStatement     #loopStatement3
    ;
expressionStatement returns [Eval ast]:
      expression ';'
    ;
assertStatement returns [Assert ast]:
      'assert' expression ';'                         #assertStatement0
    | 'assert' e1=expression ':' e2=expression ';'    #assertStatement1
    ;
switchStatement returns [Switch ast]:
      'switch' '(' expression ')' switchBlock
    ;
switchBlock returns [List<SwitchElement> ast]:
      '{' switchBlockStatementGroupsopt switchLabelsopt '}'
    ;
switchBlockStatementGroupsopt returns [List<SwitchElement> ast]:
        switchBlockStatementGroup*
    ;
switchBlockStatementGroup returns [List<SwitchElement> ast]:
      switchLabels blockStatements
    ;
switchLabelsopt returns [List<Case> ast]:
        switchLabels?
    ;
switchLabels returns [List<Case> ast]:
        switchLabel+
    ;
switchLabel returns [Case ast]:
      'case' constantExpression ':'    #switchLabel0
    | 'default' ':'                    #switchLabel1
    ;
whileStatement returns [While ast]:
      'while' '(' expression ')' statement
    ;
doStatement returns [Do ast]:
      'do' statement 'while' '(' expression ')' ';'
    ;
forStatement returns [Loop ast]:
      basicForStatement        #forStatement0
    | enhancedForStatement     #forStatement1
    ;
basicForStatement returns [For ast]:
      'for' '(' forInitopt ';' expressionopt ';' forUpdateopt ')' statement
    ;
forInit returns [List<ForInit> ast]:
      statementExpressionList     #forInit0
    | localVariableDeclaration    #forInit1
    ;
forUpdate returns [List<ForUpdate> ast]:
      statementExpressionList
    ;
statementExpressionList returns [List<? extends Eval> ast]:
      expression (',' expression)*
    ;
breakStatement returns [Branch ast]:
      'break' identifieropt ';'
    ;
continueStatement returns [Branch ast]:
      'continue' identifieropt ';'
    ;
returnStatement returns [Return ast]:
      'return' expressionopt ';'
    ;
throwStatement returns [Throw ast]:
      'throw' expression ';'
    ;
tryStatement returns [Try ast]:
      'try' block catches                    #tryStatement0
    | 'try' block catchesopt finallyBlock    #tryStatement1
    ;
catches returns [List<Catch> ast]:
      catchClause+
    ;
catchClause returns [Catch ast]:
      'catch' '(' formalParameter ')' block
    ;
finallyBlock returns [Block ast]:
      'finally' block
    ;
clockedClauseopt returns [List<Expr> ast]:
      ('clocked' arguments)?
    ;
asyncStatement returns [Async ast]:
      'async' clockedClauseopt statement    #asyncStatement0
    | 'clocked' 'async' statement           #asyncStatement1
    ;
atStatement returns [AtStmt ast]:
      'at' '(' expression ')' statement
    ;
atomicStatement returns [Atomic ast]:
      'atomic' statement
    ;
whenStatement returns [When ast]:
      'when' '(' expression ')' statement
    ;
atEachStatement returns [X10Loop ast]:
      'ateach' '(' loopIndex 'in' expression ')' clockedClauseopt statement     #atEachStatement0
    | 'ateach' '(' expression ')' statement                                     #atEachStatement1
    ;
enhancedForStatement returns [X10Loop ast]:
      'for' '(' loopIndex 'in' expression ')' statement     #enhancedForStatement0
    | 'for' '(' expression ')' statement                    #enhancedForStatement1
    ;
finishStatement returns [Finish ast]:
      'finish' statement                #finishStatement0
    | 'clocked' 'finish' statement      #finishStatement1
    ;
castExpression returns [Expr ast]:
      primary                          #castExpression0
    | expressionName                   #castExpression1
    | castExpression 'as' type         #castExpression2
    ;
typeParamWithVarianceList returns [List<TypeParamNode> ast]:
      typeParameter                                                     #typeParamWithVarianceList0
    | oBSOLETE_TypeParamWithVariance                                    #typeParamWithVarianceList1
    | typeParamWithVarianceList ',' typeParameter                       #typeParamWithVarianceList2
    | typeParamWithVarianceList ',' oBSOLETE_TypeParamWithVariance      #typeParamWithVarianceList3
    ;
typeParameterList returns [List<TypeParamNode> ast]:
      typeParameter (',' typeParameter)*
    ;
oBSOLETE_TypeParamWithVariance returns [TypeParamNode ast]:
      '+' typeParameter     #oBSOLETE_TypeParamWithVariance0
    | '-' typeParameter     #oBSOLETE_TypeParamWithVariance1
    ;
typeParameter returns [TypeParamNode ast]:
      identifier
    ;
closureExpression returns [Closure ast]:
      formalParameters whereClauseopt hasResultTypeopt oBSOLETE_Offersopt '=>' closureBody
    ;
lastExpression returns [Return ast]:
      expression
    ;
closureBody returns [Block ast]:
      expression                                               #closureBody0
    | annotationsopt '{' blockStatements? lastExpression '}'   #closureBody1
    | annotationsopt block                                     #closureBody2
    ;
atExpression returns [AtExpr ast]:
      annotationsopt 'at' '(' expression ')' closureBody
    ;
oBSOLETE_FinishExpression returns [FinishExpr ast]:
      'finish' '(' expression ')' block
    ;
typeName returns [ParsedName ast]:
      identifier                #typeName0
    | typeName '.' identifier   #typeName1
    ;
className returns [ParsedName ast]:
      typeName
    ;
typeArguments returns [List<TypeNode> ast]:
      '[' type (',' type)* ']'
    ;
packageName returns [ParsedName ast]:
      identifier                            #packageName0
    | packageName '.' identifier            #packageName1
    ;
expressionName returns [ParsedName ast]:
      identifier
    | fullyQualifiedName '.' identifier
    ;
methodName returns [ParsedName ast]:
      identifier
    | fullyQualifiedName '.' identifier
    ;
packageOrTypeName returns [ParsedName ast]:
      identifier
    | packageOrTypeName '.' identifier
    ;
fullyQualifiedName returns [ParsedName ast]:
      identifier
    | fullyQualifiedName '.' identifier
    ;
compilationUnit returns [SourceFile ast]:
      packageDeclaration? importDeclarationsopt typeDeclarationsopt
    ;
packageDeclaration returns [PackageNode ast]:
      annotationsopt 'package' packageName ';'
    ;
importDeclarationsopt returns [List<Import> ast]:
        importDeclaration*
    ;
importDeclaration returns [Import ast]:
      'import' typeName ';'                    #singleTypeImportDeclaration
    | 'import' packageOrTypeName '.' '*' ';'   #typeImportOnDemandDeclaration
    ;
typeDeclarationsopt returns [List<TopLevelDecl> ast]:
        typeDeclaration*
    ;
typeDeclaration returns [TopLevelDecl ast]:
      classDeclaration
    | structDeclaration
    | interfaceDeclaration
    | typeDefDeclaration
    | ';'
    ;
interfacesopt returns [List<TypeNode> ast]:
      ('implements' interfaceTypeList)?
    ;
interfaceTypeList returns [List<TypeNode> ast]:
      type (',' type)*
    ;
classBody returns [ClassBody ast]:
      '{' classMemberDeclarationsopt '}'
    ;
classMemberDeclarationsopt returns [List<ClassMember> ast]:
        classMemberDeclaration*
    ;
classMemberDeclaration returns [ClassMember ast]:
      interfaceMemberDeclaration
    | constructorDeclaration
    ;
formalDeclarators returns [List<Object[]> ast]:
      formalDeclarator (',' formalDeclarator)*
    ;
fieldDeclarators returns [List<Object[]> ast]:
      fieldDeclarator (',' fieldDeclarator)*
    ;
variableDeclaratorsWithType returns [List<Object[]> ast]:
      variableDeclaratorWithType (',' variableDeclaratorWithType)*
    ;
variableDeclarators returns [List<Object[]> ast]:
      variableDeclarator (',' variableDeclarator)*
    ;
homeVariableList returns [List<Node> ast]:
      homeVariable (',' homeVariable)*
    ;
homeVariable returns [Node ast]:
      identifier
    | 'this'
    ;
variableInitializer returns [Expr ast]:
      expression
    ;
resultType returns [TypeNode ast]:
      ':' type
    ;
hasResultType returns [TypeNode ast]:
      resultType
    | '<:' type
    ;
formalParameterList returns [List<Formal> ast]:
      formalParameter (',' formalParameter)*
    ;
loopIndexDeclarator returns [Object[] ast]:
      identifier hasResultTypeopt
    | '[' identifierList ']' hasResultTypeopt
    | identifier '[' identifierList ']' hasResultTypeopt
    ;
loopIndex returns [X10Formal ast]:
      modifiersopt loopIndexDeclarator
    | modifiersopt varKeyword loopIndexDeclarator
    ;
formalParameter returns [X10Formal ast]:
      modifiersopt formalDeclarator
    | modifiersopt varKeyword formalDeclarator
    | type
    ;
oBSOLETE_Offersopt returns [TypeNode ast]:
      ('offers' type)?
    ;
throwsopt returns [List<TypeNode> ast]:
      ('throws' type (',' type)*)?
    ;
methodBody returns [Block ast]:
      '=' lastExpression ';'
    | '=' annotationsopt '{' blockStatements? lastExpression '}'
    | '=' annotationsopt block
    | annotationsopt block
    | ';'
    ;
constructorBody returns [Block ast]:
      '=' constructorBlock
    | constructorBlock
    | '=' explicitConstructorInvocation
    | '=' assignPropertyCall
    | ';'
    ;
constructorBlock returns [Block ast]:
      '{' explicitConstructorInvocation? blockStatements? '}'
    ;
arguments returns [List<Expr> ast]:
      '(' argumentList ')'
    ;
extendsInterfacesopt returns [List<TypeNode> ast]:
      'extends' type (',' type)*
    ;
interfaceBody returns [ClassBody ast]:
      '{' interfaceMemberDeclarationsopt '}'
    ;
interfaceMemberDeclarationsopt returns [List<ClassMember> ast]:
        interfaceMemberDeclaration*
    ;
interfaceMemberDeclaration returns [ClassMember ast]:
      methodDeclaration
    | propertyMethodDeclaration
    | fieldDeclaration
    | typeDeclaration
    ;
annotationsopt returns [List<AnnotationNode> ast]:
      annotation*
    ;
annotations returns [List<AnnotationNode> ast]:
      annotation+
    ;
annotation returns [AnnotationNode ast]:
      '@' namedTypeNoConstraints
    ;
identifier returns [Id ast]:
      IDENTIFIER
    ;
block returns [Block ast]:
      '{' blockStatements? '}'
    ;
blockStatements returns [List<Stmt> ast]:
      blockInteriorStatement+
    ;
blockInteriorStatement returns [List<Stmt> ast]:
      localVariableDeclarationStatement
    | classDeclaration
    | structDeclaration
    | typeDefDeclaration
    | statement
    ;
identifierList returns [List<Id> ast]:
      identifier (',' identifier)*
    ;
formalDeclarator returns [Object[] ast]:
      identifier resultType
    | '[' identifierList ']' resultType
    | identifier '[' identifierList ']' resultType
    ;
fieldDeclarator returns [Object[] ast]:
      identifier hasResultType
    | identifier hasResultTypeopt '=' variableInitializer
    ;
variableDeclarator returns [Object[] ast]:
      identifier hasResultTypeopt '=' variableInitializer
    | '[' identifierList ']' hasResultTypeopt '=' variableInitializer
    | identifier '[' identifierList ']' hasResultTypeopt '=' variableInitializer
    ;
variableDeclaratorWithType returns [Object[] ast]:
      identifier hasResultType '=' variableInitializer
    | '[' identifierList ']' hasResultType '=' variableInitializer
    | identifier '[' identifierList ']' hasResultType '=' variableInitializer
    ;
localVariableDeclarationStatement returns [List<LocalDecl> ast]:
      localVariableDeclaration ';'
    ;
localVariableDeclaration returns [List<LocalDecl> ast]:
      modifiersopt varKeyword variableDeclarators
    | modifiersopt variableDeclaratorsWithType
    | modifiersopt varKeyword formalDeclarators
    ;
primary returns [Expr ast]:
      'here'
    | '[' argumentListopt ']'
    | literal
    | 'self'
    | 'this'
    | className '.' 'this'
    | '(' expression ')'
//    | classInstanceCreationExpression
    | 'new' typeName typeArgumentsopt '(' argumentListopt ')' classBody?
    | primary '.' 'new' identifier typeArgumentsopt '(' argumentListopt ')' classBody?
    | fullyQualifiedName '.' 'new' identifier typeArgumentsopt '(' argumentListopt ')' classBody?
//    | fieldAccess
    | primary '.' identifier
    | 'super' '.' identifier
    | className '.' 'super' '.' identifier
//    | methodInvocation
    | methodName typeArgumentsopt '(' argumentListopt ')'
    | primary '.' identifier typeArgumentsopt '(' argumentListopt ')'
    | 'super' '.' identifier typeArgumentsopt '(' argumentListopt ')'
    | className '.' 'super' '.' identifier typeArgumentsopt '(' argumentListopt ')'
    | primary typeArgumentsopt '(' argumentListopt ')'
    | className '.' 'operator' 'as' '[' type ']' typeArgumentsopt '(' argumentListopt ')'
    | className '.' 'operator' '[' type ']' typeArgumentsopt '(' argumentListopt ')'
//    | operatorPrefix typeArgumentsopt '(' argumentListopt ')'
    | 'operator' binOp
    | fullyQualifiedName '.' 'operator' binOp
    | primary '.' 'operator' binOp
    | 'super' '.' 'operator' binOp
    | className '.' 'super' '.' 'operator' binOp
    | 'operator' '(' ')' binOp
    | fullyQualifiedName '.' 'operator' '(' ')' binOp
    | primary '.' 'operator' '(' ')' binOp
    | 'super' '.' 'operator' '(' ')' binOp
    | className '.' 'super' '.' 'operator' '(' ')' binOp
    | 'operator' '(' ')'
    | fullyQualifiedName '.' 'operator' '(' ')'
    | primary '.' 'operator' '(' ')'
    | 'super' '.' 'operator' '(' ')'
    | className '.' 'super' '.' 'operator' '(' ')'
    | 'operator' '(' ')' '='
    | fullyQualifiedName '.' 'operator' '(' ')' '='
    | primary '.' 'operator' '(' ')' '='
    | 'super' '.' 'operator' '(' ')' '='
    | className '.' 'super' '.' 'operator' '(' ')' '='
    ;
literal returns [Lit ast]:
      IntLiteral
    | LongLiteral
    | ByteLiteral
    | UnsignedByteLiteral
    | ShortLiteral
    | UnsignedShortLiteral
    | UnsignedIntLiteral
    | UnsignedLongLiteral
    | FloatingPointLiteral
    | DoubleLiteral
    | BooleanLiteral
    | CharacterLiteral
    | StringLiteral
    | 'null'
    ;
argumentList returns [List<Expr> ast]:
      expression (',' expression)*
    ;
fieldAccess returns [Field ast]:
      primary '.' identifier
    | 'super' '.' identifier
    | className '.' 'super' '.' identifier
    ;
conditionalExpression returns [Expr ast]:
      castExpression
    | conditionalExpression ('++'|'--')
    | annotations conditionalExpression
    | ('+'|'-'|'++'|'--') conditionalExpression
    | ('~'|'!'|'^'|'|'|'&'|'*'|'/'|'%') conditionalExpression
    | conditionalExpression '..' conditionalExpression
    | conditionalExpression ('*'|'/'|'%'|'**') conditionalExpression
    | conditionalExpression ('+'|'-') conditionalExpression
    | hasZeroConstraint
    | isRefConstraint
    | subtypeConstraint
    | conditionalExpression ('<<'|'>>'|'>>>'|'->'|'<-'|'-<'|'>-'|'!'|'<>'|'><') conditionalExpression
    | conditionalExpression 'instanceof' type
    | conditionalExpression ('<'|'>'|'<='|'>=') conditionalExpression
    | conditionalExpression ('=='|'!=') conditionalExpression
    | type '==' type // Danger some type equalities can be capture by the previous rule
    | conditionalExpression ('~'|'!~') conditionalExpression
    | conditionalExpression '&' conditionalExpression
    | conditionalExpression '^' conditionalExpression
    | conditionalExpression '|' conditionalExpression
    | conditionalExpression '&&' conditionalExpression
    | conditionalExpression '||' conditionalExpression
    | closureExpression
    | atExpression
    | oBSOLETE_FinishExpression
    | conditionalExpression '?' conditionalExpression ':' conditionalExpression
    ;

assignmentExpression returns [Expr ast]:
      assignment
    | conditionalExpression
    ;
assignment returns [Expr ast]:
      leftHandSide assignmentOperator assignmentExpression
    | expressionName '(' argumentListopt ')' assignmentOperator assignmentExpression
    | primary '(' argumentListopt ')' assignmentOperator assignmentExpression
    ;
leftHandSide returns [Expr ast]:
      expressionName
    | fieldAccess
    ;
assignmentOperator returns [Assign.Operator ast]:
      '='
    | '*='
    | '/='
    | '%='
    | '+='
    | '-='
    | '<<='
    | '>>='
    | '>>>='
    | '&='
    | '^='
    | '|='
    | '..='
    | '->='
    | '<-='
    | '-<='
    | '>-='
    | '**='
    | '<>='
    | '><='
    | '~='
    ;
expression returns [Expr ast]:
      assignmentExpression
    ;
constantExpression returns [Expr ast]:
      expression
    ;
prefixOp returns [Unary.Operator ast]:
      '+'
    | '-'
    | '!'
    | '~'
    | '^'
    | '|'
    | '&'
    | '*'
    | '/'
    | '%'
    ;
binOp returns [Binary.Operator ast]:
      '+'
    | '-'
    | '*'
    | '/'
    | '%'
    | '&'
    | '|'
    | '^'
    | '&&'
    | '||'
    | '<<'
    | '>>'
    | '>>>'
    | '>='
    | '<='
    | '>'
    | '<'
    | '=='
    | '!='
    | '..'
    | '->'
    | '<-'
    | '-<'
    | '>-'
    | '**'
    | '~'
    | '!~'
    | '!'
    | '<>'
    | '><'
    ;


hasResultTypeopt returns [TypeNode ast]:
      hasResultType?
    ;
typeArgumentsopt returns [List<TypeNode> ast]:
      typeArguments?
    ;
argumentListopt returns [List<Expr> ast]:
      argumentList?
    ;
argumentsopt returns [List<Expr> ast]:
      arguments?
    ;
identifieropt returns [Id ast]:
      identifier?
    ;
forInitopt returns [List<ForInit> ast]:
      forInit?
    ;
forUpdateopt returns [List<ForUpdate> ast]:
      forUpdate?
    ;
expressionopt returns [Expr ast]:
      expression?
    ;
catchesopt returns [List<Catch> ast]:
      catches?
    ;
