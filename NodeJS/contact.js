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


app.post("/contact",(req,res)=>{
    var name = req.body.name
    var type = req.body.type
    var user_id = req.body.type

    var query = "insert into contact values(?,?,?)"

    con.query(query,[name,type,user_id],(err,rows,fields)=>{
       if(err)
       {
           console.log("failed to add contact")
           res.send(0)
       }
       console.log("added new contact")
       res.send(1)
    })

})

app.use(bodyParser.urlencoded({extended: false}))

app.listen(3000,()=>{
    console.log("server is live on 3000")
})