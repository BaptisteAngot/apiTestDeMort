var express = require('express');
var router = express.Router();
var request = require('request');
var https = require('https');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.post('/add', function(req, res, next) {
  let produit = req.body;
  if (produit.stock > 0) {
      request.post("https://localhost:8080/panier/add/20/126", produit , () => {
        res.json({status: "OK", body: produit})
      });
  }else{
    res.json({status: "BAD"});
  }
});

router.post('/validate', function(req, res, next) {
  https.get("https://localhost:8080/panier/"+req.query.id ,(response) => {
    let totalPrice = response.map(item => item.price);
    totalPrice.reduce((previousValue, currentValue) => previousValue + currentValue);
    request.post("https://localhost:8080/panier/add/20/126", {payment: true, body: response} , () => {
      res.json({payment: true, body: response, montant: totalPrice.reduce((previousValue, currentValue) => previousValue + currentValue)});
    });
  });



});

module.exports = router;
