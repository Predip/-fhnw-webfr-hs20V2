import React from 'react';

const textStyle = {
    verticalAlign: 'middle'
};

const QuestionnaireTableElement = ({questionnaire}) => (
    <tr>
        <td colSpan="1" style={textStyle}>{questionnaire.id}</td>
        <td colSpan="3" style={textStyle}>{questionnaire.title}</td>
        <td colSpan="10" style={textStyle}>{questionnaire.description}</td>
        <td colSpan="1" style={textStyle}><button className="btn btn-danger">Delete</button></td>
        <td colSpan="1" style={textStyle}><button className="btn btn-primary">Show</button></td>
        <td><button className="btn btn-success">Edit</button></td>
    </tr>
)

export default QuestionnaireTableElement;