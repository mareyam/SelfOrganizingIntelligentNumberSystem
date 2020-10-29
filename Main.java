//package sessional1;
/*
*       This program was created by Maryam Naveed
*       FA19-BSE-069 Section-A
*       on 22th October 2020, 9:15pm
*	
*/
import java.util.Scanner;
import java.util.Set;

class NumEditor {

    private final int ARRAY_SIZE = 100;
    private int[] numArray = new int[ARRAY_SIZE];
    private num_and_index ObjectNumIndex = new num_and_index();
    private Stack stack = new Stack();
    private int counter, number;

    public NumEditor() {      
        this.counter = 0;
    }
	public int getCounter(){
		return counter;
	}
	public int getSize(){
		return numArray.length;
	}

    public boolean insertNumber(int number){
        int i = 0, j;

        if (counter >= ARRAY_SIZE)              //counter exceeds array size
            return false;

        if (counter == 0)
        {
            numArray[counter++] = number;      //if counter value is 0 then place the number at the position
            return true;
        }
        for (i = 0; i < counter; i++)
        {
            if (numArray[i] > number)    //if array exceeds the number then break
            {
                break;
            }
        }

        for (j = counter; j > i; j--)    //starting from counter, till j is greater than i, move the elements to the left
        {
            numArray[j] = numArray[j - 1];
        }

        numArray[j] = number;        //replacing the number and incrementing the counter
        counter++; 
        return true;
    }

    public void deleteOne(int index)
    {         
        stack.push(index, numArray[index]);
		int i = index;
        while ( i < counter -1)
		{
                   numArray[i] = numArray[++i];
		}
		counter--;
    }
    
    public void deleteInRange(int start, int end){       
	int totalDeletions = end - start;
       while(end< counter){
		   numArray[start++] = numArray[end++];
		   
	   }
	   counter-= totalDeletions;
     
    }

    public boolean findAndReplace_onlyOne(int oldNumber, int newNumber){            
		for(int  i = 0; i< counter ;i++){
	        if(numArray[i]==oldNumber)        //first delete the oldNumber, if the condition is true
	        {
				deleteOne(i);
	            insertNumber(newNumber);    //insert the number using insertNumber
	            return true;
	        }
		}
        return false;
    }
    
    public int replaceAll(int oldNumber, int newNumber){
      int i =0,replacements = 0;
        while (i<counter){
                if(numArray[i] == oldNumber){   //while the condition does not return false, keep replacing all the occurence
					deleteOne(i);
					insertNumber(newNumber);
					replacements++;
					continue;
					
                }
                i++;
            }
            return replacements;
    }
    
    public void ViewArray()   //simply print the array
    {
        int i;
        for (i = 0; i < counter; i++)
        {
            System.out.println("\n\t" + numArray[i]);
        }
    }
    public void insertWithIndex(int number, int index)
    {
        int i;
        if(index>=counter)
        {
            numArray[counter++] = number;
            return;
        }
        i=counter;
        while(i>index)
        {
            numArray[i] = numArray[--i];
        }
        numArray[index]=number;
        counter++;
               
    }
    public void undoDelete()
    {
       
        num_and_index ObjectNumIndex = stack.pop();
        insertWithIndex(ObjectNumIndex.getNum(),ObjectNumIndex.getIndex());
        
    }
}

public class Main 
{
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int opt = 1;
        int num, num2;
        NumEditor NE = new NumEditor();

