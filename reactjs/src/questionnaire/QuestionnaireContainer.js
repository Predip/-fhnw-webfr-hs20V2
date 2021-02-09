import React, {useEffect, useState} from 'react';
import _ from "lodash";
import QuestionnaireCreateDialog from './QuestionnaireCreateDialog';
import QuestionnaireTable from './QuestionnaireTable';

const QuestionnaireContainer = ({ serverUrl, questions }) => {
    let [qs, setQuestionnaires] = useState(questions);

    useEffect(() => {
        setQuestionnaires(questions);
    }, [questions]);

    const requestFunc = async (urlAdd, CRUD, bodyContent) => {
        let request;
        if(CRUD === 'DELETE') request = new Request(serverUrl + urlAdd, { method: CRUD })
        else {
            request = new Request(serverUrl + urlAdd, {
                method: CRUD,
                headers: new Headers({ 'Content-Type': 'application/json; charset=utf-8' }),
                body: JSON.stringify(bodyContent)
            })
        }
        try { return await fetch(request); }
        catch (error) { console.error(error); }
    }

    const create = async questionnaire => {
        const response = await requestFunc("", 'POST', questionnaire);
        if (!response.ok) console.log('Status Code: ' + response.status);
        else {
            const q = await response.json();
            setQuestionnaires(_.concat(qs, q));
        }
    }

    const update = async questionnaire => {
        const response = await requestFunc('/' + questionnaire.id, 'PUT', questionnaire);
        if (!response.ok) console.log('Status Code: ' + response.status);
        else setQuestionnaires(_.map(qs, q => q.id === questionnaire.id ? questionnaire : q));
    }

    const remove = async id => {
        const response = await requestFunc('/' + id, 'DELETE', "");
        if (!response.ok) console.log('Status Code: ' + response.status);
        else setQuestionnaires(_.reject(qs, { id: id }));
    }

    return (
        <div>
            <QuestionnaireCreateDialog create={create} />
            <h3>Frageb&ouml;gen</h3>
            <QuestionnaireTable questionnaires={qs} update={update} remove={remove} />
        </div>
    );
}

export default QuestionnaireContainer;