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
    http.get('http://gateway:8080/produits/' + req.params.id,
        function(resr) {
            console.log('STATUS: ' + resr.statusCode);
            console.log('HEADERS: ' + JSON.stringify(resr.headers));

            // Buffer the body entirely for processing as a whole.
            var bodyChunks = '';
            resr.on('data', function(chunk) {
                // You can process streamed parts here...
                bodyChunks += chunk;
            }).on('end', function() {
                console.log('BODY: ' + bodyChunks);
                let json = JSON.parse(bodyChunks);
                // ...and/or process the entire body here.
                console.log(json);
                request.post('http://panier:3000/add', json , (response) => {
                    console.log(response);
                    res.json(json);
                });
            })
        });
});

module.exports = router;
