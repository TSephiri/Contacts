const mysql = require('mysql')
const bodyParser = require('body-parser')
const express = require('express')
var router = express.Router()


router.use(bodyParser.urlencoded({extended:false}))

//Global connection string
var con = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'timetable',
    database: 'contacts'
})

router.get("/getPersonalContacts",(req,res)=>{
    
    var query = "select * from contact C join personal P on (C.user_id = P.user_id) join address A on (C.user_id = A.user_id) ";
    con.query(query,(err,rows,field)=>{
        if(err)
        {
            logErr("failed to get personal contacts",err,res,"-1");
            logStatus(res,500);
        }
        if(rows && rows.length)
        {
            res.json(rows)
            console.log("retrieved personal contact")
        }else{
            console.log("no personal contacts")
            res.send("0")
        }    
    })     
})

function logErr(msg,err,res,msg2){
    console.log(msg);
    console.log(err);
    res.send(msg2);
}

function logStatus(res,status)
{
    res.sendStatus(status);
}


module.exports = router;