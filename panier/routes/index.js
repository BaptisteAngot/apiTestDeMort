var express = require('express');
var router = express.Router();
var request = require('request');
var http = require('http');


router.post('/add', function(req, res, next) {
  let produit = req.body;
  console.log(req);
  console.log(produit);
  if (produit.stock > 0) {
      request.post("http://gateway:8080/panier/add/20/182/"+produit.id, produit , () => {
        res.json({status: "OK", body: produit})
      });
  }else{
    res.json({status: "BAD"});
  }
});

router.post('/validate/:id', function(req, res, next) {
  request.post("http://gateway:8080/panier/validate/"+req.params.id, {} , (response) => {
    res.json({id: req.params.id, status: true});
  });
});

module.exports = router;
