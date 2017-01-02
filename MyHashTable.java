//Amarjot Parmar
//1255668
 
//Contains method to maintain/use a Hash table
public class MyHashTable
{
    //array of references to data objects
    private Account[] accountArray; 
    //array of integers to hold keys
    private int[] refrenceArray;   
    
    //Total amount of keys/data this HashTable can hold
    private int capacity;   
    //Amount of keys/data stored in HashTable atm
    private int currentlyUsed; 
    //Amount of times had to reprob hashcode, only incremented in resolution methods
    private int collisions;    
    
    //Holds char value 'K' or 'L',indicateing what collision resolution to use
    private char resolutionType; 
    
    //Contructor, Sets size of table 
    public MyHashTable(int _capacity)
    {
        capacity      = _capacity;
        currentlyUsed = 0;
        collisions    = 0;
        
        accountArray  = new Account[_capacity];
        refrenceArray = new int[_capacity];
   }

    //puts data object/key in hashtable at location given by hashcode computed for key
    public void put(int _k, Account _data)
    {
        //modulo hash
        int hashCode = _k % capacity;       
        boolean emptySpaceFound = false; 
        
        //loop until found empty slot
        while(emptySpaceFound == false)
        { 
            if(accountArray[hashCode] == null)
            {  
                 emptySpaceFound = true; 
            }
            else
            {   
                 //Update hashcode with new hashcode
                 hashCode = getNewhashCode(hashCode, _k);
            }   
        }
        
        //Adding new data to arrays, Incrementing amount of slots used
        refrenceArray[hashCode] = _k;
        accountArray[hashCode]  = _data;
        currentlyUsed = currentlyUsed + 1;
    }
	
    //that removes the datum with key k
    public void remove(int _k)
    { 
        int hashCode = _k % capacity;
        boolean targetFound = false;
        int notFound = 0;
        
        //follow path of key, until key is found or empty slot 
        while(targetFound == false)
        {
            
            if(refrenceArray[hashCode] == _k)
            {
                refrenceArray[hashCode] = 0;
                accountArray[hashCode]  = null;
                currentlyUsed = currentlyUsed - 1;
                targetFound = true;
            }
            else{                                 
                 if(refrenceArray[hashCode] == 0)
                 {
                     break; 
                 }
                 hashCode = getNewhashCode(hashCode, _k);
            }
        }
    }
	
    //returns a reference to the data object associated with the key k,or "null" if key is not found
    public Account get(int _k)
    {
        int hashCode = _k % capacity;
        
        //loop until key we want is found or an empty slot
        while(1>0){ 
            
            if(refrenceArray[hashCode] == _k)
            {     
                 return accountArray[hashCode];
            }
            else{
                if(accountArray[hashCode] == null)
                { 
                     break;                        
                }
                hashCode = getNewhashCode(hashCode, _k); 
            }  
        } 
        return null;
    }
    
    /*Method which takes old hashcode, key and returns new hashcode using linear or 
    keyoffset resolution depending on what resoulation type is set too*/
    private int getNewhashCode(int _hashCode, int _k)
    { 
        collisions++; 
        if(resolutionType == 'K')
        {
            return keyOffset(_hashCode, _k);
        }
        else
        {
            return linearProbing(_hashCode);
        } 
    }

    //Method takes old hashcode, increments it and returns it 
    private int linearProbing(int _hashCode)
    {
        int newHashCode = (_hashCode + 1)% capacity;
        return newHashCode;   
    }
    
    //Method which takes old hashcode, key and returns a new hashcose using key offset resolution
    private int keyOffset(int _hashCode, int _k)
    {
        int newHashCode;
        int offSet = (_k / capacity)% capacity;
        //if the offset is a multiple of the table size or factor then add 1. 
        if(capacity % offSet == 0 )
        {
            offSet++;
        }
        newHashCode = offSet + _hashCode;
        newHashCode = newHashCode % capacity; 
          
        return newHashCode; 
    }
    
    //returns amount of collisions occured while probing
    public int getCollisions()
    {
        return collisions;
    }
    
    //sets collision resoulution method
    public void setResolutionType(char _r)
    {
        resolutionType = _r; 
    }
    
    //Returns true if hashtable has exceeded 80% full
    public boolean LoadFactorExceeded()
    {
        boolean overFlow = false;
        double loadFactor = (double)currentlyUsed / (double)capacity; 
        // 0.80 --> 80
        loadFactor = loadFactor * 100; 
        
        //if over 80% full
        if(loadFactor > 80)
        { 
            return true; 
        }
        return overFlow;
    }   
}
//https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html
//http://loverboyit.blogspot.co.nz/2009/10/key-offset.html

























