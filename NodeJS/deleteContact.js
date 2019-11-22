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
router.post("/deleteContact",(req,res)=>{
    var id = req.body.id
    var type = req.body.type

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
                    query = "delete from Address where user_id = ?"
                    con.query(query,[id],(err,rows,fields)=>{
                        if(err)
                        {
                            console.log("failed to delete user address details")
                            console.log(err)
                            res.sendStatus(500)
                        }else
                        { 
                            if(type == "p")
                            {
                                query = "delete from personal where user_id = ?"
                                con.query(query,[id],(err,rows,fields)=>{
                                if(err)
                                {
                                    console.log("failed to delete user personal contact details")
                                    console.log(err)
                                    res.sendStatus(500)
                                }else
                                {
                                    query = "delete from contact where user_id = ?"
                                    con.query(query,[id],(err,rows,fields)=>
                                    {
                                        if(err)
                                        {   
                                            console.log("failed to delete user personal contact details")
                                            console.log(err)
                                            res.sendStatus(500)
                                        }else
                                        {
                                            console.log("deleted personal user")
                                            res.send("1")
                                        }
                                    })
                                    //console.log("deleted user")
                                }
                                })
                        } else if(type == "b")
                       {
                               query = "delete from business where user_id = ?"
                               con.query(query,[id],(err,rows,fields)=>{
                               if(err)
                               {
                                   console.log("failed to delete user business contact details")
                                   console.log(err)
                                   res.sendStatus(500)
                               }else
                               {
                                   query = "delete from contact where user_id = ?"
                                   con.query(query,[id],(err,rows,fields)=>
                                   {
                                       if(err)
                                       {   
                                           console.log("failed to delete user business contact details")
                                           console.log(err)
                                           res.sendStatus(500)
                                       }else
                                       {
                                           console.log("deleted business user")
                                           res.send("1")
                                       }
                                   })
                               }    
                           })
                       }   
                    }       
                })
                }else
                {
                    logErr("user does not exist",null,res,"0");
                }
            }
        })

 })

 module.exports = router;