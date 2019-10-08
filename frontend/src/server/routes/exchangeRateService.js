const express = require('express');
const router  = express.Router();
const axios = require('axios');

router.get('/exhangerate', async (req, res) =>     {
    return await  axios.get('http://data.fixer.io/api/latest?access_key=709153594283e678aef879c95817de7b&format=1&symbols=USD,NOK,EUR')
        .then(response => {
            res.status(200).send(response.data);
        }).catch(error => {
            res.status(500).send(error)
        })
});



module.exports = router;