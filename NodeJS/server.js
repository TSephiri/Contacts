var getPersonalContacts = require('./getPersonalContact');
var getBusinessContacts = require('./getBusinessContact');
var deleteContact = require('./deleteContact');
var updateContact = require('./addContact');
var addContact = require('./addContact');
var express = require('express');
var app = express();

app.use('/personal',getPersonalContacts);

app.use('/business',getBusinessContacts);

app.use('/add',addContact);

app.use('/update',updateContact);

app.use('delete',deleteContact);

app.listen(3000,()=>{
    console.log("server is live on 3000");
})

