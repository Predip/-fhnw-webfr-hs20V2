import React from 'react'
import _ from 'lodash'

/**
 * Event-Handler. Ruft den Callback auf.
 * 
 * @param {function} updateFilterTerm Die Update Funktion
 * @param {object} event Der onChange Event
 */
const _onChange = (updateFilterTerm, event) => {
    event.preventDefault()
    updateFilterTerm(event.target.value) // Schickt den Filter-Term via props.filterFn nach 'oben'.
}

/**
 * Callback für das Update des Zustandes im Parent.
 * 
 * @param {function} updateFilterTerm Die Filter-Term-Update-Funktion
 * @return {jsx} Das zu rendernde Element
 */
const Filter = ({ updateFilterTerm, term }) => 
    <form>
        <input type='text' placeholder='Liste Filtern mit...' value={ term } onChange={ _.partial(_onChange, updateFilterTerm) } />
    </form>

export default Filter