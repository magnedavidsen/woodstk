var React = require('react');

var Header = require('./header.jsx');
var List = require('./list.jsx');

var statistics = require('./js/statistics.js');
var rest = require('./js/rest.js');

var _ = require('underscore');

rest.getArtists(function (artists) {
  statistics.artists = artists;

  React.render(

      <List initialArtists={statistics.artists} />
    ,
    document.getElementById('page'));
  });

document.ontouchstart = function(e){
    e.preventDefault();
}
