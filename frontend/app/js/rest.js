var request = require('superagent');

function getArtists(callback) {
  request.get('api/artists').end(function(res) {
    callback(res.body);
  });
};

function vote(id, points) {
  request
    .post('/api/vote')
    .send({ artistId: id, points: points, session: "", ip: ""})
    .set('Accept', 'application/json')
    .end(function(res){
      if (res.ok) {
        console.log("Voted!")
      } else {
        console('Oh no! Error while voting ' + res.text);
      }
    });
}

exports.getArtists = getArtists;
exports.vote = vote;