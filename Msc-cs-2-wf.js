//Q1.Create an HTML form that contain the Student Registration details and write a
// JavaScript to validate Student first and last name as it should not contain other than
// alphabets and age should be between 18 to 50.
<form id="studentForm">
         <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName">

                      <label for="lastName">Last Name:</label>
                      <input type="text" id="lastName" name="lastName">
                        <label for="age">Age:</label>
                        <input type="number" id="age" name="age">

                      <button type="submit">Submit</button>
  </form>

<script>
const form = document.getElementById('studentForm');

form.addEventListener('submit', (e) => {
    let firstName = form.firstName.value;
    let lastName = form.lastName.value;
    let age = form.age.value;

    if (!/^[a-zA-Z]+$/.test(firstName)) {
        e.preventDefault();
        alert('First name can only contain alphabets');
    }

    if (!/^[a-zA-Z]+$/.test(lastName)) {
        e.preventDefault();
        alert('Last name can only contain alphabets');
    }

    if (age < 18 || age > 50) {
        e.preventDefault();
        alert('Age must be between 18 to 50');
    }
});
</script>
// Q2.Create an HTML form that contain the Employee Registration details and writea
// JavaScript to validate DOB, Joining Date, and Salary.
<form id="studentForm">
         <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName">

                                <label for="lastName">Last Name:</label>
                  <input type="text" id="lastName" name="lastName">
                      <label for="age">Age:</label>
                      <input type="number" id="age" name="age">

                    <button type="submit">Submit</button>
                </form>

          <script>
const form = document.getElementById('studentForm');

form.addEventListener('submit', (e) => {
    let firstName = form.firstName.value;
    let lastName = form.lastName.value;
    let age = form.age.value;

    if (!/^[a-zA-Z]+$/.test(firstName)) {
        e.preventDefault();
        alert('First name can only contain alphabets');
    }

    if (!/^[a-zA-Z]+$/.test(lastName)) {
        e.preventDefault();
        alert('Last name can only contain alphabets');
    }

    if (age < 18 || age > 50) {
        e.preventDefault();
        alert('Age must be between 18 to 50');
    }
});
</script>
// Q3. Create an HTML form for Login and write a JavaScript to validate email ID
// using Regular Expression.
<form id="studentForm">
         <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName">

                                <label for="lastName">Last Name:</label>
                                            <input type="text" id="lastName" name="lastName">

                                                    <label for="age">Age:</label>
                                                                <input type="number" id="age" name="age">

                                                                        <button type="submit">Submit</button>
                                                                                </form>

                                                                                <script>
                                                                                const form = document.getElementById('studentForm');

form.addEventListener('submit', (e) => {
    let firstName = form.firstName.value;
    let lastName = form.lastName.value;
    let age = form.age.value;

    if (!/^[a-zA-Z]+$/.test(firstName)) {
        e.preventDefault();
        alert('First name can only contain alphabets');
    }

    if (!/^[a-zA-Z]+$/.test(lastName)) {
        e.preventDefault();
        alert('Last name can only contain alphabets');
    }

    if (age < 18 || age > 50) {
        e.preventDefault();
        alert('Age must be between 18 to 50');
    }
});
</script>
// Q4. Create a Node.js file that will convert the output "Hello World!" into upper-case
// letters.
var http = require('http');
var uc = require('upper-case');
http.createServer(function (req, res) {
    res.writeHead(200, {'Content-Type': 'text/html'});
    /*Use our upper-case module to upper case a string:*/
    res.write(uc.upperCase("Hello World!"));
    res.end();
}).listen(8080);
console.log('Server running at http://localhost:8080');

// Q5. Using nodejs create a web page to read two file names from user and append contents
// of first file into second file.
const fs = require('fs');
// Get the file contents before the append operation
console.log("\nFile Contents of file before append:",
            fs.readFileSync("example_file.txt", "utf8"));
fs.appendFile("example_file.txt", "World", (err) => {
    if (err) {
        console.log(err);
    } else {
// Get the file contents after the append operation
        console.log("\nFile Contents of file after append:",
                    fs.readFileSync("example_file.txt", "utf8"));
    }
});

