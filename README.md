### Connect to database server

The application implements a database 'guestbook' where a user can store:
- name
- email
- home page
- comment

The post is saved in a database server. All text is checked not to contain
any HTML, if so the HTML is replaced with 'censored'.

A script is prepared to populate a table 'guests' with three guests when
the application is executed. In the script the table is dropped if it exists
so it will be 'rebooted' for every application restart.

### Design/architecture
Inspiration taken from Spring Boot n-tier architecture.

Guest creation:<br>
```dto -> service -> Mapper -> validate HTML -> entity -> repository.```

From db:
```Repository -> ResultSet -> service -> ArrayList<GuestDto> -> UI -> GuestDto```


### Database username and password 
username and password to the database are set as environment variables
either in IDE or in terminal.

Terminal, macOS:
export DB_USERNAME="yourUsername"
export DB_PASSWORD="yourPassword"
echo $DB_USERNAME (should show)
yourUsername

### .jar & compiled files
<span style="color:lightgreen">The compiled files (.class) are packed in a .jar (Guestbook.jar) together with the JDBC interface to MySQL.</span><br><br>

<b>MySQL Connector/Driver:</b><br>
Download and add mysql-connector-j-26.7.0.jar to the project
Connector/Driver to MySQL database server.

🟢 run the .jar-file:<br>
java -jar Guestbook.jar