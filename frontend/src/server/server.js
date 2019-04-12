const express = require('express');
const app = express();
const path = require('path');
app.use(express.static('public'));

app.use((req, res, next) => {
    res.sendFile(path.resolve(__dirname, '..', '..', 'public', 'index.html'));
});

const port = process.env.PORT || 8080;

app.listen(port, () => {
    console.log('server started on port:' + port);
});

