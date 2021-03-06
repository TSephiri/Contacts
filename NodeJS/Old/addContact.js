const express = require('express')
const router = express.Router()
const mysql = require('mysql')
const bodyParser = require('body-parser')
const date = require('date-and-time')


router.use(bodyParser.urlencoded({extended:false}))

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
router.post("/AddContact",(req,res)=>{

    var type = req.body.type
    var pic_add = req.body.pic_add
    var id = " "
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
       //***************************************************
        query = "select last_insert_id() as id"
        con.query(query,(err,rows,field)=>{
            if(err){
                console.log("failed to retrieve id");
                res.sendStatus(500)}
            if(rows && rows.length){
                id = rows[0].id;
                console.log(rows);
                console.log(id);  
                }     
            console.log("retrieved contact id")
            /***************************************
             * conditions to check what kind of contact is to be added
             * either personal "p" or business "b"
            */
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
                        console.log(err)

                        /****************
                         * query to delete generated contact if adding more informaion fails
                         ****************/
                        query = "delete contact where id = ?"
                        con.query(query,[id],(err,rows,fields)=>{
                            if(err)
                            {
                                logErr("failed to delete unused contact",err,res,"-1");
                                logStatus(500);
                            }
                                res.send("1")
                                console.log("successfully deleted contact");
                        })
                        //res.sendStatus(500)
                    }else
                    {
                        console.log("added business contact")
                        
                        /**************************************
                         * adding address for business contact
                         */

                         //First address 
                        var type_ad_1 = req.body.type_ad1
                        var street_1 = req.body.street1
                        var postal_code_1 = req.body.postal_code1
                        var city_1 = req.body.city1

                            query = "insert into address(type_add,street,postal_code,city,user_id) values (?,?,?,?,?)"
                            con.query(query,[type_ad_1,street_1,postal_code_1,city_1,id],(err,rows,fields)=>{
                                if(err)
                                {
                                    res.sendStatus(500)
                                    console.log("failed to add address 1")
                                }else
                                {
                                // *******************************
                                //adding second address if first address is added successfully
                                var type_ad_2 = req.body.type_ad2
                                var street_2 = req.body.street2
                                var postal_code_2 = req.body.postal_code2
                                var city_2 = req.body.city2

                                query = "insert into address(type_add,street,postal_code,city,user_id) values (?,?,?,?,?)"
                                con.query(query,[type_ad_2,street_2,postal_code_2,city_2,id],(err,rows,fields)=>{
                                    if(err)
                                    {
                                        res.sendStatus(500)
                                        console.log("failed to add address 2")
                                    }else{
                                    console.log("added second address")
                                    res.send("1")
                                    }
                                })
                                }
                            })
                    }      

                })

            }else if(type == "p")
            {

                var name = req.body.name
                var surname = req.body.surname
                var birthday = req.body.birthday
                var email = req.body.email
                var phone = req.body.phone_number

                birthday = date.parse(birthday,'DD-MM')
                console.log(birthday);

                query = "insert into personal(user_id,birthday,email,phone_number,name,surname) values (?,?,?,?,?,?)"
                con.query(query,[id,birthday,email,phone,name,surname],(err,rows,fields)=>{
                    if(err)
                    {
                        console.log("failed to add personal contact")
                        console.log(err)
                        query = "delete contact where id = ?"
                        con.query(query,[id],(err,rows,fields)=>{
                            if(err)
                            {
                                logErr("failed to delete unused contact",err,res,"-1");
                                logStatus(500);
                            }
                                res.send("1")
                                console.log("successfully deleted contact");
                        })
                        //res.sendStatus(500)
                    }else
                    {
                        var type_ad_1 = req.body.type_ad1
                        var street_1 = req.body.street1
                        var postal_code_1 = req.body.postal_code1
                        var city_1 = req.body.city1

                        query = "insert into address(type_add,street,postal_code,city,user_id) values (?,?,?,?,?)"
                        con.query(query,[type_ad_1,street_1,postal_code_1,city_1,id],(err,rows,fields)=>{
                                if(err)
                                {
                                    res.sendStatus(500)
                                    console.log("failed to add address 1")
                                }
                            })
                        console.log("added personal contact")    
                        res.send("1") 
                    }
                })
                
            } 
        
        })
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
// function updateAddress(type_add,street,postal_code,city,id){

//     query = "update address set type_add = ?, street = ?,postal_code = ?, city= ? where user_id = ?"
//     con.query(query,[type_add,street,postal_code,city,id],(err,rows,fields)=>
//     {
//         if(err)
//         {
//             return err;
//         }else
//         {
//             return "success";
//         }
//     })

// }

