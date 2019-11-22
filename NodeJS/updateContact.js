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

router.post("/update",(req,res)=>{
    var id = req.body.id
    var type = req.body.type
    var pic_add = req.body.pic_add

     query = "select * from contact where user_id = ?"
     con.query(query,[id],(err,rows,fields)=>{
         if(err)
         {
            logErr("cant find user",err);
            res.sendStatus(500)
         }else
         {
            if(rows && rows.length)
            {
                if(type == "b")
                {
                    var name = req.body.name
                    var emails = req.body.emails
                    var phone_numbers = req.body.phone_numbers
                    var vat_no = req.body.vat_no

                    query = "update business set name = ?, vat_no = ?, emails = ?, phone_numbers = ? where user_id = ?"
                    con.query(query,[name,vat_no,emails,phone_numbers,id],(err,rows,fields)=>{
                        if(err)
                        {
                            logErr("failed to update business contact",err);
                            logStatus(res,500);
                        }
                        /**********************************
                        * Update the business users address 1
                        **********************************/
                        var type_ad_1 = req.body.type_ad1
                        var street_1 = req.body.street1
                        var postal_code_1 = req.body.postal_code1
                        var city_1 = req.body.city1

                        query = "update address set type_add = ?, street = ?,postal_code = ?, city= ? where user_id = ?"
                            con.query(query,[type_add_1,street_1,postal_code_1,city_1,id],(err,rows,fields)=>
                            {
                                if(err)
                                {
                                    logErr("failed to update address 1",err,res,"0");
                                }else
                                {
                                     type_ad_1 = req.body.type_ad2
                                     street_1 = req.body.street2
                                     postal_code_1 = req.body.postal_code2
                                     city_1 = req.body.city2

                                    query = "update address set type_add = ?, street = ?,postal_code = ?, city= ? where user_id = ?"
                                    con.query(query,[type_add_1,street_1,postal_code_1,city_1,id],(err,rows,fields)=>
                                    {
                                        if(err)
                                        {
                                            logErr("failed to update address 2",err,res,"0");
                                            logStatus(500)
                                        }else
                                        {
                                            console.log("success updated address 2");
                                            res.send("1")
                                        }
                                    })
                                }
                            })

                        // if(updateAddress(type_ad_1,street_1,postal_code_1,city_1,id)=="success")
                        // {       
                        //     //res.send("1")
                        //     /*****************************
                        //     * Update the business users address 2
                        //     */
                        //     console.log("updated business contact address 1");
                        //     var type_ad_1 = req.body.type_ad2
                        //     var street_1 = req.body.street2
                        //     var postal_code_1 = req.body.postal_code2
                        //     var city_1 = req.body.city2

                        //     if(updateAddress(type_ad_1,street_1,postal_code_1,city_1,id)=="success")
                        //     {       
                        //         res.send("1")
                        //         console.log("updated business contact address 1"); 
                        //     }else
                        //     {
                        //         logErr("failed to update address",updateAddress(type_ad_1,street_1,postal_code_1,city_1,id),res,"0")
                        //     }

                        // }else
                        // {
                        //     logErr("failed to update address",updateAddress(type_ad_1,street_1,postal_code_1,city_1,id),res,"0")
                        // }
                        

                    })
                }else(type == "p")
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
                        }else{
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
                                            console.log("success updated personal address ");
                                            res.send("1")
                                        }
                                    })
                            // address = updateAddress(type_ad_1,street_1,postal_code_1,city_1,id);
                            // console.log();
                            // if(address == "success")
                            // {       
                            //     res.send("1")
                            //     console.log("updated personal contact");
                            // }else
                            // {
                            //     logErr("failed to update address",address,res,"0")
                            // }
                        }
                    })
                }

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