var React = require('react');
var Swing = require('swing');

var statistics = require('./js/statistics.js');
var rest = require('./js/rest.js');

var _ = require('underscore');

var ArtistList = React.createClass({
    stackConfig: {
        throwOutConfidence: function (offset, element) {
            return Math.min(Math.abs(offset) / (element.offsetWidth / 2), 1);
        }
    },

    stack: {},

    getInitialState: function() {
        return {swipes: 0, artists: this.props.initialArtists, cards: []};
    },

    render: function () {
        var swipes = this.state.swipes;

        return (<ul className="stack">
            <li className="clubs"><img src={this.state.artists[swipes + 1].imgUrl} width="100%" />{this.state.artists[swipes + 1].name}</li>
            <li className="clubs"><img src={this.state.artists[swipes].imgUrl} width="100%" />{this.state.artists[swipes].name}</li>
        </ul>);
    },
    componentDidMount: function () {
        var aModule = this;
        this.stack = Swing.Stack(this.stackConfig);

        console.log("comp did mount");

        // Create cards of all ul li elements
        [].slice.call(document.querySelectorAll('ul li')).forEach(function (targetElement) {
            // Add card element to the Stack.
            aModule.stack.createCard(targetElement);
        });

        // Add event listener for when a card is thrown out of the stack.
        this.stack.on('throwout', function (e) {
            // e.target Reference to the element that has been thrown out of the stack.
            // e.throwDirection Direction in which the element has been thrown (Card.DIRECTION_LEFT, Card.DIRECTION_RIGHT).

            var card = aModule.stack.getCard(e.target);
            card.throwIn(0, 0);
 
            aModule.setState({swipes: aModule.state.swipes + 1});

            console.log('Card has been thrown out of the stack.');
            console.log(aModule.state.swipes + ' cards have been thrown out of the stack.');
            console.log('Throw direction: ' + (e.throwDirection));
        });

// Add event listener for when a card is thrown in the stack, including the spring back into place effect.
        this.stack.on('throwin', function (e) {
            console.log('Card has snapped back to the stack.');
        });
    }
});

module.exports = React.createClass({
  render: function() {
    return (
      <div id="viewport">
        <ArtistList initialArtists={this.props.initialArtists} />
      </div>
    );
  }});
