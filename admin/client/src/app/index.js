import React from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'

import { NavBar } from '../components'
import { memberList } from '../pages'

import 'bootstrap/dist/css/bootstrap.min.css'


function App() {
    return (
        <Router>
            <NavBar />
            <Switch>
                <Route path="/admin/selectList" exact component={memberList} />
            </Switch>
        </Router>
    )
}
export default App