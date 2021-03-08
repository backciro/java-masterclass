import static org.junit.Assert.*;
import lists.EmptyListException;
import lists.InvalidExpression;
import lists.PostfixEvaluator;


public class Test {

	@org.junit.Test
	public void test() {

		try{
			String postfix = " 9 2 3 + 4 5 - * 2 ^ 4 % + 3 / ";
			int r = PostfixEvaluator.evaluatePostfixExpression( postfix );
			assertEquals(3,r);
			
			postfix = " 9 2 / * ";
			r = PostfixEvaluator.evaluatePostfixExpression( postfix );
			fail("An EmptyListException should be thrown");
			
			postfix = " 9 2 ";
			r = PostfixEvaluator.evaluatePostfixExpression( postfix );
			fail("An InvalidExpression should be thrown");
		}
		catch (EmptyListException el) {
			assertTrue(true);
		}
		catch (InvalidExpression ie) {
			assertTrue(true);
		}
	}

}
