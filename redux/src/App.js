import React from 'react'
import { useSelector } from 'react-redux'
import _ from 'lodash'
import './css/style.css'

import Filter from './Filter'
import Movie from './Movie'

/**
 * Filtert die Liste abhängig vom Term. Der Term wird in allen Properties
 * des Movie-Objektes gesucht.
 *
 * @param {array} movies Die Liste der Filme
 * @param {string} term Der String nachdem gefiltert werden soll
 * @return {array} Die gefilterte Liste der Filme
 */
const filter = (movies, term) => {
    let filterTerm = '^(?=.*' + _.trim(term).split(/\s+/).join(')(?=.*') + ').*$'
    let pattern = RegExp(filterTerm, 'i')

    return _.filter(movies, movie => 
            pattern.test(_.join([movie.year, movie.director, movie.title], ' ')))
}

/**
 * Die Root Komponente App.
 *
 * @return {jsx} Das zu rendernde Element
 */
const App = () => {
    const movies = useSelector(state => state.movies, _.isEqual)
    const filterTerm = useSelector(state => state.filterTerm, _.isEqual)

    return <main>
        <Filter filterTerm={ filterTerm } />
        { _.map(filter(movies, filterTerm), movie => <Movie key={ movie.rank } data={ movie } />) }
    </main>
}

export default App
