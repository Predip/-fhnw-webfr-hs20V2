import React, { useState } from 'react';
import { Button, Modal, ModalHeader, ModalBody, Form, FormGroup, Label, Col, Input } from 'reactstrap';

const QuestionnaireShowDialog = props => {
    let [showModal, setShowModal] = useState(false);
    let [qs, setQuestionnaire] = useState({ title: '', description: '' })

    const change = event => setQuestionnaire({ ...qs, [event.target.name]: event.target.value });
    const save = () => { props.create(qs); close(); }
    const close = () => { setShowModal(false); setQuestionnaire({ title: '', description: '' }); }
    const open = () => setShowModal(true);

    return (
        <div>
            <Button color="success" onClick={open} className="float-right">Add Questionnaire</Button>
            <Modal isOpen={showModal} toggle={close} size="lg" autoFocus={false}>
                <ModalHeader toggle={close}>Create Questionnaire</ModalHeader>
                <ModalBody>
                    <Form>
                        <FormGroup row>
                            <Label md={2} for="formTitle"> Title </Label>
                            <Col md={10}>
                                <Input onChange={change} type="text" id="formTitle" name="title" autoFocus value={qs.title} />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label md={2} for="formDescription"> Description </Label>
                            <Col md={10}>
                                <Input onChange={change} type="text" id="formDescription" name="description" value={qs.description} />
                            </Col>
                        </FormGroup>
                        <FormGroup>
                            <Col className="clearfix" style={{ padding: '.2rem' }}>
                                <Button className="float-right" color="primary" onClick={save}>Save</Button>
                            </Col>
                            <Col className="clearfix" style={{ padding: '.2rem' }}>
                                <Button className="float-right" color="secondary" onClick={close}>Close</Button>
                            </Col>
                        </FormGroup>
                    </Form>
                </ModalBody>
            </Modal>
        </div>
    );
}

export default QuestionnaireShowDialog;