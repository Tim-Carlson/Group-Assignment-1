# Group-Assignment-1

This project needs json.simple in a referenced library to run properly,</br>
I found it here: http://www.java2s.com/Code/Jar/j/Downloadjsonsimple111jar.htm</br>
I would like to step back and allow you guys to contribute, so please make sure that commited files leave the project in a working state.</br></br>

Please select your preferred jobs and claim them with a message on the metrostate group email including all five of us as recipients.
Things left todo:</br></br>

In previous commits, examples of Json File Writer methods have been added and need to be re-implemented to work.</br>
The Json file writer needs to print ALL READINGS to a Json file. I would recommend using the json.simple extended library's</br> "obj.put" function to write, so we don't have to implement another extended library element.</br>
siteId(Please make sure that the program can read Json files that it has created!)</br></br>

The Gui class needs to be updated to allow the Json file writer function, I would recommend creating another Jlabel, jtextarea and Jbutton just like the ones on the "read file" tab and use them as an area for the user to create a file name and create file</br></br>

There is pretty much no input validation. The application could send warning messages to Gui text areas to alert the user
when a file is not found or created, or when a searched Site Id cannot be found</br></br>

The Site collection open/close buttons don't do anything. These Open/Close buttons should read input from the siteId input text area and change the site.collection_open boolean value. This boolean value also needs to be implemented in the FileOperator.readFile() method to prevent reading elements for a closed Site</br></br>

The readFile() and addReading() methods add duplicate information to memory, so you can press the import button repeatedly</br>
to make many copies of the same file</br></br>

We could increase functionality, such as a drop-down list that shows the available Site ids to choose from and view. We can also
implement some type of Gui file reader so we can more easily import Json files</br></br>
