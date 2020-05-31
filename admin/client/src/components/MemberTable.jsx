import React, { Component } from 'react';



export default class MemberTable extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <tr>
                <td>{this.props.data.MEMBER_SEQ}</td>
                <td>{this.props.data.MEMBER_ID}</td>
                <td>{this.props.data.MEMBER_PW}</td>
                <td>{this.props.data.MEMBER_NAME}</td>
                <td>{this.props.data.MEMBER_PHONE}</td>
                <td>{this.props.data.MEMBER_EMAIL}</td>
                <td>{this.props.data.MEMBER_ROLE}</td>
                <td>{this.props.data.MEMBER_DELFLAG}</td>
                <td>{this.props.data.MEMBER_OADDR}</td>
                <td>{this.props.data.MEMBER_ADDR}</td>
                <td>{this.props.data.MEMBER_DETAILADDR}</td>
                <td>{this.props.data.MEMBER_DATE}</td>
            </tr>
        )
    }
}
