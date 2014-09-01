FISH Server
===========
* Manages a directory (list) of shared files and associated addresses of clients
* If a client exits or crashes, the server should remove records corresponding to the client from the directory
* On client query for a file, reply by either a 'not found' message, or by giving the address of the client(s) that is(are) sharing the file

FISH Client
===========
* Share personal files with other clients via FISH server
* Send a list of shared files to the server
* query the server to know if a given file is currently shared by another user
* If the file is available, the requesting client may connect to the client that shares the file, and download the file

Notes
=====
* Note that the file transfer take place directly between clients, the server is not involved
* Later we will remove the need of the server being the directory of shared files and have a P2P directory

Sample Execution (Server side)
==============================
* new Client: client1 from 123.234.245.233:2434
* client1 shares a file: file1.txt
* client1 shares a file: file2.txt
* new Client: client2 from 218.244.244.243:2323
* client2 requests file3.txt: not found
* client3 requests file2.txt: found from client1(123.234.245.233:2434)
* (client2 asks client1 directly for file2.txt)
* (transfer of file2.txt)
* client2 exits
* client1 does not respond to ping: removes it and its shared files

