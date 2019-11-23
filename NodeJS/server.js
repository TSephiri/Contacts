var getPersonalContacts = require('./getPersonalContact');
var getBusinessContacts = require('./getBusinessContact');

// var deletePersonalContact = require('./deletePersonalContact');
// var deleteBusinessContact = require('./deleteBusinessContact');

// var updateBusinessContact = require('./updateBusinessContact');
// var updatePersonalContact = require('./updatePersonalContact');

var addBusinessContact = require('./addBusinessContact');
var addPersonalContact = require('./addPersonalContact');

var express = require('express');
var app = express();

app.use('/personal',getPersonalContacts);
app.use('/business',getBusinessContacts);

app.use('/add',addPersonalContact);
app.use('/add',addBusinessContact);

// app.use('/update',updatePersonalContact);
// app.use('/update',updateBusinessContact);

// app.use('/delete',deletePersonalContact);
// app.use('/delete',deleteBusinessContact);

app.listen(3000,()=>{
    console.log("server is live on 3000");
})

