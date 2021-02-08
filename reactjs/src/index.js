import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import App from './app/App';


const defaultQuestion = [
    {'id': 1, 'title': 'Test Title 1', 'description': 'Test Description 1'},
    {'id': 2, 'title': 'Test Title 2', 'description': 'Test Description 2'},
    {'id': 3, 'title': 'Test Title 3', 'description': 'Test Description 3'},
    {'id': 4, 'title': 'Test Title 4', 'description': 'Test Description 4'},
    {'id': 5, 'title': 'Test Title 5', 'description': 'Test Description 5'}
]

ReactDOM.render(
    <App qs= {defaultQuestion} />,
    document.getElementById('root')
);