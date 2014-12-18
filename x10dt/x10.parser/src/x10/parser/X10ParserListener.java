// Generated from X10Parser.g4 by ANTLR 4.4

  package x10.parser;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link X10ParserParser}.
 */
public interface X10ParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#applyOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterApplyOperatorDeclaration(@NotNull X10ParserParser.ApplyOperatorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#applyOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitApplyOperatorDeclaration(@NotNull X10ParserParser.ApplyOperatorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#identifieropt}.
	 * @param ctx the parse tree
	 */
	void enterIdentifieropt(@NotNull X10ParserParser.IdentifieroptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#identifieropt}.
	 * @param ctx the parse tree
	 */
	void exitIdentifieropt(@NotNull X10ParserParser.IdentifieroptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#fieldAccess}.
	 * @param ctx the parse tree
	 */
	void enterFieldAccess(@NotNull X10ParserParser.FieldAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#fieldAccess}.
	 * @param ctx the parse tree
	 */
	void exitFieldAccess(@NotNull X10ParserParser.FieldAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#implicitConversionOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterImplicitConversionOperatorDeclaration(@NotNull X10ParserParser.ImplicitConversionOperatorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#implicitConversionOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitImplicitConversionOperatorDeclaration(@NotNull X10ParserParser.ImplicitConversionOperatorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#formalParametersopt}.
	 * @param ctx the parse tree
	 */
	void enterFormalParametersopt(@NotNull X10ParserParser.FormalParametersoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#formalParametersopt}.
	 * @param ctx the parse tree
	 */
	void exitFormalParametersopt(@NotNull X10ParserParser.FormalParametersoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#variableDeclaratorsWithType}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorsWithType(@NotNull X10ParserParser.VariableDeclaratorsWithTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#variableDeclaratorsWithType}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorsWithType(@NotNull X10ParserParser.VariableDeclaratorsWithTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(@NotNull X10ParserParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(@NotNull X10ParserParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#switchBlockStatementGroups}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroups(@NotNull X10ParserParser.SwitchBlockStatementGroupsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#switchBlockStatementGroups}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroups(@NotNull X10ParserParser.SwitchBlockStatementGroupsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#classMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassMemberDeclaration(@NotNull X10ParserParser.ClassMemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#classMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassMemberDeclaration(@NotNull X10ParserParser.ClassMemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#oBSOLETE_OfferStatement}.
	 * @param ctx the parse tree
	 */
	void enterOBSOLETE_OfferStatement(@NotNull X10ParserParser.OBSOLETE_OfferStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#oBSOLETE_OfferStatement}.
	 * @param ctx the parse tree
	 */
	void exitOBSOLETE_OfferStatement(@NotNull X10ParserParser.OBSOLETE_OfferStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#interfaces}.
	 * @param ctx the parse tree
	 */
	void enterInterfaces(@NotNull X10ParserParser.InterfacesContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#interfaces}.
	 * @param ctx the parse tree
	 */
	void exitInterfaces(@NotNull X10ParserParser.InterfacesContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#throws_}.
	 * @param ctx the parse tree
	 */
	void enterThrows_(@NotNull X10ParserParser.Throws_Context ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#throws_}.
	 * @param ctx the parse tree
	 */
	void exitThrows_(@NotNull X10ParserParser.Throws_Context ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#oBSOLETE_TypeParamWithVariance}.
	 * @param ctx the parse tree
	 */
	void enterOBSOLETE_TypeParamWithVariance(@NotNull X10ParserParser.OBSOLETE_TypeParamWithVarianceContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#oBSOLETE_TypeParamWithVariance}.
	 * @param ctx the parse tree
	 */
	void exitOBSOLETE_TypeParamWithVariance(@NotNull X10ParserParser.OBSOLETE_TypeParamWithVarianceContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#classInstanceCreationExpression}.
	 * @param ctx the parse tree
	 */
	void enterClassInstanceCreationExpression(@NotNull X10ParserParser.ClassInstanceCreationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#classInstanceCreationExpression}.
	 * @param ctx the parse tree
	 */
	void exitClassInstanceCreationExpression(@NotNull X10ParserParser.ClassInstanceCreationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#methodName}.
	 * @param ctx the parse tree
	 */
	void enterMethodName(@NotNull X10ParserParser.MethodNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#methodName}.
	 * @param ctx the parse tree
	 */
	void exitMethodName(@NotNull X10ParserParser.MethodNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#finallyBlock}.
	 * @param ctx the parse tree
	 */
	void enterFinallyBlock(@NotNull X10ParserParser.FinallyBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#finallyBlock}.
	 * @param ctx the parse tree
	 */
	void exitFinallyBlock(@NotNull X10ParserParser.FinallyBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#setOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSetOperatorDeclaration(@NotNull X10ParserParser.SetOperatorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#setOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSetOperatorDeclaration(@NotNull X10ParserParser.SetOperatorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#varKeyword}.
	 * @param ctx the parse tree
	 */
	void enterVarKeyword(@NotNull X10ParserParser.VarKeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#varKeyword}.
	 * @param ctx the parse tree
	 */
	void exitVarKeyword(@NotNull X10ParserParser.VarKeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(@NotNull X10ParserParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(@NotNull X10ParserParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#namedTypeNoConstraints}.
	 * @param ctx the parse tree
	 */
	void enterNamedTypeNoConstraints(@NotNull X10ParserParser.NamedTypeNoConstraintsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#namedTypeNoConstraints}.
	 * @param ctx the parse tree
	 */
	void exitNamedTypeNoConstraints(@NotNull X10ParserParser.NamedTypeNoConstraintsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#interfaceTypeList}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceTypeList(@NotNull X10ParserParser.InterfaceTypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#interfaceTypeList}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceTypeList(@NotNull X10ParserParser.InterfaceTypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeParametersopt}.
	 * @param ctx the parse tree
	 */
	void enterTypeParametersopt(@NotNull X10ParserParser.TypeParametersoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeParametersopt}.
	 * @param ctx the parse tree
	 */
	void exitTypeParametersopt(@NotNull X10ParserParser.TypeParametersoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void enterCatchClause(@NotNull X10ParserParser.CatchClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void exitCatchClause(@NotNull X10ParserParser.CatchClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#blockInteriorStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockInteriorStatement(@NotNull X10ParserParser.BlockInteriorStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#blockInteriorStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockInteriorStatement(@NotNull X10ParserParser.BlockInteriorStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(@NotNull X10ParserParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(@NotNull X10ParserParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#atCaptureDeclaratorsopt}.
	 * @param ctx the parse tree
	 */
	void enterAtCaptureDeclaratorsopt(@NotNull X10ParserParser.AtCaptureDeclaratorsoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#atCaptureDeclaratorsopt}.
	 * @param ctx the parse tree
	 */
	void exitAtCaptureDeclaratorsopt(@NotNull X10ParserParser.AtCaptureDeclaratorsoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#expressionopt}.
	 * @param ctx the parse tree
	 */
	void enterExpressionopt(@NotNull X10ParserParser.ExpressionoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#expressionopt}.
	 * @param ctx the parse tree
	 */
	void exitExpressionopt(@NotNull X10ParserParser.ExpressionoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#clockedClause}.
	 * @param ctx the parse tree
	 */
	void enterClockedClause(@NotNull X10ParserParser.ClockedClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#clockedClause}.
	 * @param ctx the parse tree
	 */
	void exitClockedClause(@NotNull X10ParserParser.ClockedClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#interfacesopt}.
	 * @param ctx the parse tree
	 */
	void enterInterfacesopt(@NotNull X10ParserParser.InterfacesoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#interfacesopt}.
	 * @param ctx the parse tree
	 */
	void exitInterfacesopt(@NotNull X10ParserParser.InterfacesoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#namedType}.
	 * @param ctx the parse tree
	 */
	void enterNamedType(@NotNull X10ParserParser.NamedTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#namedType}.
	 * @param ctx the parse tree
	 */
	void exitNamedType(@NotNull X10ParserParser.NamedTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#explicitConstructorInvocation}.
	 * @param ctx the parse tree
	 */
	void enterExplicitConstructorInvocation(@NotNull X10ParserParser.ExplicitConstructorInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#explicitConstructorInvocation}.
	 * @param ctx the parse tree
	 */
	void exitExplicitConstructorInvocation(@NotNull X10ParserParser.ExplicitConstructorInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#annotationsopt}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationsopt(@NotNull X10ParserParser.AnnotationsoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#annotationsopt}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationsopt(@NotNull X10ParserParser.AnnotationsoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializer(@NotNull X10ParserParser.VariableInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializer(@NotNull X10ParserParser.VariableInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#interfaceMemberDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMemberDeclarations(@NotNull X10ParserParser.InterfaceMemberDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#interfaceMemberDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMemberDeclarations(@NotNull X10ParserParser.InterfaceMemberDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#expressionName}.
	 * @param ctx the parse tree
	 */
	void enterExpressionName(@NotNull X10ParserParser.ExpressionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#expressionName}.
	 * @param ctx the parse tree
	 */
	void exitExpressionName(@NotNull X10ParserParser.ExpressionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#functionType}.
	 * @param ctx the parse tree
	 */
	void enterFunctionType(@NotNull X10ParserParser.FunctionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#functionType}.
	 * @param ctx the parse tree
	 */
	void exitFunctionType(@NotNull X10ParserParser.FunctionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(@NotNull X10ParserParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(@NotNull X10ParserParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#atEachStatement}.
	 * @param ctx the parse tree
	 */
	void enterAtEachStatement(@NotNull X10ParserParser.AtEachStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#atEachStatement}.
	 * @param ctx the parse tree
	 */
	void exitAtEachStatement(@NotNull X10ParserParser.AtEachStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterList(@NotNull X10ParserParser.FormalParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterList(@NotNull X10ParserParser.FormalParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeParamWithVarianceList}.
	 * @param ctx the parse tree
	 */
	void enterTypeParamWithVarianceList(@NotNull X10ParserParser.TypeParamWithVarianceListContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeParamWithVarianceList}.
	 * @param ctx the parse tree
	 */
	void exitTypeParamWithVarianceList(@NotNull X10ParserParser.TypeParamWithVarianceListContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#catches}.
	 * @param ctx the parse tree
	 */
	void enterCatches(@NotNull X10ParserParser.CatchesContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#catches}.
	 * @param ctx the parse tree
	 */
	void exitCatches(@NotNull X10ParserParser.CatchesContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#whenStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhenStatement(@NotNull X10ParserParser.WhenStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#whenStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhenStatement(@NotNull X10ParserParser.WhenStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoopStatement(@NotNull X10ParserParser.LoopStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoopStatement(@NotNull X10ParserParser.LoopStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#conversionOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConversionOperatorDeclaration(@NotNull X10ParserParser.ConversionOperatorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#conversionOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConversionOperatorDeclaration(@NotNull X10ParserParser.ConversionOperatorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(@NotNull X10ParserParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(@NotNull X10ParserParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#fieldDeclarators}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclarators(@NotNull X10ParserParser.FieldDeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#fieldDeclarators}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclarators(@NotNull X10ParserParser.FieldDeclaratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#packageDeclarationopt}.
	 * @param ctx the parse tree
	 */
	void enterPackageDeclarationopt(@NotNull X10ParserParser.PackageDeclarationoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#packageDeclarationopt}.
	 * @param ctx the parse tree
	 */
	void exitPackageDeclarationopt(@NotNull X10ParserParser.PackageDeclarationoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#explicitConversionOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterExplicitConversionOperatorDeclaration(@NotNull X10ParserParser.ExplicitConversionOperatorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#explicitConversionOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitExplicitConversionOperatorDeclaration(@NotNull X10ParserParser.ExplicitConversionOperatorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStatement(@NotNull X10ParserParser.SwitchStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStatement(@NotNull X10ParserParser.SwitchStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(@NotNull X10ParserParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(@NotNull X10ParserParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(@NotNull X10ParserParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(@NotNull X10ParserParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(@NotNull X10ParserParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(@NotNull X10ParserParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(@NotNull X10ParserParser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(@NotNull X10ParserParser.CastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#homeVariable}.
	 * @param ctx the parse tree
	 */
	void enterHomeVariable(@NotNull X10ParserParser.HomeVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#homeVariable}.
	 * @param ctx the parse tree
	 */
	void exitHomeVariable(@NotNull X10ParserParser.HomeVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#switchBlockStatementGroupsopt}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroupsopt(@NotNull X10ParserParser.SwitchBlockStatementGroupsoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#switchBlockStatementGroupsopt}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroupsopt(@NotNull X10ParserParser.SwitchBlockStatementGroupsoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#throwStatement}.
	 * @param ctx the parse tree
	 */
	void enterThrowStatement(@NotNull X10ParserParser.ThrowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#throwStatement}.
	 * @param ctx the parse tree
	 */
	void exitThrowStatement(@NotNull X10ParserParser.ThrowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#annotations}.
	 * @param ctx the parse tree
	 */
	void enterAnnotations(@NotNull X10ParserParser.AnnotationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#annotations}.
	 * @param ctx the parse tree
	 */
	void exitAnnotations(@NotNull X10ParserParser.AnnotationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeArgumentList}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgumentList(@NotNull X10ParserParser.TypeArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeArgumentList}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgumentList(@NotNull X10ParserParser.TypeArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(@NotNull X10ParserParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(@NotNull X10ParserParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull X10ParserParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull X10ParserParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(@NotNull X10ParserParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(@NotNull X10ParserParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#enhancedForStatement}.
	 * @param ctx the parse tree
	 */
	void enterEnhancedForStatement(@NotNull X10ParserParser.EnhancedForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#enhancedForStatement}.
	 * @param ctx the parse tree
	 */
	void exitEnhancedForStatement(@NotNull X10ParserParser.EnhancedForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(@NotNull X10ParserParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(@NotNull X10ParserParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeParamsWithVariance}.
	 * @param ctx the parse tree
	 */
	void enterTypeParamsWithVariance(@NotNull X10ParserParser.TypeParamsWithVarianceContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeParamsWithVariance}.
	 * @param ctx the parse tree
	 */
	void exitTypeParamsWithVariance(@NotNull X10ParserParser.TypeParamsWithVarianceContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(@NotNull X10ParserParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(@NotNull X10ParserParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#forUpdateExpression}.
	 * @param ctx the parse tree
	 */
	void enterForUpdateExpression(@NotNull X10ParserParser.ForUpdateExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#forUpdateExpression}.
	 * @param ctx the parse tree
	 */
	void exitForUpdateExpression(@NotNull X10ParserParser.ForUpdateExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#importDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterImportDeclarations(@NotNull X10ParserParser.ImportDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#importDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitImportDeclarations(@NotNull X10ParserParser.ImportDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#forUpdateopt}.
	 * @param ctx the parse tree
	 */
	void enterForUpdateopt(@NotNull X10ParserParser.ForUpdateoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#forUpdateopt}.
	 * @param ctx the parse tree
	 */
	void exitForUpdateopt(@NotNull X10ParserParser.ForUpdateoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#assertStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssertStatement(@NotNull X10ParserParser.AssertStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#assertStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssertStatement(@NotNull X10ParserParser.AssertStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#operatorPrefix}.
	 * @param ctx the parse tree
	 */
	void enterOperatorPrefix(@NotNull X10ParserParser.OperatorPrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#operatorPrefix}.
	 * @param ctx the parse tree
	 */
	void exitOperatorPrefix(@NotNull X10ParserParser.OperatorPrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(@NotNull X10ParserParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(@NotNull X10ParserParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#constructorBlock}.
	 * @param ctx the parse tree
	 */
	void enterConstructorBlock(@NotNull X10ParserParser.ConstructorBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#constructorBlock}.
	 * @param ctx the parse tree
	 */
	void exitConstructorBlock(@NotNull X10ParserParser.ConstructorBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDeclaration(@NotNull X10ParserParser.InterfaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDeclaration(@NotNull X10ParserParser.InterfaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#depNamedType}.
	 * @param ctx the parse tree
	 */
	void enterDepNamedType(@NotNull X10ParserParser.DepNamedTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#depNamedType}.
	 * @param ctx the parse tree
	 */
	void exitDepNamedType(@NotNull X10ParserParser.DepNamedTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#methodModifiersopt}.
	 * @param ctx the parse tree
	 */
	void enterMethodModifiersopt(@NotNull X10ParserParser.MethodModifiersoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#methodModifiersopt}.
	 * @param ctx the parse tree
	 */
	void exitMethodModifiersopt(@NotNull X10ParserParser.MethodModifiersoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#throwsList}.
	 * @param ctx the parse tree
	 */
	void enterThrowsList(@NotNull X10ParserParser.ThrowsListContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#throwsList}.
	 * @param ctx the parse tree
	 */
	void exitThrowsList(@NotNull X10ParserParser.ThrowsListContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#fieldDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclarator(@NotNull X10ParserParser.FieldDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#fieldDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclarator(@NotNull X10ParserParser.FieldDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void enterTypeArguments(@NotNull X10ParserParser.TypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void exitTypeArguments(@NotNull X10ParserParser.TypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarations(@NotNull X10ParserParser.TypeDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarations(@NotNull X10ParserParser.TypeDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(@NotNull X10ParserParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(@NotNull X10ParserParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(@NotNull X10ParserParser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(@NotNull X10ParserParser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#lastExpression}.
	 * @param ctx the parse tree
	 */
	void enterLastExpression(@NotNull X10ParserParser.LastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#lastExpression}.
	 * @param ctx the parse tree
	 */
	void exitLastExpression(@NotNull X10ParserParser.LastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#switchLabels}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabels(@NotNull X10ParserParser.SwitchLabelsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#switchLabels}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabels(@NotNull X10ParserParser.SwitchLabelsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#resultType}.
	 * @param ctx the parse tree
	 */
	void enterResultType(@NotNull X10ParserParser.ResultTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#resultType}.
	 * @param ctx the parse tree
	 */
	void exitResultType(@NotNull X10ParserParser.ResultTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#hasResultTypeopt}.
	 * @param ctx the parse tree
	 */
	void enterHasResultTypeopt(@NotNull X10ParserParser.HasResultTypeoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#hasResultTypeopt}.
	 * @param ctx the parse tree
	 */
	void exitHasResultTypeopt(@NotNull X10ParserParser.HasResultTypeoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#constrainedType}.
	 * @param ctx the parse tree
	 */
	void enterConstrainedType(@NotNull X10ParserParser.ConstrainedTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#constrainedType}.
	 * @param ctx the parse tree
	 */
	void exitConstrainedType(@NotNull X10ParserParser.ConstrainedTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclaration(@NotNull X10ParserParser.LocalVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclaration(@NotNull X10ParserParser.LocalVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#hasZeroConstraint}.
	 * @param ctx the parse tree
	 */
	void enterHasZeroConstraint(@NotNull X10ParserParser.HasZeroConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#hasZeroConstraint}.
	 * @param ctx the parse tree
	 */
	void exitHasZeroConstraint(@NotNull X10ParserParser.HasZeroConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(@NotNull X10ParserParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(@NotNull X10ParserParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(@NotNull X10ParserParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(@NotNull X10ParserParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#blockStatementsopt}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatementsopt(@NotNull X10ParserParser.BlockStatementsoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#blockStatementsopt}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatementsopt(@NotNull X10ParserParser.BlockStatementsoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeParameterList}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameterList(@NotNull X10ParserParser.TypeParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeParameterList}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameterList(@NotNull X10ParserParser.TypeParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#singleTypeImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSingleTypeImportDeclaration(@NotNull X10ParserParser.SingleTypeImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#singleTypeImportDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSingleTypeImportDeclaration(@NotNull X10ParserParser.SingleTypeImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#atomicStatement}.
	 * @param ctx the parse tree
	 */
	void enterAtomicStatement(@NotNull X10ParserParser.AtomicStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#atomicStatement}.
	 * @param ctx the parse tree
	 */
	void exitAtomicStatement(@NotNull X10ParserParser.AtomicStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void enterLabeledStatement(@NotNull X10ParserParser.LabeledStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void exitLabeledStatement(@NotNull X10ParserParser.LabeledStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#tryStatement}.
	 * @param ctx the parse tree
	 */
	void enterTryStatement(@NotNull X10ParserParser.TryStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#tryStatement}.
	 * @param ctx the parse tree
	 */
	void exitTryStatement(@NotNull X10ParserParser.TryStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#formalParameterListopt}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterListopt(@NotNull X10ParserParser.FormalParameterListoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#formalParameterListopt}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterListopt(@NotNull X10ParserParser.FormalParameterListoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#className}.
	 * @param ctx the parse tree
	 */
	void enterClassName(@NotNull X10ParserParser.ClassNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#className}.
	 * @param ctx the parse tree
	 */
	void exitClassName(@NotNull X10ParserParser.ClassNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(@NotNull X10ParserParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(@NotNull X10ParserParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#constraintConjunctionopt}.
	 * @param ctx the parse tree
	 */
	void enterConstraintConjunctionopt(@NotNull X10ParserParser.ConstraintConjunctionoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#constraintConjunctionopt}.
	 * @param ctx the parse tree
	 */
	void exitConstraintConjunctionopt(@NotNull X10ParserParser.ConstraintConjunctionoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#leftHandSide}.
	 * @param ctx the parse tree
	 */
	void enterLeftHandSide(@NotNull X10ParserParser.LeftHandSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#leftHandSide}.
	 * @param ctx the parse tree
	 */
	void exitLeftHandSide(@NotNull X10ParserParser.LeftHandSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#classMemberDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterClassMemberDeclarations(@NotNull X10ParserParser.ClassMemberDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#classMemberDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitClassMemberDeclarations(@NotNull X10ParserParser.ClassMemberDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#classType}.
	 * @param ctx the parse tree
	 */
	void enterClassType(@NotNull X10ParserParser.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#classType}.
	 * @param ctx the parse tree
	 */
	void exitClassType(@NotNull X10ParserParser.ClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(@NotNull X10ParserParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(@NotNull X10ParserParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(@NotNull X10ParserParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(@NotNull X10ParserParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#forUpdateExpressionList}.
	 * @param ctx the parse tree
	 */
	void enterForUpdateExpressionList(@NotNull X10ParserParser.ForUpdateExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#forUpdateExpressionList}.
	 * @param ctx the parse tree
	 */
	void exitForUpdateExpressionList(@NotNull X10ParserParser.ForUpdateExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(@NotNull X10ParserParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(@NotNull X10ParserParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#extendsInterfaces}.
	 * @param ctx the parse tree
	 */
	void enterExtendsInterfaces(@NotNull X10ParserParser.ExtendsInterfacesContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#extendsInterfaces}.
	 * @param ctx the parse tree
	 */
	void exitExtendsInterfaces(@NotNull X10ParserParser.ExtendsInterfacesContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#binaryOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOperatorDeclaration(@NotNull X10ParserParser.BinaryOperatorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#binaryOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOperatorDeclaration(@NotNull X10ParserParser.BinaryOperatorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#simpleNamedType}.
	 * @param ctx the parse tree
	 */
	void enterSimpleNamedType(@NotNull X10ParserParser.SimpleNamedTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#simpleNamedType}.
	 * @param ctx the parse tree
	 */
	void exitSimpleNamedType(@NotNull X10ParserParser.SimpleNamedTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#nonExpressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterNonExpressionStatement(@NotNull X10ParserParser.NonExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#nonExpressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitNonExpressionStatement(@NotNull X10ParserParser.NonExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#fUTURE_ExistentialList}.
	 * @param ctx the parse tree
	 */
	void enterFUTURE_ExistentialList(@NotNull X10ParserParser.FUTURE_ExistentialListContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#fUTURE_ExistentialList}.
	 * @param ctx the parse tree
	 */
	void exitFUTURE_ExistentialList(@NotNull X10ParserParser.FUTURE_ExistentialListContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#propertiesopt}.
	 * @param ctx the parse tree
	 */
	void enterPropertiesopt(@NotNull X10ParserParser.PropertiesoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#propertiesopt}.
	 * @param ctx the parse tree
	 */
	void exitPropertiesopt(@NotNull X10ParserParser.PropertiesoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#packageName}.
	 * @param ctx the parse tree
	 */
	void enterPackageName(@NotNull X10ParserParser.PackageNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#packageName}.
	 * @param ctx the parse tree
	 */
	void exitPackageName(@NotNull X10ParserParser.PackageNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(@NotNull X10ParserParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(@NotNull X10ParserParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#accept}.
	 * @param ctx the parse tree
	 */
	void enterAccept(@NotNull X10ParserParser.AcceptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#accept}.
	 * @param ctx the parse tree
	 */
	void exitAccept(@NotNull X10ParserParser.AcceptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#loopIndexDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterLoopIndexDeclarator(@NotNull X10ParserParser.LoopIndexDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#loopIndexDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitLoopIndexDeclarator(@NotNull X10ParserParser.LoopIndexDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(@NotNull X10ParserParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(@NotNull X10ParserParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(@NotNull X10ParserParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(@NotNull X10ParserParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterImportDeclaration(@NotNull X10ParserParser.ImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitImportDeclaration(@NotNull X10ParserParser.ImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#throwsopt}.
	 * @param ctx the parse tree
	 */
	void enterThrowsopt(@NotNull X10ParserParser.ThrowsoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#throwsopt}.
	 * @param ctx the parse tree
	 */
	void exitThrowsopt(@NotNull X10ParserParser.ThrowsoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#atExpression}.
	 * @param ctx the parse tree
	 */
	void enterAtExpression(@NotNull X10ParserParser.AtExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#atExpression}.
	 * @param ctx the parse tree
	 */
	void exitAtExpression(@NotNull X10ParserParser.AtExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#argumentListopt}.
	 * @param ctx the parse tree
	 */
	void enterArgumentListopt(@NotNull X10ParserParser.ArgumentListoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#argumentListopt}.
	 * @param ctx the parse tree
	 */
	void exitArgumentListopt(@NotNull X10ParserParser.ArgumentListoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#prefixOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPrefixOperatorDeclaration(@NotNull X10ParserParser.PrefixOperatorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#prefixOperatorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPrefixOperatorDeclaration(@NotNull X10ParserParser.PrefixOperatorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarators(@NotNull X10ParserParser.VariableDeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarators(@NotNull X10ParserParser.VariableDeclaratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#switchBlock}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlock(@NotNull X10ParserParser.SwitchBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#switchBlock}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlock(@NotNull X10ParserParser.SwitchBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeArgumentsopt}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgumentsopt(@NotNull X10ParserParser.TypeArgumentsoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeArgumentsopt}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgumentsopt(@NotNull X10ParserParser.TypeArgumentsoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#prefixOp}.
	 * @param ctx the parse tree
	 */
	void enterPrefixOp(@NotNull X10ParserParser.PrefixOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#prefixOp}.
	 * @param ctx the parse tree
	 */
	void exitPrefixOp(@NotNull X10ParserParser.PrefixOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#classMemberDeclarationsopt}.
	 * @param ctx the parse tree
	 */
	void enterClassMemberDeclarationsopt(@NotNull X10ParserParser.ClassMemberDeclarationsoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#classMemberDeclarationsopt}.
	 * @param ctx the parse tree
	 */
	void exitClassMemberDeclarationsopt(@NotNull X10ParserParser.ClassMemberDeclarationsoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(@NotNull X10ParserParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(@NotNull X10ParserParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#oBSOLETE_Offersopt}.
	 * @param ctx the parse tree
	 */
	void enterOBSOLETE_Offersopt(@NotNull X10ParserParser.OBSOLETE_OffersoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#oBSOLETE_Offersopt}.
	 * @param ctx the parse tree
	 */
	void exitOBSOLETE_Offersopt(@NotNull X10ParserParser.OBSOLETE_OffersoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameter(@NotNull X10ParserParser.TypeParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameter(@NotNull X10ParserParser.TypeParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(@NotNull X10ParserParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(@NotNull X10ParserParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void enterStatementExpression(@NotNull X10ParserParser.StatementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void exitStatementExpression(@NotNull X10ParserParser.StatementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull X10ParserParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull X10ParserParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(@NotNull X10ParserParser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(@NotNull X10ParserParser.ConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclarationStatement(@NotNull X10ParserParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclarationStatement(@NotNull X10ParserParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#asyncStatement}.
	 * @param ctx the parse tree
	 */
	void enterAsyncStatement(@NotNull X10ParserParser.AsyncStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#asyncStatement}.
	 * @param ctx the parse tree
	 */
	void exitAsyncStatement(@NotNull X10ParserParser.AsyncStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#fullyQualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterFullyQualifiedName(@NotNull X10ParserParser.FullyQualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#fullyQualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitFullyQualifiedName(@NotNull X10ParserParser.FullyQualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#modifiers}.
	 * @param ctx the parse tree
	 */
	void enterModifiers(@NotNull X10ParserParser.ModifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#modifiers}.
	 * @param ctx the parse tree
	 */
	void exitModifiers(@NotNull X10ParserParser.ModifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#loopIndex}.
	 * @param ctx the parse tree
	 */
	void enterLoopIndex(@NotNull X10ParserParser.LoopIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#loopIndex}.
	 * @param ctx the parse tree
	 */
	void exitLoopIndex(@NotNull X10ParserParser.LoopIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#basicForStatement}.
	 * @param ctx the parse tree
	 */
	void enterBasicForStatement(@NotNull X10ParserParser.BasicForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#basicForStatement}.
	 * @param ctx the parse tree
	 */
	void exitBasicForStatement(@NotNull X10ParserParser.BasicForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabel(@NotNull X10ParserParser.SwitchLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabel(@NotNull X10ParserParser.SwitchLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#closureBody}.
	 * @param ctx the parse tree
	 */
	void enterClosureBody(@NotNull X10ParserParser.ClosureBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#closureBody}.
	 * @param ctx the parse tree
	 */
	void exitClosureBody(@NotNull X10ParserParser.ClosureBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameters(@NotNull X10ParserParser.TypeParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameters(@NotNull X10ParserParser.TypeParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#propertyMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPropertyMethodDeclaration(@NotNull X10ParserParser.PropertyMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#propertyMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPropertyMethodDeclaration(@NotNull X10ParserParser.PropertyMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#atCaptureDeclarators}.
	 * @param ctx the parse tree
	 */
	void enterAtCaptureDeclarators(@NotNull X10ParserParser.AtCaptureDeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#atCaptureDeclarators}.
	 * @param ctx the parse tree
	 */
	void exitAtCaptureDeclarators(@NotNull X10ParserParser.AtCaptureDeclaratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#methodInvocation}.
	 * @param ctx the parse tree
	 */
	void enterMethodInvocation(@NotNull X10ParserParser.MethodInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#methodInvocation}.
	 * @param ctx the parse tree
	 */
	void exitMethodInvocation(@NotNull X10ParserParser.MethodInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#homeVariableList}.
	 * @param ctx the parse tree
	 */
	void enterHomeVariableList(@NotNull X10ParserParser.HomeVariableListContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#homeVariableList}.
	 * @param ctx the parse tree
	 */
	void exitHomeVariableList(@NotNull X10ParserParser.HomeVariableListContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#whereClauseopt}.
	 * @param ctx the parse tree
	 */
	void enterWhereClauseopt(@NotNull X10ParserParser.WhereClauseoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#whereClauseopt}.
	 * @param ctx the parse tree
	 */
	void exitWhereClauseopt(@NotNull X10ParserParser.WhereClauseoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#depParameters}.
	 * @param ctx the parse tree
	 */
	void enterDepParameters(@NotNull X10ParserParser.DepParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#depParameters}.
	 * @param ctx the parse tree
	 */
	void exitDepParameters(@NotNull X10ParserParser.DepParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(@NotNull X10ParserParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(@NotNull X10ParserParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#constructorBody}.
	 * @param ctx the parse tree
	 */
	void enterConstructorBody(@NotNull X10ParserParser.ConstructorBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#constructorBody}.
	 * @param ctx the parse tree
	 */
	void exitConstructorBody(@NotNull X10ParserParser.ConstructorBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#classBodyopt}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyopt(@NotNull X10ParserParser.ClassBodyoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#classBodyopt}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyopt(@NotNull X10ParserParser.ClassBodyoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#varKeywordopt}.
	 * @param ctx the parse tree
	 */
	void enterVarKeywordopt(@NotNull X10ParserParser.VarKeywordoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#varKeywordopt}.
	 * @param ctx the parse tree
	 */
	void exitVarKeywordopt(@NotNull X10ParserParser.VarKeywordoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(@NotNull X10ParserParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(@NotNull X10ParserParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#assignPropertyCall}.
	 * @param ctx the parse tree
	 */
	void enterAssignPropertyCall(@NotNull X10ParserParser.AssignPropertyCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#assignPropertyCall}.
	 * @param ctx the parse tree
	 */
	void exitAssignPropertyCall(@NotNull X10ParserParser.AssignPropertyCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#subtypeConstraint}.
	 * @param ctx the parse tree
	 */
	void enterSubtypeConstraint(@NotNull X10ParserParser.SubtypeConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#subtypeConstraint}.
	 * @param ctx the parse tree
	 */
	void exitSubtypeConstraint(@NotNull X10ParserParser.SubtypeConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeDeclarationsopt}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationsopt(@NotNull X10ParserParser.TypeDeclarationsoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeDeclarationsopt}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationsopt(@NotNull X10ParserParser.TypeDeclarationsoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#constraintConjunction}.
	 * @param ctx the parse tree
	 */
	void enterConstraintConjunction(@NotNull X10ParserParser.ConstraintConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#constraintConjunction}.
	 * @param ctx the parse tree
	 */
	void exitConstraintConjunction(@NotNull X10ParserParser.ConstraintConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#binOp}.
	 * @param ctx the parse tree
	 */
	void enterBinOp(@NotNull X10ParserParser.BinOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#binOp}.
	 * @param ctx the parse tree
	 */
	void exitBinOp(@NotNull X10ParserParser.BinOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#importDeclarationsopt}.
	 * @param ctx the parse tree
	 */
	void enterImportDeclarationsopt(@NotNull X10ParserParser.ImportDeclarationsoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#importDeclarationsopt}.
	 * @param ctx the parse tree
	 */
	void exitImportDeclarationsopt(@NotNull X10ParserParser.ImportDeclarationsoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#finishStatement}.
	 * @param ctx the parse tree
	 */
	void enterFinishStatement(@NotNull X10ParserParser.FinishStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#finishStatement}.
	 * @param ctx the parse tree
	 */
	void exitFinishStatement(@NotNull X10ParserParser.FinishStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#propertyList}.
	 * @param ctx the parse tree
	 */
	void enterPropertyList(@NotNull X10ParserParser.PropertyListContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#propertyList}.
	 * @param ctx the parse tree
	 */
	void exitPropertyList(@NotNull X10ParserParser.PropertyListContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#atStatement}.
	 * @param ctx the parse tree
	 */
	void enterAtStatement(@NotNull X10ParserParser.AtStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#atStatement}.
	 * @param ctx the parse tree
	 */
	void exitAtStatement(@NotNull X10ParserParser.AtStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull X10ParserParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull X10ParserParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#isRefConstraint}.
	 * @param ctx the parse tree
	 */
	void enterIsRefConstraint(@NotNull X10ParserParser.IsRefConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#isRefConstraint}.
	 * @param ctx the parse tree
	 */
	void exitIsRefConstraint(@NotNull X10ParserParser.IsRefConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(@NotNull X10ParserParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(@NotNull X10ParserParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPackageDeclaration(@NotNull X10ParserParser.PackageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPackageDeclaration(@NotNull X10ParserParser.PackageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeImportOnDemandDeclaration(@NotNull X10ParserParser.TypeImportOnDemandDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeImportOnDemandDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeImportOnDemandDeclaration(@NotNull X10ParserParser.TypeImportOnDemandDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#switchLabelsopt}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabelsopt(@NotNull X10ParserParser.SwitchLabelsoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#switchLabelsopt}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabelsopt(@NotNull X10ParserParser.SwitchLabelsoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#closureExpression}.
	 * @param ctx the parse tree
	 */
	void enterClosureExpression(@NotNull X10ParserParser.ClosureExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#closureExpression}.
	 * @param ctx the parse tree
	 */
	void exitClosureExpression(@NotNull X10ParserParser.ClosureExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(@NotNull X10ParserParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(@NotNull X10ParserParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(@NotNull X10ParserParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(@NotNull X10ParserParser.PropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#formalDeclarators}.
	 * @param ctx the parse tree
	 */
	void enterFormalDeclarators(@NotNull X10ParserParser.FormalDeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#formalDeclarators}.
	 * @param ctx the parse tree
	 */
	void exitFormalDeclarators(@NotNull X10ParserParser.FormalDeclaratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull X10ParserParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull X10ParserParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#interfaceMemberDeclarationsopt}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMemberDeclarationsopt(@NotNull X10ParserParser.InterfaceMemberDeclarationsoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#interfaceMemberDeclarationsopt}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMemberDeclarationsopt(@NotNull X10ParserParser.InterfaceMemberDeclarationsoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#parameterizedNamedType}.
	 * @param ctx the parse tree
	 */
	void enterParameterizedNamedType(@NotNull X10ParserParser.ParameterizedNamedTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#parameterizedNamedType}.
	 * @param ctx the parse tree
	 */
	void exitParameterizedNamedType(@NotNull X10ParserParser.ParameterizedNamedTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBody(@NotNull X10ParserParser.InterfaceBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBody(@NotNull X10ParserParser.InterfaceBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#extendsInterfacesopt}.
	 * @param ctx the parse tree
	 */
	void enterExtendsInterfacesopt(@NotNull X10ParserParser.ExtendsInterfacesoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#extendsInterfacesopt}.
	 * @param ctx the parse tree
	 */
	void exitExtendsInterfacesopt(@NotNull X10ParserParser.ExtendsInterfacesoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#packageOrTypeName}.
	 * @param ctx the parse tree
	 */
	void enterPackageOrTypeName(@NotNull X10ParserParser.PackageOrTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#packageOrTypeName}.
	 * @param ctx the parse tree
	 */
	void exitPackageOrTypeName(@NotNull X10ParserParser.PackageOrTypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#void_}.
	 * @param ctx the parse tree
	 */
	void enterVoid_(@NotNull X10ParserParser.Void_Context ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#void_}.
	 * @param ctx the parse tree
	 */
	void exitVoid_(@NotNull X10ParserParser.Void_Context ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#modifiersopt}.
	 * @param ctx the parse tree
	 */
	void enterModifiersopt(@NotNull X10ParserParser.ModifiersoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#modifiersopt}.
	 * @param ctx the parse tree
	 */
	void exitModifiersopt(@NotNull X10ParserParser.ModifiersoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#explicitConstructorInvocationopt}.
	 * @param ctx the parse tree
	 */
	void enterExplicitConstructorInvocationopt(@NotNull X10ParserParser.ExplicitConstructorInvocationoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#explicitConstructorInvocationopt}.
	 * @param ctx the parse tree
	 */
	void exitExplicitConstructorInvocationopt(@NotNull X10ParserParser.ExplicitConstructorInvocationoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclaration(@NotNull X10ParserParser.StructDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclaration(@NotNull X10ParserParser.StructDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#clockedClauseopt}.
	 * @param ctx the parse tree
	 */
	void enterClockedClauseopt(@NotNull X10ParserParser.ClockedClauseoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#clockedClauseopt}.
	 * @param ctx the parse tree
	 */
	void exitClockedClauseopt(@NotNull X10ParserParser.ClockedClauseoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#oBSOLETE_Offers}.
	 * @param ctx the parse tree
	 */
	void enterOBSOLETE_Offers(@NotNull X10ParserParser.OBSOLETE_OffersContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#oBSOLETE_Offers}.
	 * @param ctx the parse tree
	 */
	void exitOBSOLETE_Offers(@NotNull X10ParserParser.OBSOLETE_OffersContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(@NotNull X10ParserParser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(@NotNull X10ParserParser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeParamsWithVarianceopt}.
	 * @param ctx the parse tree
	 */
	void enterTypeParamsWithVarianceopt(@NotNull X10ParserParser.TypeParamsWithVarianceoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeParamsWithVarianceopt}.
	 * @param ctx the parse tree
	 */
	void exitTypeParamsWithVarianceopt(@NotNull X10ParserParser.TypeParamsWithVarianceoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(@NotNull X10ParserParser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(@NotNull X10ParserParser.IdentifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#atCaptureDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterAtCaptureDeclarator(@NotNull X10ParserParser.AtCaptureDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#atCaptureDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitAtCaptureDeclarator(@NotNull X10ParserParser.AtCaptureDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#blockStatements}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatements(@NotNull X10ParserParser.BlockStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#blockStatements}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatements(@NotNull X10ParserParser.BlockStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#ifThenElseStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfThenElseStatement(@NotNull X10ParserParser.IfThenElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#ifThenElseStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfThenElseStatement(@NotNull X10ParserParser.IfThenElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#forInitopt}.
	 * @param ctx the parse tree
	 */
	void enterForInitopt(@NotNull X10ParserParser.ForInitoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#forInitopt}.
	 * @param ctx the parse tree
	 */
	void exitForInitopt(@NotNull X10ParserParser.ForInitoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#oBSOLETE_FinishExpression}.
	 * @param ctx the parse tree
	 */
	void enterOBSOLETE_FinishExpression(@NotNull X10ParserParser.OBSOLETE_FinishExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#oBSOLETE_FinishExpression}.
	 * @param ctx the parse tree
	 */
	void exitOBSOLETE_FinishExpression(@NotNull X10ParserParser.OBSOLETE_FinishExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#doStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoStatement(@NotNull X10ParserParser.DoStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#doStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoStatement(@NotNull X10ParserParser.DoStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#ifThenStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfThenStatement(@NotNull X10ParserParser.IfThenStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#ifThenStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfThenStatement(@NotNull X10ParserParser.IfThenStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#catchesopt}.
	 * @param ctx the parse tree
	 */
	void enterCatchesopt(@NotNull X10ParserParser.CatchesoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#catchesopt}.
	 * @param ctx the parse tree
	 */
	void exitCatchesopt(@NotNull X10ParserParser.CatchesoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#typeDefDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefDeclaration(@NotNull X10ParserParser.TypeDefDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#typeDefDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefDeclaration(@NotNull X10ParserParser.TypeDefDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(@NotNull X10ParserParser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(@NotNull X10ParserParser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#superExtendsopt}.
	 * @param ctx the parse tree
	 */
	void enterSuperExtendsopt(@NotNull X10ParserParser.SuperExtendsoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#superExtendsopt}.
	 * @param ctx the parse tree
	 */
	void exitSuperExtendsopt(@NotNull X10ParserParser.SuperExtendsoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#fUTURE_ExistentialListopt}.
	 * @param ctx the parse tree
	 */
	void enterFUTURE_ExistentialListopt(@NotNull X10ParserParser.FUTURE_ExistentialListoptContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#fUTURE_ExistentialListopt}.
	 * @param ctx the parse tree
	 */
	void exitFUTURE_ExistentialListopt(@NotNull X10ParserParser.FUTURE_ExistentialListoptContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#annotationStatement}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationStatement(@NotNull X10ParserParser.AnnotationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#annotationStatement}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationStatement(@NotNull X10ParserParser.AnnotationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#hasResultType}.
	 * @param ctx the parse tree
	 */
	void enterHasResultType(@NotNull X10ParserParser.HasResultTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#hasResultType}.
	 * @param ctx the parse tree
	 */
	void exitHasResultType(@NotNull X10ParserParser.HasResultTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#superExtends}.
	 * @param ctx the parse tree
	 */
	void enterSuperExtends(@NotNull X10ParserParser.SuperExtendsContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#superExtends}.
	 * @param ctx the parse tree
	 */
	void exitSuperExtends(@NotNull X10ParserParser.SuperExtendsContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(@NotNull X10ParserParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(@NotNull X10ParserParser.MethodBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#formalDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterFormalDeclarator(@NotNull X10ParserParser.FormalDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#formalDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitFormalDeclarator(@NotNull X10ParserParser.FormalDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#variableDeclaratorWithType}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorWithType(@NotNull X10ParserParser.VariableDeclaratorWithTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#variableDeclaratorWithType}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorWithType(@NotNull X10ParserParser.VariableDeclaratorWithTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#statementExpressionList}.
	 * @param ctx the parse tree
	 */
	void enterStatementExpressionList(@NotNull X10ParserParser.StatementExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#statementExpressionList}.
	 * @param ctx the parse tree
	 */
	void exitStatementExpressionList(@NotNull X10ParserParser.StatementExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroup(@NotNull X10ParserParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroup(@NotNull X10ParserParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMemberDeclaration(@NotNull X10ParserParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMemberDeclaration(@NotNull X10ParserParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link X10ParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull X10ParserParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link X10ParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull X10ParserParser.LiteralContext ctx);
}