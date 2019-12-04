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
router.post("/addBusinessContact",(req,res)=>{

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
            /***************************************/
             
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

                query = "insert into business(user_id,vat_no,emails,phone_numbers,name,street1,postal_code1,city1,street2,postal_code2,city2) values(?,?,?,?,?,?,?,?,?,?,?)"
                con.query(query,[id,vat_no,emails,phone_numbers,name,street_1,postal_code_1,city_1,street_2,postal_code_2,city_2],(err,rows,fields)=>{
                    if(err)
                    {
                        console.log("failed to add business contact")
                        console.log(err)

                        /****************
                         * query to delete generated contact if adding more informaion fails
                         ****************/
                        query = "delete from contact where user_id = ?"
                        con.query(query,[id],(err,rows,fields)=>{
                            if(err)
                            {
                                logErr("failed to delete unused contact",err,res,"-1");
                                logStatus(res,500);
                            }
                                //res.send("0")
                                console.log("successfully deleted contact");
                        })
                    }else
                    {
                        console.log("added business contact");
                        res.send("1")
                    }
                })
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