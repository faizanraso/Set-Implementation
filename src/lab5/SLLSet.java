package lab5;

public class SLLSet{
	private int SetSize; // size of set
	private SLLNode start; //reference to the start of the set
	
	
	//CONSTRUCTOR 1
	public SLLSet(){
		start = null; //initializes the null set
		SetSize = 0;
    }
	
	//CONSTRUCTOR 2 ----- creates set with parameters of input array
	public SLLSet( int[] sortedArray){
		//if size is invalid, the constructor does not perform any thing
		if (sortedArray.length <= 0 ){
			SetSize = 0;
			start = null;
		}

		else { 
			//Creates SSLSet object containing same parameters as input
			int counter = 1;
			SetSize = sortedArray.length; //sets setsize as the length of the input array
			SLLNode placeholder = new SLLNode(sortedArray[0], null); //placeholder is referencing to start of set
			start = placeholder;
			while (counter < sortedArray.length) {
				placeholder.next = new SLLNode(sortedArray[counter], null);
				placeholder = placeholder.next;
				counter++;
			}
		}
	}
	
	//METHOD 1
	public int getSize(){
		return SetSize;
	}
	
	//METHOD 2
	public SLLSet copy(){
		int counter = 0;
		 int[] set = new int[SetSize]; //creates list of size SetSize
         SLLNode placeholder = start; // placeholder points to start of set
         while(placeholder != null){
             set[counter] = placeholder.value;
             placeholder = placeholder.next; // takes values of placeholder and adds them to list
             counter++;
         }
         SLLSet newSet = new SLLSet(set);
         newSet.SetSize = SetSize;
         return newSet;
	}
	
	//METHOD 3
	public boolean isIn(int v) {
		SLLNode count = start; //count points to start
		while (count != null) {
			if (count.value == v) {
				return true; //returns true if v is found
			}
			count = count.next;
		}
		return false; //if v is not found this line executes and returns false
	}
	
	//METHOD 4
	public void add(int v){
	    SLLNode placeholder = start;
	    if(this.isIn(v) == false){
	        if(start == null){
	            start = new SLLNode(v,null);
	        }
	        else if(placeholder.value > v){
	            start = new SLLNode(v,start);
	        }
	        else{
	            int i;
	            while(placeholder.next != null){
	                i = placeholder.next.value;
	                if(v > placeholder.value && v < i ){
	                    placeholder.next = new SLLNode(v,placeholder.next);
	                }   
	                placeholder = placeholder.next;
	            }
	            if(v > placeholder.value){
	                placeholder.next = new SLLNode(v,null);
	            }
	        }
	        SetSize++;
	    }
	}
	
	//METHOD 5
	public void remove(int v){
        SLLNode placeholder = start;
        if(isIn(v)){
            SetSize--;
            if(placeholder.value == v){
                this.start = placeholder.next;//if first value is v, takes second value and makes it first
            }
            else{
                while(placeholder != null){
                    if(placeholder.next == null){
                        break;
                    }
                    else if(placeholder.next.value == v){
                    	placeholder.next = placeholder.next.next;
                        break;
                    }
                    placeholder = placeholder.next;
                }
            }
        }
    }
	
	//METHOD 6
	public SLLSet union(SLLSet s) {// combines both sets, without repeat
		SLLNode placeholder = s.start;
		SLLSet newUnion = this.copy();
		if(s.start != null){
			while(placeholder != null){
				newUnion.add(placeholder.value);
				placeholder = placeholder.next;
	            }
			}
		return newUnion;
		}
	
	
	//METHOD 7
	public SLLSet intersection(SLLSet s){
		SLLNode placeholder = this.start;
        SLLSet intersection = new SLLSet(); // creates new set which will hold intersections
        SLLNode placeholder2 = s.start;
        if(  placeholder != null || placeholder2 != null){
        	while(placeholder != null){
        		placeholder2 = s.start;
                while(placeholder2 != null){
                	if(placeholder2.value == placeholder.value){
                		intersection.add(placeholder.value); //checks if values match and if they do adds to new set
                		}
                	placeholder2 = placeholder2.next;
                	}
                placeholder = placeholder.next;
                }
        	}
        return intersection;
        }
	
	//METHDO 8
	public SLLSet difference(SLLSet s){
   	 SLLSet difference = new SLLSet();
        SLLNode placeholder = this.start;      
        while(placeholder != null)
        {
            difference.add(placeholder.value);
            placeholder = placeholder.next;
        }
        SLLNode placeholder2 = s.start;
        while(placeholder2 != null)
        {
            difference.remove(placeholder2.value);
            placeholder2 = placeholder2.next;
        }
        return difference;
   }
	
	//METHOD 9
	 public static SLLSet union(SLLSet[] sArray){// returns object of union set
		 SLLSet union = new SLLSet();
		 SLLSet setCopy = sArray[0].copy();
		 int counter = 0;
		 while ( counter < sArray.length){
			 setCopy  = setCopy.union(sArray[counter]);
			 counter++;
			 }
		 return setCopy;
		 }
	
	 
	 //METHOD 10
	 public String toString(){ //returns string of values in increasing order
		 String newString;
		 SLLNode placeholder = start;
		 newString = "(";
		 while(placeholder != null){
			 newString = newString + placeholder.value;
			 placeholder = placeholder.next;
			 if(placeholder != null){
				 newString = newString+ ",";
				 }
			 }
		 newString = newString + ")";
		 return newString;
		 }
}