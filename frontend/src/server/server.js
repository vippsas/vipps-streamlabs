const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const path = require('path');
const paymentApi = require('./routes/paymentApi');
const exchangeRateService = require('./routes/exchangeRateService');

app.use(bodyParser.json());

app.use(express.static('public'));

app.use('/api', paymentApi);
app.use('/api', exchangeRateService);


app.use((req, res, next) => {
    res.sendFile(path.resolve(__dirname, '..', '..', 'public', 'index.html'));
});

const port = process.env.PORT || 8081;

app.listen(port, () => {
    console.log('server started on port:' + port);
});

