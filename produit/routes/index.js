var express = require('express');
var router = express.Router();
const https = require('https');
const http = require('http');


/* GET home page. */
router.get('/', function (req, res, next) {
    http.get('http://localhost:8080/produits', (response) => {
        res.render('index', {produits: response});
    });
    res.json({produits: produit});
});

router.get('/add/', function (req, res, next) {
    res.send('id: ' + req.query.id);
    http.get('http://localhost:8080/produits/' + req.query.id, (response) => {
        http.post('http://localhost:3001/add', {produit: response}, (response)=>{
            res.json({response});
        });
    });
});

module.exports = router;
