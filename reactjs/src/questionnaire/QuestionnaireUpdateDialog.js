import React, { useState } from 'react';
import { Button, Modal, ModalHeader, ModalBody, Form, FormGroup, Label, Col, Input } from 'reactstrap';

const QuestionnaireUpdateDialog = ({ questionnaire: oldQuestionnaire, update }) => {
    let [showModal, setShowModal] = useState(false);
    let [qs, setQuestionnaire] = useState(oldQuestionnaire)

    const change = event => setQuestionnaire({ ...qs, [event.target.name]: event.target.value });
    const save = () => { setShowModal(false); update({ ...qs, id: oldQuestionnaire.id }); }
    const close = () => { setShowModal(false); setQuestionnaire({ title: '', description: '' }); }
    const open = () => setShowModal(true);

    return (
        <div>
            <Button color="primary" onClick={open}>Edit</Button>
            <Modal isOpen={showModal} toggle={close} size="lg" autoFocus={false}>
                <ModalHeader toggle={close}>Edit Questionnaire</ModalHeader>
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

export default QuestionnaireUpdateDialog;