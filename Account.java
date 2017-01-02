//Amarjot Parmar
//1255668
/*
 Class holds an integer "key" to hold the account number,
 and a floating point "balance" to keep track of how much money is in this account.
 There are public methods to get this information from this class
 And an method which updates the balance after a transaction
 */
public class Account 
{
    private int key;  		     
    private float balance = 0;  
	
	public Account(int _key, float _balance) 
    {
		key = _key;
		balance = _balance; 
	}
    
    //Method which recalculates the remaininng balance after a transaction
	public void ReProcess(char _TransactionType,float _Amount){
        
		if(_TransactionType == 'd')
        {        
			balance = balance + _Amount;    
		}
		else if (_TransactionType == 'w')
        {  
			balance = balance - _Amount;    
		}
	}
    
    public void update(float _balance)
    {
        balance = _balance; 
    }

    public int hashCode()
    {
        return key;
    }
	
	public int getKey() 
    {
		return key;
	}
    public float getBalance() 
    {
		return balance;
	}
}
