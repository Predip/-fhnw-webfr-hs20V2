import React, {useEffect, useState} from "react";
import { Container } from 'reactstrap';
import Header from './Header';
import Footer from './Footer';
import QuestionnaireContainer from '../questionnaire/QuestionnaireContainer';
import Loader from '../misc/Loader';
import Message from '../misc/Message';

const App = () => {
    const defaultServerUrl = "http://localhost:8080/flashcard-rest/questionnaires";
    let [qs, setQuestionnaires] = useState([]);
    const [serverUrl, setServerUrl] = useState(null);
    const [isError, setIsError] = useState(false);

    useEffect(() => {
        fetch("application.json")
            .then((response) => response.json())
            .then((js) => {
                const SERVER_URL = js.url ? js.url : defaultServerUrl;
                console.log("serverUrl is %s", SERVER_URL);
                setServerUrl(SERVER_URL + "/questionnaires");
            })
            .catch((error) => {
                console.error("App Error: " + error);
                setIsError(true);
            });
    }, []);

    useEffect(() => {
        const readAll = async () => {
            const response = await fetch(serverUrl);
            const qsRes = await response.json();
            setQuestionnaires(qsRes);
        };
        readAll().catch((error) => console.error(error));
    }, [serverUrl]);

    let comp;
    if (isError) comp = <Message message="Network error" />;
    else if (serverUrl === null) comp = <Loader />;
    else comp = <QuestionnaireContainer serverUrl={ serverUrl } questions={qs}/>;

    const rightMessageTxt = "Initial total of " + qs.length + " questionnaires";
    return (
        <Container fluid>
            <Header title="Flashcard Client with React" subtitle="Version 1" />
            {comp}
            <Footer leftMessage="The FHNW Team" rightMessage={rightMessageTxt} />
        </Container>
    );
}

export default App;