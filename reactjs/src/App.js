import React, { Component } from "react";
import { Button } from 'reactstrap';

class App extends Component {
  constructor(props) {
    super(props);
    this.names = [
      "Balin",
      "Dwalin",
      "Fili",
      "Kili",
      "Dori",
      "Nori",
      "Ori",
      "Oin",
      "Gloin",
      "Bifur",
      "Bofur",
      "Bombur",
    ];
    this.state = {
        index: 0,
    };
    setInterval(this.tick, 1000);
  }
  componentDidMount() {
    this.timer = setInterval(this.tick, 1000);
  }
  componentWillUnmount() {
    clearInterval(this.timer);
  }
  tick = () => {
    const newIndex = (this.state.index + 1) % this.names.length;
    this.setState({ index: newIndex });
  }
  reset = () => {
    this.setState({ index: 0 });
  }
  render() {
    return (
        <div>
          <div className="jumbotron">
            <h1>{this.props.message} {this.names[this.state.index]}</h1>
          </div>

          <Button color="secondary" onClick={this.reset}>REFRESH</Button>
        </div>
    );
  }
}
export default App;
