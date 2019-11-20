const express = require('express')
const app = express()
const mysql = require('mysql')
const bodyParser = require('body-parser')
const crypto = require('crypto')

app.use(bodyParser.urlencoded({extended:false}))

//Global connection string
var con = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'timetable',
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
    var query = "insert into contact(type,pic_add) values(?,?)"

    con.query(query,[type,pic_add],(err,rows,fields)=>{
       if(err)
       {
           console.log("failed to add contact")
           res.sendStatus(500)
       }
       console.log("added basic contact");

       //***************************************************
       //get the contact id because its auto generated
        var id = " "
        query = "select last_insert_id()"
        con.query(query,(err,rows,field)=>{
            if(err){
                console.log("failed to retrieve id");
                res.sendStatus(500)}
            if(rows && rows.length){
                id = rows.last_insert_id;
                console.log(rows);
                console.log(id);  
                }     
        })
        

        if(type == "b")
        {

            var name = req.body.name
            var emails = req.body.emails
            var phone_numbers = req.body.phone_numbers
            var vat_no = req.body.vat_no

            query = "insert into business(user_id,vat_no,emails,phone_numbers,name) values(?,?,?,?,?)"
            con.query(query,[id,vat_no,emails,phone_numbers,name],(err,rows,fields)=>{
                if(err)
                {
                    console.log("failed to add business contact")
                    res.sendStatus(500)
                }
                    console.log("added business contact")
                    res.send("1")
            })

        }else if(type == "p")
        {

            var name = req.body.name
            var surname = req.body.surname
            var birthday = req.body.birthday
            var email = req.body.email
            var phone = req.body.phone_number

            query = "insert into personal values (?,?,?)"
            con.query(query,[id,birthday,email,phone,name,surname],(err,rows,fields)=>{
                if(err)
                {
                    console.log("failed to add personal contact")
                    res.sendStatus(500)
                }
                console.log("added personal contact")
                res.send("1") 
            })
   
        } 
        
    })

    
 })


app.use(bodyParser.urlencoded({extended: false}))

app.listen(3000,()=>{
    console.log("server is live on 3000")
})