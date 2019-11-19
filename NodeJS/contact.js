const express = require('express')
const app = express()
const mysql = require('mysql')
const bodyParser = require('body-parser')
const crypto = require('crypto')

//Global connection string
var con = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'Tumisang@07',
    database: 'contacts'
})

//***********************
//inserting contact info 
//based on type
//***********************
app.post("/contact",(req,res)=>{

    var type = req.body.type
    var pic_add = req.body.pic_add

    //Initial query to insert basic contact details and generate contact_id
    var query = "insert into contact values(?,?)"

    con.query(query,[type,pic_add],(err,rows,fields)=>{
       if(err)
       {
           console.log("failed to add contact")
           res.send(0)
       }
       console.log("added basic contact");
       
       //get the contact id because its auto generated
       var id = " "
       query = "select last_insert_id()"
       con.query(query,(err,rows,field)=>{
            if(err){console.log("failed to retrieve id")}
       })
       

       if(type == "b")
       {
        var name = req.body.name
        var addresses = req.body.address
        var emails = req.body.emails
        var phone_numbers = req.body.phone_numbers
        var vat_no = req.body.vat_no


         query = "insert into business values(?,?,?,?,?,?)"
         con.query(query,[id,vat_no,emails,phone_numbers],(err,rows,fields)=>{
            if(err)
            {
                console.log("failed to add business contact")
                res.send(0)
            }
         })
       }else if(type == "p")
       {


       } 

       res.send(1)
    })

})

app.use(bodyParser.urlencoded({extended: false}))

app.listen(3000,()=>{
    console.log("server is live on 3000")
})