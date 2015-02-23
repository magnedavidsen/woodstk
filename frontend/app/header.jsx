var React = require('react');

module.exports = React.createClass({
  render: function() {
    return (
      <header className="t-sectionHeader">
        <span>{this.props.title}</span>
      </header>
    );
  }});
