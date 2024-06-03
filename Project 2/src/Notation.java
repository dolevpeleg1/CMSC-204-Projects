import java.util.Stack;

/**
 * Utility class for the notation
 * @author Dolev Peleg
 */
public class Notation 
{
	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix
	 * @return a postfix form of the parameter's string
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException
	{
		String result = "";
		MyStack<Character> operatorStack = new MyStack<>(infix.length()); // A new MyStack to hold the operators
		MyQueue<Character> infixQueue = new MyQueue<>(infix.length()); // A new MyQueue to gold the infix expression
		MyQueue<Character> postfixQueue = new MyQueue<>(infix.length()); // A new MyQueue to gold the postfix expression
		
		// For loop to go through all the characters of the infix expression
		for(int counter = 0; counter < infix.length(); counter++)
		{
			// Try to enqueue the current character into the queue
			try
			{
				infixQueue.enqueue(infix.charAt(counter));
			}
			// If the queue is full, print the error message
			catch(QueueOverflowException e)
			{
				System.out.println(e.getMessage());
			}
			
			// If the current character is a space, dequeue its
			if(infix.charAt(counter) == ' ')
			{
				try
				{
					infixQueue.dequeue();
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
			}
			
			// Else if the current added character is a digit
			else if(Character.isDigit(infix.charAt(counter)))
			{
				// Try to dequeue the infix queue, adding it to the postfix queue
				try
				{
					postfixQueue.enqueue(infixQueue.dequeue());
				}
				// If either exception is thrown, print its message
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			// If the current character in the infix is a left parenthesis, push it onto the stack
			else if (infix.charAt(counter) == '(')
			{
				try
				{
					operatorStack.push(infixQueue.dequeue());
				}
				// Catch the exception
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			// If the character is either a multiplication or division operator, push it onto the stack
			else if(infix.charAt(counter) == '*' || infix.charAt(counter) == '/' || infix.charAt(counter) == '%')
			{
				try
				{
					// If the stack is empty, or the top of the stack is not multiplication, division and module operators, push the character onto the stack
					if(operatorStack.isEmpty() || (operatorStack.top() != '*' && operatorStack.top() != '/' && operatorStack.top() != '%'))
					{
						operatorStack.push(infixQueue.dequeue());
					}
					// Else
					else
					{
						// While the top of the stack is either multiplication division, or module operators
						while(operatorStack.top() == '*' || operatorStack.top() == '/' || operatorStack.top() == '%')
						{
							// Pop all operators from the stack into the postfix queue
							postfixQueue.enqueue(operatorStack.pop());

							// If the stack is now empty, break the while loop
							if(operatorStack.isEmpty())
							{
								break;
							}
						}
						operatorStack.push(infixQueue.dequeue()); // Add the operator to the stack
					}
				}
				
				// Catch all exceptions
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			// If the character is either a plus or a minus operator
			else if(infix.charAt(counter) == '+' || infix.charAt(counter) == '-' )
			{
				// If the stack is empty, push the character onto the stack
				if(operatorStack.isEmpty())
				{
					try
					{
						operatorStack.push(infixQueue.dequeue());
					}
					// Catch the exception
					catch (Exception e)
					{
						System.out.println(e.getMessage());
					}
				}
				// if stack is not empty
				else
				{
					try
					{
						// While the top of the stack is either minus, plus, multiplication, division, or module operators
						while(operatorStack.top() == '-' ||operatorStack.top() == '+' ||
								operatorStack.top() == '*' || operatorStack.top() == '/' || operatorStack.top() == '%')
						{
							// Pop all operators from the stack into the postfix queue
							postfixQueue.enqueue(operatorStack.pop());

							// If the stack is now empty, break the while loop
							if(operatorStack.isEmpty())
							{
								break;
							}
						}
						operatorStack.push(infixQueue.dequeue()); // Add the operator to the stack
					}
					// Catch all exceptions
					catch (Exception e)
					{
						System.out.println(e.getMessage());
					}
				}
			}
			
			// If the current character in the infix is a right parenthesis 
			else if (infix.charAt(counter) == ')')
			{
				try
				{
					// While the top of the stack is not a left parenthesis
					while(operatorStack.top() != '(')
					{
						postfixQueue.enqueue(operatorStack.pop()); //pop all the operators from the stack, enqueue them to the postfixQueue
						if(operatorStack.isEmpty()) // if no left parenthesis, throw an InvalidNotationFormatException
						{
							throw new InvalidNotationFormatException();
						} 
					}
					operatorStack.pop(); //	Pop (and discard) the left parenthesis from the stack
					infixQueue.dequeue();// Dequeue the right parenthesis from the infix queue  
				}
				// If an exception was thrown, print its message
				catch(InvalidNotationFormatException e)
				{
					throw new InvalidNotationFormatException(); // if the caught exception is an InvalidNotationFormatException(), throw it again
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		} // End for loop
		
		// Pop all remaining operators into the postfix queue using a for loop
		int amountOfOperators = operatorStack.size(); // Variable to hold the size of the stack
		for(int counter = 0; counter < amountOfOperators; counter++)
		{
			// If the stack is not empty
			if(!operatorStack.isEmpty())
			try
			{	
				postfixQueue.enqueue(operatorStack.pop());
			}
			// Catch the exception
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
		
		// return the postfixQueue toString method
		return result = postfixQueue.toString();
		
	} // End infixToPostfix
	
	/**
	 * Convert postfix expressions into infix expressions
	 * @param postfix
	 * @return
	 * @throws InvalidNotationFormatException
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException
	{
		String result = "";
		MyStack<String> infixStack = new MyStack<>(postfix.length() * 2); // A new MyStack to hold the infix expression
		String operand1; // Variable that holds the first operand
		String operand2; // Variable that holds the second operand
		String operator; // Variable that holds the operator
		String tempString = ""; // Variable that holds a temporary infix expression
		
		// For loop to go through all the characters of the postfix expression
		for(int counter = 0; counter < postfix.length(); counter++)
		{
			// If the current character in the postfix is a space, ignore it
			if(postfix.charAt(counter) == ' ')
			{
				continue;
			}
			
			// If the current character is an operand, push it on the stack
			if(Character.isDigit(postfix.charAt(counter)))
			{
				try
				{
					infixStack.push(String.valueOf(postfix.charAt(counter)));
				}
				// Catch any exception
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			// If the character is an operator
			else if(postfix.charAt(counter) == '+' || postfix.charAt(counter) == '-'  || postfix.charAt(counter) == '*' 
					|| postfix.charAt(counter) == '/' || postfix.charAt(counter) == '%')
			{
				try
				{
					// Pop the top 2 values from the stack. If there are fewer than 2 values throw an error
					if(infixStack.size() < 2)
					{
						throw new InvalidNotationFormatException();
					}
					operand2 = infixStack.pop();
					operand1 = infixStack.pop();
					operator = String.valueOf(postfix.charAt(counter));
					
					// Create a string with 1st value and then the operator and then the 2nd value, encapsulate the resulting string within parenthesis
					switch(operator)
					{
					case "+": tempString = "(" + operand1 + "+" + operand2 + ")"; break;
					case "-": tempString = "(" + operand1 + "-" + operand2 + ")"; break;
					case "*": tempString = "(" + operand1 + "*" + operand2 + ")"; break;
					case "/": tempString = "(" + operand1 + "/" + operand2 + ")"; break;
					case "%": tempString = "(" + operand1 + "%" + operand2 + ")"; break;
					}
					// Push the resulting string onto the stack
					infixStack.push(tempString);
				}
				catch(InvalidNotationFormatException e)
				{
					throw new InvalidNotationFormatException(); // If InvalidNotationFormatException was thrown, throw it again
				}
				// Catch any exception
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		} // End for loop
		// When the postfix expression has been read

		// If there is only one value in the stack – it is the infix string, if more than one value, throw an error
		if(infixStack.size() > 1)
		{
			throw new InvalidNotationFormatException();
		}
		return result = infixStack.toString(); // Return the stack as a string
	}
	
	/**
	 * Evaluate postfix expression
	 * @param postfix
	 * @return the postfix value as a double
	 * @throws InvalidNotationFormatException
	 */
	public static double evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException
	{
		double result = 0;
		double operand1; // Variable that holds the first operand
		double operand2; // Variable that holds the second operand
		char operator; // Variable that holds the operator
		double tempValue = 0; // Variable that holds the temporary expression's results 
		MyStack<Double> evaluationStack = new MyStack<>(postfix.length()); // A new MyStack to hold the evaluated expression
		
		// For loop to go through all the characters of the postfix expression
		for(int counter = 0; counter < postfix.length(); counter++)
		{
			// If the character is a space, ignore it
			if(postfix.charAt(counter) == ' ')
			{
				continue;
			}
			
			// If the current character is an operand or left parenthesis, push on the stack
			else if(postfix.charAt(counter) == '(' || Character.isDigit(postfix.charAt(counter)))
			{
				try
				{
					evaluationStack.push(Double.valueOf(String.valueOf(postfix.charAt(counter))));
				}
				// Catch any exception
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			//If the current character is an operator
			else if(postfix.charAt(counter) == '+' || postfix.charAt(counter) == '-'  || postfix.charAt(counter) == '*' 
					|| postfix.charAt(counter) == '/' || postfix.charAt(counter) == '%')
			{
				try
				{
					// Pop the top 2 values from the stack. If there are fewer than 2 values, if the stack has less than 2, an exception will be thrown
					if(evaluationStack.size() < 2)
					{
						throw new InvalidNotationFormatException();
					}
					operand2 = evaluationStack.pop();
					operand1 =evaluationStack.pop();
					operator = postfix.charAt(counter);
					
					// Perform the arithmetic calculation of the operator with the first popped value as the right operand and the second popped value as the left operand
					switch(operator)
					{
					case '+': tempValue = operand1 + operand2; break;
					case '-': tempValue = operand1 - operand2; break;
					case '*': tempValue = operand1 * operand2; break;
					case '/': tempValue = operand1 / operand2; break;
					case '%': tempValue = operand1 % operand2; break;
					}
					evaluationStack.push(tempValue); // Push the resulting value onto the stack
				}
				catch(InvalidNotationFormatException e)
				{
					throw new InvalidNotationFormatException(); // If InvalidNotationFormatException was thrown, throw it again
				}
				// Catch any exception
 				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		} // End for loop
		
		// When the postfix expression has been read
		
		// If there is only one value in the stack – it is the result of the postfix expression, if more than one value, throw an error
		if(evaluationStack.size() > 1)
		{
			throw new InvalidNotationFormatException();
		}
		
		// If the stack is not empty, assign its value to the result as a double
		if(!evaluationStack.isEmpty())
		{
			result = Double.valueOf(evaluationStack.toString());
		}
		return result;
	} // End evaluatePostfix
	
} // End Notation
