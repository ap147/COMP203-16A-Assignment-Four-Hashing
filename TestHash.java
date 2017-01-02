//Amarjot Parmar
//1255668

//Reads from txt documents and perform operations on a hashtable
import java.io.FileReader;
import java.io.BufferedReader;
public class TestHash
{
    private static MyHashTable myHashTable;       
   
    //takes 3 arguments in format L/K Size of table txtname
    public static void main(String[] args)
    {
        if(args.length != 3)
        {
            System.out.println("Error: Arguement Length Not 3 !!");
            return;
        }
        
        try
        {
            //-------------------------------------------------------------- Capacity
            int capacity = Integer.parseInt(args[1]);

            if(capacity <= 0)
            { 
                System.out.println("Error: Capacity Too Small");
                return;
            }
            
            myHashTable = new MyHashTable(capacity);     
            //-------------------------------------------------------------- Resolution 
            String resolution;
            resolution = args[0];
            
            if(resolution.equals("L") || resolution.equals("K"))
            { 
                //Setting collision resolution method table will be using
                if(resolution.equals("L"))
                {
                    myHashTable.setResolutionType('L');
                }
                else
                {
                     myHashTable.setResolutionType('K');  
                }
                
                //---------------------------------------------------------- File  
                BufferedReader br = new BufferedReader(new FileReader(args[2])); 
                String line; 

                while((line = br.readLine()) != null)
                {
                    //If hashtables loadfactor hasnt exceeded 80%
                    if(myHashTable.LoadFactorExceeded() == false)
                    {
                        process(line);
                    }
                    else
                    { 
                        break;
                    }
                }
                br.close();
            }
            else
            {
              System.out.println("Error: Resolution Type is not 'L' or 'K'");  
            }            
        }
        catch(Exception e)
        {
            System.out.println("");
            System.out.println("Exception occured while reading file or arguement." + e);
        }
       
        System.out.println("COLLISIONS: " + myHashTable.getCollisions());  
    }

    //Method which takes a string of instructions, breaks them down and carries out operation on hashtable
    public static void process(String line)
    {
        int key =0; float amount =0; char type =0;
        //making an array of Strings which are seprated by a space
        String[] array = line.split(" ");
        
        //In case input is not correct/ format, method does nothing and prints error message 
        try{
            if(array.length != 3)
            {
                System.out.println("Error: line length not 3");
            }
            else
            {
               //Extracting information out of array, converting them into right data types
                key = Integer.parseInt(array[0]);
                type = array[1].charAt(0);
                amount = Float.parseFloat(array[2]);
                
                Account target = myHashTable.get(key);
                
                if(type == 'c')
                {
                    if(target != null)
                    {
                      myHashTable.remove(key); 
                    }
                }
                else
                {
                    if(target != null)
                    {
                         target.ReProcess(type, amount);
                    }
                    else{
                         target = new Account(key, 0);
                         if(type == 'w')
                         {
                            amount = amount - amount - amount; 
                         }
                         target.update(amount);
                         myHashTable.put(key,target);
                        }   
                }               
            } 
        }catch(Exception e){
            System.out.println("Error while processing line !!");
        }
        
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}