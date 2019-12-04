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
 * post method to update contacts 
 *****************************************************************/
router.post("/updateBusinessContact",(req,res)=>{
    var id = req.body.id
    //var type = req.body.type
    var pic_add = req.body.pic_add

     query = "select * from business where user_id = ?"
     con.query(query,[id],(err,rows,fields)=>{
         if(err)
         {
            logErr("cant find user",err);
            res.sendStatus(500)
         }else
                {
                    if(rows && rows.length)
                    {
                        var name = req.body.name
                        var emails = req.body.emails
                        var phone_numbers = req.body.phone_numbers
                        var vat_no = req.body.vat_no
                        
                        var street_1 = req.body.street1
                        var postal_code_1 = req.body.postal_code1
                        var city_1 = req.body.city1

                        var street_2 = req.body.street2
                        var postal_code_2 = req.body.postal_code2
                        var city_2 = req.body.city2

                        query = "update business set name = ?, vat_no = ?, emails = ?, phone_numbers = ?, street1 =?,postal_code1=? ,city1=?,street2 = ?,postal_code2 = ?,city2 = ? where user_id = ?"
                        con.query(query,[name,vat_no,emails,phone_numbers,street_1,postal_code_1,city_1,street_2,postal_code_2,city_2,id],(err,rows,fields)=>{
                            if(err)
                            {
                                logErr("failed to update business contact",err);
                                logStatus(res,500);
                            }
                            res.send("1")
                            console.log("updated business contact");
                            })
                }else
                {
                    logErr("user does not exist",null,res,"0");
                }
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