// Q6. Create a Node.js file that opens the requested file and returns the content to the client.
// If anything goes wrong, throw a 404 error.
var http=require ('http');
var url = require ('url');
var fs= require ('fs');
http.createServer(function(req,res) {
    var q=url.parse(req.url,true);
    var filename="."+ q.pathname;
    fs.readFile(filename,function(err,data) {
        if (err) {
            res.writeHead(404, {'Content-Type':'text/html'});
            return res.end("404 not found");
        }
        res.writeHead(200, {'content-Type':'text/html'});
        res.write(data);
        return res.end();
    });
}).listen(8080);
console.log('Server running at http://localhost:8080/')
// Q7. Create a Node.js file that writes an HTML form, with an upload field.
const http = require('http');
const fs=require('fs');
const server=http.createServer(function (req, res) {
    res.writeHead(200, {'Content-Type': 'text/html'});
    res.write("<form enctype='multipart/form-data'><input type='file' name='filechoooser'
              id='uplode'></form>");
    res.end();
})
server.listen(9000);
console.log('server running....9000')

// Q8. Create a Node.js file that demonstrates create database and table in MySQL.
var mysql= require('mysql');
var con=mysql.createConnection({
    host:"localhost",
    user:"root",
    password:""
});
con.connect(function(err) {
    if(err) throw err;
    console.log("Connected!");
    con.query("CREATE DATABASE mydb2", function(err,result) {
        if(err)throw err;
        console.log("Database created");
    });
});

// Q9. Create a node.js file that Select all records from the "customers" table, and delete the
// specified record.
var mysql= require('mysql');
var con=mysql.createConnection({
    host:"localhost",
    user:"root",
    password:"",
    database:"mydb2"
});
con.connect(function(err) {
    if(err)throw err;
    var sql= "DELETE FROM customer WHERE name='vaidehi'";
    con.query(sql,function (err,result) {
        if(err)throw err;
        console.log("Number of records deleted:" +result.affectedRows);
    });
});

// Q10. Create a Simple Web Server using node js.
const http = require("http")

// Creating server
const server = http.createServer((req, res) => {
// Sending the response
    res.write("This is the response from the server")
    res.end();
})

// Server listening to port 3000
server.listen((3000), () => {
    console.log("Server is Running on 3000");
})
// Q11. Write node js script to build Your Own Node.js Module. Use require (‘http’) module is a
// built-in Node module that invokes the functionality of the HTTP libraryto create a
// local server. Also use the export statement to make functions in your module available
// externally. Create a new text file to contain the functions in your module called,
// “modules.js” and add this function to return today’s date and time.
var http=require('http');
var dt=require('./p17a.js'); //path of another files
http.createServer(function(req,res) {
    res.write("The date and time is: "+dt.myDateTime());
    res.end();
}).listen(8080);
console.log(" server is running in 8080 ")
// create a another file in same directory and add this code on it
/*
exports.myDateTime = function() {
 return new Date();
};
*/
// Q12. Write node js application that transfer a file as an attachment on web and enables
// browser to prompt the user to download file using express js.
var express = require('express');
var app = express();
var PORT = 3080;
app.get('/', function(req, res) {
    res.download('Hello.txt', 'Hello.txt', function(err) {
        if (err) {
            console.error('Error while downloading the file:', err);
            res.status(500).send('Internal Server Error');
        }
    });
});
app.listen(PORT, function(err) {
    if (err) {
        console.error("Server listening on PORT", PORT);
    } else {
        console.log("Server is running on PORT", PORT);
    }
});

// Q13. Create your Django app in which after running the server, you should see on the browser,
// the text “Hello! I am learning Django”, which you defined in the index view.
myapp/views.py
from django.http import HttpResponse
def index(request):
return HttpResponse("Hello! I am learning Django")
       myapp/urls.py
       from django.urls import path
       from . import views
       urlpatterns = [
                         path('', views.index, name='index'),
                     ]
                     myproject/urls.py
                     from django.contrib import admin
                     from django.urls import path, include
                     urlpatterns = [
                                       path('admin/', admin.site.urls),
                                       path('', include('myapp.urls')), # Include the app's URLs
                                       ]

                                       // Q14. Design a Django application: A public site in which user can pick their favourite
                                       // programming language and vote.
                                       myapp/views.py
                                       from django.http import HttpResponse
                                       def index(request):
                                       return HttpResponse("Hello! I am learning Django")
                                       myapp/urls.py
                                       from django.urls import path
                                       from . import views
                                       urlpatterns = [
                                       path('', views.index, name='index'),
                                       ]
                                       myproject/urls.py
                                       from django.contrib import admin
                                       from django.urls import path, include
                                       urlpatterns = [
                                       path('admin/', admin.site.urls),
                                       path('', include('myapp.urls')), # Include the app's URLs
                                   ]

// Q15. Implement Login System using Django.

// Q16. Create a Simple Web Server using node js.
                                   const http = require("http")

// Creating server
const server = http.createServer((req, res) => {
// Sending the response
    res.write("This is the response from the server")
    res.end();
})

// Server listening to port 3000
server.listen((3000), () => {
    console.log("Server is Running on 3000");
})

// Q17. Create a Node.js file that demonstrates create database and table in MySQL.
var mysql= require('mysql');
var con=mysql.createConnection({
    host:"localhost",
    user:"root",
    password:""
});
con.connect(function(err) {
    if(err) throw err;
    console.log("Connected!");
    con.query("CREATE DATABASE mydb2", function(err,result) {
        if(err)throw err;
        console.log("Database created");
    });
});

// Q18. Create your Django app in which after running the server, you should see on the browser,
// the text “Hello! I am learning Django”, which you defined in the index view.
myapp/views.py
from django.http import HttpResponse
def index(request):
return HttpResponse("Hello! I am learning Django")
       myapp/urls.py
       from django.urls import path
       from . import views
       urlpatterns = [
                         path('', views.index, name='index'),
                     ]
                     myproject/urls.py
                     from django.contrib import admin
                     from django.urls import path, include
                     urlpatterns = [
                                       path('admin/', admin.site.urls),
                                       path('', include('myapp.urls')), # Include the app's URLs
                                       ]
