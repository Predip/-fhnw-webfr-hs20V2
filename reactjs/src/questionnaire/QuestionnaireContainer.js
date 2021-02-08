import React from 'react';
import QuestionnaireTable from './QuestionnaireTable';

const QuestionnaireContainer = ({ questions }) => (
    <div>
        <button className="btn btn-success float-right">Add Questionnaire</button>
        <h3>Frageb&ouml;gen</h3>
        <QuestionnaireTable questionnaires = {questions} />
    </div>
)

export default QuestionnaireContainer;