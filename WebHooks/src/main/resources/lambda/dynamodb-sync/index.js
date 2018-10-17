'use strict';

var axios = require('axios');
var endpoint = '';

exports.handler = (event, context, callback) => {
    event.Records.forEach((record) => {
        var params = {
            'tableName': record.eventSourceARN.split(':')[5].split('/')[1],
            'keys': record.dynamodb.Keys
        };
        var config = {
            'auth' : {
                'username': '',
                'password': ''
            }
        };
        axios.post(endpoint, params, config)
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    });
    callback(null, `Successfully processed ${event.Records.length} records.`);
};