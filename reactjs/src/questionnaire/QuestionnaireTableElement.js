import React from 'react';
import { Button } from 'reactstrap';
import _ from "lodash";
import QuestionnaireShowDialog from './QuestionnaireShowDialog';
import QuestionnaireUpdateDialog from './QuestionnaireUpdateDialog';

const textStyle = {
    verticalAlign: 'middle'
};

const QuestionnaireTableElement = ({questionnaire, update, remove}) => (
    <tr>
        <td colSpan="1" style={textStyle}>{questionnaire.id}</td>
        <td colSpan="3" style={textStyle}>{questionnaire.title}</td>
        <td colSpan="10" style={textStyle}>{questionnaire.description}</td>
        <td style={textStyle}>
            <div className="btn-group" role="group">
                <Button color="danger" onClick={_.partial(remove, questionnaire.id)}>Delete</Button>
                <QuestionnaireShowDialog questionnaire={questionnaire} />
                <QuestionnaireUpdateDialog questionnaire={questionnaire} update={update} />
            </div>
        </td>
    </tr>
)

export default QuestionnaireTableElement;