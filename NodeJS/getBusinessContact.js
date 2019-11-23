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

router.get("/getBusinessContacts",(req,res)=>{
    
    var query = "select * from contact C join business B on (C.user_id = B.user_id) join address A on (B.user_id = A.user_id)";
    con.query(query,(err,rows,field)=>{
        if(err)
        {
            logErr("failed to get business contacts ",err,res,"-1")
            logStatus(res,500);
        }
        if(rows && rows.length)
        {
            console.log("retrieved business contacts")
            res.json(rows)
        }else
        {
            console.log("no business contacts")
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