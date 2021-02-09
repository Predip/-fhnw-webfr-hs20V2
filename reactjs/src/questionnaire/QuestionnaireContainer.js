import React, { useState } from 'react';
import _ from "lodash";
import QuestionnaireCreateDialog from './QuestionnaireCreateDialog';
import QuestionnaireTable from './QuestionnaireTable';

const QuestionnaireContainer = ({ questions }) => {
    let [qs, setQuestionnaires] = useState(questions);
    const id = (qs) => _.get(_.maxBy(qs, "id"), "id", 0) + 1;
    const create = (questionnaire) => setQuestionnaires(_.concat(qs, { id: id(qs), ...questionnaire }));
    const update = (questionnaire) => setQuestionnaires(_.map(qs, (q) => (q.id === questionnaire.id ? questionnaire : q)));
    const remove = (id) => setQuestionnaires(_.reject(qs, { id: id }));

    return (
        <div>
            <QuestionnaireCreateDialog create={create} />
            <h3>Frageb&ouml;gen</h3>
            <QuestionnaireTable questionnaires={qs} update={update} remove={remove} />
        </div>
    );
}

export default QuestionnaireContainer;