var request = require('superagent');

function getArtists(callback) {
  request.get('api/artists').end(function(res) {
    callback(res.body);
  });
};

exports.getArtists = getArtists;
