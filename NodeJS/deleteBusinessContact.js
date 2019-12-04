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

/*****************************************************************
 * post method to delete contacts 
 *****************************************************************/
router.post("/deleteBusinessContact",(req,res)=>{
    var id = req.body.id

    var query = "select * from contact where user_id = ?"
        con.query(query,[id],(err,rows,fields)=>{
                if(err)
                {
                    console.log("failed to retrieve user")
                    console.log(err)
                    res.sendStatus(500)
                }else
                {
                    if(rows && rows.length)
                    {
                                    query = "delete from business where user_id = ?"
                                    con.query(query,[id],(err,rows,fields)=>{
                                    if(err)
                                    {
                                        console.log("failed to delete user business contact DETAILS")
                                        console.log(err)
                                        res.sendStatus(500)
                                    }else
                                    {
                                        query = "delete from contact where user_id = ?"
                                        con.query(query,[id],(err,rows,fields)=>
                                        {
                                            if(err)
                                            {   
                                                logErr("failed to delete user business contact",err,res,"-1")
                                                res.sendStatus(500)
                                            }else
                                            {
                                                console.log("deleted Business user")
                                                res.send("1")
                                            }
                                        })
                                        //console.log("deleted user")
                                    }
                            })
                
                    }else
                    {
                        //user does not exist
                        console.log("user does not exist")
                        res.send("0")
                    }
            }
        })
    })    
/************************************************************** */
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