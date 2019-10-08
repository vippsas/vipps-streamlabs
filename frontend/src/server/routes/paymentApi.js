const express = require('express');
const router  = express.Router();
const axios = require('axios');

SERVER_URL = "https://vast-dawn-85228.herokuapp.com/";

router.post('/donations', (req, res) =>     {
    fetchDeepLink(
        req.body.senderName,
        req.body.amountInNOK,
        req.body.amountInUSD,
        req.body.transactionText
    ).then( response => {
        res.status(200).send(response.data);
    })
        .catch(error => {
            res.status(500).send(error)
        })
});

router.get('/donations/:orderId', (req, res) => {
    fetchOrderStatus(req.params.orderId)
        .then(response => {
            res.status(200).send(response.data);
        })
        .catch(error => {
            res.status(500).send(error)
        })
});

fetchOrderStatus = async (orderId) =>  {
    return await axios.get(SERVER_URL + '/getOrderStatus' + '/' + orderId)
        .catch(error =>
        console.log(error))
};


fetchDeepLink = async (donationFrom, donationAmountInNOK, donationAmountInUSD, donationMessage) => {
   return await  axios.post(SERVER_URL + '/initiatePayment',
       {
           senderName: donationFrom,
           amountInNOK: parseInt(donationAmountInNOK),
           amountInUSD: parseInt(donationAmountInUSD),
           transactionText: donationMessage
       })
       .catch(error =>
        console.log(error))
};



module.exports = router;