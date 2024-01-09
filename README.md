## Discord Managment
### Funktions
- Config to set up api and other stuff
#### Normal Users:
  - shows status of pixel bot servers (survival, copy, test)
  - shows mobswitch status
  - tracks stats of user last time online
#### Admin Users:
  - manage users and verify them
    - when a player join the server to add the mc name and store it to the db  
  - whitelist users on all Servers (api)
  - ban users from all servers (api)
#### Commands:
- Admin:
  - ban (bans user from dis & Mc)
  - whitelist
    - remove (removes player from the whitelist from all servers)
    - add (adds player to whitelist to all servers)
### Dev Structure
#### Database
  - SQLite Database to store MC and Discord names

Table: 
- DISCORDACCOUNTS
  - GUID : guid
  - ID : varchar

Table: 
- MCACCOUNTS
  - GUID : guid
  - ZDISCORDACCOUNTS : guid
  - MCNAME : varchar
  - UUID : guid
  - ADMIN : bool
  - WHITELIST : bool
  - LASTONLINE : date

## Server Management
###Funktions
- Send commands to server nodes
- Check status of server nodes
- if a server is down, reboot the server
- if a server is not in use, shut it down
### Dev Structure
  - In-Memory-Database for temporary storage
