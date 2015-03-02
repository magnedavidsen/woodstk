var React = require('react');

var Header = require('./header.jsx');
var Cards = require('./cards.jsx');

var statistics = require('./js/statistics.js');
var rest = require('./js/rest.js');

var _ = require('underscore');

rest.getArtists(function (artists) {
  statistics.artists = artists;

  React.render(
    <div>
      <Header title="woodstk" />
      <Cards initialArtists={statistics.artists} />
    </div>
    ,
    document.getElementById('page'));
});

document.ontouchstart = function(e){
  e.preventDefault();
}
