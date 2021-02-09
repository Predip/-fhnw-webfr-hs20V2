import React, { useState } from 'react';
import { Button, Modal, ModalHeader, ModalBody, Form, FormGroup, Label, Col, Input } from 'reactstrap';

const QuestionnaireShowDialog = ({questionnaire}) => {
    let [showModal, setShowModal] = useState(false);
    const close = () => setShowModal(false);
    const open = () => setShowModal(true);

    return (
        <div>
            <Button color="secondary" onClick={open}>Show</Button>
            <Modal isOpen={showModal} toggle={close} size="lg" autoFocus={false}>
                <ModalHeader toggle={close}>Show Questionnaire</ModalHeader>
                <ModalBody>
                    <Form>
                        <FormGroup row>
                            <Label md={2} for="formTitle"> Title </Label>
                            <Col md={10}>
                                <Input type="text" id="formTitle" defaultValue={questionnaire.title} plaintext />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label md={2} for="formDescription"> Description </Label>
                            <Col md={10}>
                                <Input type="text" id="formDescription" defaultValue={questionnaire.description} plaintext />
                            </Col>
                        </FormGroup>
                        <FormGroup>
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