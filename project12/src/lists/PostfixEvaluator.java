package lists;

import java.util.Scanner;

public class PostfixEvaluator {

	public static void main( String[] args )  {
		   
		   String expression = new String();
		   Scanner input = new Scanner(System.in);
		   expression = input.nextLine();
		   input.close();
		   
		   try {
			if (evaluatePostfixExpression(expression)) 
				System.out.println("SYNTAX EXPRESSION CORRECT");
		   }	
		  catch (EmptyListException EL) {
			
		}
		  catch (InvalidExpression IE) {
		  }
	   } 

	   // evaluate the postfix notation
	   public static boolean evaluatePostfixExpression( String expr ) throws EmptyListException, InvalidExpression{

		   Stack myStack = new Stack("myStack");  
		   final int ASCII_MOD = 48; final int ASCII_SPACE_MOD = -16;  
		   int pointer, op1 = -1, op2 = -1, res = 0, cntOp = 0, cntOr = 0; 
		   char[] array = expr.toCharArray();
		   
		   
		   for (int i = 0; i < expr.length(); i++) {
			   pointer = array[i] - ASCII_MOD;
			 
			   if (isNum(pointer)){
				   myStack.push(pointer);
				   cntOp++;
			   }
			  
			   else if (pointer != ASCII_SPACE_MOD) { // skip ASCII spaces
				   
				   cntOr++;
				   
				   op1 = (int) myStack.pop();   
				   op2 = (int) myStack.pop(); 
				   
				   switch( (int)array[i] ) {
					   
				   default: 
					   System.out.println("OPERAND NOT RECOGNISED");
					   break;
				   case 43: // ASCII add
					   res = (int) Math.addExact(op2, op1);
					   break;
				   case 45: // ASCII sub
					   res = (int) Math.subtractExact(op2, op1);
					   break;
				   case 42: // ASCII mul
					   res = (int) Math.multiplyExact(op2, op1);
					   break;
				   case 47: // ASCII div
					   res = (int) Math.floorDiv(op2, op1);
					   break;
				   case 37: // ASCII mod
					   res = (int) Math.floorMod(op2, op1);
					   break;
				   case 94: // ASCII exp
					   res = (int) Math.pow(op2, op1) ;
					   break;
				   }						   
			   myStack.push((int)res);
		   }   
			   else
				   continue;
		   }
		   
		   if ( cntOp != cntOr + 1 ) {
			   throw new InvalidExpression();
		   }
		   
		   System.out.println("\nRESULT: " + myStack.pop() );
		   return true;
	   }

	private static boolean isNum(int c) {
		
		if ( c >= 0 && c <= 9 )
			return true;
		
		else
			return false;
	}
}
