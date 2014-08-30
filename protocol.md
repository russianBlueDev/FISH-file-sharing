Basic Idea
====================
* Client sends a String announcing his next action
* So the server always wait for a string

On Client Connection
====================
* Client sends the string "SHARE" to announce it will send a List<String> of files to be shared
* Client sends the List<String> of files to be shared


After Client Connection
=======================
* Client can send the String UNSHARE to stop the connection (the server removes shared files)
* Client can send SEARCH String to announce it looks for some file, followed by another string (name of file)
