var React = require('react');

module.exports = React.createClass({
  render: function() {
    return (<div className="button-container" >
      <div className="round-button" ><div className="round-button-circle" onClick={this.props.addSwipe}><span className="round-button">?</span></div></div></div>
    );
  }});

