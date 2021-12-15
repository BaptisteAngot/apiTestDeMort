var express = require('express');
var router = express.Router();
const https = require('https');
const http = require('http');
var request = require('request');


/* GET home page. */
router.get('/', function (req, res, next) {
    http.get('http://gateway:8080/produits', (response) => {
        response.on('data', function (chunk) {
            res.setHeader('Content-Type', 'application/json');
            res.end(chunk);
        });
    });
});

router.get('/add/:id', function (req, res, next) {
    http.get('http://gateway:8080/produits/' + req.params.id, (produit) => {
        produit.on('data', function (chunk) {
            req.raw_body = chunk;                 // save collected data in req.body
        });
        request.post('http://panier:3001/add', req.raw_body, (response) => {
            console.log(response);
            res.json('ok');
        });
    });
});

module.exports = router;
