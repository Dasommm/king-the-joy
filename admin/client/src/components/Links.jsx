import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import styled from 'styled-components'

const Collapse = styled.div.attrs({
    className: 'collpase navbar-collapse',
})``

const List = styled.div.attrs({
    className: 'navbar-nav mr-auto',
})``

const Item = styled.div.attrs({
    className: 'collpase navbar-collapse',
})``

class Links extends Component {
    render() {
        return (
            <React.Fragment>
                <Link to="/" className="navbar-brand">
                    관리자 페이지
                </Link>
                <Collapse>
                    <List>
                        <Item>
                            <Link to="/admin/selectList" className="nav-link">
                                회원 목록
                            </Link>
                        </Item>
                        <Item>
                            <Link to="/admin/kibanaPage" className="nav-link">
                                키바나
                            </Link>
                        </Item>
                        <Item>
                            <Link to="/admin/chartPage" className="nav-link">
                                차트
                            </Link>
                        </Item>
                    </List>
                </Collapse>
            </React.Fragment>
        )
    }
}

export default Links
