import React from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'

import { NavBar } from '../components'
import { memberList, kibanaPage, chartPage } from '../pages'

import 'bootstrap/dist/css/bootstrap.min.css'


function App() {
    return (
        <Router>
            <NavBar />
            <Switch>
                <Route path="/admin/selectList" exact component={memberList} />
                <Route path="/admin/kibanaPage" exact component={kibanaPage} />
                <Route path="/admin/chartPage" exact component={chartPage} />
            </Switch>
        </Router>
    )
}
export default App