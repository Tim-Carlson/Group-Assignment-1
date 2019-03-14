# Group-Assignment-2

I have/will add a file showing the general class layout that Mr. Professor and I agreed upon-  
It should be called something like layout.png  
Please create a new branch to build upon! Once the code is working we can merge it to Main  

Firstly, I would like to list the attributes that have been agreed upon:

-We should incorporate a list of Study objects rather than 4 hard-coded objects.  
-For permanent storage we can merely write data to a local JSon file, that will be accessed and   loaded into temporary storage every time the program is run.  
-We do not need to incorporate XML writing capabilities, only reading.  
-XMLReader needs to have fairly good input validation.

Along with adding the desired features, we need to separate out tasks that fileOperator is  currently handling. 

-We need to separate the read and write Json methods into JsonReader and JsonWriter objects.   
-We need to create a controller object that handles delegation, study/site/reading memory searching, and on-start-up responsibilities (Initiating JsonReader to restore long-term memory).
-To keep things consistent, we will also need to create XML reading capabilities in a separate class.  
