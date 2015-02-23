var React = require('react');

var Header = require('./header.jsx');
var List = require('./list.jsx');

var statistics = require('./js/statistics.js');
var rest = require('./js/rest.js');

rest.getArtists(function (artists) {
  statistics.artists = artists;

  React.render(
    <div className="u-col4">
      <Header title='by:larm' />
      <List artists={statistics.artists} />
    </div>,
    document.getElementById('page'));
  });
