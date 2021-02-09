import React from "react";
import { Container } from 'reactstrap';
import Header from './Header';
import Footer from './Footer';
import QuestionnaireContainer from '../questionnaire/QuestionnaireContainer';

const App = props => {
    const rightMessageTxt = "Total of " + props.qs.length + " questionnaires";
    return (
        <Container fluid>
            <Header title = "Flashcard Client with React" subtitle = "Version 1" />
            <QuestionnaireContainer questions = {props.qs}/>
            <Footer leftMessage = "The FHNW Team" rightMessage = {rightMessageTxt} />
        </Container>
    );
}

export default App;