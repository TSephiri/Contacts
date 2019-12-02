const mysql = require('mysql')
const bodyParser = require('body-parser')
const express = require('express')
var router = express.Router()
const date = require('date-and-time')

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
router.post("/updatePersonalContact",(req,res)=>{
    var id = req.body.id
    //var type = req.body.type
    var pic_add = req.body.pic_add

     query = "select * from personal where user_id = ?"
     con.query(query,[id],(err,rows,fields)=>{
         if(err)
         {
            logErr("cant find user",err);
            res.sendStatus(500)
         }
            if(rows && rows.length)
            { 
                query = "update contact set pic_add = ? where user_id = ?"
                con.query(query,[pic_add,id],(err,rows,fields)=>{
                    if(err)
                    {
                        logErr("failed to update profile picture",err,res,"-1");
                        res.sendStatus(500)
                    }else
                    {
                         
                        var name = req.body.name
                        var surname = req.body.surname
                        var birthday = req.body.birthday
                        var email = req.body.email
                        var phone = req.body.phone_number

                        birthday = date.parse(birthday,'DD-MM')

                        query = "update personal set name = ?, surname = ?, birthday = ?, email = ?, phone_number = ? where user_id = ?"
                                            con.query(query,[name,surname,birthday,email,phone,id],(err,rows,fields)=>{
                                                if(err)
                                                {
                                                    logErr("failed to update personal contact",err,res,"0");
                                                    logStatus(res,status);
                                                }else
                                                {
                                                                var type_ad_1 = req.body.type_ad1
                                                                var street_1 = req.body.street1
                                                                var postal_code_1 = req.body.postal_code1
                                                                var city_1 = req.body.city1

                                                                query = "update address set type_add = ?, street = ?,postal_code = ?, city= ? where user_id = ?"
                                                                        con.query(query,[type_ad_1,street_1,postal_code_1,city_1,id],(err,rows,fields)=>
                                                                        {
                                                                            if(err)
                                                                            {
                                                                                logErr("failed to update personal address ",err,res,"0");
                                                                                logStatus(500)
                                                                            }else
                                                                            {
                                                                                console.log("successfully updated personal address ");
                                                                                res.send("1")
                                                                            }
                                                                        })
                                
                                                }
                                            })
                    
                        
                    }
                })
            }else
            {
                logErr("user does not exist",null,res,"0");
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