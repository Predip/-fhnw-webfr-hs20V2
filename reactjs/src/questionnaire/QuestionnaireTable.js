import React from 'react';
import { Table } from 'reactstrap';
import QuestionnaireTableElement from './QuestionnaireTableElement';

const textStyle = {
    verticalAlign: 'middle'
};

const QuestionnaireTable = ({questionnaires}) => (
    <Table class="table table-condensed table-hover">
        <thead>
            <tr>
                <th colSpan="1" style={textStyle}>ID</th>
                <th colSpan="3" style={textStyle}>Title</th>
                <th colSpan="10" style={textStyle}>Beschreibung</th>
                <th colSpan="1" style={textStyle}>Delete</th>
                <th colSpan="1" style={textStyle}>Show</th>
                <th colSpan="1" style={textStyle}>Update</th>
            </tr>
        </thead>
        <tbody>
            { questionnaires.map((questionnaire) =>
                    <QuestionnaireTableElement key={questionnaire.id} questionnaire={questionnaire} />)
            }
        </tbody>
    </Table>
)

export default QuestionnaireTable;