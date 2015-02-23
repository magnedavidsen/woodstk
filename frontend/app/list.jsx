var React = require('react');
var Swing = require('swing');

var ArtistList = React.createClass({
    render: function () {
        var artists = this.props.artists.map(function (artist, i) {

            return (<li className="clubs"><div style={{backgroundImage: 'url(' + artist.imgUrl + ')'}} width="100%" />
            {artist.name}</li>);
        });

        return (<ul className="stack">{artists}</ul>);
    },
    componentDidMount: function () {
        var stack, cards, config;

        config = {
            /**
             * Invoked in the event of dragmove.
             * Returns a value between 0 and 1 indicating the completeness of the throw out condition.
             * Ration of the absolute distance from the original card position and element width.
             *
             * @param {Number} offset Distance from the dragStart.
             * @param {HTMLElement} element Element.
             * @return {Number}
             */
            throwOutConfidence: function (offset, element) {
                return Math.min(Math.abs(offset) / (element.offsetWidth / 2), 1);
            }
        };


        // Prepare the cards in the stack for iteration.
        cards = [].slice.call(document.querySelectorAll('ul li'))

        // An instance of the Stack is used to attach event listeners.
        stack = Swing.Stack(config);

        cards.forEach(function (targetElement) {
            // Add card element to the Stack.
            stack.createCard(targetElement);
        });

        // Add event listener for when a card is thrown out of the stack.
        stack.on('throwout', function (e) {
            // e.target Reference to the element that has been thrown out of the stack.
            // e.throwDirection Direction in which the element has been thrown (Card.DIRECTION_LEFT, Card.DIRECTION_RIGHT).

            console.log('Card has been thrown out of the stack.');
            console.log('Throw direction: ' + (e.throwDirection));
        });

// Add event listener for when a card is thrown in the stack, including the spring back into place effect.
        stack.on('throwin', function (e) {
            console.log('Card has snapped back to the stack.');
        });
    }
});

module.exports = React.createClass({
  render: function() {
    return (
      <div id="viewport">
        <ArtistList artists={this.props.artists} />
      </div>
    );
  }});
