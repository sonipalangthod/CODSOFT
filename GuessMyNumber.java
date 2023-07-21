import java.util.Scanner;
import java.util.*;
public class GuessMyNumber{
public static void main(String[] args)
{
Scanner console = new Scanner(System.in);
int number,guess;
int tries = 0;
Random ran = new Random();
number = ran.nextInt(100);
System.out.println("Guess My Number Game"); 
System.out.println();
do
{
System.out.print("Enter a guess between 1 and 100: "); 
guess=console.nextInt();
tries++;
if (guess > number)
{
System.out.println("Too high! Try Again");
}
else if (guess < number)
{
System.out.println("Too low! Try Again");
}
else
{
System.out.println("Correct! You got it in " +tries+ " guesses!");
}
} while (guess != number);
}
}