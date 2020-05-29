import React, { Component } from 'react'
import api from '../api'
import MemberTable from '../components/MemberTable'
import Table from 'react-bootstrap/Table'


class memberList extends Component {
    constructor(props) {
        super(props)
        this.state = {
            member: [],
        }
    }
    async memberList() {
        const response = await api.adminSelect();
        this.setState({
            member: response.data,
        })
    }

    componentDidMount() {
        this.memberList();
    }

    DataTable() {
        const member = this.state.member
        console.log(member)
        return member.map((member) => {
            return <MemberTable data={member} />
        });
    }



    render() {
        return (
            <div className="table-wrapper">
                <Table striped bordered hocer>
                    <thead>
                        <tr>
                            <th>회원번호</th>
                            <th>아이디</th>
                            <th>비밀번호</th>
                            <th>이름</th>
                            <th>핸드폰</th>
                            <th>이메일</th>
                            <th>회원등급</th>
                            <th>탈퇴유무</th>
                            <th>우편번호</th>
                            <th>주소</th>
                            <th>상세주소</th>
                            <th>가입일</th>
                            <th>회원탈퇴유무</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.DataTable()}
                    </tbody>
                </Table>
            </div>

        )
    }
}


export default memberList