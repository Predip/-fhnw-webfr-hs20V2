"use strict";

const dispatcher = require('express').Router();

const index_controller = require('./index-controller')
const questionnaire_controller = require('./questionnaire-controller')

// dispatcher.route('/').get(index_controller.index)
// shortcut to add a route
dispatcher.get('/', index_controller.index)
dispatcher.get('/questionnaires', questionnaire_controller.findAll)
dispatcher.get('/questionnaires/:id', questionnaire_controller.findById)
dispatcher.post('/questionnaires', questionnaire_controller.create)
dispatcher.put('/questionnaires/:id', questionnaire_controller.update)
dispatcher.delete('/questionnaires/:id', questionnaire_controller.delete)

module.exports = dispatcher;