import React from 'react';
import { Row, Col } from 'reactstrap';

const Footer = ({leftMessage, rightMessage}) => (
    <footer>
        <Row>
            <Col>&copy; { leftMessage }</Col>
            <Col className='text-right'>{ rightMessage }</Col>
        </Row>
    </footer>
)

export default Footer;