import React from 'react'

/**
 * Diese Komponente stellt einen Film dar.
 * 
 * @param {object} data Die Daten des Films
 * @return {jsx} Das zu rendernde Element
 */
const Movie = ({ data }) => <section>
    <span>{ data.rank }.</span>
    <span>{ data.year }</span>
    <span>{ data.title }</span>
    <span>{ data.director }</span>
</section>

export default Movie