        while (true) {
            System.out.print("\n\t\t 1: Insert Number. ");
            System.out.print("\n\t\t 2: Delete Number. ");
            System.out.print("\n\t\t 3: Delete Numbers in range. ");
            System.out.print("\n\t\t 4: Find and replace a number only once ");
            System.out.print("\n\t\t 5: Find and replace all the occurences of a number ");
            System.out.print("\n\t\t 6: Undo delete");
            System.out.print("\n\t\t 7: View Numbers. ");
            System.out.print("\n\t\t 0: Exit. ");
            System.out.print("\n\t\t Enter Your Choice:    ");
            opt = input.nextInt();
            if (opt == 0) break;
            switch (opt) {
                
                /*
                *      method for printStatement is given at the end
                */
                
                case 1:
					if(NE.getCounter() >= NE.getSize())
						System.out.println("Array Full");
					else{
						System.out.print("\n\t\t Sir Please Enter Number. ");
						num = input.nextInt();
						printStatement(NE.insertNumber(num), "Insertion");       } 
                    break;
                case 2:
					if(NE.getCounter() <= 0)
						System.out.println("Array Empty");
					else{
						System.out.print("\n\t\t Sir Please Enter index. ");
						num = input.nextInt();
						if(num<0 || num >=NE.getCounter())
						{
							System.out.println("Invalid");
							break;
						}
						NE.deleteOne(num);
						printStatement(true, "Deletion");       }
                    break;
                case 3:
                	if(NE.getCounter() <= 0)
							System.out.println("Array Empty");
					else{
						System.out.print("\n\t\t Sir Please Enter Minimum index. ");
						num = input.nextInt();
						
						System.out.print("\n\t\t Sir Please Enter Maximum index. ");
						num2 = input.nextInt();
						if(num < 0 || num2 > NE.getCounter() || num2 <= num) {
							System.out.println("Invalid");
							break;
						}
						NE.deleteInRange(num, num2);
						printStatement(true, "Deletion");}
                    break;
                case 4:
                	if(NE.getCounter() <= 0)
						System.out.println("Array Empty");
					else{
						System.out.print("\n\t\t Sir Please Enter Old Number. ");
						num = input.nextInt();
						System.out.print("\n\t\t Sir Please Enter New Number. ");
						num2 = input.nextInt();
						printStatement(NE.findAndReplace_onlyOne(num, num2), "replacement");
                    }break;
                case 5:
                	if(NE.getCounter() <= 0)
						System.out.println("Array Empty");
					else{
						System.out.print("\n\t\t Sir Please Enter Old Number. ");
						num = input.nextInt();
						System.out.print("\n\t\t Sir Please Enter New Number. ");
						num2 = input.nextInt();
						if(NE.replaceAll(num, num2)==0)
							printStatement(false, "replacement");
						else
							printStatement(true, "replacement");
						}
					break;
                case 6:
                   
                                        System.out.println("\n\tUndo-ing the delete call");
                                        NE.undoDelete();
                                        break;
                case 7:
                	if(NE.getCounter() <= 0)
							System.out.println("Array Empty");
					else
						NE.ViewArray();
                    break;
				default :
					break;
            }

        }
    }

   public static void printStatement(boolean result, String statement) 
    {      
        if (result)   //if the statement is true
        {    
            System.out.println("Congratulation: " + statement + " successful");
        } 
        else        //if statement is false 
        {
            System.out.println("Sorry! " + statement + " Failed");
        }
    }
}



class Stack
{
    private num_and_index[] stackArray = new num_and_index[100];
    private int top;  
    
    num_and_index pop()
    {
        top--;
        return stackArray[top];
    }
    
    boolean isEmpty()
    {
        if(top <= 0)
            return true;
        return false;
    }
    
    boolean isFull()
    {
        if(top >= stackArray.length)
            return true;
        return false;    
    }
    
    
    
    void push(int index, int number)
    {
        stackArray[top] = new num_and_index();
        stackArray[top].setNum(number);
        stackArray[top].setIndex(index);
        
        top++;
        
       
    }
    
    
    
    
}


class num_and_index
{
    private int number;
    private int index;
   
    
    public int getNum()
    {
        return number;
    }
    
     public int getIndex()
    {
        return index;
    }
     
    public void setIndex(int index)
    {
        this.index =  index;
    }

    public void setNum(int number)
    {
        this.number = number;
    }
}