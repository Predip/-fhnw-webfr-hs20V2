import React from 'react';
import { Jumbotron } from 'reactstrap';

const Header = ({title, subtitle}) => (
    <header>
        <Jumbotron>
            <h1>{title}</h1>
            <h3>{subtitle}</h3>
        </Jumbotron>
    </header>
)

export default Header;