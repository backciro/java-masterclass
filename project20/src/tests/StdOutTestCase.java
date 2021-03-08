package tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

public abstract class StdOutTestCase extends TestCase {
	protected static void assertContains(String output, String sub) {
		if(output==null || output.indexOf(sub)<0){
			AssertionFailedError e = new AssertionFailedError
				("String '" + sub + "' is not contained in '" + output + "'");
			fixStack(e);
			throw e;
		}
	}

	private static void fixStack(Throwable e) {
		StackTraceElement[] original = e.getStackTrace();
		StackTraceElement[] modified = new StackTraceElement[original.length-1];
		for(int i=0; i<modified.length; ++i){
			modified[i] = original[i+1];
		}
		e.setStackTrace(modified);
	}

	protected static void assertContainsInOrder(String output, String[] subs) {
		if(output==null){
			NullPointerException e = new NullPointerException();
			fixStack(e);
			throw e;
		}
		int prev,curr;
		prev = -2;
		for(int i=0; i<subs.length; ++i){
			curr = output.indexOf(subs[i]);
			if(curr<0){
				AssertionFailedError e = new AssertionFailedError
				("String '" + subs[i] + "' is not contained in '" + output + "'");
				fixStack(e);
				throw e;
			}
			if(curr<prev){
				AssertionFailedError e = new AssertionFailedError
				("String '" + subs[i] + "' does not follow '" + subs[i-1] + "'");
				fixStack(e);
				throw e;
			}
		}
	}

	private PrintStream out;
	private ByteArrayOutputStream buffer;
	protected synchronized void startCapture(){
		while(out!=null){
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException("Got interrupted!",e);
			}
		}
		out = System.out;
		buffer = new ByteArrayOutputStream();
		System.setOut(new PrintStream(buffer));
	}
	
	protected synchronized String stopCapture(){
		String output = buffer.toString();
		System.setOut(out);
		out = null;
		notify();
		return output;
	}
}
