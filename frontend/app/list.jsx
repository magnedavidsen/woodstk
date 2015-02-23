var React = require('react');

var ArtistList = React.createClass({
    render: function () {
        var artists = this.props.artists.map(function (artist, i) {
            return (<div><img src={artist.imgUrl} />{artist.name}</div>);
        });

        return (<div>{artists}</div>);
    }
});

module.exports = React.createClass({
  render: function() {
    return (
      <div className="gender">
        <ArtistList artists={this.props.artists} />
      </div>
    );
  }});
