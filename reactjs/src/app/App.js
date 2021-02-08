import React, { Component } from "react";
import { Container } from 'reactstrap';
import Header from './Header';
import Footer from './Footer';
import QuestionnaireContainer from '../questionnaire/QuestionnaireContainer';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            questionnaires: this.props.qs,
        };
    }
    render () {
        const rightMessageTxt = "Total of " + this.state.questionnaires.length + " questionnaires";
        return (
            <Container fluid>
                <Header title = "Flashcard Client with React" subtitle = "Version 1" />
                <QuestionnaireContainer questions = {this.state.questionnaires}/>
                <Footer leftMessage = "The FHNW Team" rightMessage = {rightMessageTxt} />
            </Container>
        );
    }
}

export default App;