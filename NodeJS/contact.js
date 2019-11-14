const express = require('express')
const app = express()
const mysql = require('mysql')
const bodyParser = require('body-parser')
const crypto = require('crypto')

//Global connection string
var connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'Tumisang@07',
    database: 'contacts'
})