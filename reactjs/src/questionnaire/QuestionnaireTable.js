import React from 'react';
import { Table } from 'reactstrap';
import _ from "lodash";
import QuestionnaireTableElement from './QuestionnaireTableElement';

const textStyle = {
    verticalAlign: 'middle'
};

const QuestionnaireTable = ({questionnaires, update, remove}) => (
    <Table className="table table-condensed table-hover">
        <thead>
            <tr>
                <th colSpan="1" style={textStyle}>ID</th>
                <th colSpan="3" style={textStyle}>Title</th>
                <th colSpan="10" style={textStyle}>Beschreibung</th>
                <th colSpan="1" style={textStyle}>Aktionen</th>
            </tr>
        </thead>
        <tbody>
            { _.map(questionnaires, questionnaire =>
                <QuestionnaireTableElement
                    key={questionnaire.id}
                    questionnaire={questionnaire}
                    update={ update }
                    remove={ remove } />)
            }
        </tbody>
    </Table>
)

export default QuestionnaireTable;